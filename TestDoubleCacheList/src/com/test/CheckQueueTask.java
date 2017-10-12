package com.test;

import java.util.concurrent.TimeUnit;

public class CheckQueueTask implements Runnable{
	private CacheList cl = null;
	private Author a = null;
	
	public CheckQueueTask(CacheList cl,Author a){
		this.cl = cl;
		this.a = a;
	}

	@Override
	public void run() {
		Priority p = null;
		try {
			while((p = this.cl.getQ().poll(100, TimeUnit.MILLISECONDS)) != null){
				//Priority p = this.cl.getQ().poll();
				p.setPriority(10);
				System.out.println("we can clear priority delay time:"+p.getDelay(TimeUnit.MILLISECONDS));
				System.out.println("we can clear priority device ID:"+p.getDeviceID());
				System.out.println("we can clear priority user ID:"+p.getUserID());
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
