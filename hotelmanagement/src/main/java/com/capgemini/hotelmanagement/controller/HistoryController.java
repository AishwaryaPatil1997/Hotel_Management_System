package com.capgemini.hotelmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.hotelmanagement.beans.HistoryBean;
import com.capgemini.hotelmanagement.beans.HotelResponse;
import com.capgemini.hotelmanagement.service.HistoryService;

@RestController
//To connect rest with angular
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HistoryController {
	
	@Autowired
	private HistoryService historyService;
	
	public HotelResponse saveHistory(@RequestBody HistoryBean historyBean) {
		
	}//End of saveHistory()
}//End of Class
