package com.serviceImp;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDAO;
import com.entity.User;
import com.service.UserService;

@Service
@Transactional
public class UserService_imp implements UserService {
	@Autowired
	UserDAO userDAO;
	@Override
	public User getUser(String id) {
		return userDAO.getUser(id);
	}

	@Override
	public User getUserByUsername(String userName) {
		return userDAO.getUserByUsername(userName);
	}

	@Override
	public boolean saveUser(User user) {
		try{
		return userDAO.saveUser(user);
		}
		catch(org.springframework.dao.DataIntegrityViolationException ex){
		System.out.println("hhhh");	
		return false;
		}
	}
	

	@Override
	public boolean deleteUser(String id) {
		return userDAO.deleteUser(id);
	}

	@Override
	public List<Tuple> getAllUsers() {
		// TODO Auto-generated method stub
		return userDAO.getAllUsers();
	}
	
	@Override
	public String getLastUserId() {
		// TODO Auto-generated method stub
		return userDAO.getLastUserId();
	}

	@Override
	public boolean updateUser(User user) {
		return userDAO.updateUser(user);
	}
	
	
	
	
	
	

}
