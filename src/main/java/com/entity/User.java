package com.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.entity.Transaction;
import com.entity.Address;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="User_info")
public class User {
	@Id
	@NotEmpty
	@Size(min=5,max=15)
	private String userId;
	
	@NotEmpty
	private String firstName;
	
	@NotEmpty
	private String lastName;
	
	@NotEmpty
	private String jobTitle;
	
	@NotEmpty
	@Size(min=10,max=10, message="Enter correct phone number")
	private String contactNo;
	
	private String photo;
	@Transient
	private MultipartFile photo_temp;
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private Collection<Transaction> transaction=new ArrayList<Transaction>();
	@Embedded
	private Address address;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Collection<Transaction> getTransaction() {
		return transaction;
	}
	public void setTransaction(Collection<Transaction> transaction) {
		this.transaction = transaction;
	}
	public MultipartFile getPhoto_temp() {
		return photo_temp;
	}
	public void setPhoto_temp(MultipartFile photo_temp) {
		this.photo_temp = photo_temp;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	
	

}
