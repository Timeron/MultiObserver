package com.timeron.MultiObserver.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import antlr.collections.List;

public class HibernateDao implements DAO{

	static SessionFactory sessionFactory;
	static Session session;
	static Transaction transaction;

	public Criteria criteria;
	public List results;
	
	@SuppressWarnings("deprecation")
	public HibernateDao() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	
	
}
