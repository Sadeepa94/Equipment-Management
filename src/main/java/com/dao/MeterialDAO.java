package com.dao;

import java.util.List;

import javax.persistence.Tuple;

import com.entity.Meterial;
import com.entity.Transaction;

public interface MeterialDAO {
	public Meterial getMeterial(String id);
	public boolean saveMeterial(Meterial meterial);
	public boolean updateMeterial(Meterial meterial);
	public boolean deleteMeterial(String id);
	public List<Tuple> getAllMeterials();
	public String getLastMeterialId();
}
