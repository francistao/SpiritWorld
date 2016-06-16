package zhp.java.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Utils_Dlownload {
	/**
	 * 下载一张网络图片到sd卡中
	 * @param url	网络地址
	 * @param sdcardPath 要保存在sd卡中的路径
	 * @return	下载成功返回true，否则false
	 */
	public boolean download(String url, String dstFolderPath, String dstFileName){
		URL _url;
		try {
			_url = new URL(url);
		} catch (MalformedURLException e) {
			zhp.android.debug.Debug.Log(this.getClass().getName() + "#download()", "将String转化为URL时出错");
			zhp.android.debug.Debug.Log(this.getClass().getName() + "#download()", "URL:" + url);
			return false;
		}
		return download(_url, dstFolderPath, dstFileName);
	}
	
	/**
	 * 下载一个文件到sd卡中
	 * @param url	网络地址
	 * @param sdcardPath 要保存在sd卡中的路径
	 * @return	下载成功返回true，否则false
	 */
	public boolean download(URL url, String dstFolderPath, String dstFileName){
		try {
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			
			InputStream is = conn.getInputStream();
			
			File folder = new File(dstFolderPath);
			folder.mkdirs();
			
			File file = new File(dstFolderPath + dstFileName);
			file.createNewFile();
			
			FileOutputStream fos = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			do{
				int count = is.read(buffer);
				if(count < 0){
					break;
				}
				fos.write(buffer, 0, count);
			}while(true);
			fos.close();
			is.close();
			
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
