package com.example.dao;

import java.util.List;

public interface DaoCRUD<T> {

	public T getOneByName(String name);
	public List<T> getAll();
	
	public void create(T t);
	public void update(T t);
	public void remove(String name);
	
}
