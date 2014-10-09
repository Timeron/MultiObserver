package com.timeron.MultiObserver.stoper;

public class Stoper {

	private long time;
	private int sec = 0;
	private int min = 0;
	private int hour = 0;
	
	public Stoper(){
		
	}
	
	public Stoper(long time){
		this.time = time;
	}
	
	public String getTime(long time){
		time = (int) time/1000;
		hour = (int) time/(60*60);
		time = time%(60*60);
		min = (int) time/(60);
		time = time%60;
		sec = (int) time;
		
		return hour+"h :"+min+"min:"+sec+"sec";
	}
	
	public String getTime(){
		time = (int) time/1000;
		hour = (int) time/(60*60);
		time = time%(60*60);
		min = (int) time/(60);
		time = time%60;
		sec = (int) time;
		
		return hour+"h :"+min+"min:"+sec+"sec";
	}
}
