package com.service;

import java.util.List;

import com.entity.User;
import javax.persistence.Tuple;

public interface UserService {
	
	public User getUser(String id);
	public User getUserByUsername(String userName);
	public boolean saveUser(User user);
	public boolean updateUser(User user);
	public boolean deleteUser(String id);
	public List<Tuple> getAllUsers();
	public String getLastUserId();
}
