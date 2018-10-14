package com.service;

import java.util.List;

import javax.persistence.Tuple;

import com.entity.Meterial;

public interface MeterialService {
	public Meterial getMeterial(String id);
	public boolean saveMeterial(Meterial meterial);
	public boolean deleteMeterial(String id);
	public List<Tuple> getAllMeterials();
	public boolean updateMeterial(Meterial meterial);
	public Object getMeterialWithLastTransaction(String id);
	public String getLastMeterialId();
}
