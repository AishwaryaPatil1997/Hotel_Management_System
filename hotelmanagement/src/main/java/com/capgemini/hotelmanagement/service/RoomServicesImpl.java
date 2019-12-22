package com.capgemini.hotelmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.hotelmanagement.beans.BookingInfoBean;
import com.capgemini.hotelmanagement.beans.RoomBean;
import com.capgemini.hotelmanagement.dao.RoomDAO;

@Service
public class RoomServicesImpl implements RoomServices {
	
	@Autowired
	private RoomDAO userDAO;
	
	@Override
	public boolean bookHotelRoom(BookingInfoBean bookingInfoBean) {
		return userDAO.bookHotelRoom(bookingInfoBean);
	}

	@Override
	public boolean checkRoomStatus(int roomId) {
		return userDAO.checkRoomStatus(roomId);
	}

	@Override
	public List<RoomBean> showAvailableRoom(String roomType) {
		return userDAO.showAvailableRoom(roomType);
	}

	@Override
	public List<BookingInfoBean> showBooking(int userId) {
		return userDAO.showBooking(userId);
	}

	@Override
	public boolean cancelBooking(int bookingId) {
		return userDAO.cancelBooking(bookingId);
	}

	@Override
	public double totalBill(int userId) {
		return userDAO.totalBill(userId);
	}
}//End of Class
