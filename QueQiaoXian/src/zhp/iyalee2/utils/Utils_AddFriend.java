package zhp.iyalee2.utils;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import zhp.android.handlers.StrategyHandler;
import zhp.android.strategies.IStrategy;
import zhp.iyalee2.server.results.Result_SuccessOrFail;
import zhp.iyalee2.server.servers.Server_AddFriend;

/**
 * 添加好友
 * @author 郑海鹏
 * @since 2015年9月8日
 */
public class Utils_AddFriend {

	StrategyHandler handler;
	
	public Utils_AddFriend(StrategyHandler handler) {
		this.handler = handler;
	}
	
	public void addFriend(final Context context, final String userName, final String friendName, final IStrategy strategy){
		if(context instanceof Activity){
			android.util.Log.i("郑海鹏", "Utils_AddFriend#addFriend(): " + "不要传入一个Activity！！！！！");
			throw new IllegalArgumentException("不要传入一个Activity！！！！！");
		}
		
		new AsyncTask<Void, Void, Result_SuccessOrFail>() {

			@Override
			protected Result_SuccessOrFail doInBackground(Void... params) {
				Result_SuccessOrFail result = new Server_AddFriend().getServer(userName, friendName);
				return result;
			}

			@Override
			protected void onPostExecute(Result_SuccessOrFail result) {
				if(result == Result_SuccessOrFail.SUCCESS){
					handler.execStrategy(strategy);
					
				}else if(result == Result_SuccessOrFail.CAN_NOT_CONNECT_TO_SERVER){
					Toast.makeText(context, "无法连接服务器！", Toast.LENGTH_LONG).show();
					
				}else if(result == Result_SuccessOrFail.FAIL){
					Toast.makeText(context, "添加好友失败！请检查用户名。", Toast.LENGTH_LONG).show();
					
				}else{
					Toast.makeText(context, "未知结果", Toast.LENGTH_LONG).show();
					
				}
			}
			
			
		}.execute();
		
		
	}
	
}
