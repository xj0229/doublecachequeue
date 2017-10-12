package com.test;

public class CheckCommandTask implements Runnable{
	private Author a = null;
	
	public CheckCommandTask(Author a){
		this.a = a;
	}

	@Override
	public void run() {
		while(!this.a.commandlist.isEmpty()){
			try {
				String s = this.a.commandlist.take();
				this.a.checkControlPriority(s.substring(1, 2), s.substring(0,1));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//this.a.commandlist.
	}

}
