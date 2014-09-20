package com.timeron.MultiObserver.shop.controler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.timeron.MultiObserver.shop.bean.Ikea;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;


public class IkeaControler extends ShopControler{

	private static final Logger LOG = LoggerFactory
			.getLogger(IkeaControler.class);

	public IkeaControler() {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"ikea.xml");
		this.shop = (Ikea) context.getBean("ikea");
	}

//	protected String buildArticleUrl(Object objectUrl){
//		String url = "";
//		HtmlAnchor articleObject = (HtmlAnchor) objectUrl;
//		//articleLink = articleLink.getByXPath(articleLink.getCanonicalXPath()+"/a/";
//		return url = shop.getUrl()+articleObject.getHrefAttribute();
//	}
	
	protected String buildArticleName(Object objectName){
		String name = "";
		DomNode articleName = (DomNode) objectName;
		name = articleName.asText();
		return name;
	}
	
}
