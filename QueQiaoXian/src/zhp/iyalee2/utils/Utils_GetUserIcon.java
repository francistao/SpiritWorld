package zhp.iyalee2.utils;

import zhp.android.utils.Utils_TLCBitmapLoader;
import zhp.iyalee2.server.results.Result_String;
import zhp.iyalee2.server.servers.Server_GetUserIcon;
import android.graphics.Bitmap;
import android.os.AsyncTask;

/**
 * 获得用户头像
 * 
 * @author 郑海鹏
 * @since 2015年9月11日
 */
public abstract class Utils_GetUserIcon {

	/**
	 * @param context
	 * @param userName
	 * @param strategy	获得头像之后要执行的事情。
	 */
	public void getUserIcon(final String userName) {
		new AsyncTask<Void, Void, Bitmap>() {

			@Override
			protected Bitmap doInBackground(Void... params) {
				Result_String result = new Server_GetUserIcon().getServer(userName);
				String url = result.getContain();
				if (url == null || url.equals("")) {
					return null;
				}
				return new Utils_TLCBitmapLoader().loadBitmapByUrl(url, zhp.android.data.FinalValue.FOLDER_BASE_PATH + "icon/", 500, 500);
			}

			@Override
			protected void onPostExecute(Bitmap result) {
				if (result != null) {
					onDownloadFinish(result);
				} else {
					onDownloadFail();
				}
			}
		}.execute();
	}
	
	/**
	 * 当图片下载完之后要做的事。
	 */
	public abstract void onDownloadFinish(Bitmap bitmap);

	/**
	 * 当图片下载失败之后要做的事。
	 */
	public abstract void onDownloadFail();
}
