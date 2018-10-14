package com.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.entity.Admin;
import com.entity.Meterial;
import com.entity.Transaction;
import com.entity.User;
import com.service.AdminService;
import com.service.MeterialService;
import com.service.TransactionService;
import com.service.UserService;

@Controller
public class MeterialController {
	
	@Autowired
	MeterialService meterialservice;
	
	@RequestMapping(value="/items", method=RequestMethod.GET)
	public String items(Model model){
		model.addAttribute("meterial", new Meterial());
		model.addAttribute("meterials", meterialservice.getAllMeterials());
		return "items";
	}

	@RequestMapping(value="/add_item", method=RequestMethod.GET)
	public String additem(Model model){
		model.addAttribute("meterial", new Meterial());
		model.addAttribute("lastMeterial", meterialservice.getLastMeterialId());
		return "add_item";
	}
	
	@RequestMapping(value="/deleteMeterial", method=RequestMethod.POST)
	public @ResponseBody boolean deleteitem(@RequestParam("id") String id){
		boolean success= meterialservice.deleteMeterial(id);
		return success;
	}
	
	
	@RequestMapping(value="/saveItem", method=RequestMethod.GET)
	public String saveitem(@Valid @ModelAttribute("meterial") Meterial meterial,BindingResult result, RedirectAttributes ra){
		if(result.hasErrors())
		{
			return "add_item";
		}
		
		else{
			
		
		try{
		boolean success=meterialservice.saveMeterial(meterial);
		System.out.println(success);
		}
		catch( org.springframework.dao.DataIntegrityViolationException ex){
			return "redirect:/mfail";
		}
		
		
		ra.addFlashAttribute("meterial",meterial);
		return "redirect:/meterial_add_success";
		}
	}
	
	@RequestMapping(value="/editItem", method=RequestMethod.GET)
	public String edititem(@Valid @ModelAttribute("meterial") Meterial meterial,BindingResult result, RedirectAttributes ra){
		if(result.hasErrors())
		{
			return "items";
		}
		
		else{
			
		
		try{
		boolean success=meterialservice.updateMeterial(meterial);
		System.out.println(success);
		}
		catch( org.springframework.dao.DataIntegrityViolationException ex){
			return "redirect:/mfail";
		}
		
		
		ra.addFlashAttribute("meterial",meterial);
		return "redirect:/meterial_edit_success";
		}
	}
	
	@RequestMapping(value="/meterial_edit_success", method=RequestMethod.GET)
	public String editsucess(@ModelAttribute("meterial") Meterial meterial, Model model){
		//boolean success=userservice.saveUser(user);
		System.out.println(meterial.getMeterial_name());
		model.addAttribute("success","sucessfully edited "+meterial.getMeterial_id());
		model.addAttribute("meterial", new Meterial());
		model.addAttribute("meterials", meterialservice.getAllMeterials());
		return "items";
	}
	
	@RequestMapping(value="/meterial_add_success", method=RequestMethod.GET)
	public String sucess(@ModelAttribute("meterial") Meterial meterial, Model model){
		//boolean success=userservice.saveUser(user);
		System.out.println(meterial.getMeterial_name());
		model.addAttribute("success","sucessfully added "+meterial.getMeterial_id());
		model.addAttribute("meterial", new Meterial());
		model.addAttribute("lastMeterial", meterialservice.getLastMeterialId());
		model.addAttribute("meterials", meterialservice.getAllMeterials());
		return "add_item";
	}
	
	@RequestMapping(value="/getMeterial", method=RequestMethod.POST)
	public @ResponseBody Meterial getmeterial(@RequestParam("id") String id) {
		return meterialservice.getMeterial(id);
		
	}
	
	@RequestMapping(value="/mfail", method=RequestMethod.GET)
	public String fail(@ModelAttribute("meterial") Meterial meterial, Model model){
		//boolean success=userservice.saveUser(user);
		
		model.addAttribute("success","meterial ID already exist");
		model.addAttribute("meterial", new Meterial());
		model.addAttribute("lastMeterial", meterialservice.getLastMeterialId());
		return "add_item";
	}
	
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String test() throws IOException {
		
		//just throw exception to test the exceptionhandler mapping
		if(true) {
			throw new IOException("this is io exception");
		}
		
		// render hello.jsp page
		return "hello";
	}


}
