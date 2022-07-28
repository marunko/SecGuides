package com.example.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entities.AppUser;
import com.example.services.UserDetailServiceImpl;

@Controller
public class UserAuthController {

	@Autowired
	private UserDetailServiceImpl Uservice;
	
	@GetMapping("/form")
	public String getForm() {
		return "form";
	}
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String userInfo(Model model, Principal principal) {

		// After user login successfully.
		String userName = principal.getName();

		System.out.println("User Name: " + userName);

		User loginedUser = (User) ((Authentication) principal).getPrincipal();

		String userInfo =  loginedUser.getUsername();
		model.addAttribute("userInfo", userInfo);

		return "info";
	}
	
	@GetMapping("/successpage")
	public String login(Principal principal, Model model) {
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		model.addAttribute("user", loginedUser.toString());
		return "successpage";
	}
	@GetMapping("/registration")
	public String registration( Model model) {
		 
		//model.addAttribute("user", user);
		
		return "register";
	}
	@ResponseBody
	@PostMapping("/register")
	public ResponseEntity<AppUser> register(@RequestBody AppUser user) {
		// if user name exist -> out
		if(Uservice.getOneByName(user.getName()) != null) return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		user.setEnabled(true);
		System.out.println(user);
		Uservice.create(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
		
	}
	
	@GetMapping("/403")
	public String get403() {
		
		return "403";
	}
}
