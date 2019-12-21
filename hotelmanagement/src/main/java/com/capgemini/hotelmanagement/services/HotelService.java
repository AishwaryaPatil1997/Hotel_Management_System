package com.capgemini.hotelmanagement.services;

import com.capgemini.hotelmanagement.beans.UserBean;

public interface HotelService {
	public boolean userRegistration(UserBean userBean);

	public UserBean userLogin(String userName, String password);

	public boolean resetPassword(int userId, long phoneNumber, String password);

	public boolean updateProfile(UserBean userBean);
	
	public UserBean showProfile(int userId);

}// End of interface
