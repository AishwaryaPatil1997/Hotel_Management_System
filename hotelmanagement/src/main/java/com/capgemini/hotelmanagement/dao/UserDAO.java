package com.capgemini.hotelmanagement.dao;

import java.util.List;

import com.capgemini.hotelmanagement.beans.UserBean;

public interface UserDAO {
	public boolean userRegistration(UserBean userBean);
	
	public UserBean userLogin(String email, String password);
	
	public boolean resetPassword(int userId, long phoneNumber, String password);
	
	public boolean updateProfile(UserBean userBean);
	
	public UserBean showProfile(int userId);
	
	public List<UserBean> showAdmin();
	
	public List<UserBean> showUser();
	
	public boolean removeUser(int userId);
	
	public UserBean emailPresent(String email);
}//End of interface
