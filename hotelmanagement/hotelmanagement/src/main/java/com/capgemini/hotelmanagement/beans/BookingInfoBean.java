package com.capgemini.hotelmanagement.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "booking_info")
public class BookingInfoBean {
	@Id
	@Column(name = "booking_id")
	private int bookingId;
	
	@Column(name = "room_id")
	private int roomId;
	
	@Column(name = "user_id")
	private int userId;
	
	@Column
	private double amount;
	
	@Column(name = "payment_status")
	private String paymentStatus;
	
	@Column(name = "mode_of_payment")
	private String modeOfPayment;
	
	@Column(name = "checkin_date")
	private String checkinDate;
	
	@Column(name = "checkout_date")
	private String checkoutDate;
	
	//Getters and Setters
	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getModeOfPayment() {
		return modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	public String getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(String checkinDate) {
		this.checkinDate = checkinDate;
	}

	public String getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(String checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	@Override
	public String toString() {
		return "BookingInfo [bookingId=" + bookingId + ", roomId=" + roomId + ", userId=" + userId + ", amount="
				+ amount + ", paymentStatus=" + paymentStatus + ", modeOfPayment=" + modeOfPayment + ", checkinDate="
				+ checkinDate + ", checkoutDate=" + checkoutDate + "]";
	}//End of toString()
	
}//End of Class
