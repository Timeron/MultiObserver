package com.timeron.MultiObserver.dao;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;

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
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<ProposedProductKay> proposedProductKay = session.createCriteria(ProposedProductKay.class)
				.add(Restrictions.eq("observedSite", observedSite))
				.add(Restrictions.eq("observedObject", observedObject))
				.list();
						
		session.close();
		
		if (proposedProductKay.size() > 0) {
			return proposedProductKay;
		} else {
			List<ProposedProductKay> emptyList = Collections.emptyList();
			return emptyList;
		}
	}

	public void update(ProposedProductKay proposedProductKay) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(proposedProductKay);
		session.getTransaction().commit();
		session.close();
		
	}
}
