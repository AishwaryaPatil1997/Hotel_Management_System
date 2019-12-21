package com.capgemini.hotelmanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.hotelmanagement.beans.BookingInfoBean;
import com.capgemini.hotelmanagement.beans.RoomBean;
import com.capgemini.hotelmanagement.dao.UserDAO;

@Service
public class UserServicesImpl implements UserServices {
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public boolean bookHotelRoom(BookingInfoBean bookingInfoBean) {
		return userDAO.bookHotelRoom(bookingInfoBean);
	}

	@Override
	public boolean checkRoomStatus(int roomId) {
		return userDAO.checkRoomStatus(roomId);
	}

	@Override
	public List<RoomBean> showAvailableRoom() {
		return userDAO.showAvailableRoom();
	}

	@Override
	public List<BookingInfoBean> showBooking(int userId) {
		return userDAO.showBooking(userId);
	}

	@Override
	public boolean deleteBookedRoom(int bookingId) {
		return userDAO.deleteBookedRoom(bookingId);
	}

	@Override
	public double totalBill(int userId) {
		return userDAO.totalBill(userId);
	}
}//End of Class
