package com.dao;

import com.entity.Admin;

public interface AdminDAO {
	
	public Admin getAdmin(String id);
	public Admin getAdminByUsername(String userName);
	public boolean saveAdmin(Admin Admin);
	public boolean deleteAdmin(String id);
	public boolean adminEmpty();

}
