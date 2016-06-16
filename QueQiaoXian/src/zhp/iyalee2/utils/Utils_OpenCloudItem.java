package zhp.iyalee2.utils;

import zhp.android.data.FinalValue;
import zhp.android.utils.Utils_SaveBitmap;
import zhp.iyalee2.beans.ItemValue;
import zhp.iyalee2.database.DBOperation_FriendsItems;
import zhp.java.netio.download.FileDownload;
import android.content.Context;
import android.os.AsyncTask;

/**
 * 打开未下载的云端项目。
 * 流程大致为：
 * 下载到本地 -> 创建本地item -> 将item保存到数据库 -> 返回该item
 * @author 郑海鹏
 * @since 2015年9月22日
 */
public abstract class Utils_OpenCloudItem {
	Context context;
	
	public Utils_OpenCloudItem(Context context) {
		this.context = context;
	}
	
	public void open(final String friendName, final ItemValue item){
		new AsyncTask<Void, Void, ItemValue>() {

			@Override
			protected ItemValue doInBackground(Void... params) {
				return downloadFile(friendName, item);
			}

			@Override
			protected void onPostExecute(ItemValue result) {
				doWhenFinish(result);
			}
			
		}.execute();
	}
	
	public abstract void doWhenFinish(ItemValue item);
	
	/**
	 * 第一步，先下载文件
	 */
	private ItemValue downloadFile(String friendName, ItemValue item){
		// 1. 下载文件
		FileDownload download = new FileDownload();
		Utils_General utils = new Utils_General();
		
		// 1.1 下载目标文件
		String targetLocalPath = FinalValue.FOLDER_BASE_PATH + "arworld/localItem/t"
				+ System.currentTimeMillis() + "." + utils.getSuffix(item.getTargetPath());
		download.download(item.getTargetPath(), targetLocalPath);
		
		// 1.2 下载内容文件
		String containLocalPath = FinalValue.FOLDER_BASE_PATH + "arworld/localItem/c"
				+ System.currentTimeMillis() + "." + utils.getSuffix(item.getTargetPath());
		download.download(item.getContainPath(), containLocalPath);
		
		return saveInDatabase(friendName, item, targetLocalPath, containLocalPath);
	}
	
	/**
	 * deprecated【第二步，保存到数据库, 并返回一个本地的Item，供打开。】
	 * 第二步：转移文件，生成新的项目, 保存到数据库，返回item
	 */
	private ItemValue saveInDatabase(String userName, ItemValue item, String targetLocalPath,
			String containLocalPath) {
		// 在新线程中保存图像
		final String targetSavePath = FinalValue.FOLDER_BASE_PATH + "arworld/localItem/"
				+ System.currentTimeMillis() + "target.jpg";
		final String containSavePath = FinalValue.FOLDER_BASE_PATH + "arworld/localItem/"
				+ System.currentTimeMillis() + "contain.jpg";
		
		Utils_SaveBitmap utils = new Utils_SaveBitmap();
		utils.saveFileAsJpg(targetLocalPath, 200, 200, targetSavePath,null);	// 保存目标文件
		utils.saveFileAsJpg(containLocalPath, 200, 200, containSavePath,null);	// 保存内容文件
		
		ItemValue localItem = new ItemValue(targetSavePath, item.getTitle(), item.getProfile(),
				containSavePath, item.getType(), item.getCreateTime());
		
		DBOperation_FriendsItems dbo = new DBOperation_FriendsItems(context);
		dbo.openDataBase();
		dbo.addItem(userName, localItem);
		dbo.closeDataBase();
		return localItem;
	}
}
