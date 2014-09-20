package com.timeron.MultiObserver.shop.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Price {

	private static final Logger LOG = LoggerFactory.getLogger(Price.class);
	private Float price;

	/**
	 * Getters Setters
	 * 
	 * @return
	 */

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	/**
	 * Constructor
	 */

	public Price() {
		this.price = (float) 0;
	}

	public Price(Float price) {
		LOG.debug(price.toString());
		this.price = price;
	}

	public Price(String price) {
		LOG.debug(price.toString());
		this.price = convertStringToFloat(price);
	}

	@Override
	public String toString() {
		return "Price [price=" + price + "]";
	}

	private Float convertStringToFloat(String stringPrice) {
		LOG.info("Price to convert: " + stringPrice);
		float floatPrice = 0;
		
		if (stringPrice.contains("(")) {
			stringPrice = stringPrice.substring(0, stringPrice.indexOf('('));
		}
		stringPrice = stringPrice.trim();
		stringPrice = stringPrice.replaceAll(",", ".");
		stringPrice = stringPrice.replaceAll("Cena za sztuke", "");
		stringPrice = stringPrice.replaceAll("[a-zA-Z]+\\W+", "");
		//it's character from komputronik site (it's not the space character and it cannot be replaced by \\s+)
		stringPrice = stringPrice.replaceAll(" ", "");
		stringPrice = stringPrice.replaceAll("\\s", "");
		stringPrice = stringPrice.replaceAll("-", "");
		LOG.info("Price converted to: " + stringPrice);
		floatPrice = Float.parseFloat(stringPrice);
		return floatPrice;
	}

}
