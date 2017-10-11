package com.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class Author {
	private ScheduledExecutorService ses = new ScheduledThreadPoolExecutor(16);
	
	private Map<String, Integer> userprioritymap = new HashMap<>();
	
	private CacheList cl = new CacheList();
	
	private Map<Integer, Priority> devicemap = new HashMap<>();
	
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
	
	
	
	
	

}
