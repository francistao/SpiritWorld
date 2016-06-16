package zhp.android.utils;

import zhp.android.utils.Utils_DLCBitmapLoader;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * 图像三级缓存
 * @author 郑海鹏
 * @since 2015年9月6日
 */
public class Utils_TLCBitmapLoader {

	/**
	 * 从网络中获取数据。如果加载成功，将会把数据保存在本地。加载进内存之后，还会把图像加载到缓存（默认的）中。
	 * 如果缓存中有数据，就不再去本地或者网络上获取数据了。
	 * 如果本地有数据，也不会再从网络上获取数据了。
	 * @param url	图片的地址
	 * @param cacheFolderPath	本地缓存的地址, 如果为null，默认将使用"sdcard/mzdxl/cache/img/"
	 * @param maxWidth	加载的最大宽度，大于此宽度将被压缩
	 * @param maxHeight	加载的最大高度，大于此高度将被压缩
	 * @return	如果加载成功返回bitmap，否则返回null
	 */
	public Bitmap loadBitmapByUrl(String url, String cacheFolderPath, int maxWidth, int maxHeight){
		return loadBitmapByUrl(Utils_BitmapLruCache.getInstance(), url, cacheFolderPath, maxWidth, maxHeight);
	}
	
	/**
	 * 从本地加载数据，成功后会把图像保存到缓存中。
	 * 如果缓存中有数据，就不再去本地获取数据了。
	 * @param filePath	本地文件路径
	 * @param maxWidth	加载的最大宽度，大于此宽度将被压缩
	 * @param maxHeight	加载的最大高度，大于此高度将被压缩
	 * @return	如果加载成功返回bitmap，否则返回null
	 */
	public Bitmap loadBitmapLocal(String filePath, int maxWidth, int maxHeight){
		return loadBitmapLocal(Utils_BitmapLruCache.getInstance(), filePath, maxWidth, maxHeight);
	}
	
	/**
	 * 从网络中获取数据。如果加载成功，将会把数据保存在本地。加载进内存之后，还会把图像加载到指定的缓存中。
	 * 如果缓存中有数据，就不再去本地或者网络上获取数据了。
	 * 如果本地有数据，也不会再从网络上获取数据了。
	 * @param url	图片的地址
	 * @param cacheFolderPath	本地缓存的地址, 如果为null，默认将使用"sdcard/mzdxl/cache/img/"
	 * @param maxWidth	加载的最大宽度，大于此宽度将被压缩
	 * @param maxHeight	加载的最大高度，大于此高度将被压缩
	 * @return	如果加载成功返回bitmap，否则返回null
	 */
	public Bitmap loadBitmapByUrl(LruCache<String, Bitmap> cache, String url, String cacheFolderPath, int maxWidth, int maxHeight){
		if(cache == null){
			cache = Utils_BitmapLruCache.getInstance();
		}
		// 先从缓存中取
		Bitmap bitmap = cache.get(url);
		if(bitmap == null){
			android.util.Log.i("郑海鹏", "Utils_TLCBitmapLoader#loadBitmapByUrl(): " + "缓存中不存在对应图片。");
			// 如果为null，再从本地文件或网络中取。
			if(cacheFolderPath == null){
				android.util.Log.i("郑海鹏", "Utils_TLCBitmapLoader#loadBitmapByUrl(): " + "使用默认路径");
				cacheFolderPath = "sdcard/mzdxl/cache/img/";
			}
			bitmap = new Utils_DLCBitmapLoader().loadBitmap(url, cacheFolderPath, maxWidth, maxHeight);
			if(bitmap == null){
				return null;
			}else{
				cache.put(url, bitmap);
				return bitmap;
			}
		}else{
			return bitmap;
		}
	}
	
	/**
	 * 从本地加载数据，成功后会把图像保存到指定缓存中。
	 * 如果缓存中有数据，就不再去本地获取数据了。
	 * @param filePath	本地文件路径
	 * @param maxWidth	加载的最大宽度，大于此宽度将被压缩
	 * @param maxHeight	加载的最大高度，大于此高度将被压缩
	 * @return	如果加载成功返回bitmap，否则返回null
	 */
	public Bitmap loadBitmapLocal(LruCache<String, Bitmap> cache, String filePath, int maxWidth, int maxHeight){
		if(cache == null){
			cache = Utils_BitmapLruCache.getInstance();
		}
		// 先从缓存中取。
		Bitmap bitmap = Utils_BitmapLruCache.getInstance().get(filePath);
		if(null == bitmap){
			// 缓存中没有
			bitmap = new Utils_DLCBitmapLoader().loadBitmapFromFile(filePath, maxWidth, maxHeight);
			if(bitmap != null){
				cache.put(filePath, bitmap);
			}
		}
		return bitmap;
	}
}
