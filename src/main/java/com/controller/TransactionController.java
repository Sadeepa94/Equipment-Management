package com.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.service.MeterialService;
import com.service.TransactionService;
import com.util.JsonResponse;


@Controller
public class TransactionController {

	@Autowired
	TransactionService transactionservice;
	
	@RequestMapping(value="/transaction", method=RequestMethod.GET)
	public String items(@ModelAttribute("success") String success, Model model){
		model.addAttribute("transaction", new Transaction());
		model.addAttribute("users", transactionservice.getAllUsers());
		System.out.println(success);
		return "new_transaction";
	}
	
	@RequestMapping(value="/getTransaction", method=RequestMethod.POST)
	public @ResponseBody Transaction gettransaction(@RequestParam("id") String id) {
		Transaction t= transactionservice.getLastTransactionByMeterial(id);
		if(t!=null)
			return t;
		else
			return null;
	}
	
	@RequestMapping(value="/history", method=RequestMethod.GET)
	public String history(Model model, @ModelAttribute("transactions") ArrayList<Transaction> transactions,@ModelAttribute("sdate") String sdate, @ModelAttribute("edate") String edate){
		model.addAttribute("transaction", new Transaction());
		return "history";
	}
	
	@RequestMapping(value="/getTransactionHistory", method=RequestMethod.POST)
	public String gettransactionhistory(@RequestParam("sdate") String sdate,@RequestParam("edate") String edate, RedirectAttributes ra) throws ParseException{
		System.out.println(edate);
		System.out.println(sdate);
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		ArrayList<Transaction> transactions = (ArrayList)transactionservice.getTransactionsBetweenDates(f.parse(sdate),f.parse(edate));
		System.out.println(transactions.size());
		System.out.println(transactions.toString());
		if(transactions!=null){
			ra.addFlashAttribute("transactions", transactions);
			ra.addFlashAttribute("sdate", sdate);
			ra.addFlashAttribute("edate", edate);
		}
		return "redirect:/history";
	}
	
	@RequestMapping(value="/saveTransaction", method=RequestMethod.POST)
	public @ResponseBody JsonResponse savetransaction(@Valid @ModelAttribute("transaction") Transaction transaction,BindingResult result, RedirectAttributes ra) {
		JsonResponse js=new JsonResponse();
		if(result.hasErrors())
		{
			
			String message="Transaction fail Please check inputs ";
			return js.getesponse(message, result);
			
		}
		
		else{
			boolean success=transactionservice.saveTransaction(transaction);
			
			if(success){
				 js.setStatus("Sucessfully done");
				 return js;
			}
			else
			{
				 js.setStatus("invalid userID");
				 return js;
			}
			
	}
		
	}
	


}
