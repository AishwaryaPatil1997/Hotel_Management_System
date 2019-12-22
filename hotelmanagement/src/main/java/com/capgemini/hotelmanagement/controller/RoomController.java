package com.capgemini.hotelmanagement.controller;

import java.util.List;

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
import com.capgemini.hotelmanagement.beans.HotelResponse;
import com.capgemini.hotelmanagement.beans.RoomBean;
import com.capgemini.hotelmanagement.service.RoomServices;

@RestController
//To connect rest with angular
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoomController {
	@Autowired
	private RoomServices userServices;

	@PostMapping(path = "/bookingRoom", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HotelResponse userRegistration(@RequestBody BookingInfoBean bookingInfoBean) {
		HotelResponse hotelResponse = new HotelResponse();
		if (userServices.checkRoomStatus(bookingInfoBean.getRoomId())) {
			boolean isBooked = userServices.bookHotelRoom(bookingInfoBean);
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
	public HotelResponse showRooms(@RequestParam String roomType) {
		List<RoomBean> roomList = userServices.showAvailableRoom(roomType);
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
	public HotelResponse showBookingDetails(@RequestParam int userId) {
		List<BookingInfoBean> bookingInfoList = userServices.showBooking(userId);
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
	public HotelResponse cancelBooking(@RequestParam int bookingId) {
		boolean isCancel = userServices.cancelBooking(bookingId);
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
	public HotelResponse totalBill(@RequestParam int userId) {
		double totalBill = userServices.totalBill(userId);
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

}// End of Class
