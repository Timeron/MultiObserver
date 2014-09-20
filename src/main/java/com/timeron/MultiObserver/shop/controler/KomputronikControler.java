package com.timeron.MultiObserver.shop.controler;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.timeron.MultiObserver.shop.bean.Komputronik;

public class KomputronikControler extends ShopControler{
	private static final Logger LOG = LoggerFactory
			.getLogger(MoreleControler.class);

	Komputronik komputronik;
	
	public KomputronikControler() {
		LOG.info("constructor: KomputronikControler");
		
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"komputronik.xml");
		this.komputronik = (Komputronik) context.getBean("komputronik");
		this.shop = komputronik;
	}
	
//	protected void checkNextSite(){
//		String nextUrl = komputronik.getNextUrl();
//		LOG.info("Next site's url: "+nextUrl);
//		nextUrl = nextUrl.replaceFirst("\\!", super.siteCounter.toString());
//		if (!komputronik.getNextUrl().isEmpty()) {
//			LOG.info("next link: "+nextUrl);
//			shopLinks.add(nextUrl);
//			downloadArticlesFromUrl(nextUrl);
//		} else {
//			LOG.info("No next Link");
//		}
//	}

}
