package zhp.iyalee2.utils;

import zhp.android.data.CurrentUser;
import zhp.iyalee2.beans.ItemValue;
import zhp.iyalee2.database.DBOperation_LocalItem;
import zhp.iyalee2.server.servers.Server_DeleteItem;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

/**
 * 
 * @author 郑海鹏
 * @since 2015年9月18日
 */
public class Utils_DeleteItem {
	
	public void deleteItem(Activity activity, ItemValue item){
		deleteLocal(activity, item);
//		deleteCloud(activity, item);	TODO 放弃删除服务器上的东西
	}
	
	/**
	 * 先从本地删除
	 */
	private void deleteLocal(Context context, ItemValue item){
		DBOperation_LocalItem dbo = new DBOperation_LocalItem(context);
		dbo.openDataBase();
		dbo.deleteItem(item.getCreateTime());
		dbo.closeDataBase();
		// TODO 还应该将本地的文件也删掉，这里暂时不做此操作，以后补上
	}
	
	/**
	 *  删除服务器上的item
	 * @param context
	 * @param item
	 * @deprecated 暂时不要这样做 
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private void deleteCloud(final Activity context, final ItemValue item){
		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				new Server_DeleteItem().getServer(CurrentUser.getUserName(), CurrentUser.getPassword(), item);
				return null;
			}

		}.execute();
	}
}
