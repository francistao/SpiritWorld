package zhp.iyalee2.classes;

import java.lang.Thread.UncaughtExceptionHandler;

import zhp.java.utils.Utils_Exception;
import android.app.Activity;
import android.os.Handler;

public class UncatchExceptionHandleTool implements UncaughtExceptionHandler {
	Activity activity;
	Handler handler;

	public UncatchExceptionHandleTool(Activity activity, Handler handler) {
		this.activity = activity;
		this.handler = handler;
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		String s = new Utils_Exception().getExceptionContains(ex);
		zhp.android.debug.Debug.Log(this.getClass().getName(), s);
	}

}
