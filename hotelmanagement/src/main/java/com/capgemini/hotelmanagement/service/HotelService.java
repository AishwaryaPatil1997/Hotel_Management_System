package com.capgemini.hotelmanagement.service;

import java.util.List;

import com.capgemini.hotelmanagement.beans.HotelBean;

public interface HotelService {

	public List<HotelBean> showAllHotels();
	public boolean updateHotelDetails(HotelBean hotelBean);
	public List<HotelBean> searchHotelDetails(String hotelName);
	public HotelBean getHotelDetails(int hotelId);
	public boolean addHotelDetails(HotelBean hotelBean);
	public boolean deleteHotelDetails(int hotelId);

}
