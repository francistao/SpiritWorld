package zhp.android.utils;

import zhp.java.utils.Utils_Dlownload;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

/**
 * Double Level Cache 网络-本地两级缓存的图片加载。
 * @author 郑海鹏
 * @since 2015年8月22日
 */
public class Utils_DLCBitmapLoader {

	/**
	 * 从本地文件中加载一张图片，有可能返回null，文件大小超过了指定的宽度或高度将被压缩
	 * @param filePath	文件路径
	 * @param maxWidth	最大宽度
	 * @param maxHeight	最大高度
	 */
	public Bitmap loadBitmapFromFile(String filePath, int maxWidth, int maxHeight){
		BitmapFactory.Options option = new Options();
		option.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(filePath, option);
		if(option.outWidth > maxWidth || option.outHeight > maxHeight){
			int wScale = option.outWidth / maxWidth;
			int hScale = option.outHeight / maxHeight;
			option.inSampleSize = wScale > hScale ? wScale : hScale;
			android.util.Log.i("郑海鹏", "Utils_DLCBitmapLoader#loadBitmapFromFile(): " + "缩放倍数为：" + option.inSampleSize);
			if(option.inSampleSize < 1){
				option.inSampleSize = 1;
			}
		}
		option.inJustDecodeBounds = false;
		android.util.Log.i("郑海鹏", "Utils_DLCBitmapLoader#loadBitmapFromFile(): " + "从本地加载图片的路径为：" + filePath);
		bitmap = BitmapFactory.decodeFile(filePath, option);
		return bitmap;
	}
	
	/**
	 * @二级缓存 先从本地加载图片，如果没有，则从网络上加载。
	 * @本地保存 从网络加载的图片会保存到本地，本地文件名是网络地址的hashCode。
	 * @压缩图片	加载到内存中时，如果图片超过了指定的大小，压缩它
	 * @param url	网络地址
	 * @param cacheFolderPath	缓存的文件夹, eg: "sdcard/"
	 * @param maxWidth	最大宽度
	 * @param maxHeight	最大高度
	 */
	public Bitmap loadBitmap(String url, String cacheFolderPath, int maxWidth, int maxHeight){
		String suffix = ".jpg";
		if(url.contains(".png")){
			suffix = ".png";
		}
		android.util.Log.i("郑海鹏", "Utils_DLCBitmapLoader#loadBitmap(): " + "文件后缀名为" + suffix);
		int hashCode = url.hashCode();
		String filePath = cacheFolderPath + hashCode + suffix;
		Bitmap bitmap = loadBitmapFromFile(filePath, maxWidth, maxHeight);
		if(bitmap == null){
			boolean isSuccess = new Utils_Dlownload().download(url, cacheFolderPath, hashCode + suffix);
			if(isSuccess){
				// 如果从网上下载成功
				bitmap = loadBitmapFromFile(filePath, maxWidth, maxHeight);
			}else{
				// 从网上下载失败，返回null
				return null;
			}
		}
		return bitmap;
	}
	
}
