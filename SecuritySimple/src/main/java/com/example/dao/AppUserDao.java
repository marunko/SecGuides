package com.example.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entities.AppUser;
import com.example.entities.Role;
import com.example.entities.User_Role;

@Repository
public class AppUserDao implements DaoCRUD<AppUser> {

	@Autowired
	private RoleDao roleDao;
	
	private final String DEFAULT_ROLE = "user";
	@PersistenceContext EntityManager em;
	
	public AppUser findUserByName(String name) {
		Query query = em.createQuery(" from AppUser u WHERE u.name LIKE :name");
		query.setParameter("name", name);
		try {
			return (AppUser)query.getSingleResult();
		}
		catch(Exception e){
			return null;
		}
		 
	}
	
	public AppUser findUserById(Long id) {
		 
		return em.find(AppUser.class, id);
	}
	
	public void registerUser(AppUser newUser, Role role) {	
		if(newUser == null) return;
		
		
		AppUser user = new AppUser();
		user.setName(newUser.getName());
		// Set it in service
		user.setPassword(newUser.getPassword());
		user.setEnabled(newUser.isEnabled());
		em.persist(user);
		
		User_Role u_role = new User_Role();
		u_role.setAppUser(user);
		u_role.setAppRole(role== null? roleDao.getRoleByName(DEFAULT_ROLE) : role);
		 
		em.persist(u_role);
		em.flush();
		 
	}
	
	public void updateUser(AppUser newUser, Role role) {	
		  
	}
	
	public void removeUser(String name) {
		AppUser user = this.findUserByName(name);
		em.remove(user);
		em.flush();
		em.close();
	}

	@Override
	public AppUser getOneByName(String name) {
		Query query = em.createQuery(" from AppUser u WHERE u.name LIKE :name");
		query.setParameter("name", name);
		return (AppUser)query.getSingleResult();
	}

	@Override
	public List<AppUser> getAll() {
		// TODO Auto-generated method stub
		return em.createQuery(" from AppUser u").getResultList();
	}

	@Override
	public void create(AppUser userNew) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(AppUser userNew) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(String name) {
		// TODO Auto-generated method stub
		
	}
	public List<AppUser> getAllByRole(String name){
		Role role = roleDao.getRoleByName(name);
		Query query = em.createQuery("Select ur.appUser from User_Role ur where ur.appRole LIKE :role");
		query.setParameter("role", role);
		return query.getResultList();
	}
	//find by Role 
}
