package com.timeron.MultiObserver.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.timeron.MultiObserver.dao.entity.ObservedSite;
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

	@SuppressWarnings("unchecked")
	public boolean priceChanged(ObservedSiteHistory newObservedSiteHistory) {
		List<ObservedSiteHistory> observedSiteHistories;
		session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "FROM ObservedSiteHistory WHERE observedSite = '"+newObservedSiteHistory.getObservedSite().getId()+"' ORDER BY timestamp DESC";
		Query query = session.createQuery(hql);
		query.setMaxResults(1);
		observedSiteHistories = (List<ObservedSiteHistory>) query.list();
		session.close();
		
		if(!observedSiteHistories.isEmpty()){
			if(newObservedSiteHistory.getPrice() != observedSiteHistories.get(0).getPrice() || newObservedSiteHistory.getOldPrice() != observedSiteHistories.get(0).getOldPrice()){
				return true;
			}else{
				return false;
			}
		}else{
			return true;
		}
		
	}

}
