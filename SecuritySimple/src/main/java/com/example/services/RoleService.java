package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.RoleDao;
import com.example.entities.Role;

@Service
@Transactional
public class RoleService implements Services<Role> {

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public Role getOneByName(String name) {
		// TODO Auto-generated method stub
		System.out.println(name);
		return null;
	}
	public List<Role> getRolesByUserName(String nameUser){
		return roleDao.getRolesByUserName(nameUser);
	}

	@Override
	public List<Role> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Role t) {
		// TODO Auto-generated method stub
		
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
