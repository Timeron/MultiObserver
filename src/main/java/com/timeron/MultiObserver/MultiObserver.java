package com.timeron.MultiObserver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import com.timeron.MultiObserver.stoper.Stoper;

public class MultiObserver {
	private static final Logger LOG = Logger.getLogger(MultiObserver.class);

	private static MultiObserverControler multiObserverControler = new MultiObserverControler();
	public static DateFormat timerFormat = new SimpleDateFormat("HH:mm:ss");
	public static long start = System.currentTimeMillis();
	
	public static void main(String[] args) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		LOG.info("#################################################");
		LOG.info("#                                               #");
		LOG.info("#   Nexus Multi Observer: " + dateFormat.format(start)
				+ "   #");
		LOG.info("#                                               #");
		LOG.info("#################################################");
	
		getObservedSites();
	}
	
	private static void getObservedSites(){
		LOG.info("method: getObservedSites");
		
		multiObserverControler.runMultiObserver();
		
		long stop = System.currentTimeMillis();
		Stoper stoper = new Stoper(stop - start);
		LOG.info("Łączny czas: "+ (stoper.getTime()));
	}
}


