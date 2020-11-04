package com.wellsfargo.fsd.intrtracker.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserModel {

	@NotNull(message = "User Id is mandatory")
	@Min(value=1,message = "User Id can not be negative or zero")
	private Integer userId;
	
	@NotBlank(message = "User First Name cannot be blank")
	@Size(min = 5,max=30,message = "User first name should be between 5 to 30 charcaters")
	private String fname;
	
	@NotBlank(message = "User last Name cannot be blank")
	@Size(min = 3,max=25,message = "User last name should be between 5 to 30 charcaters")
	private String lname;
	
	@NotBlank(message = "User email cannot be blank")
	@Email(message = "Please enter a valid email id")
	private String email;
	
	@NotBlank(message = "User mobile number cannot be blank")
	@Digits(integer=10, fraction = 0,message = "Please enter valid mobile number")
	@Size(min = 10,max=10,message = "Mobile number should be 10 digits")
	private String mobile;
	
	public UserModel() {
		//left unimplemented
	}

	public UserModel(Integer userId, String fname, String lname, String email, String mobile) {
		super();
		this.userId = userId;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.mobile = mobile;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Override
	public String toString() {
		return "UserModel [userId=" + userId + ", fname=" + fname + ", lname=" + lname + ", email=" + email
				+ ", mobile=" + mobile + "]";
	}

}
