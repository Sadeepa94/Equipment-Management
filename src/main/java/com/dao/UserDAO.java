package com.dao;

import java.util.List;

import javax.persistence.Tuple;

import com.entity.User;

public interface UserDAO {
	
	public User getUser(String id);
	public User getUserByUsername(String userName);
	public boolean saveUser(User user);
	public boolean updateUser(User user);
	public boolean deleteUser(String id);
	public List<Tuple> getAllUsers();
	public String getLastUserId();

}
