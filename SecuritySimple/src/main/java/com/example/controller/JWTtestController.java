package com.example.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Controller
public class JWTtestController {

	private final String secret = "secret";
	
	@ResponseBody
	@GetMapping("/jwtTake/{string}")
	public String getJwtGenerate(@PathVariable("string") String string) {
		String resStr = Jwts.builder().setClaims(new HashMap<String, Object>()).setSubject(string).signWith(SignatureAlgorithm.HS512, secret).compact();
		return resStr;
	}
	
	@ResponseBody
	@GetMapping("/jwtParse/{token}")
	public String getJwtParse(@PathVariable("token") String token) {
		Claims claim = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		String resStr = claim.getSubject();
		return resStr;
	}
	
	
	 
}
