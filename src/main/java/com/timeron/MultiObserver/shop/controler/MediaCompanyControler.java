package com.timeron.MultiObserver.shop.controler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.timeron.MultiObserver.shop.bean.MediaCompany;
import com.timeron.MultiObserver.shop.helper.Price;

public class MediaCompanyControler extends ShopControler{
	
	private static final Logger LOG = LoggerFactory
			.getLogger(MediaMarktControler.class);
	
	protected MediaCompany mediaCompany;
	
	public MediaCompanyControler() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected Price buildPrice(Object objectPrice){
		String strPrice = "";
		DomNode domNodePrice = (DomNode) objectPrice;
		List<DomNode> priceElements = (List<DomNode>) domNodePrice.getByXPath(domNodePrice.getCanonicalXPath()+this.mediaCompany.getProductPriceElementXPath());
		
		for(DomNode priceElement : priceElements){
			String em = (String) priceElement.getAttributes().getNamedItem("class").getNodeValue();
			switch (em){
				case "p1" : strPrice = strPrice+"1";
					break;
				case "p2" : strPrice = strPrice+"2";
					break;
				case "p3" : strPrice = strPrice+"3";
					break;
				case "p4" : strPrice = strPrice+"4";
					break;
				case "p5" : strPrice = strPrice+"5";
					break;
				case "p6" : strPrice = strPrice+"6";
					break;
				case "p7" : strPrice = strPrice+"7";
					break;
				case "p8" : strPrice = strPrice+"8";
					break;
				case "p9" : strPrice = strPrice+"9";
					break;
				case "p0" : strPrice = strPrice+"0";
					break;
				case "p-coma" : strPrice = strPrice+".";
					break;
				default :
					break;
			}
		}
		
		LOG.info("Text "+domNodePrice.getByXPath(domNodePrice.getCanonicalXPath()+this.mediaCompany.getProductPriceElementXPath()));
		LOG.info("Text "+priceElements.get(0).getAttributes().getNamedItem("class").getNodeValue());

		
		Price price = new Price(strPrice);
		return price;
	}

}
