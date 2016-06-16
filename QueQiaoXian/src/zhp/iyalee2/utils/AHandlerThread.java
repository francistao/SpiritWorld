package zhp.iyalee2.utils;

import android.os.Handler;

public abstract class AHandlerThread extends Thread{
	Handler handler;
	
	public AHandlerThread(Handler handler){
		this.handler = handler;
	}
	
	public abstract void run();
}
