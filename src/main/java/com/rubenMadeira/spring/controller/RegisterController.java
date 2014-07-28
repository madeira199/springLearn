package com.rubenMadeira.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rubenMadeira.spring.entity.User;
import com.rubenMadeira.spring.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {
    
	@Autowired
	UserService userService;
	
	@ModelAttribute("user")
	public User construct(){
		return new User();
	}
	
	@RequestMapping
	public String showRegister(){
		return "user-register";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute("user") User user, BindingResult result){
		
		if(result.hasErrors()){
			return "user-register";
		}
		userService.save(user);
		return "redirect:/register.html?success=true";
	}
	
	@RequestMapping("/availableUser")
	@ResponseBody
	public String availableUser(@RequestParam String username){
		Boolean available = userService.findOneUsername(username) == null;
		return available.toString();	
	}
	
	@RequestMapping("/availableEmail")
	@ResponseBody
	public String availableEmail(@RequestParam String email){
		Boolean available = userService.findOneEmail(email) == null;
		return available.toString();	
	}
}
