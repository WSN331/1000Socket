package com.lab.sockettest.model.biz;

import java.util.List;

import com.lab.sockettest.model.bean.MyEntity;

public interface BaseBiz<T extends MyEntity> {

	
	T save(T t);
	
	T update(T t);
	
	boolean delete(T t);
	
	List<T> findAll();
	
	T findById(Integer id);
}
