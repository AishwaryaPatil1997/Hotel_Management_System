package com.capgemini.hotelmanagement.service;

import com.capgemini.hotelmanagement.beans.HotelBean;
import com.capgemini.hotelmanagement.beans.UserBean;

public interface EmployeeService {
	
	public UserBean employeeLogin(String email, String password,String userType);
	public HotelBean getHotelDetailsForEmployee(int hotelId);

}
