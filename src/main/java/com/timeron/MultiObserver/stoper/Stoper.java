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
		sec = (int) time/1000;
		min = (int) time/60000;
		hour = (int) time/360000;
		return hour+":"+min+":"+sec;
	}
	
	public String getTime(){
		sec = (int) this.time/1000;
		min = (int) this.time/60000;
		hour = (int) this.time/360000;
		return hour+":"+min+":"+sec;
	}
}
