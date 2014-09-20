package com.timeron.MultiObserver.shop.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Komputronik extends Shop {

	private static final Logger LOG = LoggerFactory
			.getLogger(Arest.class);
	
	private String nextUrl = "";
					
	/**
	 * Getters & Setters
	 */
	
	public String getNextUrl() {
		return nextUrl;
	}

	public void setNextUrl(String nextUrl) {
		this.nextUrl = nextUrl;
	}

	/**
	 * Constructor
	 */
	
	public Komputronik() {
		super();
	}
	
}
