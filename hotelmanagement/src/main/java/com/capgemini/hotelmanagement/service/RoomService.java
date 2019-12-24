package com.capgemini.hotelmanagement.service;

import java.util.List;

import com.capgemini.hotelmanagement.beans.BookingInfoBean;
import com.capgemini.hotelmanagement.beans.HistoryBean;
import com.capgemini.hotelmanagement.beans.RoomBean;

public interface RoomService {
public boolean bookHotelRoom(BookingInfoBean bookingInfoBean);
	
	public boolean checkRoomStatus(int roomId);
	
	public List<RoomBean> showAvailableRoom(String roomType);
	
	public List<BookingInfoBean> showBooking(int userId);
	
	public boolean cancelBooking(int bookingId);
	
	public double totalBill(int userId);
	
	public boolean saveHistory(HistoryBean historyBean);

	public List<HistoryBean> showHistory(int userId);

}// End of interface
