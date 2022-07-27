package com.example.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserAuthController {

	 
	
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
	
	@GetMapping("/403")
	public String get403() {
		return "403";
	}
}
