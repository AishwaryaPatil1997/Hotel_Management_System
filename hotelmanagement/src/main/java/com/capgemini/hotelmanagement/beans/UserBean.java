package com.capgemini.hotelmanagement.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class UserBean {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;

	@Column(name = "user_name")
	@NotBlank(message = "Name cannot be empty...")
	private String userName;

	@Column
	@NotBlank(message = "Email cannot be empty...")
	@Pattern(regexp = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$", message = "Email should be valid...")
//	@Email(message = "Email should be valid...")
	private String email;

	@Column(name = "phone_number")
//	@Pattern(regexp = "[6-9]{1}[0-9]{9}", message = "Phone number should be of 10 digits only...")
//	@Size(min = 10, max = 10, message = "Phone number should be of 10 digits only...")
	private long phoneNumber;

	@Column
	@NotBlank(message = "Nationality cannot be empty...")
	private String nationality;

	@Column
	@NotBlank(message = "Password cannot be empty...")
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})", message = "Password should contain 1 lowercase character, 1 uppercase character, digits from 0-9, atleast one special character from '@#$%', minimum Length of password should be 8.")
	private String password;

	@Column
	@NotBlank(message = "Gender cannot be empty...")
	private String gender;

	@Column(name = "user_type")
	@NotBlank(message = "User type cannot be empty...")
	private String userType;

	// Getters and Setters
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
