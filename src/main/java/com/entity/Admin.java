package com.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="Admin_info")
public class Admin {
	@Id
	@NotEmpty
	@Size(min=5,max=15)
	private String userName;
	
	@NotEmpty
	private String firstName;
	
	@NotEmpty
	private String lastName;
	
	@NotEmpty
	@Size(min=5,max=100, message="Minimum password length is 6")
	private String password;
	
	@NotEmpty
	@Size(min=10,max=10, message="Enter correct phone number")
	private String contactNo;
	private String photo;
	@Transient
	private MultipartFile photo_temp;


	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public MultipartFile getPhoto_temp() {
		return photo_temp;
	}
	public void setPhoto_temp(MultipartFile photo_temp) {
		this.photo_temp = photo_temp;
	}
	
	
}
