package com.timeron.MultiObserver.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.timeron.MultiObserver.dao.entity.ObservedObject;
import com.timeron.MultiObserver.dao.entity.ObservedSite;
import com.timeron.MultiObserver.dao.entity.ProposedProductKay;

public class ProposedProductKayDAO extends HibernateDao{

	static Logger log = Logger.getLogger(ProposedProductKayDAO.class.getName());
	
	public void save(ProposedProductKay proposedProductKay) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(proposedProductKay);
		session.getTransaction().commit();
		session.close();
		log.info("ObservedSite saved");
		
	}

	@SuppressWarnings("unchecked")
	public List<ProposedProductKay> getByObservedSiteAndObject(ObservedSite observedSite,
			ObservedObject observedObject) {
		List<ProposedProductKay> proposedProductKay = new ArrayList<ProposedProductKay>();
		session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "FROM observedObject WHERE observedSite = '"+observedSite.getId()+"' AND observedObject = '"+observedObject.getId()+"'";
		Query query = session.createQuery(hql);
		proposedProductKay = (List<ProposedProductKay>) query.list();
		session.close();
		
		if (proposedProductKay.size() > 0) {
			return proposedProductKay;
		} else {
			List<ProposedProductKay> emptyList = Collections.emptyList();
			return emptyList;
		}
	}
}
