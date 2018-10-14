package com.serviceImp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.MeterialDAO;
import com.dao.TransactionDAO;
import com.dao.UserDAO;
import com.entity.Meterial;
import com.entity.Transaction;
import com.entity.User;
import com.service.TransactionService;

@Service
@Transactional
public class TransactionService_imp implements TransactionService {
	@Autowired
	TransactionDAO transactionDAO;
	@Autowired
	UserDAO userDAO;
	@Autowired
	MeterialDAO meterialDAO;
	@Override
	public Transaction getTransaction(int id) {
		// TODO Auto-generated method stub
		return transactionDAO.getTransaction(id);
	}

	@Override
	public boolean saveTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		
		
		
		
		
		
		try{
		Meterial meterial=meterialDAO.getMeterial(transaction.getMeterial().getMeterial_id());
		User user=userDAO.getUser(transaction.getUser().getUserId());
			if(user!=null&&meterial!=null){
		meterial.setMeterial_condition(transaction.getMeterial().getMeterial_condition());
		meterial.setAvailability(!meterial.isAvailability());
		return transactionDAO.saveTransaction(transaction) && meterialDAO.saveMeterial(meterial);
			}
			
			else
				return false;
		
		}
		catch(Exception e)
		{
			System.out.println("Invalid user name");
			return false;
		}
	}

	@Override
	public boolean deleteTransaction(int id) {
		// TODO Auto-generated method stub
		return transactionDAO.deleteTransaction(id);
	}

	@Override
	public List<Transaction> getAllTransaction() {
		
		return transactionDAO.getAllTransaction();
	}
	
	@Override
	public List<Transaction> getAllTransactionByMonth(Date date) {
		
		return transactionDAO.getAllTransactionByMonth(date);
	}
	
	@Override
	public Transaction getLastTransactionByMeterial(String id) {
		try{
		Meterial meterial = meterialDAO.getMeterial(id);
		if(meterial.isAvailability()){
			Transaction transaction = new Transaction();
			transaction.setMeterial(meterial);
			return transaction;
		}else{
			Transaction transaction = transactionDAO.getLastTransactionByMeterial(meterial);
			return transaction;
		}
		}
		catch(Exception e)
		{
			System.out.println("incorrect meterial id");
			return null;
		}
		
	}
	
	@Override
	public List<Tuple> getAllUsers() {
		// TODO Auto-generated method stub
		return userDAO.getAllUsers();
	}
	
	@Override
	public List<Transaction> getTransactionsBetweenDates(Date sdate,Date edate){
		return transactionDAO.getTransactionsBetweenDates(sdate, edate);
		
	}

}
