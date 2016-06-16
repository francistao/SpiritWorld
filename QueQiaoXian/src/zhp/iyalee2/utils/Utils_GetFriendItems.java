package zhp.iyalee2.utils;

import java.util.ArrayList;

import zhp.iyalee2.beans.ItemValue;
import zhp.iyalee2.database.DBOperation_FriendsItems;
import zhp.iyalee2.server.servers.Server_GetFriendItems;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

/**
 * 获得好友的分享列表
 * 
 * @author 郑海鹏
 * @since 2015年9月11日
 */
public abstract class Utils_GetFriendItems {

	final int ON_LOCAL_ITEMS_GOT = 100;
	
	ArrayList<ItemValue> local;
	
	// 关于警告的处理：
	// 由于这个地方一般不会出现延时很长的情况（从本地数据库中获取数据并更新视图，不涉及网络操作）。
	// 因此不太可能出现内存泄露。
	// 所以这个地方暂且无视此警告。
	@SuppressLint("HandlerLeak")
	Handler handler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if(msg.what == ON_LOCAL_ITEMS_GOT){
					onLocalItemsGot(local);
				}
			}
			
	};
	
	/**
	 * @param context
	 * @param userName
	 */
	public void getFriendItems(final Context context, final String userName) {
		if (context instanceof Activity) {
			zhp.android.debug.Debug.Log(this.getClass().getName() + "#getFriends()", "不要传入一个Activity！！！！！");
			throw new IllegalArgumentException("不要传入一个Activity！！！！！");
		}

		// 从本地获取已经下载的item列表
		new AsyncTask<Void, ArrayList<ItemValue>, ArrayList<ItemValue>>() {

			@Override
			protected ArrayList<ItemValue> doInBackground(Void... params) {
				DBOperation_FriendsItems dbo = new DBOperation_FriendsItems(context);
				dbo.openDataBase();
				local = dbo.getItems(userName);
				dbo.closeDataBase();	
				handler.sendEmptyMessage(ON_LOCAL_ITEMS_GOT);
				
				ArrayList<ItemValue> cloud = new Server_GetFriendItems().getServer(userName);
				ArrayList<ItemValue> notDownload = getDifferent(local, cloud);
				return notDownload;
			}

			@Override
			protected void onPostExecute(ArrayList<ItemValue> result) {
				onNotDownloadItemsGot(result);
			}
		}.execute();
	}
	
	/**
	 * 当本地已经存在项目获取好之后
	 */
	public abstract void onLocalItemsGot(ArrayList<ItemValue> items);
	
	/**
	 *	当没有下载的列表已经获得之后 
	 */
	public abstract void onNotDownloadItemsGot(ArrayList<ItemValue> items);
	
	/**
	 * @功能	从后者找出前面没有的
	 * 从 cloud 中找出 local 中不存在的元素。
	 * @param local	已存在的元素集合
	 * @param cloud	含有 在local中没有的 元素的集合
	 * @return			在local中没有的 元素的集合
	 */
	public static ArrayList<ItemValue> getDifferent(ArrayList<ItemValue> local, ArrayList<ItemValue> cloud){
//		/* -------------------------------------------- */
//			Utils_General utilstemp = new Utils_General();
//			android.util.Log.i("郑海鹏", "Utils_GetFriendItems#getDifferent(): " + "本地的：\n");
//			utilstemp.printCollection(local);
//			android.util.Log.i("郑海鹏", "Utils_GetFriendItems#getDifferent(): " + "云端的：\n");
//			utilstemp.printCollection(cloud);
//		/* -------------------------------------------- */
//		
//		
		ArrayList<ItemValue> result = new ArrayList<ItemValue>();
		
		// 如果云端为null
		if(cloud == null){
			zhp.android.debug.Debug.Log(Utils_GetFriendItems.class.getName() + "#getDifferent()", "cloud == null");
			return result;
		}
		
		if(local == null){
			zhp.android.debug.Debug.Log(Utils_GetFriendItems.class.getName() + "#getDifferent()", "local == null");
			return cloud;
		}
		
		// 如果本地没有，直接返回cloud
		if(local.size() == 0){
			zhp.android.debug.Debug.Log(Utils_GetFriendItems.class.getName() + "#getDifferent()", "local.size() == null");
			return cloud;
		}
		
		// 遍历cloud
		for(int i = 0; i < cloud.size(); i++){
			String createTime = cloud.get(i).getCreateTime();
			
			// 遍历local
			for(int j = 0; j < local.size(); j++){
				if(local.get(j).getCreateTime().equals(createTime)){
					// 如果相同，说明已经存在，跳过
					break;
				}else if(j == local.size() - 1){
					result.add(cloud.get(i));
				}
			}
		}
		return result;
	}
}
