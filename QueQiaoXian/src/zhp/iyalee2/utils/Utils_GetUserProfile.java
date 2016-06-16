package zhp.iyalee2.utils;

import zhp.iyalee2.server.results.Result_String;
import zhp.iyalee2.server.servers.Server_GetUserProfile;
import android.os.AsyncTask;

/**
 * 获得用户签名
 * 
 * @author 郑海鹏
 * @since 2015年9月11日
 */
public abstract class Utils_GetUserProfile {
	/**
	 * @param context
	 * @param userName
	 * @param strategy	获得头像之后要执行的事情。
	 */
	public void getUserProfile(final String userName) {
		new AsyncTask<Void, Void, String>() {

			@Override
			protected String doInBackground(Void... params) {
				Result_String result = new Server_GetUserProfile().getServer(userName);
				return result.getContain();
			}

			@Override
			protected void onPostExecute(String result) {
				onGetProfileFinish(result);
			}
		}.execute();
	}
	
	/**
	 * 当图片下载完之后要做的事。
	 */
	public abstract void onGetProfileFinish(String profile);
}
