package com.example.services;

import java.util.List;

public interface Services<T> {

	public T getOneByName(String name);
	public List<T> getAll();
	
	public void create(T t);
	public void update();
	public void remove();
}
