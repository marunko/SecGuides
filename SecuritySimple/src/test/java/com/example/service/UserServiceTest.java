package com.example.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.dao.CrudCheck;
import com.example.entities.AppUser;
import com.example.entities.Role;
import com.example.services.RoleService;
import com.example.services.Services;
import com.example.services.UserDetailServiceImpl;

@SpringBootTest
public class UserServiceTest implements CrudCheck {

	@Autowired 
	private UserDetailServiceImpl Uservice;
	@Autowired 
	private RoleService Rservice;
	
	 @Test
	@Override
	public void getOne() {
		 
		System.out.println(Uservice.loadUserByUsername("WWWWWWWQQ").getAuthorities());
		
	}

	@Override
	public void getAll() {
		// TODO Auto-generated method stub
		
	}
	
	// @Test
	public void getAllByRole() {
		// TODO Auto-generated method stub
		//Uservice.getAllByRole("admin").stream().forEach(System.out::println);
		// Rservice.getRolesByUserName("name").stream().forEach(System.out::println);
	}

	//@Test
	@Override
	public void create() {
		// TODO Auto-generated method stub
		var t = new AppUser();
		t.setName("WWWWWWWQQ");
		t.setPassword("123");
		t.setEnabled(true);
		
		Uservice.create(t);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

}
