package com.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="Meterial_info")
public class Meterial {
	
	@Id
	@NotEmpty
	private String meterial_id;
	
	@NotEmpty
	private String meterial_name;
	
	@NotEmpty
	private String meterial_condition;
	
	@NotEmpty
	private String description;
	
	
	private boolean availability;
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy="meterial")
	private Collection<Transaction> transaction=new ArrayList<Transaction>();

	public String getMeterial_id() {
		return meterial_id;
	}

	public void setMeterial_id(String meterial_id) {
		this.meterial_id = meterial_id;
	}

	public String getMeterial_name() {
		return meterial_name;
	}

	public void setMeterial_name(String meterial_name) {
		this.meterial_name = meterial_name;
	}


	public String getMeterial_condition() {
		return meterial_condition;
	}

	public void setMeterial_condition(String meterial_condition) {
		this.meterial_condition = meterial_condition;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	



	
}
