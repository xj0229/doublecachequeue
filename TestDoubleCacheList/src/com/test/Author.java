package com.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Author {
	private ScheduledExecutorService ses = new ScheduledThreadPoolExecutor(16);
	
	private Map<String, Integer> userprioritymap = new HashMap<>();
	
	private CacheList cl = new CacheList();
	
	private Map<String, Priority> devicemap = new HashMap<>();
	public BlockingQueue<String> commandlist = new LinkedBlockingQueue<>();
	
	public Author(){
		this.userprioritymap.put("w", new Integer(1));
		this.userprioritymap.put("s", new Integer(2));
		this.userprioritymap.put("x", new Integer(3));
		this.userprioritymap.put("r", new Integer(1));
		this.userprioritymap.put("f", new Integer(2));
		this.userprioritymap.put("v", new Integer(3));
		this.userprioritymap.put("y", new Integer(1));
		this.userprioritymap.put("h", new Integer(2));
		this.userprioritymap.put("n", new Integer(3));
	}

	public ScheduledExecutorService getSes() {
		return ses;
	}
	
	public void addNewCommand(String c){
		try {
			this.commandlist.put(c);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//.add(c);
	}
	
	public void checkControlPriority(String deviceID, String userID){
		Priority p = this.devicemap.get(deviceID);
		if(p != null){
			if(this.userprioritymap.get(userID) < p.getPriority()){
				//high priority
				if(p.getPriority() == 10){//need a new task
					p.setUserID(userID);
					p.setPriority(this.userprioritymap.get(userID));
					p.setTriggertime(10000);
					this.cl.addNewPriority(p);
					this.createNextTask();
				}else{
					p.setUserID(userID);
					p.setPriority(this.userprioritymap.get(userID));
					p.setTriggertime(10000);
					//this.cl.addNewPriority(p);
				}
			}else if(this.userprioritymap.get(userID) == p.getPriority()){
				//same priority
				if(p.getUserID().equals(userID)){
					//same user
					System.out.println("same people delay time:"+p.getDelay(TimeUnit.MILLISECONDS));
					
					this.cl.getDq().remove(p);
					p.setTriggertime(10000);
					this.cl.addNewPriority(p);
					System.out.println("same people delay time:"+p.getDelay(TimeUnit.MILLISECONDS));
				}else{
					System.out.println("failed to control");
					System.out.println("delay time:"+p.getDelay(TimeUnit.MILLISECONDS));
				}
			}else{
				//low priority
				System.out.println("failed to control");
				System.out.println("delay time:"+p.getDelay(TimeUnit.MILLISECONDS));
			}
			
		}else{
			p = new Priority(deviceID, userID, 10000, this.userprioritymap.get(userID));
			this.devicemap.put(deviceID, p);
			this.cl.addNewPriority(p);
			this.createNextTask();
			System.out.println("start new priority");
		}
	}
	
	public void createNextTask(){
		CheckDelayQueueTask cdqt = new CheckDelayQueueTask(this.cl,this);
		//this.cl.getDq()
		this.getSes().schedule(cdqt, this.cl.getDq().peek().getDelay(TimeUnit.MILLISECONDS), TimeUnit.MILLISECONDS);
	}
	

}
