package com.capgemini.hotelmanagement.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.hotelmanagement.beans.HotelResponse;
import com.capgemini.hotelmanagement.beans.UserBean;
import com.capgemini.hotelmanagement.service.UserServices;

@RestController
//To connect rest with angular
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	@Autowired
	private UserServices userServices;

	@PostMapping(path = "/registerUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HotelResponse userRegistration(@Valid @RequestBody UserBean userBean) {
		boolean registerUser = userServices.userRegistration(userBean);
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
	}// userRegistration()

	@PostMapping(path = "/userLogin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HotelResponse UsersLogin(@Valid @RequestParam String email,
			@Valid @RequestParam String password) {
		UserBean userLogin = userServices.userLogin(email, password);
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
	}// End of UserLogin()

	@PostMapping(path = "/updatePassword")
	public HotelResponse passwordUpdate(@Valid @RequestParam int userId, @Valid @RequestParam long phoneNumber,
			@Valid @RequestParam String password) {
		boolean isUpdated = userServices.resetPassword(userId, phoneNumber, password);
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
	}// End of passwordUpdate()

	@PostMapping(path = "/updateProfile", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HotelResponse updateProfile(@Valid @RequestBody UserBean userBean) {
		boolean isUpdated = userServices.updateProfile(userBean);
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
	}// End of updateProfile()

	@GetMapping(path = "/showProfile")
	public HotelResponse showProfile(@Valid @RequestParam int userId) {
		UserBean userBean = userServices.showProfile(userId);
		HotelResponse hotelResponse = new HotelResponse();
		if (userBean != null) {
			hotelResponse.setStatusCode(201);
			hotelResponse.setMessage("Success");
			hotelResponse.setUserBean(userBean);
			hotelResponse.setDescription("Profile Retrived Successfully...");
		} else {
			hotelResponse.setStatusCode(401);
			hotelResponse.setMessage("Failed");
			hotelResponse.setDescription("Unable To Retrieve Profile...");
		}
		return hotelResponse;
	}// End of showProfile()
}// End of Class
