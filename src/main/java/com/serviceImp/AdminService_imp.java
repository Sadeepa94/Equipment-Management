package com.serviceImp;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;

import com.dao.AdminDAO;
import com.entity.Admin;
import com.entity.User;
import com.service.AdminService;
import com.util.AdminFileValidator;
import com.util.FileValidator;


@Service
public class AdminService_imp implements AdminService {
	
	@Autowired
	AdminDAO adminDAO;
	
	@Autowired
	AdminFileValidator fileValidator;
	
	@Autowired
	PasswordEncoder passwordEncoder;


	@InitBinder("uservalid")
		protected void initBinderFileBucket(WebDataBinder binder) {
		binder.setValidator(fileValidator);
	}

	@Override
	public Admin getAdmin(String id) {
		System.out.println(id);
		return adminDAO.getAdmin(id);
	}

	@Override
	public Admin getAdminByUsername(String userName) {
		return adminDAO.getAdminByUsername(userName);
	}

	@Override
	public boolean saveAdmin(Admin admin) {
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		return adminDAO.saveAdmin(admin);
	}

	@Override
	public boolean updateAdminName(String username, String fname, String lname) {
		Admin admin=adminDAO.getAdminByUsername(username);
		admin.setFirstName(fname);
		admin.setLastName(lname);
		return adminDAO.saveAdmin(admin);
	}

	@Override
	public boolean updateAdminPhone(String username, String phone) {
		Admin admin=adminDAO.getAdminByUsername(username);
		admin.setContactNo(phone);
		return adminDAO.saveAdmin(admin);
	}

	@Override
	public boolean updateAdminPhoto(Admin admin ,HttpServletRequest request) {
		MultipartFile photo=admin.getPhoto_temp();
		String photo_path=request.getServletContext().getRealPath("/resources/images/")+"adminpic";
		admin=adminDAO.getAdminByUsername(admin.getUserName());
		if(photo.getSize()!=0)
		{
			try {
				FileCopyUtils.copy(photo.getBytes(),new File(photo_path+"." + FilenameUtils.getExtension(photo.getOriginalFilename())));
				System.out.println(photo_path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			admin.setPhoto("adminpic."+FilenameUtils.getExtension(photo.getOriginalFilename()));
		}
		return adminDAO.saveAdmin(admin);
	}

	
	@Override
	public boolean deleteAdmin(String id) {
		return adminDAO.deleteAdmin(id);
	}
	
	@Override
	public boolean adminEmpty(){
		return adminDAO.adminEmpty();
	}

	
	
}
