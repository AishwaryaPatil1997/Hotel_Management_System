package com.capgemini.hotelmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.hotelmanagement.beans.UserBean;
import com.capgemini.hotelmanagement.dao.UserDAO;
import com.capgemini.hotelmanagement.exceptions.ExceptionMethods;
import com.capgemini.hotelmanagement.exceptions.HotelException;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public boolean userRegistration(UserBean userBean) {
		return userDAO.userRegistration(userBean);
	}

	@Override
	public UserBean userLogin(String email, String password) {
		return userDAO.userLogin(email, password);
	}

	@Override
	public boolean resetPassword(int userId, long phoneNumber, String password) {
		return userDAO.resetPassword(userId, phoneNumber, password);
	}

	@Override
	public boolean updateProfile(UserBean userBean) {
		return userDAO.updateProfile(userBean);
	}

	@Override
	public UserBean showProfile(int userId) {
		return userDAO.showProfile(userId);
	}

	@Override
	public List<UserBean> showAdmin() {
		return userDAO.showAdmin();
	}

	@Override
	public List<UserBean> showUser() {
		return userDAO.showUser();
	}

	@Override
	public boolean removeUser(int userId) {
		return userDAO.removeUser(userId);
	}

	@Override
	public UserBean emailPresent(String email) {
		return userDAO.emailPresent(email);
	}


}//End of Class
