package com.timeron.MultiObserver.shop.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Arest extends Shop{

	private static final Logger LOG = LoggerFactory
			.getLogger(Arest.class);
	
	private String nextXPathPostfix = "";
	
	/**
	 * Getters & Setters
	 */
	
	public void setNextXPathPostfix(String nextXPathPostfix) {
		this.nextXPathPostfix = nextXPathPostfix;
	}

	public String getNextXPathPostfix() {
		return nextXPathPostfix;
	}
	
	/**
	 * Constructor
	 */

	public Arest() {
		super();
	}

	

}
