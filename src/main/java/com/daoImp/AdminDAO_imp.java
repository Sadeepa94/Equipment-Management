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

import com.dao.AdminDAO;
import com.entity.Admin;
import com.entity.Meterial;



@Repository
@Transactional
public class AdminDAO_imp implements AdminDAO {
	
	@Autowired
	SessionFactory session;
	
	
	@Override
	public Admin getAdmin(String id) {
		try{
			//System.out.println(id);
			Admin admin=session.getCurrentSession().get(Admin.class,id);
			return admin;
		}
		catch(Exception ex){
			System.out.println(id);
			return null;
		}
	}

	@Override
	public Admin getAdminByUsername(String userName) {
		try{
			
			CriteriaBuilder builder = session.getCriteriaBuilder();

			CriteriaQuery<Admin> criteria = builder.createQuery( Admin.class );
			Root<Admin> root = criteria.from( Admin.class );
			criteria.select( root );
			criteria.where( builder.equal( root.get("userName"), userName ) );

			Admin admin = session.getCurrentSession().createQuery( criteria ).getSingleResult();
			return admin;
		}
		catch(Exception ex)
		{
			
			return null;
		}
	}

	@Override
	public boolean saveAdmin(Admin Admin) {
		try{
			session.getCurrentSession().saveOrUpdate(Admin);
			return true;
		}
		catch(Exception ex){
		return false;
		}
	}

	@Override
	public boolean deleteAdmin(String id) {
		try{
			Admin admin=session.getCurrentSession().get(Admin.class,id);
			session.getCurrentSession().delete(admin);
			return true;
		}
		catch(Exception ex){
			return false;
		}
	}
	@Override
	public boolean adminEmpty() {
		// TODO Auto-generated method stub
					
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteria = builder.createTupleQuery();
			Root<Admin> root = criteria.from( Admin.class );
			criteria.multiselect(root.get("userName")); 
			//criteria.where( builder.equal( root.get("userName"), userName ) );

			boolean check = session.getCurrentSession().createQuery( criteria ).getResultList().isEmpty();
			return check;
		
	}
	
	

}
