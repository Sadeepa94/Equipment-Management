package com.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.entity.Admin;
import com.entity.User;

@Component
public class AdminFileValidator implements Validator {

	
	public boolean supports(Class<?> clazz) {
		return Admin.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
		Admin admin = (Admin) obj;
		if(admin!=null){
			if (admin.getPhoto_temp().getSize() == 0) {
				errors.rejectValue("file", "missing.file");
			}
		}
	}
}