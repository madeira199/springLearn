package com.rubenMadeira.spring.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.rubenMadeira.spring.repository.UserRepository;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String>{

	@Autowired
	private UserRepository userRespository;
	
	@Override
	public void initialize(UniqueUsername constraintAnnotation) {	
	}

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		if(userRespository == null)
		{
			return true;
		}
		return userRespository.findByName(username) == null;
	}

}
