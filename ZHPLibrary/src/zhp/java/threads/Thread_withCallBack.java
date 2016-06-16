package zhp.java.threads;

import zhp.java.listeners.OnFinishListener;

/**
 * 带回调的线程
 * @author 郑海鹏
 * @since 2015年7月8日
 */
public class Thread_withCallBack extends Thread{
	OnFinishListener listener;
	
	public Thread_withCallBack(Runnable runnable, OnFinishListener listener){
		super(runnable);
		this.listener = listener;
	}

	@Override
	public void run() {
		super.run();
		listener.onFinish();
	}
	
}
