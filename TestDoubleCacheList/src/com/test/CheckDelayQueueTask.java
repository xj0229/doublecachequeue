package com.test;

import java.util.concurrent.TimeUnit;

public class CheckDelayQueueTask implements Runnable{
	private CacheList cl = null;
	private Author a = null;
	
	public CheckDelayQueueTask(CacheList cl,Author a){
		this.cl = cl;
		this.a = a;
	}

	@Override
	public void run() {
		int i = this.cl.checkDelayQueue();
		if(i == -1){
			//create next task
			this.a.createNextTask();
			System.out.println("Start next task to check delay queue");
		}else if(i == 0){
			//check dq is empty and create next task
			Priority p = this.cl.getDq().peek();
			if(p != null){
				this.a.createNextTask();
				System.out.println("Start next task to check delay queue");
			}
			//deal task in queue
			CheckQueueTask cqt = new CheckQueueTask(this.cl,this.a);
			this.a.getSes().execute(cqt);
		}
	}

}
