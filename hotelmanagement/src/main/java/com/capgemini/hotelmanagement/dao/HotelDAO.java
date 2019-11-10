package com.capgemini.hotelmanagement.dao;

import java.util.List;

import com.capgemini.hotelmanagement.beans.HotelBean;

public interface HotelDAO {

	public List<HotelBean> showAllHotels();

	public boolean updateHotelDetails(HotelBean hotelBean);

	public HotelBean searchHotelDetails(String hotelName);

	public HotelBean getHotelDetails(int hotelId);

	public boolean addHotelDetails(HotelBean hotelBean);

	public boolean deleteHotelDetails(int hotelId);

}// End of interface
