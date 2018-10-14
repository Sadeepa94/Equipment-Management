package com.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.entity.Admin;

public interface AdminService {
	
	public Admin getAdmin(String id);
	public Admin getAdminByUsername(String userName);
	public boolean saveAdmin(Admin Admin);
	public boolean deleteAdmin(String id);
	public boolean updateAdminPhone(String username,String phone);
	public boolean updateAdminName(String username, String fname, String lname);
	public boolean updateAdminPhoto(Admin admin, HttpServletRequest request);
	public boolean adminEmpty();
}
