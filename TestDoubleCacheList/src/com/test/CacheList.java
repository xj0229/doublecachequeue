package com.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class CacheList {
	
	private DelayQueue<Priority> dq = new DelayQueue<>();
	
	//private PriorityQueue<Priority> pq = new PriorityQueue<>();
	private BlockingQueue<Priority> q = new LinkedBlockingQueue<>();
	//private ArrayList<Priority> ap = new ArrayList<>();
	
	public void addNewPriority(Priority p){
		this.dq.add(p);
		System.out.println("add new priority:"+p.getUserID()+":"+p.getDeviceID());
	}
	
	public DelayQueue<Priority> getDq() {
		return dq;
	}

	public BlockingQueue<Priority> getQ() {
		return q;
	}

	public void setDq(DelayQueue<Priority> dq) {
		this.dq = dq;
	}

	public void setQ(BlockingQueue<Priority> q) {
		this.q = q;
	}

	public int checkDelayQueue(){
		Priority p = this.dq.poll();
		if(p != null){
			try {
				if(this.q.offer(p, 100, TimeUnit.MILLISECONDS)){
					System.out.println("timeout! priority will be clean!");
					return 0;
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}//.add(p);
			//priority time out and 
		}
		System.out.println("no priority time out");
		return -1;
	}
	
	public void delayWithNewTask(){
		//this.pq.element().getTriggertime()
	}

}
