package com.capgemini.hotelmanagement.controller;

import java.util.List;

import javax.validation.Valid;

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
//To connect rest with angular
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HotelController {

	@Autowired
	private HotelService hotelService;

	@GetMapping(path = "/showAllHotels")
	public HotelResponse showAllHotels() {
		List<HotelBean> hotelList = hotelService.showAllHotels();
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
	public HotelResponse getHotelDetails(@Valid int hotelId) {
		HotelBean hotelBean = hotelService.getHotelDetails(hotelId);
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
	public HotelResponse addHotel(@Valid @RequestBody HotelBean hotelBean) {
		boolean isAdded = hotelService.addHotelDetails(hotelBean);

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
	public HotelResponse updateHotelDetails(@Valid @RequestBody HotelBean hotelBean) {

		boolean isUpdated = hotelService.updateHotelDetails(hotelBean);

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
	public HotelResponse deleteHotel(@Valid int hotelId) {

		boolean isDeleted = hotelService.deleteHotelDetails(hotelId);
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
	public HotelResponse searchHotel(@Valid String hotelName) {
		HotelBean hotelBean = hotelService.searchHotelDetails(hotelName);

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

}// End of Class
