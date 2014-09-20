package com.timeron.MultiObserver.dao;

import org.apache.log4j.Logger;

import com.timeron.MultiObserver.dao.entity.ObservedSiteHistory;

public class ObservedSiteHistoryDAO extends HibernateDao{

	static Logger log = Logger.getLogger(ObservedSiteHistoryDAO.class.getName());
	
	public void save(ObservedSiteHistory observedSiteHistory) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(observedSiteHistory);
		session.getTransaction().commit();
		session.close();
		log.info("ObservedSite saved");
		
	}

}
