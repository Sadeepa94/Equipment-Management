package com.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name="Transaction_info")
public class Transaction {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@NotNull
	@Min(0)
	private int transaction_id;
	
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date borrowed_date;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date hand_overed_date;
	
	@NotNull
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_userId")
	private User user;
	
	@NotNull
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="meterial_meterial_id")
	private Meterial meterial;
	
	
	
	
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	public Date getBorrowed_date() {
		return borrowed_date;
	}
	public void setBorrowed_date(Date borrowed_date) {
		this.borrowed_date = borrowed_date;
	}
	public Date getHand_overed_date() {
		return hand_overed_date;
	}
	public void setHand_overed_date(Date hand_overed_date) {
		this.hand_overed_date = hand_overed_date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Meterial getMeterial() {
		return meterial;
	}
	public void setMeterial(Meterial meterial) {
		this.meterial = meterial;
	}
	
	
}
