package com.capgemini.hotelmanagement.dao;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.hotelmanagement.beans.BookingInfoBean;
import com.capgemini.hotelmanagement.beans.HistoryBean;
import com.capgemini.hotelmanagement.beans.RoomBean;

public interface RoomDAO {

	public boolean bookHotelRoom(BookingInfoBean bookingInfoBean);

	public boolean checkRoomStatus(int roomId);

	public List<RoomBean> showAvailableRoom(String roomType);

	public List<BookingInfoBean> showBooking(int userId);

	public boolean cancelBooking(int bookingId);

	public double totalBill(int userId);

	public int calculateDays(LocalDate checkinDate, LocalDate checkoutData);

	public boolean saveHistory(HistoryBean historyBean);

	public List<HistoryBean> showHistory(int userId);

	public List<RoomBean> showAllRooms();

	public boolean addNewRoom(RoomBean roomBean);
	
	public boolean deleteRoom(int roomId);

}
