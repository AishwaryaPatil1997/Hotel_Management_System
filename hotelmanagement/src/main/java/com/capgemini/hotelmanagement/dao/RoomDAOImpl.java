package com.capgemini.hotelmanagement.dao;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.capgemini.hotelmanagement.beans.BookingInfoBean;
import com.capgemini.hotelmanagement.beans.HistoryBean;
import com.capgemini.hotelmanagement.beans.HotelBean;
import com.capgemini.hotelmanagement.beans.RoomBean;

@Repository
public class RoomDAOImpl implements RoomDAO {

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	EntityManager entityManager;

	@Override
	public boolean bookHotelRoom(BookingInfoBean bookingInfoBean) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		boolean isBooked = false;
		try {
			// Checking room is available or not
			boolean isPresent = checkRoomStatus(bookingInfoBean.getRoomId());
			if (isPresent) {
				// HotelBean hotelBean = entityManager.find(HotelBean.class,
				// bookingInfoBean.getHotelId());
				RoomBean roomBean = entityManager.find(RoomBean.class, bookingInfoBean.getRoomId());
				double roomRent = roomBean.getRoomRent();
				bookingInfoBean.setHotelId(roomBean.getHotelId());

				// Calculating days between checkin and checkout days
				int days = calculateDays(bookingInfoBean.getCheckinDate(), bookingInfoBean.getCheckoutDate());

				// Calculating total amount
				double totalAmount = days * roomRent;
				System.out.println(totalAmount);
				bookingInfoBean.setAmount(totalAmount);

				entityManager.persist(bookingInfoBean);
				isBooked = true;
				if (isBooked) {
					roomBean.setRoomStatus("Unavailable");
					if (bookingInfoBean.getPaymentStatus().equals("Done")) {
						HistoryBean historyBean = new HistoryBean();
						int hotelId = roomBean.getHotelId();
						historyBean.setHotelId(hotelId);
						HotelBean hotelBean = entityManager.find(HotelBean.class, hotelId);
						// Getting hotel id and hotel name
						historyBean.setHotelName(hotelBean.getHotelName());
						historyBean.setRoomId(bookingInfoBean.getRoomId());
						historyBean.setUserId(bookingInfoBean.getUserId());
						historyBean.setAmount(totalAmount);
						historyBean.setPaymentStatus(bookingInfoBean.getPaymentStatus());
						historyBean.setModeOfPayment(bookingInfoBean.getModeOfPayment());
						historyBean.setCheckinDate(bookingInfoBean.getCheckinDate());
						historyBean.setCheckoutDate(bookingInfoBean.getCheckoutDate());
						saveHistory(historyBean);
					}
				}
				entityTransaction.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isBooked;
	}// End of bookHotel()

	@Override
	public boolean checkRoomStatus(int roomId) {
		entityManager = entityManagerFactory.createEntityManager();
		boolean isAvailable = false;
		try {
			// Checking room status
			RoomBean roomBean = entityManager.find(RoomBean.class, roomId);

			if (roomBean.getRoomStatus().equals("Available")) {
				isAvailable = true;
			}

		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("Room is Not Available");
		}
		return isAvailable;
	}// End of checkRoomStatus()

	@Override
	public List<RoomBean> showAvailableRoom(String roomType) {
		entityManager = entityManagerFactory.createEntityManager();
		List<RoomBean> roomList = null;
		try {
			String roomStatus = "Available";
			String jpql = "FROM RoomBean WHERE roomStatus =: roomStatus AND roomType =: roomType";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("roomStatus", roomStatus);
			query.setParameter("roomType", roomType);
			roomList = query.getResultList();

			entityManager.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return roomList;
	}// End of showAvailableRoom()

	@Override
	public List<BookingInfoBean> showBooking(int userId) {
		entityManager = entityManagerFactory.createEntityManager();
		List<BookingInfoBean> bookingInfoList = null;
		try {
			String jpql = "FROM BookingInfoBean WHERE userId =: userId";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("userId", userId);
			bookingInfoList = query.getResultList();
			entityManager.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookingInfoList;
	}// End of showBooking()

	@Override
	public boolean cancelBooking(int bookingId) {
		entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		boolean isDeleted = false;
		try {
			BookingInfoBean bookingInfoBean = entityManager.find(BookingInfoBean.class, bookingId);
			RoomBean roomBean = entityManager.find(RoomBean.class, bookingInfoBean.getRoomId());
			entityTransaction.begin();
			roomBean.setRoomStatus("Available");
			entityManager.remove(bookingInfoBean);
			isDeleted = true;
			entityTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDeleted;
	}// End of deleteBookedRoom()

	@Override
	public double totalBill(int userId) {
		entityManager = entityManagerFactory.createEntityManager();
		double totalBill = 0.0;
		try {
			String jpql = "SELECT sum(amount) FROM BookingInfoBean WHERE userId= :userId";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("userId", userId);
			totalBill = (double) query.getSingleResult();
			System.out.println(totalBill);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalBill;
	}// End of totalBill()

	@Override
	public int calculateDays(LocalDate checkinDate, LocalDate checkoutData) {
		int daysBetween = (int) ChronoUnit.DAYS.between(checkinDate, checkoutData);
		return daysBetween;
	}// End of calculateDays()

	@Override
	public boolean saveHistory(HistoryBean historyBean) {
		entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		boolean isAdded = false;
		try {
			entityTransaction.begin();
			entityManager.persist(historyBean);
			entityTransaction.commit();
			isAdded = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isAdded;
	}// End of saveHistory

	@Override
	public List<HistoryBean> showHistory(int userId) {
		entityManager = entityManagerFactory.createEntityManager();
		List<HistoryBean> historyList = null;
		try {
			historyList = (List<HistoryBean>) entityManager.find(HistoryBean.class, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return historyList;
	}// End of showOrder()

	@Override
	public List<RoomBean> showAllRooms() {
		entityManager = entityManagerFactory.createEntityManager();
		List<RoomBean> roomList = null;
		try {
			String jpql = "FROM RoomBean WHERE roomType =: roomType";
			Query query = entityManager.createQuery(jpql);
			roomList = query.getResultList();
			entityManager.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roomList;
	}

	@Override
	public boolean addNewRoom(RoomBean roomBean) {
		boolean isRoomAdded = false;
		entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		try {
			entityTransaction.begin();
			entityManager.persist(roomBean);
			entityTransaction.commit();
			isRoomAdded = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		entityManager.close();
		return isRoomAdded;
	}

}
