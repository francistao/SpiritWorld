package zhp.iyalee2.utils;

import java.util.ArrayList;

import zhp.iyalee2.beans.ItemFriend;
import zhp.iyalee2.server.servers.Server_GetFriends;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * 获得好友列表
 * 
 * @author 郑海鹏
 * @since 2015年9月11日
 */
public abstract class Utils_GetFriends {

	/**
	 * @param context
	 * @param userName
	 */
	public void getFriends(final Context context, final String userName, final String password) {
		if (context instanceof Activity) {
			android.util.Log.i("郑海鹏", "Utils_GetUserIcon#getUserIcon(): " + "不要传入一个Activity！！！！！");
			throw new IllegalArgumentException("不要传入一个Activity！！！！！");
		}

		new AsyncTask<Void, Void, ArrayList<ItemFriend>>() {

			@Override
			protected ArrayList<ItemFriend> doInBackground(Void... params) {
				ArrayList<ItemFriend> items = new Server_GetFriends().getServer(userName, password);
				return items;
			}

			@Override
			protected void onPostExecute(ArrayList<ItemFriend> result) {
				if (result != null) {
					onDownloadFinish(result);
				} else {
					Toast.makeText(context, "获取好友列表失败！", Toast.LENGTH_SHORT).show();
				}
			}
		}.execute();
	}
	
	/**
	 * 当图片下载完之后要做的事。
	 */
	public abstract void onDownloadFinish(ArrayList<ItemFriend> friends);

}
