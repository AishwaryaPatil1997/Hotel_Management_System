package com.capgemini.hotelmanagement.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hotel")
public class HotelBean {
	
		@Id
		@Column(name = "hotel_id")
		private int hotelId;
		
		@Column(name = "hotel_name")
		private String hotelName;

		@Column
		private String location;

		//All getters and setters
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

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}
		
}
