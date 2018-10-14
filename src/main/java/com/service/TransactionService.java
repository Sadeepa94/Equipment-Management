package com.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Tuple;

import com.entity.Meterial;
import com.entity.Transaction;

public interface TransactionService {
	
	public Transaction getTransaction(int id);
	public boolean deleteTransaction(int id);
	public List<Transaction> getAllTransaction();
	public Transaction getLastTransactionByMeterial(String id);
	public boolean saveTransaction(Transaction transaction);
	public List<Tuple> getAllUsers();
	public List<Transaction> getAllTransactionByMonth(Date date);
	List<Transaction> getTransactionsBetweenDates(Date sdate, Date edate);

}
