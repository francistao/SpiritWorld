package zhp.iyalee2.utils;

import zhp.android.handlers.StrategyHandler;
import zhp.android.strategies.IStrategy;
import zhp.iyalee2.server.results.Result_SignUp;
import zhp.iyalee2.server.servers.Server_SignUp;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * 用于注册
 * @author 郑海鹏
 * @since 2015年9月8日
 */
public class Utils_SignUp {
	
	StrategyHandler handler;
	
	public Utils_SignUp(StrategyHandler handler){
		this.handler = handler;
	}
	
	/**
	 * @警告 由于用到了AsyncTask，必须在主线程中调用此方法。
	 * @警告 context 这个参数请使用application，不要使用Activity,防止内存泄露。
	 * @param signUpSuccess	注册成功之后要执行的策略
	 */
	public void signUp(final Context context, final String userName, final String password, final IStrategy signUpSuccess){
		if(context instanceof Activity){
			android.util.Log.i("郑海鹏", "Utils_SignUp#signUp(): " + "不要传入一个Activity！！！！！");
			throw new IllegalArgumentException("不要传入一个Activity！！！！！");
		}
		
		new AsyncTask<Void, Void, Result_SignUp>() {

			@Override
			protected Result_SignUp doInBackground(Void... params) {
				Result_SignUp result = new Server_SignUp().getServer(userName, password, "");	// 邮箱没有用到
				return result;
			}

			@Override
			protected void onPostExecute(Result_SignUp result) {
				if (result == Result_SignUp.SIGN_UP_SUCCESS) {
					handler.execStrategy(signUpSuccess);					
				} else if (result == Result_SignUp.USER_NAME_EXIST) {
					Toast.makeText(context, "用户名已存在！", Toast.LENGTH_LONG).show();

				} else if (result == Result_SignUp.CAN_NOT_CONNECT_TO_SERVER) {
					Toast.makeText(context, "无法连接服务器！", Toast.LENGTH_LONG).show();

				} else if (result == Result_SignUp.SIGN_UP_FAIL) {
					Toast.makeText(context, "注册失败！", Toast.LENGTH_LONG).show();
					
				} else {
					android.util.Log.i("郑海鹏", "Utils_SignUp#onPostExecute(): " + "存在未处理的回复！");
				}
			}
		}.execute();
		
		
		
	}
	
}
