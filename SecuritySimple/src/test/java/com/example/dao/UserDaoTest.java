package com.example.dao;

import java.io.PrintStream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.entities.AppUser;
import com.example.entities.Role;
import com.example.entities.User_Role;

@SpringBootTest
public class UserDaoTest {

	static PrintStream p = System.out;
	
	@Autowired 
	public AppUserDao aud;
	
	@PersistenceContext
	EntityManager em;
	
	//@Test
	@Transactional
	public void takeOne() {
		 
		em.<AppUser>createQuery(" from AppUser u").getResultList().stream().forEach(x->p.println(x));
		//em.<User_Role>createQuery(" from User_Role ur").getResultList().stream().forEach(x->p.println(x));
		/*em.createQuery("Select ur.appRole.name from User_Role ur WHERE ur.appUser.id = 1")
		.getResultList().stream().forEach(x->p.println(x));
		*/
		  
		  em.close();
		p.println(em.isOpen()); 
		 em.<User_Role>createQuery(" from User_Role ur").getResultList().stream().forEach(x->p.println(x));
	}
	//   @Test
		@Transactional
		public void takeTwo() {
		   	
			//em.<AppUser>createQuery(" from AppUser u").getResultList().stream().forEach(x->p.println(x));
			 //em.<User_Role>createQuery(" from User_Role ur").getResultList().stream().forEach(x->p.println(x));
			Query query = em.createQuery("Select ur.appRole.name from User_Role ur WHERE ur.appUser.name LIKE :name");
			query.setParameter("name", "Three");
			query.getResultList().stream().forEach(x->p.println(x));
			
			//p.println(User_Role.class.getName());*/
			//em.close();
		}
	 //@Test
	//@Transactional
	public void createOne() {
		AppUser user = new AppUser();
		user.setName("Two");
		user.setPassword("ssadadaaqqqqq");
		user.setEnabled(true);
		aud.registerUser(user, null);
	}
	 ///@Test
	//@Transactional
	public void romove() {
		 aud.removeUser("Two");
	}
	 
}

