package zhp.iyalee2.broadcastreciver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 收到广播后关闭activity
 * @author 郑海鹏
 * @since 2015年9月18日
 */
public class BroadcastReceiver_CloaseActivity extends BroadcastReceiver {
	public static final String CLOSE_ACTIVITY = "zhp.iyalee2.broadcastreciver.BroadcastReceiver_CloaseActivity";
	
	private Activity activity; 
	
	public BroadcastReceiver_CloaseActivity(Activity activity) {
		this.activity = activity;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		android.util.Log.i("郑海鹏", "BroadcastReceiver_CloaseActivity#onReceive(): " + "即将关闭" + activity.getClass().getName());
		this.activity.finish();
	}

}
