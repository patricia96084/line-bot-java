package com.line.linebot.dao;

import java.util.List;

public interface Dao<T, U> {
	T getByPrimaryKey(String id);
	
	List<T> getAll();
	
	void insertInto(T newT);
	
	void update(T oldT, U newTExample);
	
	void delete(U tExample);
}