package com.controller;



import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.entity.Admin;
import com.entity.Meterial;
import com.entity.User;
import com.service.AdminService;
import com.util.AdminFileValidator;
import com.util.FileValidator;







@Controller
@SessionAttributes("username")
public class AppController {
	@Autowired
	AdminService adminservice;
	
	@Autowired
	AdminFileValidator fileValidator;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@InitBinder("adminvalid")
		protected void initBinderFileBucket(WebDataBinder binder) {
		binder.setValidator(fileValidator);
	}
	

	@RequestMapping(value={"/","home"}, method=RequestMethod.GET)
	public String home(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	    model.addAttribute("username",name);
	    Admin admin=adminservice.getAdmin(name);
	    model.addAttribute(admin);
	    model.addAttribute("admin_object", new Admin());
		return "index";
	}

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Model model){
		boolean d=adminservice.adminEmpty();
		if(!d){
			System.out.println(d);
		return "login";
		
		}
		else{
			System.out.println(d);
			model.addAttribute("admin", new Admin());
			return "page";
		}
	}
	
	
	@RequestMapping(value="/newAdmin", method=RequestMethod.POST)
	public String newAdmin(@Valid @ModelAttribute("admin") Admin admin,BindingResult result){
		if(result.hasErrors()){
			return "page";
		}
		
		else{
			adminservice.saveAdmin(admin);
			return "login";
		}
	}
	
	@RequestMapping(value = "/login/{error}" )
	public String login(@PathVariable("error") String error, Model model){
	if(error.equals("bad"))		
		model.addAttribute("error","*incorrect password");
	else if(error.equals("nouser"))
		model.addAttribute("error","*incorrect username");
	return "login";
	}
	
	
	@RequestMapping(value="/changeAdminName", method=RequestMethod.POST)
	public @ResponseBody boolean changeadminname(@RequestParam("uname") String username, @RequestParam("fname") String fname, @RequestParam("lname") String lname){
		boolean success= adminservice.updateAdminName(username, fname, lname);
		return success;
	}
	
	@RequestMapping(value="/changeAdminPhone", method=RequestMethod.POST)
	public @ResponseBody boolean changeadminphone(@RequestParam("uname") String username, @RequestParam("phone") String phone){
		boolean success= adminservice.updateAdminPhone(username, phone);
		return success;
	}
	
	@RequestMapping(value="/changeAdminSettings", method=RequestMethod.POST)
	public String changeadminsettings(@RequestParam("uname") String username,@RequestParam("new_uname") String new_username,@RequestParam("old_pwd") String old_pwd, @RequestParam("new_pwd") String new_pwd, @RequestParam("confirm_new_pwd") String confirm_new_pwd, RedirectAttributes ra){
		if(!new_pwd.equals(confirm_new_pwd)){
			System.out.println("Password & Confirmed Password Does not match !");
			return "redirect:/home";
		}
		else{
			Admin admin=adminservice.getAdminByUsername(username);
			boolean success=passwordEncoder.matches(old_pwd, admin.getPassword());
			if(success)
			{
				admin.setUserName(new_username);
				admin.setPassword(new_pwd);
				adminservice.saveAdmin(admin);
				adminservice.deleteAdmin(username);
				return "redirect:/login";
			}
			
			else
			{
				System.out.println("Password Does not match !");
				return "redirect:/home";
			}
		}
	}
	
	
	@RequestMapping(value="/changeAdminPhoto", method=RequestMethod.POST)
	public String changeadminphoto(@ModelAttribute("admin") Admin admin, RedirectAttributes ra,HttpServletRequest request,BindingResult result){
		boolean success= adminservice.updateAdminPhoto(admin, request);
		return "redirect:/home";
	}
	
}	


