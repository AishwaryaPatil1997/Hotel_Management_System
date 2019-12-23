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
import com.capgemini.hotelmanagement.service.UserService;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(path = "/registerUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HotelResponse userRegistration(@Valid @RequestBody UserBean userBean) {
		boolean registerUser = userService.userRegistration(userBean);
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
	public HotelResponse UsersLogin(@RequestParam("email") String email, @RequestParam("password") String password) {
		UserBean userLogin = userService.userLogin(email, password);
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
	public HotelResponse passwordUpdate(@RequestParam int userId, @RequestParam long phoneNumber,
			@RequestParam String password) {
		boolean isUpdated = userService.resetPassword(userId, phoneNumber, password);
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
	public HotelResponse updateProfile(@RequestBody UserBean userBean) {
		boolean isUpdated = userService.updateProfile(userBean);
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
	public HotelResponse showProfile(@RequestParam int userId) {
		UserBean userBean = userService.showProfile(userId);
		HotelResponse hotelResponse = new HotelResponse();
		if (userBean != null) {
			hotelResponse.setStatusCode(201);
			hotelResponse.setMessage("Success");
			hotelResponse.setUserBean(userBean);
			hotelResponse.setDescription("Profile Retrived Successfully...");
		} else {
			hotelResponse.setStatusCode(401);
			hotelResponse.setMessage("Failed");
			hotelResponse.setDescription("Unable To Retrive Profile...");
		}
		
		return hotelResponse;
	}// End of showProfile()
}// End of Class
