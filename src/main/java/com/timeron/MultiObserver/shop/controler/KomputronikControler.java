package com.timeron.MultiObserver.shop.controler;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.timeron.MultiObserver.shop.bean.Komputronik;

public class KomputronikControler extends ShopControler {
	private static final Logger LOG = LoggerFactory
			.getLogger(MoreleControler.class);

	Komputronik komputronik;
	int siteCounter = 1;

	public KomputronikControler() {
		LOG.info("constructor: KomputronikControler");

		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"komputronik.xml");
		this.komputronik = (Komputronik) context.getBean("komputronik");
		this.shop = komputronik;
	}

	protected String checkNextSite(List<HtmlAnchor> next) {
		if (!next.isEmpty()) {
			String url = "";
			try {
				url = next.get(0).click().getUrl().toString();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (!url.isEmpty()) {
				siteCounter++;
				url = url.substring(0, url.length() - 1);
				url = url + "&p=" + siteCounter;
				LOG.info("Next site's url: " + url);
				return url;
			} else {
				LOG.info("No next Link");
				return null;
			}
		}
		return null;
	}

}
