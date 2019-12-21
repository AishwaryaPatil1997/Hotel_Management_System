package com.capgemini.hotelmanagement.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.hotelmanagement.beans.BookingInfoBean;
import com.capgemini.hotelmanagement.beans.RoomBean;

@Repository
public class UserDAOImpl implements UserDAO {

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
				bookingInfoBean.setAmount(roomBean.getRoomRent());
				
				entityManager.persist(bookingInfoBean);
				entityTransaction.commit();
				isBooked = true;
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
			String jpql = "FROM RoomBean WHERE roomId=:roomId";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("roomId", roomId);
			RoomBean roomBean = (RoomBean) query.getSingleResult();
			if (roomBean.getRoomStatus().equals("Available")) {
				isAvailable = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isAvailable;
	}// End of checkRoomStatus()

	@Override
	public List<RoomBean> showAvailableRoom() {
		entityManager = entityManagerFactory.createEntityManager();
		List<RoomBean> roomList = null;
		try {
			String roomStatus = "Available";
			String jpql = "FROM RoomBean WHERE roomStatus =: roomStatus";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("roomStatus", roomStatus);
			roomList = query.getResultList();
			entityManager.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roomList;
	}//End of showAvailableRoom()

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
	}//End of showBooking()

	@Override
	public boolean deleteBookedRoom(int bookingId) {
		entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		boolean isDeleted = false;
		try {
			BookingInfoBean bookingInfoBean = entityManager.find(BookingInfoBean.class, bookingId);
			entityTransaction.begin();
			entityManager.remove(bookingInfoBean);
			entityTransaction.commit();
			isDeleted = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDeleted;
	}//End of deleteBookedRoom()

	@Override
	public double totalBill(int userId) {
		entityManager = entityManagerFactory.createEntityManager();
		double totalBill = 0.0;
		try {
			String jpql = "SELECT sum(price) FROM BookingInfoBean WHERE userId= :userId";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("userId", userId);
			totalBill = (double) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalBill;
	}//End of totalBill()

}// End of Class
