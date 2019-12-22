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
	private RoomBean roomBean;
	private List<RoomBean> roomList;
	private List<BookingInfoBean> bookingInfoList;
	private double totalAmount;
	private HotelBean hotelBean;
	private List<HotelBean> hotelList;
	
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
	public RoomBean getRoomBean() {
		return roomBean;
	}
	public void setRoomBean(RoomBean roomBean) {
		this.roomBean = roomBean;
	}
	public List<RoomBean> getRoomList() {
		return roomList;
	}
	public void setRoomList(List<RoomBean> roomList) {
		this.roomList = roomList;
	}
	public List<BookingInfoBean> getBookingInfoList() {
		return bookingInfoList;
	}
	public void setBookingInfoList(List<BookingInfoBean> bookingInfoList) {
		this.bookingInfoList = bookingInfoList;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public HotelBean getHotelBean() {
		return hotelBean;
	}
	public void setHotelBean(HotelBean hotelBean) {
		this.hotelBean = hotelBean;
	}
	public List<HotelBean> getHotelList() {
		return hotelList;
	}
	public void setHotelList(List<HotelBean> hotelList) {
		this.hotelList = hotelList;
	}

}//End of Class
