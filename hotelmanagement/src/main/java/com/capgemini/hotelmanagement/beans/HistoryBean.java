package com.capgemini.hotelmanagement.beans;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "order_history")
public class HistoryBean {
	@Id
	@Column(name = "booking_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;
	
	@Column(name = "hotel_id")
	private int hotelId;
	
	@Column(name = "hotel_name")
	private String hotelName;
	
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
	
	@JsonFormat(pattern = "DD-MM-YYYY")
	@Column(name = "checkin_date")
	private LocalDate checkinDate;
	
	@JsonFormat(pattern = "DD-MM-YYYY")
	@Column(name = "checkout_date")
	private LocalDate checkoutDate;
	
	// Getters and Setters
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
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

	public LocalDate getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(LocalDate checkinDate) {
		this.checkinDate = checkinDate;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}
	
}//End of Class
