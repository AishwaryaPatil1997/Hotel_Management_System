package com.capgemini.hotelmanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.hotelmanagement.beans.UserBean;
import com.capgemini.hotelmanagement.dao.HotelDAO;

@Service
public class HotelServiceImpl implements HotelService {
	
	@Autowired
	private HotelDAO hotelDAO;

	@Override
	public boolean userRegistration(UserBean userBean) {
		return hotelDAO.userRegistration(userBean);
	}

	@Override
	public UserBean userLogin(String userName, String password) {
		return hotelDAO.userLogin(userName, password);
	}

	@Override
	public boolean resetPassword(int userId, long phoneNumber, String password) {
		return hotelDAO.resetPassword(userId, phoneNumber, password);
	}

	@Override
	public boolean updateProfile(UserBean userBean) {
		return hotelDAO.updateProfile(userBean);
	}

	@Override
	public UserBean showProfile(int userId) {
		return hotelDAO.showProfile(userId);
	}

}//End of Class
