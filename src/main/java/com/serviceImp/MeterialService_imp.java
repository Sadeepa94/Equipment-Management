package com.serviceImp;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.MeterialDAO;
import com.dao.TransactionDAO;
import com.entity.Meterial;
import com.entity.Transaction;
import com.service.MeterialService;

@Service
public class MeterialService_imp implements MeterialService {
	@Autowired
	MeterialDAO meterialDAO;
	@Autowired
	TransactionDAO transactionDAO;
	@Override
	public Meterial getMeterial(String id) {
		// TODO Auto-generated method stub
		return meterialDAO.getMeterial(id);
	}

	@Override
	public boolean saveMeterial(Meterial meterial) {
		// TODO Auto-generated method stub
		return meterialDAO.saveMeterial(meterial);
	}

	@Override
	public boolean deleteMeterial(String id) {
		// TODO Auto-generated method stub
		return meterialDAO.deleteMeterial(id);
	}

	@Override
	public List<Tuple> getAllMeterials() {
		// TODO Auto-generated method stub
		return meterialDAO.getAllMeterials();
	}
	
	@Override
	public Transaction getMeterialWithLastTransaction(String id){
		Meterial meterial=meterialDAO.getMeterial(id);
		Transaction transaction = transactionDAO.getLastTransactionByMeterial(meterial);
		return transaction;
	}
	
	@Override
	public String getLastMeterialId(){
		return meterialDAO.getLastMeterialId();
	}

	@Override
	public boolean updateMeterial(Meterial meterial) {
		return meterialDAO.updateMeterial(meterial);
	}
	

}
