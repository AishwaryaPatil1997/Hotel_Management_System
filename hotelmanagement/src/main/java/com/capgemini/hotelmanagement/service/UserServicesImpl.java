package com.capgemini.hotelmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.hotelmanagement.beans.UserBean;
import com.capgemini.hotelmanagement.dao.UserDAO;

@Service
public class UserServicesImpl implements UserServices {
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public boolean userRegistration(UserBean userBean) {
		return userDAO.userRegistration(userBean);
	}

	@Override
	public UserBean userLogin(String userName, String password) {
		return userDAO.userLogin(userName, password);
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


}//End of Class
