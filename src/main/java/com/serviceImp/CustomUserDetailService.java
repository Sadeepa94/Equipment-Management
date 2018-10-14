package com.serviceImp;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Admin;
import com.service.AdminService;

@Service("userDetailsService")
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private AdminService adminService;

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Admin admin=adminService.getAdmin(username);
		
		if(admin!=null){
		
		Collection<GrantedAuthority> autho=new ArrayList <GrantedAuthority>();
		autho.add(new SimpleGrantedAuthority("admin"));
		
		User newuser=new User(username, admin.getPassword(),true,true,true,true, autho);
		return newuser;
		}
		else
		{
			System.out.println("username nt found");
			throw new UsernameNotFoundException("Invalid Username0000");
			
		}
	}

}
