package com.capgemini.hotelmanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.hotelmanagement.beans.BookingInfoBean;
import com.capgemini.hotelmanagement.beans.HistoryBean;
import com.capgemini.hotelmanagement.beans.HotelResponse;
import com.capgemini.hotelmanagement.beans.RoomBean;
import com.capgemini.hotelmanagement.service.RoomServices;

@RestController
//To connect rest with angular
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoomController {
	@Autowired
	private RoomServices roomServices;

	@PostMapping(path = "/bookingRoom", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HotelResponse userRegistration(@Valid @RequestBody BookingInfoBean bookingInfoBean) {
		HotelResponse hotelResponse = new HotelResponse();
		if (roomServices.checkRoomStatus(bookingInfoBean.getRoomId())) {
			boolean isBooked = roomServices.bookHotelRoom(bookingInfoBean);
			if (isBooked) {
				hotelResponse.setStatusCode(201);
				hotelResponse.setMessage("Success");
				hotelResponse.setDescription("Booking Successful.......");
			} else {
				hotelResponse.setStatusCode(401);
				hotelResponse.setMessage("Failed");
				hotelResponse.setDescription("Failed To booked Room........");
			}
		} else {
			hotelResponse.setStatusCode(401);
			hotelResponse.setMessage("Failed");
			hotelResponse.setDescription("Room is not Availables........");
		}
		return hotelResponse;
	}// userRegistration()

	@GetMapping(path = "/getAvailableRooms", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HotelResponse showRooms(@Valid @RequestParam String roomType) {
		List<RoomBean> roomList = roomServices.showAvailableRoom(roomType);
		HotelResponse hotelResponse = new HotelResponse();
		if (roomList != null) {
			hotelResponse.setStatusCode(201);
			hotelResponse.setMessage("Success");
			hotelResponse.setRoomList(roomList);
			hotelResponse.setDescription("Retrieve Available All Rooms.......");
		} else {
			hotelResponse.setStatusCode(401);
			hotelResponse.setMessage("Failed");
			hotelResponse.setDescription("There is No Available Rooms........");
		}
		return hotelResponse;
	}// End of showRooms()

	@GetMapping(path = "/getBookedRecords")
	public HotelResponse showBookingDetails(@Valid @RequestParam int userId) {
		List<BookingInfoBean> bookingInfoList = roomServices.showBooking(userId);
		HotelResponse hotelResponse = new HotelResponse();
		if (bookingInfoList != null) {
			hotelResponse.setStatusCode(201);
			hotelResponse.setMessage("Success");
			hotelResponse.setBookingInfoList(bookingInfoList);
			hotelResponse.setDescription("Retrieve All Booking.......");
		} else {
			hotelResponse.setStatusCode(401);
			hotelResponse.setMessage("Failed");
			hotelResponse.setDescription("There is No Booked data Available........");
		}
		return hotelResponse;
	}// End of showBookingDetails()

	@DeleteMapping(path = "/cancelBooking")
	public HotelResponse cancelBooking(@Valid @RequestParam int bookingId) {
		boolean isCancel = roomServices.cancelBooking(bookingId);
		HotelResponse hotelResponse = new HotelResponse();
		if (isCancel) {
			hotelResponse.setStatusCode(201);
			hotelResponse.setMessage("Success");
			hotelResponse.setDescription("Cancel Booking Successfully.......");
		} else {
			hotelResponse.setStatusCode(401);
			hotelResponse.setMessage("Failed");
			hotelResponse.setDescription("Unable to Cancel Booking........");
		}
		return hotelResponse;
	}// End of deleteBooking

	@GetMapping(path = "/totalAmount")
	public HotelResponse totalBill(@Valid @RequestParam int userId) {
		double totalBill = roomServices.totalBill(userId);
		HotelResponse hotelResponse = new HotelResponse();
		if (totalBill > 0.0) {
			hotelResponse.setStatusCode(201);
			hotelResponse.setMessage("Success");
			hotelResponse.setTotalAmount(totalBill);
			hotelResponse.setDescription("Amount Calculated Successfully.......");
		} else {
			hotelResponse.setStatusCode(401);
			hotelResponse.setMessage("Failed");
			hotelResponse.setDescription("Unable To Calculate Total Amount........");
		}
		return hotelResponse;
	}// End of totalBill()

	@GetMapping(path = "/showHistory")
	public HotelResponse showHistory(@Valid @RequestParam int userId) {
		List<HistoryBean> historyList = roomServices.showHistory(userId);
		HotelResponse hotelResponse = new HotelResponse();
		if (historyList != null) {
			hotelResponse.setStatusCode(201);
			hotelResponse.setMessage("Success");
			hotelResponse.setHistoryList(historyList);
			hotelResponse.setDescription("History Retrive Successfully.......");
		} else {
			hotelResponse.setStatusCode(401);
			hotelResponse.setMessage("Failed");
			hotelResponse.setDescription("Unable To Retrive History........");
		}
		return hotelResponse;
	}// End of showHistory()

}// End of Class
