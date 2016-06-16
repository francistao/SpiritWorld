package zhp.iyalee2.utils;

import zhp.android.handlers.StrategyHandler;
import zhp.android.strategies.IStrategy;
import zhp.iyalee2.server.results.Result_LogIn;
import zhp.iyalee2.server.servers.Server_LogIn;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.Toast;

/**
 * 用于登录
 * @author 郑海鹏
 * @since 2015年9月8日
 */
public class Utils_LogIn {
	
	StrategyHandler handler;
	
	public Utils_LogIn(StrategyHandler handler) {
		this.handler = handler;
	}
	
	public void logIn(final Context context, final String userName, final String password, final Button loginButton, final IStrategy logInSuccess){
		
		if(context instanceof Activity){
			android.util.Log.i("郑海鹏", "Utils_LogIn#LogIn(): " + "不要传入一个Activity！！！！！");
			throw new IllegalArgumentException("不要传入一个Activity！！！！！");
		}
		
		new AsyncTask<Void, Void, Result_LogIn>() {

			@Override
			protected Result_LogIn doInBackground(Void... params) {
				Result_LogIn result = new Server_LogIn().getServer(userName, password);
				return result;
			}

			@Override
			protected void onPostExecute(Result_LogIn result) {
				loginButton.setClickable(true);
				android.util.Log.i("郑海鹏", "Utils_LogIn#onPostExecute(): " + "准备处理结果！");
				
				if(result == Result_LogIn.LOG_IN_SUCCESS){
					android.util.Log.i("郑海鹏", "Utils_LogIn#onPostExecute(): " + "登陆成功！");
					handler.execStrategy(logInSuccess);
					
				}else if(result == Result_LogIn.LOG_IN_FAIL){
					Toast.makeText(context, "登录失败！", Toast.LENGTH_LONG).show();
					
				}else if(result == Result_LogIn.CAN_NOT_CONNECT_TO_SERVER){
					Toast.makeText(context, "无法连接服务器！", Toast.LENGTH_LONG).show();
					
				}else{
					android.util.Log.i("郑海鹏", "Utils_SignUp#onPostExecute(): " + "存在未处理的回复！");
				}
				
			}
		}.execute();
		
		
	}
	
}
