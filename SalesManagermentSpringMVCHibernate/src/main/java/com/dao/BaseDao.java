package com.dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public abstract class BaseDao<T> {
	private Class<T> objEntity;
	@Autowired
	protected SessionFactory sessionFactory = null;
	protected Session session = null;
	protected Transaction transaction = null;

	public BaseDao(Class<T> obj) {
		this.objEntity = obj;
	}

	public abstract boolean add(T instence);

	public abstract boolean update(T instence);

	public abstract boolean delete(Object key);

	public abstract T findObject(Object key);

	public abstract ArrayList<T> listObject();
}
