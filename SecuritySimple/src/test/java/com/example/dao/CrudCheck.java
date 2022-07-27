package com.example.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;

public interface CrudCheck {

	
	public void getOne();
	
	public void getAll();
	
	public void create();
	public void update();
	public void remove();
	
}
