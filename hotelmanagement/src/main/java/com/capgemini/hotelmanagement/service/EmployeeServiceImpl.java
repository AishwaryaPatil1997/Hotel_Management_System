package com.capgemini.hotelmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.hotelmanagement.beans.HotelBean;
import com.capgemini.hotelmanagement.beans.UserBean;
import com.capgemini.hotelmanagement.dao.EmployeeDAO;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO;

	@Override
	public UserBean employeeLogin(String email, String password, String userType) {
		return employeeDAO.employeeLogin(email, password, userType);
	}

	@Override
	public HotelBean getHotelDetailsForEmployee(int hotelId) {
		return employeeDAO.getHotelDetailsForEmployee(hotelId);
	}
}
