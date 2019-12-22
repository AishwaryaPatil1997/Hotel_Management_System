package com.capgemini.hotelmanagement.service;

import java.util.List;

import com.capgemini.hotelmanagement.beans.BookingInfoBean;
import com.capgemini.hotelmanagement.beans.RoomBean;

public interface RoomServices {
public boolean bookHotelRoom(BookingInfoBean bookingInfoBean);
	
	public boolean checkRoomStatus(int roomId);
	
	public List<RoomBean> showAvailableRoom(String roomType);
	
	public List<BookingInfoBean> showBooking(int userId);
	
	public boolean cancelBooking(int bookingId);
	
	public double totalBill(int userId);

}// End of interface
