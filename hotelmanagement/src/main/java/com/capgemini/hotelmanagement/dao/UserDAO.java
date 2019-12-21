package com.capgemini.hotelmanagement.dao;

import java.util.List;

import com.capgemini.hotelmanagement.beans.BookingInfoBean;
import com.capgemini.hotelmanagement.beans.RoomBean;

public interface UserDAO {
	public boolean bookHotelRoom(BookingInfoBean bookingInfoBean);
	
	public boolean checkRoomStatus(int roomId);
	
	public List<RoomBean> showAvailableRoom();
	
	public List<BookingInfoBean> showBooking(int userId);
	
	public boolean deleteBookedRoom(int bookingId);
	
	public double totalBill(int userId);
	
}//End of interface
