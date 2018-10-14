package com.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {
	@ExceptionHandler(value=Exception.class)
	public String exceptionHanler(Exception ex,Model model)
	{
		model.addAttribute("error", ex.getMessage());
		return "error";
	}
	
	@ExceptionHandler(value=org.springframework.transaction.CannotCreateTransactionException.class)
	public String exceptionHanler1(Exception ex,Model model)
	{
		model.addAttribute("error", ex.getCause().toString());
		return "error";
	}

}