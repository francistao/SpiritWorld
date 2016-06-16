package zhp.iyalee2.utils;

import zhp.android.utils.Utils_File;
import zhp.iyalee2.aractivities.single.ARActivity_SingleModel;
import zhp.iyalee2.aractivities.single.ARActivity_SingleMovie;
import zhp.iyalee2.aractivities.single.ARActivity_SinglePicture;
import zhp.iyalee2.beans.ItemValue;
import android.app.Activity;
import android.content.Intent;

/**
 *	用于初始化SingleItem
 * @author 郑海鹏
 * @since 2015年9月7日
 */
public class Utils_OpenSingleItem {
	
	/**
	 * 打开一个增强内容为图片的ARActivity
	 * @param context	
	 * @param targetPath	目标图片
	 * @param containPath	内容图片
	 */
	public static void openPictureActivity(Activity context, String targetPath, String containPath){
		Utils_File.getInstance().copyToAssets(context, containPath, "single/containPicture.jpg");
		Utils_File.getInstance().copyToAssets(context, targetPath, "single/target.jpg");
		Intent intent = new Intent(context, ARActivity_SinglePicture.class);
		context.startActivity(intent);
	}
	
	/**
	 * 打开一个增强内容为视频的ARActivity
	 * @param context	
	 * @param targetPath	目标图片
	 * @param containPath	内容视频
	 */
	private static void openMovieActivity(Activity context, String targetPath, String containPath){
		Utils_File.getInstance().copyToAssets(context, containPath, "single/containMovie.3g2");
		Utils_File.getInstance().copyToAssets(context, targetPath, "single/target.jpg");
		Intent intent = new Intent(context, ARActivity_SingleMovie.class);
		context.startActivity(intent);
	}
	
	/**
	 * 打开一个增强内容为模型的ARActivity
	 * @param context	
	 * @param targetPath	目标图片
	 * @param containPath	内容模型
	 */
	private static void openModelActivity(Activity context, String targetPath, String containPath){
		Utils_File.getInstance().copyToAssets(context, containPath, "single/containModel.zip");
		Utils_File.getInstance().copyToAssets(context, targetPath, "single/target.jpg");
		Intent intent = new Intent(context, ARActivity_SingleModel.class);
		context.startActivity(intent);
	}
	
	/**
	 * 打开新的Activity。
	 */
//	private static void startActivity(Activity context, String targetPath){
//		Utils_File.getInstance().copyToAssets(context, targetPath, "single/target.jpg");
//		Intent intent = new Intent(context, ARActivity_SinglePicture.class);
//		context.startActivity(intent);
//	}
	
	public static void startSingleItem(Activity activity, ItemValue item){
		android.util.Log.i("郑海鹏", "Utils_OpenSingleItem#startSingleItem(): " + "标题：" + item.getTitle());
		android.util.Log.i("郑海鹏", "Utils_OpenSingleItem#startSingleItem(): " + "简介：" + item.getProfile());
		android.util.Log.i("郑海鹏", "Utils_OpenSingleItem#startSingleItem(): " + "目标地址：" + item.getTargetPath());
		android.util.Log.i("郑海鹏", "Utils_OpenSingleItem#startSingleItem(): " + "内容地址：" + item.getContainPath());
		android.util.Log.i("郑海鹏", "Utils_OpenSingleItem#startSingleItem(): " + "类型：" + item.getType());
		switch(item.getType()){
		case Picture:
			android.util.Log.i("郑海鹏", "Utils_OpenSingleItem#startSingleItem(): " + "打开图片增强视图");
			openPictureActivity(activity, item.getTargetPath(), item.getContainPath());
			break;
			
		case Model:
			android.util.Log.i("郑海鹏", "Utils_OpenSingleItem#startSingleItem(): " + "打开模型增强视图");
			openModelActivity(activity, item.getTargetPath(), item.getContainPath());
			break;
			
		case Video:
			android.util.Log.i("郑海鹏", "Utils_OpenSingleItem#startSingleItem(): " + "打开视频增强视图");
			openMovieActivity(activity, item.getTargetPath(), item.getContainPath());
			break;
		}
	}
	
}
