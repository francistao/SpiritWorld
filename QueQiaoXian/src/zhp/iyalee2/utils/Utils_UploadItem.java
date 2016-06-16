package zhp.iyalee2.utils;

import zhp.iyalee2.beans.ItemValue;
import zhp.iyalee2.server.results.Result_SuccessOrFail;
import zhp.iyalee2.server.servers.Server_UploadItem;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * 添加好友
 * @author 郑海鹏
 * @since 2015年9月8日
 */
public abstract class Utils_UploadItem {
	
	public void uploadItem(final Context context, final String userName, final String password, final ItemValue item){
		if(context instanceof Activity){
			android.util.Log.i("郑海鹏", "Utils_UploadItem#uploadItem(): " + "不要传入一个Activity！！！！！");
			throw new IllegalArgumentException("不要传入一个Activity！！！！！");
		}
		
		new AsyncTask<Void, Void, Result_SuccessOrFail>() {

			@Override
			protected Result_SuccessOrFail doInBackground(Void... params) {
				Result_SuccessOrFail result = new Server_UploadItem().getServer(userName, password, item);
				return result;
			}

			@Override
			protected void onPostExecute(Result_SuccessOrFail result) {
				if(result == Result_SuccessOrFail.SUCCESS){
					onUploadItemSuccess();
				}else if(result == Result_SuccessOrFail.CAN_NOT_CONNECT_TO_SERVER){
					Toast.makeText(context, "无法连接服务器！", Toast.LENGTH_LONG).show();
					
				}else if(result == Result_SuccessOrFail.FAIL){
					Toast.makeText(context, "上传失败！", Toast.LENGTH_LONG).show();
					
				}else{
					Toast.makeText(context, "未知结果", Toast.LENGTH_LONG).show();
					
				}
			}
			
			
		}.execute();
		
	}
	
	public abstract void onUploadItemSuccess();
	
}
