package com.example.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.AppUserDao;
import com.example.dao.RoleDao;
import com.example.entities.AppUser;
import com.example.entities.Role;

@Service
@Transactional
public class UserDetailServiceImpl implements UserDetailsService, Services<AppUser> {

	@Autowired
	private AppUserDao udao;
	@Autowired
	private RoleDao rdao;
	
	//@Autowired 
	private BCryptPasswordEncoder passwordEncoder; // try set it in method
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
 
		AppUser user = this.udao.findUserByName(username);
		if(user == null) {
			throw new UsernameNotFoundException(username + " not found");
		}
		 
		List<Role> list = rdao.getRolesByUserName(username);
		List<? extends GrantedAuthority> grantList = list.stream().map(s->new SimpleGrantedAuthority(s.getName())).toList();
		
		UserDetails userDetails = (UserDetails) new User(user.getName(), user.getPassword(), grantList);
		return userDetails;
	}

	public List<AppUser> getAllByRole(String name){
		return udao.getAllByRole(name);
	}
	
	@Override
	public AppUser getOneByName(String name) {
		System.out.println(name);
		return udao.findUserByName(name);
	}

	@Override
	public List<AppUser> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(AppUser t) {
		//Register ?????? Password Encoder
		passwordEncoder = new BCryptPasswordEncoder(); 
		t.setPassword(passwordEncoder.encode(t.getPassword()));
		udao.registerUser(t, null);
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
