package com.timeron.MultiObserver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class MultiObserver {
	private static final Logger LOG = Logger.getLogger(MultiObserver.class);

	private static MultiObserverControler multiObserverControler = new MultiObserverControler();
	
	public static void main(String[] args) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		LOG.info("#################################################");
		LOG.info("#                                               #");
		LOG.info("#   Nexus Multi Observer: " + dateFormat.format(date)
				+ "   #");
		LOG.info("#                                               #");
		LOG.info("#################################################");
	
		getObservedSites();
	}
	
	private static void getObservedSites(){
		LOG.info("method: getObservedSites");
		//new 
		multiObserverControler.runMultiObserver();
		//old
		//articleControler = new ArticleControler();

	}
}


