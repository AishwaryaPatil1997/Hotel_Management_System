package com.capgemini.hotelmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.hotelmanagement.beans.HotelBean;
import com.capgemini.hotelmanagement.beans.HotelResponse;
import com.capgemini.hotelmanagement.service.HotelService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
public class HotelController {

	@Autowired
	private HotelService service;

	@GetMapping(path = "/showAllHotels")
	public HotelResponse showAllHotels() {
		List<HotelBean> hotelList = service.showAllHotels();
		HotelResponse hotelResponse = new HotelResponse();
		if (hotelList != null && !hotelList.isEmpty()) {
			hotelResponse.setStatusCode(201);
			hotelResponse.setMessage("Success");
			hotelResponse.setDescription("Hotels Record Found...");
			hotelResponse.setHotelList(hotelList);
		} else {
			hotelResponse.setStatusCode(400);
			hotelResponse.setMessage("Failed");
			hotelResponse.setDescription("Enable to Fetch Hotel Records!");
		}
		return hotelResponse;
	}

	@GetMapping(path = "/getHotelDetails")
	public HotelResponse getHotelDetails(int hotelId) {
		HotelBean hotelBean = service.getHotelDetails(hotelId);
		HotelResponse hotelResponse = new HotelResponse();
		if (hotelBean != null) {
			hotelResponse.setStatusCode(201);
			hotelResponse.setMessage("Success");
			hotelResponse.setDescription("Hotel Record Found");
			hotelResponse.setHotelBean(hotelBean);
		} else {
			hotelResponse.setStatusCode(400);
			hotelResponse.setMessage("Failed");
			hotelResponse.setDescription("Hotel Record Not Found");
		}
		return hotelResponse;
	}

	@PostMapping(path = "/addHotel")
	public HotelResponse addHotel(@RequestBody HotelBean hotelBean) {
		boolean isAdded = service.addHotelDetails(hotelBean);

		HotelResponse hotelResponse = new HotelResponse();
		if (isAdded) {
			hotelResponse.setStatusCode(201);
			hotelResponse.setMessage("Success");
			hotelResponse.setDescription("Hotel Added Successfully...");

		} else {
			hotelResponse.setStatusCode(400);
			hotelResponse.setMessage("Failed");
			hotelResponse.setDescription("Enable to Add Hotel...");
		}
		return hotelResponse;
	}

	@PutMapping(path = "/updateHotelDetails")
	public HotelResponse updateHotelDetails(@RequestBody HotelBean hotelBean) {

		boolean isUpdated = service.updateHotelDetails(hotelBean);

		HotelResponse hotelResponse = new HotelResponse();
		if (isUpdated) {
			hotelResponse.setStatusCode(201);
			hotelResponse.setMessage("Success");
			hotelResponse.setDescription("Hotel Updated Successfully...");
		} else {
			hotelResponse.setStatusCode(400);
			hotelResponse.setMessage("Failed");
			hotelResponse.setDescription("Enable to Update Hotel...");
		}
		return hotelResponse;
	}

	@DeleteMapping(path = "/deleteHotel")
	public HotelResponse deleteHotel(int hotelId) {

		boolean isDeleted = service.deleteHotelDetails(hotelId);
		HotelResponse hotelResponse = new HotelResponse();

		if (isDeleted) {
			hotelResponse.setStatusCode(201);
			hotelResponse.setMessage("Success");
			hotelResponse.setDescription("Hotel Record Deleted");
		} else {
			hotelResponse.setStatusCode(400);
			hotelResponse.setMessage("Failed");
			hotelResponse.setDescription("Enable to Delete Hotel Record");
		}
		return hotelResponse;
	}

	@PostMapping(path = "/searchHotel")
	public HotelResponse searchHotel(String hotelName) {
		HotelBean hotelBean = service.searchHotelDetails(hotelName);

		HotelResponse hotelResponse = new HotelResponse();
		if (hotelBean != null) {
			hotelResponse.setStatusCode(201);
			hotelResponse.setMessage("Success");
			hotelResponse.setDescription("Hotel Record found");
		} else {
			hotelResponse.setStatusCode(400);
			hotelResponse.setMessage("Failed");
			hotelResponse.setDescription("No Records Found");
		}
		return hotelResponse;
	}
}
