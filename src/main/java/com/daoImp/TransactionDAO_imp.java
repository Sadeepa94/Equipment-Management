package com.daoImp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.TransactionDAO;
import com.entity.Meterial;
import com.entity.Transaction;

@Repository
@Transactional
public class TransactionDAO_imp implements TransactionDAO{
	@Autowired
	SessionFactory session;
	@Override
	public Transaction getTransaction(int id) {
		try{
			//System.out.println(id);
			Transaction transaction=session.getCurrentSession().get(Transaction.class,id);
			return transaction;
		}
		catch(Exception ex){
			return null;
		}
	}

	@Override
	public boolean saveTransaction(Transaction transaction) {
		try{
			session.getCurrentSession().saveOrUpdate(transaction);
			return true;
		}
		catch(Exception ex){
		return false;
		}
	}

	@Override
	public boolean deleteTransaction(int id) {
		try{
			Transaction transaction=session.getCurrentSession().get(Transaction.class,id);
			session.getCurrentSession().delete(transaction);
			return true;
		}
		catch(Exception ex){
			return false;
		}
	}
	
	@Override
	public List<Transaction> getAllTransaction() {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();

			CriteriaQuery<Transaction> criteria = builder.createQuery( Transaction.class );
			Root<Transaction> root = criteria.from( Transaction.class );
			criteria.select( root );
			//criteria.where( builder.equal( root.get("userName"), userName ) );

			 List<Transaction> transaction = session.getCurrentSession().createQuery( criteria ).getResultList();
			return transaction;
		}
		catch(Exception ex)
		{
			
			return null;
		}
	}
	
	@Override
	public List<Transaction> getAllTransactionByMonth(Date date) {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();

			CriteriaQuery<Transaction> criteria = builder.createQuery( Transaction.class );
			Root<Transaction> root = criteria.from( Transaction.class );
			criteria.select( root );
			//criteria.where( builder.equal( root.get("userName"), userName ) );

			 List<Transaction> transaction = session.getCurrentSession().createQuery( criteria ).getResultList();
			return transaction;
		}
		catch(Exception ex)
		{
			
			return null;
		}
	}
	
	@Override
	public Transaction getLastTransactionByMeterial(Meterial meterial) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Transaction> criteria = builder.createQuery( Transaction.class );
		Root<Transaction> root = criteria.from( Transaction.class );
		criteria.select( root );
		criteria.where( builder.equal( root.get("meterial"), meterial ) );
		try{
		
		Transaction transaction = session.getCurrentSession().createQuery( criteria.orderBy(builder.desc(root.get("transaction_id")))).getResultList().get(0);
		return transaction;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	@Override
	public List<Transaction> getTransactionsBetweenDates(Date sdate,Date edate){
		
		System.out.println(sdate.toString());
		System.out.println(edate.toString());
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();

			CriteriaQuery<Transaction> criteria = builder.createQuery( Transaction.class );
			Root<Transaction> root = criteria.from( Transaction.class );
			criteria.select( root );
			
			
			ParameterExpression<java.util.Date> par = builder.parameter(java.util.Date.class);

			Predicate p = builder.between(root.get("borrowed_date").as(java.sql.Date.class),sdate,edate);
			criteria.where(p);

			System.out.println(sdate+ " " + edate);
			
			
			List<Transaction> transaction = session.getCurrentSession().createQuery( criteria ).getResultList();
			return transaction;
		
		}
		catch(Exception ex)
		{
			
			return null;
		}
	}

	
}
