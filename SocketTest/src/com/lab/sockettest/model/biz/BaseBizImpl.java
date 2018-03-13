package com.lab.sockettest.model.biz;

import java.util.List;

import com.lab.sockettest.model.bean.MyEntity;

import shit.db.ShitDBSession;
import shit.db.exception.ShitDBConfigureException;
import shit.db.exception.ShitDBExecuteException;
import shit.db.exception.ShitDBJDBCException;
import shit.db.exception.ShitDBTranslateException;
import shit.db.exception.ShitDBWrongControlException;

public class BaseBizImpl<T extends MyEntity> implements BaseBiz<T> {
	protected ShitDBSession dbSession;
	
	protected Class<? extends MyEntity> clazz;
	
	public BaseBizImpl(ShitDBSession dbSession, Class<? extends MyEntity> clazz) {
		super();
		this.dbSession = dbSession;
		this.clazz = clazz;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T save(T t) {
		try {
			return (T) dbSession.save(t);
		} catch (ShitDBExecuteException e) {
			e.printStackTrace();
		} catch (ShitDBConfigureException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T update(T t) {
		try {
			return (T) dbSession.update(t);
		} catch (ShitDBExecuteException e) {
			e.printStackTrace();
		} catch (ShitDBConfigureException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(T t) {
		try {
			dbSession.delete(t);
			return true;
		} catch (ShitDBExecuteException e) {
			e.printStackTrace();
		} catch (ShitDBConfigureException e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		try {
			return (List<T>) dbSession.findAll(clazz);
		} catch (ShitDBJDBCException e) {
			e.printStackTrace();
		} catch (ShitDBWrongControlException e) {
			e.printStackTrace();
		} catch (ShitDBConfigureException e) {
			e.printStackTrace();
		} catch (ShitDBTranslateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(Integer id) {
		try {
			return (T) dbSession.findById(clazz, id);
		} catch (ShitDBJDBCException e) {
			e.printStackTrace();
		} catch (ShitDBConfigureException e) {
			e.printStackTrace();
		} catch (ShitDBWrongControlException e) {
			e.printStackTrace();
		}
		return null;
	}

}
