package com.timeron.MultiObserver.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateDao implements DAO{

	static SessionFactory sessionFactory;
	static Session session;

	@SuppressWarnings("deprecation")
	public HibernateDao() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
}
