package com.test;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class CacheList {
	
	private PriorityQueue<Priority> pq = new PriorityQueue<>();
	private ArrayList<Priority> ap = new ArrayList<>();
	
	public void addNewPriority(Priority p){
		this.pq.add(p);
	}
	
	public void dealWithTriggerPriority(){
		this.ap.add(this.pq.remove());
	}
	
	public void delayWithNewTask(){
		//this.pq.element().getTriggertime()
	}

}
