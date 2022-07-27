package com.example.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entities.Role;

@Repository
@Transactional
public class RoleDao implements DaoCRUD<Role> {

	@PersistenceContext private EntityManager em;
	
	public Role getRoleByName(String name) {
		
		Query query = em.createQuery(" from Role r WHERE r.name LIKE :name");
		query.setParameter("name", name);
		return (Role) query.getSingleResult();
		
	}
	public List<Role> getRolesByUserName(String nameUser) {
		
		Query query = em.createQuery("SELECT ur.appRole from User_Role ur WHERE ur.appUser.name LIKE :name");
		query.setParameter("name", nameUser);
		return query.getResultList();
		
	}
	public List<Role> getRoles() {
			
		Query query = em.createQuery(" from Role r");	 
		return query.getResultList();	
	}
	@Override
	public Role getOneByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Role> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void create(Role r) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(Role r) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void remove(String name) {
		// TODO Auto-generated method stub
		
	}
	
}
