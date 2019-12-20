package com.capgemini.hotelmanagement.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class HotelResponse {
	private int statusCode;
	private String message;
	private String description;
	private UserBean userBean;
	private List<UserBean> userList;
	
	//Getters and Setters
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UserBean getUserBean() {
		return userBean;
	}
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	public List<UserBean> getUserList() {
		return userList;
	}
	public void setUserList(List<UserBean> userList) {
		this.userList = userList;
	}
	

}//End of Class
