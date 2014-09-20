package com.timeron.MultiObserver.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.timeron.MultiObserver.dao.entity.ObservedSite;

public class ObservedSiteDAO extends HibernateDao {

	static Logger log = Logger.getLogger(ObservedSiteDAO.class.getName());

	public void save(ObservedSite observedSite) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(observedSite);
		session.getTransaction().commit();
		session.close();
		log.info("ObservedSite saved");

	}
	
	public void update(ObservedSite observedSite) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(observedSite);
		session.getTransaction().commit();
		session.close();
		log.info("ObservedSite saved");
		
	}

	public boolean checkIfIdExist(ObservedSite observedSite) {
		ObservedSite observedSiteDB;
		session = sessionFactory.openSession();
		session.beginTransaction();
		observedSiteDB = (ObservedSite) session.get(ObservedSite.class, observedSite.getId());
		session.close();
		return observedSiteDB.getId() != null ? true : false;
	}

	@SuppressWarnings("unchecked")
	public boolean checkIfHashExist(ObservedSite observedSite) {
		List<ObservedSite> observedSites;
		session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "FROM ObservedSite WHERE hashUrl = '"+observedSite.getHashUrl()+"'";
		Query query = session.createQuery(hql);
		observedSites = (List<ObservedSite>) query.list();
		session.close();
		
		if(observedSites.isEmpty()){
			return false;
		}else{
			if(observedSites.get(0).getUrl().equals(observedSite.getUrl())){
				return true;
			}else{
				return false;
			}
		}
	}

	@SuppressWarnings("unchecked")
	public ObservedSite getEntity(ObservedSite observedSite) {
		List<ObservedSite> observedSites;
		session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "FROM ObservedSite WHERE hashUrl = '"+observedSite.getHashUrl()+"'";
		Query query = session.createQuery(hql);
		observedSites = (List<ObservedSite>) query.list();
		session.close();
		
		return observedSites.get(0);
	}

	@SuppressWarnings("unchecked")
	public Integer getId(ObservedSite observedSite) {
		List<ObservedSite> observedSites;
		session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "FROM ObservedSite WHERE hashUrl = '"+observedSite.getHashUrl()+"'";
		Query query = session.createQuery(hql);
		observedSites = (List<ObservedSite>) query.list();
		session.close();
		return observedSites.get(0).getId();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public boolean siteWasAddedDoday(ObservedSite observedSite) {
		List<ObservedSite> observedSites;
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		Date today = new Date(cal.getTimeInMillis());
		
		session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "FROM ObservedSite WHERE hashUrl = '"+observedSite.getHashUrl()+"'";
		Query query = session.createQuery(hql);
		observedSites = (List<ObservedSite>) query.list();
		session.close();
		
		if(observedSites.get(0).getTimestamp().after(today)){
			return true;
		}else{
			return false;
		}
	}

	

}
