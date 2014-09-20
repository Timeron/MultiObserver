package com.timeron.MultiObserver.shop.controler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.timeron.MultiObserver.shop.bean.Morele;

public class MoreleControler extends ShopControler{

	private static final Logger LOG = LoggerFactory
			.getLogger(MoreleControler.class);

	public MoreleControler() {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"morele.xml");
		this.shop = (Morele) context.getBean("morele");
	}

	

}
