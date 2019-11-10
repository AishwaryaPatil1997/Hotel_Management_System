package com.capgemini.hotelmanagement.service;

import com.capgemini.hotelmanagement.beans.UserBean;

public interface UserServices {
	public boolean userRegistration(UserBean userBean);

	public UserBean userLogin(String userName, String password);

	public boolean resetPassword(int userId, long phoneNumber, String password);

	public boolean updateProfile(UserBean userBean);
	
	public UserBean showProfile(int userId);

}//End of interface
