package com.daoImp;


import java.util.List;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.MeterialDAO;
import com.entity.Admin;
import com.entity.Meterial;
import com.entity.Transaction;
import com.entity.User;
@Repository
@Transactional
public class MeterialDAO_imp implements MeterialDAO{
	
	@Autowired
	SessionFactory session;
	
	@Override
	public Meterial getMeterial(String id) {
		try{
			//System.out.println(id);
			Meterial meterial=session.getCurrentSession().get(Meterial.class,id);
			return meterial;
		}
		catch(Exception ex){
			System.out.println(id);
			return null;
		}
	}

	@Override
	public boolean saveMeterial(Meterial meterial) {
		try{
			session.getCurrentSession().save(meterial);
			return true;
		}
		catch(Exception ex){
		return false;
		}
	}

	@Override
	public boolean deleteMeterial(String id) {
		try{
			Meterial meterial=session.getCurrentSession().get(Meterial.class,id);
			session.getCurrentSession().delete(meterial);
			return true;
		}
		catch(Exception ex){
			return false;
		}
	}
	
	public List<Tuple> getAllMeterials() {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();

			CriteriaQuery<Tuple> criteria = builder.createTupleQuery();
			Root<Meterial> root = criteria.from( Meterial.class );
			criteria.multiselect(root.get("meterial_id"),root.get("meterial_name")); 
			//criteria.where( builder.equal( root.get("userName"), userName ) );

			List<Tuple> meterials = session.getCurrentSession().createQuery( criteria ).getResultList();

			
			return meterials;
		}
		catch(Exception ex)
		{
			
			return null;
		}
	}
	
	@Override
	public String getLastMeterialId() {
		// TODO Auto-generated method stub
		try{			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteria = builder.createTupleQuery();
			Root<Meterial> root = criteria.from( Meterial.class );
			criteria.multiselect(root.get("meterial_id")); 
			//criteria.where( builder.equal( root.get("userName"), userName ) );

			Tuple lastId = session.getCurrentSession().createQuery(criteria.orderBy(builder.desc(root.get("meterial_id")))).getResultList().get(0);
			return (String)lastId.get(0);
		}
		catch(Exception ex)
		{
			return null;
		}

	}

	@Override
	public boolean updateMeterial(Meterial meterial) {
		try{
			session.getCurrentSession().update(meterial);
			return true;
		}
		catch(Exception ex){
		return false;
		}
	}

	
}
