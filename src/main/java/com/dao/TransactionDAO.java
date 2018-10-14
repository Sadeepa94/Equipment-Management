package com.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Tuple;

import com.entity.Meterial;
import com.entity.Transaction;

public interface TransactionDAO {
	
	public Transaction getTransaction(int id);
	public boolean saveTransaction(Transaction transaction);
	public boolean deleteTransaction(int id);
	public List<Transaction> getAllTransaction();
	public Transaction getLastTransactionByMeterial(Meterial meterial);
	public List<Transaction> getAllTransactionByMonth(Date date);
	List<Transaction> getTransactionsBetweenDates(Date sdate, Date edate);
}
