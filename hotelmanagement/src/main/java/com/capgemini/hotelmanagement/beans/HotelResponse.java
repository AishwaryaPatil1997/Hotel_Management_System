package com.capgemini.hotelmanagement.beans;

import java.util.List;

public class HotelResponse {
	private int statusCode;
	private String message;
	private String description;
	private UserBean userBean;
	private List<UserBean> userList;
	private RoomBean roomBean;
	private List<RoomBean> roomList;
	private HotelBean hotelBean;
	private List<HotelBean> hotelList;
	private BookingInfoBean bookingInfoBean;

	// All getters and setters
	private List<BookingInfoBean> bookingInfoBeanList;

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

	public BookingInfoBean getBookingInfoBean() {
		return bookingInfoBean;
	}

	public void setBookingInfoBean(BookingInfoBean bookingInfoBean) {
		this.bookingInfoBean = bookingInfoBean;
	}

	public List<BookingInfoBean> getBookingInfoBeanList() {
		return bookingInfoBeanList;
	}

	public void setBookingInfoBeanList(List<BookingInfoBean> bookingInfoBeanList) {
		this.bookingInfoBeanList = bookingInfoBeanList;
	}
}
