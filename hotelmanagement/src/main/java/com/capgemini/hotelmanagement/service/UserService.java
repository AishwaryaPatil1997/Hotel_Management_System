package com.capgemini.hotelmanagement.service;

import com.capgemini.hotelmanagement.beans.UserBean;

public interface UserService {

	public boolean userRegistration(UserBean userBean);

	public UserBean userLogin(String email, String password);

	public boolean resetPassword(int userId, long phoneNumber, String password);

	public boolean updateProfile(UserBean userBean);

	public UserBean showProfile(int userId);
}
