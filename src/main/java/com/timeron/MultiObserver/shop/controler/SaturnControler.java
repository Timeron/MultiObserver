package com.timeron.MultiObserver.shop.controler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.timeron.MultiObserver.shop.bean.MediaCompany;
import com.timeron.MultiObserver.shop.bean.Saturn;

public class SaturnControler extends MediaCompanyControler{

	private static final Logger LOG = LoggerFactory
			.getLogger(MediaMarktControler.class);
	
	public SaturnControler() {
		super();
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"saturn.xml");
		
		this.mediaCompany = (MediaCompany) context.getBean("saturn");
		this.shop = (Saturn) mediaCompany;
		
	}
	
}
