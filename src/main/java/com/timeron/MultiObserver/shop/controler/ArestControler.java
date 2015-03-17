package com.timeron.MultiObserver.shop.controler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.timeron.MultiObserver.shop.bean.Arest;

public class ArestControler extends ShopControler {
	
	private static final Logger LOG = LoggerFactory
			.getLogger(MoreleControler.class);

	private Arest arest;
	
	public ArestControler() {
		LOG.info("constructor: ArestControler");
		
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"arest.xml");
		this.arest = (Arest) context.getBean("arest");
		this.shop = (Arest) arest;
	}
	
	protected String buildArticleUrl(Object objectUrl) {
		String url = "http://www.arest.pl";
		HtmlAnchor articleLink = (HtmlAnchor) objectUrl;
		url += articleLink.getHrefAttribute();
		return url;
	}

}
