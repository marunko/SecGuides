package com.example.controller;

import java.security.Principal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	@ResponseBody
	@GetMapping("/cookies")
	public String get403(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		StringBuilder sb = new StringBuilder();
		for(Cookie c : cookies) {
			sb.append("Name: "+c.getName()+ " Value: "+c.getValue() + " Age- "
					+c.getMaxAge()+" coment- "+c.getComment()+" Path- "+c.getPath()
					+" Domain "+c.getDomain()+" Version: "+c.getVersion());
			sb.append('\n');
		}
		Cookie cookie = new Cookie(cookies[0].getName(), cookies[0].getValue()); // Not necessary, but saves bandwidth.
		cookie.setPath(cookies[0].getPath());
		cookie.setHttpOnly(true);
		cookie.setMaxAge(0); // Don't set to -1 or it will become a session cookie!
		response.addCookie(cookie);
		return sb.toString();
	}
}
