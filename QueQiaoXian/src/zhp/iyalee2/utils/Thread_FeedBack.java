package zhp.iyalee2.utils;

import zhp.iyalee2.server.servers.Server_FeedBack;


public class Thread_FeedBack extends Thread{
	String mContains;
	
	public Thread_FeedBack(String feedbackContaisn){
		this.mContains = feedbackContaisn;
	}

	@Override
	public void run() {
		new Server_FeedBack().getServer(this.mContains);
	}
	
	
	
}
