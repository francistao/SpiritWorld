package zhp.iyalee2.utils;

import zhp.iyalee2.server.results.Result_SuccessOrFail;
import zhp.iyalee2.server.servers.Server_UpdateUserProfile;
import android.os.AsyncTask;

/**
 * 更新用户签名
 * @author 郑海鹏
 * @since 2015年9月8日
 */
public abstract class Utils_UpdateUserProfile {
	
	public void update(final String userName, final String password, final String newProfile){
		new AsyncTask<Void, Void, Result_SuccessOrFail>() {

			@Override
			protected Result_SuccessOrFail doInBackground(Void... params) {
				Result_SuccessOrFail result = new Server_UpdateUserProfile().getServer(userName, password, newProfile);
				return result;
			}

			@Override
			protected void onPostExecute(Result_SuccessOrFail result) {
				if(result == Result_SuccessOrFail.CAN_NOT_CONNECT_TO_SERVER){
					whenCannotConnToServer();
				}else{
					whenUpdateFinish();
				}
			}
		}.execute();
	}
	
	public abstract void whenCannotConnToServer();
	public abstract void whenUpdateFinish();
}
