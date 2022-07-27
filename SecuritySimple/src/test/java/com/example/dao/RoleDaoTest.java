package com.example.dao;

import java.io.PrintStream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoleDaoTest implements CrudCheck{

	static PrintStream p = System.out;
 
	
	@PersistenceContext
	EntityManager em;
	@Autowired
	RoleDao rd;
 
	@Test
	public void getOne() {
		p.println(rd.getRoleByName("admin"));
	}
	
	@Test
	@Override
	public void getAll() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
}
