package com.timeron.MultiObserver.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Query;

import com.timeron.MultiObserver.dao.entity.ObservedLinksPackage;

public class ObservedLinksPackageDAO extends HibernateDao{

	@SuppressWarnings("unchecked")
	public List<ObservedLinksPackage> getAll(){
		List<ObservedLinksPackage> observedLinksPackages = new ArrayList<ObservedLinksPackage>();
		
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		String hql = "FROM ObservedLinksPackage";
		
		Query query = session.createQuery(hql);
		observedLinksPackages = (List<ObservedLinksPackage>) query.list();
		
		session.close();
		
		if (observedLinksPackages.size() > 0) {
			return observedLinksPackages;
		} else {
			List<ObservedLinksPackage> emptyList = Collections.emptyList();
			return emptyList;
		}
	}
}
