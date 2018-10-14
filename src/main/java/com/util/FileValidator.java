package com.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


import com.entity.User;

@Component
public class FileValidator implements Validator {

	
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		if(user!=null){
			if (user.getPhoto_temp().getSize() == 0) {
				errors.rejectValue("file", "missing.file");
			}
		}
	}
}