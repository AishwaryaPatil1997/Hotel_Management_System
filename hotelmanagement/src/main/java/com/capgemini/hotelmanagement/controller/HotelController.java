package com.capgemini.hotelmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.hotelmanagement.beans.HotelResponse;
import com.capgemini.hotelmanagement.beans.UserBean;
import com.capgemini.hotelmanagement.services.HotelService;

@RestController
//To connect rest with angular
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HotelController {
	@Autowired
	private HotelService hotelService;
	
	@PostMapping(path = "/registerUser",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public HotelResponse userRegistration(@RequestBody UserBean userBean) {
		boolean registerUser = hotelService.userRegistration(userBean);
		HotelResponse hotelResponse = new HotelResponse();
		if (registerUser) {
			hotelResponse.setStatusCode(201);
			hotelResponse.setMessage("Success");
			hotelResponse.setDescription("User Registered.......");
		} else {
			hotelResponse.setStatusCode(401);
			hotelResponse.setMessage("Failed");
			hotelResponse.setDescription("User Registration Failed........");
		}
		return hotelResponse;
	}//userRegistration()
	
	@GetMapping(path = "/userLogin",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public HotelResponse UsersLogin(@RequestParam("email") String email, @RequestParam("password") String password) {
		UserBean userLogin = hotelService.userLogin(email, password);
		HotelResponse hotelResponse = new HotelResponse();
		if (userLogin != null) {
			hotelResponse.setStatusCode(201);
			hotelResponse.setMessage("Success");
			hotelResponse.setUserBean(userLogin);
			hotelResponse.setDescription("Users Logged in........");
		} else {
			hotelResponse.setStatusCode(401);
			hotelResponse.setMessage("Failed");
			hotelResponse.setDescription("User Login Failed........");
		}
		return hotelResponse;
	}//End of UserLogin()
	
	@PostMapping(path = "/updatePassword")
	public HotelResponse passwordUpdate(@RequestBody int userId, long phoneNumber, String password) {
		boolean isUpdated = hotelService.resetPassword(userId, phoneNumber, password);
		HotelResponse hotelResponse = new HotelResponse();
		if (isUpdated) {
			hotelResponse.setStatusCode(201);
			hotelResponse.setMessage("Success");
			hotelResponse.setDescription("Password Updated Successfully...");
		} else {
			hotelResponse.setStatusCode(401);
			hotelResponse.setMessage("Failed");
			hotelResponse.setDescription("Unable To Update Password...");
		}
		return hotelResponse;	
	}//End of passwordUpdate()
	
	@PostMapping(path = "/updateProfile")
	public HotelResponse updateProfile(@RequestBody UserBean userBean) {
		boolean isUpdated = hotelService.updateProfile(userBean);
		HotelResponse hotelResponse = new HotelResponse();
		if (isUpdated) {
			hotelResponse.setStatusCode(201);
			hotelResponse.setMessage("Success");
			hotelResponse.setDescription("Profile Updated Successfully...");
		} else {
			hotelResponse.setStatusCode(401);
			hotelResponse.setMessage("Failed");
			hotelResponse.setDescription("Unable To Update Profile...");
		}
		return hotelResponse;
	}//End of updateProfile()
	
	
}//End of Class
