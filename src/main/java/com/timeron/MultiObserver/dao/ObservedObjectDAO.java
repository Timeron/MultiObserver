package com.timeron.MultiObserver.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.timeron.MultiObserver.dao.entity.ObservedObject;

public class ObservedObjectDAO extends HibernateDao{

	static Logger log = Logger.getLogger(ObservedSiteDAO.class.getName());
	
	public void save(ObservedObject observedObject) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(observedObject);
		session.getTransaction().commit();
		session.close();
		log.info("ObservedObjects saved");
		
	}

	public ObservedObject getById(int id) {
		// TODO Auto-generated method stub
		return new ObservedObject();
	}

	@SuppressWarnings("unchecked")
	public int getId(ObservedObject observedObject) {
		List<ObservedObject> observedObjects;
		session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "FROM observedObject WHERE productKay = '"+observedObject.getProductKay()+"'";
		Query query = session.createQuery(hql);
		observedObjects = (List<ObservedObject>) query.list();
		session.close();
		return observedObjects.get(0).getId();
	}

}