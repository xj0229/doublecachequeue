package com.test;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Priority implements Delayed{//Comparable<Priority>{
	private String deviceID = null;
	private String userID = null;
	private long triggertime = 0;
	private int priority = 0;
	
	public Priority(String deviceID, String userID, long triggertime, int priority) {
		super();
		this.deviceID = deviceID;
		this.userID = userID;
		this.triggertime = System.currentTimeMillis() + triggertime;
		this.priority = priority;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public String getUserID() {
		return userID;
	}

	public long getTriggertime() {
		return triggertime;
	}

	public int getPriority() {
		return priority;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public void setTriggertime(long triggertime) {
		this.triggertime = System.currentTimeMillis() + triggertime;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

//	@Override
//	public int compareTo(Priority o) {
//		return (int) (this.triggertime - o.triggertime);
//	}

	@Override
	public int compareTo(Delayed o) {
		return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
	}

	@Override
	public long getDelay(TimeUnit unit) {
		// TODO Auto-generated method stub
		return this.triggertime - System.currentTimeMillis();
	}
	
}
