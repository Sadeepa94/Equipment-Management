package com.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.entity.Admin;
import com.entity.User;
import com.service.AdminService;
import com.service.UserService;
import com.util.FileValidator;

@Controller
public class UserController {
	
	@Autowired
	UserService userservice;
	
	@Autowired
	FileValidator fileValidator;

	

	@RequestMapping(value="/users", method=RequestMethod.GET)
	public String users(Model model){
		model.addAttribute("user", new User());
		model.addAttribute("users", userservice.getAllUsers());
		return "users";
	}
	
	@RequestMapping(value="/add_user", method=RequestMethod.GET)
	public String adduser(Model model){
		model.addAttribute("user", new User());
		model.addAttribute("lastUser", userservice.getLastUserId());
		return "add_user";
	}
	
	@RequestMapping(value="/deleteUser", method=RequestMethod.POST)
	public @ResponseBody boolean deleteuser(@RequestParam("id") String id){
		boolean success=userservice.deleteUser(id);
		return success;
	}
	
	@RequestMapping(value="/saveUser", method=RequestMethod.POST)
	public String newuser(@Valid @ModelAttribute("user") User user,BindingResult result, RedirectAttributes ra,HttpServletRequest request) throws IOException{
		if (result.hasErrors()) {
			System.out.println("validation errors");
			return "add_user";
			} 
		
		
		else{
			
			
			String photo_temp11=request.getServletContext().getRealPath("/resources/images/")+"prpic"+user.getUserId();
			System.out.println(photo_temp11);
		MultipartFile temp=user.getPhoto_temp();
		if(temp != null)
		{
			FileCopyUtils.copy(temp.getBytes(),new File(photo_temp11+"." + FilenameUtils.getExtension(temp.getOriginalFilename())));
			user.setPhoto("prpic"+user.getUserId()+"."+FilenameUtils.getExtension(temp.getOriginalFilename()));
		}
		
		try{
		boolean success=userservice.saveUser(user);
		System.out.println(success);
		}
		catch( org.springframework.dao.DataIntegrityViolationException ex){
			System.out.println("fail");
			return "redirect:/fail";
			
		}
		
		
		ra.addFlashAttribute("user",user);
		return "redirect:/success";
			}
	}
	
	@RequestMapping(value="/saveEditedUser", method=RequestMethod.POST)
	public String saveEditeduser(@Valid @ModelAttribute("user") User user,BindingResult result, RedirectAttributes ra,HttpServletRequest request) throws IOException{
		if (result.hasErrors()) {
			System.out.println("validation errors");
			return "users";
			} else{
		String photo_temp11=request.getServletContext().getRealPath("/resources/images/")+"prpic"+user.getUserId();
		System.out.println(photo_temp11);
		MultipartFile temp=user.getPhoto_temp();
		if(temp.getSize()!=0)
		{
			FileCopyUtils.copy(temp.getBytes(),new File(photo_temp11+"." + FilenameUtils.getExtension(temp.getOriginalFilename())));
			user.setPhoto("prpic"+user.getUserId()+"."+FilenameUtils.getExtension(temp.getOriginalFilename()));
		}else{
			User existing=userservice.getUser(user.getUserId());
			user.setPhoto(existing.getPhoto());
		}
		
		try{
		boolean success=userservice.updateUser(user);
		System.out.println(success);
		}
		catch( org.springframework.dao.DataIntegrityViolationException ex){
			return "redirect:/fail";
			
		}
		
		
		ra.addFlashAttribute("user",user);
		return "redirect:/users";
			}
	}
	
	@RequestMapping(value="/success", method=RequestMethod.GET)
	public String sucess(@ModelAttribute("user") User user, Model model){
		//boolean success=userservice.saveUser(user);
		System.out.println(user.getFirstName());
		model.addAttribute("success","sucessfully added "+user.getUserId());
		model.addAttribute("user", new User());
		model.addAttribute("lastUser", userservice.getLastUserId());
		return "add_user";
	}
	
	@RequestMapping(value="/getUser", method=RequestMethod.POST)
	public @ResponseBody User getuser(@RequestParam("id") String id) {
		return userservice.getUser(id);
	}
	
	@RequestMapping(value="/fail", method=RequestMethod.GET)
	public String fail(@ModelAttribute("user") User user, Model model){
		//boolean success=userservice.saveUser(user);
		System.out.println(user.getFirstName());
		model.addAttribute("success","Username is already exist");
		model.addAttribute("user", new User());
		model.addAttribute("lastUser", userservice.getLastUserId());
		return "add_user";
	}

}
