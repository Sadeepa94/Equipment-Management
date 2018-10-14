package com.daoImp;

import java.util.List;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDAO;
import com.entity.User;
@Repository

public class UserDAO_imp implements UserDAO {

	@Autowired
	SessionFactory session;
	
	@Override
	public User getUser(String id) {
		try{
			//System.out.println(id);
			User user=session.getCurrentSession().get(User.class,id);
			return user;
		}
		catch(Exception ex){
			//System.out.println(id);
			return null;
		}
	}

	@Override
	public User getUserByUsername(String userName) {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();

			CriteriaQuery<User> criteria = builder.createQuery( User.class );
			Root<User> root = criteria.from( User.class );
			criteria.select( root );
			criteria.where( builder.equal( root.get("userName"), userName ) );

			User user = session.getCurrentSession().createQuery( criteria ).getSingleResult();
			return user;
		}
		catch(Exception ex)
		{
			
			return null;
		}
	}

	@Override
	public boolean saveUser(User user) {
		try{
			session.getCurrentSession().save(user);
			return true;
		}
		catch(Exception ex){
		return false;
		}
	}
	
	@Override
	public boolean updateUser(User user) {
		try{
			session.getCurrentSession().update(user);
			return true;
		}
		catch(Exception ex){
		return false;
		}
	}
	
	

	@Override
	public boolean deleteUser(String id) {
		try{
			User user=session.getCurrentSession().get(User.class,id);
			session.getCurrentSession().delete(user);
			return true;
		}
		catch(Exception ex){
			return false;
		}
	}

	@Override
	public List<Tuple> getAllUsers() {
		// TODO Auto-generated method stub
		try{			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteria = builder.createTupleQuery();
			Root<User> root = criteria.from( User.class );
			criteria.multiselect(root.get("userId"),root.get("firstName"), root.get("lastName")); 
			//criteria.where( builder.equal( root.get("userName"), userName ) );

			List<Tuple> users = session.getCurrentSession().createQuery( criteria ).getResultList();
			return users;
		}
		catch(Exception ex)
		{
			
			return null;
		}

	}
	
	@Override
	public String getLastUserId() {
		// TODO Auto-generated method stub
		try{			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteria = builder.createTupleQuery();
			Root<User> root = criteria.from( User.class );
			criteria.multiselect(root.get("userId")); 
			//criteria.where( builder.equal( root.get("userName"), userName ) );

			Tuple lastId = session.getCurrentSession().createQuery(criteria.orderBy(builder.desc(root.get("userId")))).getResultList().get(0);
			return (String)lastId.get(0);
		}
		catch(Exception ex)
		{
			
			return null;
		}

	}
	
}
