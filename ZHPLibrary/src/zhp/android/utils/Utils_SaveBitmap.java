package zhp.android.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import zhp.java.listeners.OnFinishListener;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

/**
 * 用于位图保存的类
 */
public class Utils_SaveBitmap {
	
	public Utils_SaveBitmap(){
	}
	
	/**
	 * 保存一张位图, 文件格式是 *.jpg。
	 * @param filePath 文件名应该包含后缀名 .jpg。ps: sdcard/木质的旋律/a.jpg
	 */
	public boolean saveBitmapAsJpg(Bitmap bitmap, String filePath){
		zhp.android.debug.Debug.Log(this.getClass().getName() + "#saveBitmapAsJpg()", "filePath = " + filePath);
		File file = new File(filePath);
		
		try {
			// 创建目的文件所在的文件夹
			File folder = file.getParentFile();
			folder.mkdirs();
			// 创建目的文件
			file.createNewFile();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		FileOutputStream fos = null;	
		
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			zhp.android.debug.Debug.Log(this.getClass().getName(), "保存图片时出错！code 1");
			e.printStackTrace();
			return false;
		}
		
		bitmap.compress(CompressFormat.JPEG, 100, fos);
		
		try {
			fos.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
			zhp.android.debug.Debug.Log(this.getClass().getName(), "保存图片时出错！无法flush。code 2");
			return false;
		}
		
		try {			
			fos.close();
		} catch (IOException e) {
			zhp.android.debug.Debug.Log(this.getClass().getName(), "保存图片时出错！无法关闭fos。code 2");
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * 将一张bitmap缩小后保存，文件名为savePath。
	 * 如果原图的宽度（高度）本来就小于目的宽度（高度），直接保存到文件。
	 * @param srcBitmap	源位图
	 * @param dstWidth	目的宽度
	 * @param dstHeight	目的高度
	 * @param savePath 文件名应该包含后缀名 .jpg。ps: sdcard/木质的旋律/a.jpg
	 */
	public void saveBitmapAsJpg(Bitmap srcBitmap, int dstWidth, int dstHeight, String savePath,
			OnFinishListener listener) {
		// 例如：原图宽120， 高80，宽高比3：2
		// 新图宽50， 高40， 宽高比 5:4
		// 首先得把原图缩放，缩放比例如何确定呢？
		// 由于新图的宽高比 大于 旧图的宽高比
		// 说明旧图高了
		// 应该按照宽度来缩放。
		// scale = scaleWW;
		int srcWidth = srcBitmap.getWidth();
		int srcHeight = srcBitmap.getHeight();

		boolean isSaved = false;

		if (srcWidth < dstWidth || srcHeight < dstHeight) {
			// 如果原图本来就比较小， 不用缩放
			isSaved = saveBitmapAsJpg(srcBitmap, savePath);
		} else {
			// 如果原图较大才去缩放
			float srcWHScale = 1.0f * srcWidth / srcHeight;
			float dstWHScale = 1.0f * dstWidth / dstHeight;
			float scale = 1;

			if (dstWHScale > srcWHScale) {
				scale = 1.0f * dstWidth / srcWidth;
			} else {
				scale = 1.0f * dstHeight / srcHeight;
			}
			// 缩放之后的图像
			Bitmap scaleBitmap = Bitmap.createScaledBitmap(srcBitmap, (int) (srcWidth * scale),
					(int) (srcHeight * scale), false);
			// 保存缩放之后的图片
			isSaved = saveBitmapAsJpg(scaleBitmap, savePath);
		}

		// 保存完毕回调
		if (isSaved && listener != null) {
			listener.onFinish();
		}

	}
	
	/**
	 * 将一个地址对应的图片按新的大小保存在目的地址，文件名为savePath。
	 * @param srcBitmap	源位图
	 * @param dstWidth	目的宽度
	 * @param dstHeight	目的高度
	 * @param savePath 文件名应该包含后缀名 .jpg。ps: sdcard/木质的旋律/a.jpg
	 */
	public void saveFileAsJpg(String srcFilePath, int dstWidth, int dstHeight, String savePath, OnFinishListener listener){
		Bitmap srcBitmap = BitmapFactory.decodeFile(srcFilePath);
		saveBitmapAsJpg(srcBitmap, dstWidth, dstHeight, savePath, listener);
	}
	
}
