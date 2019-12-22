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
				RoomBean roomBean = entityManager.find(RoomBean.class, bookingInfoBean.getRoomId());
				double roomRent = roomBean.getRoomRent();

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

}// End of Class
