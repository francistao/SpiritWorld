package zhp.iyalee2.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;

import zhp.iyalee2.beans.ItemValue;

public class Utils_General {
	
	/**
	 * 用户名转为ASCII码
	 */
	public String toAscii(String userName){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < userName.length(); i++){
			if(userName.charAt(i) < 255){
				sb.append(userName.charAt(i));
			}else{
				sb.append((int)userName.charAt(i));
			}
		}
		return sb.toString();
	}
	
	
	/**
	 * 根据文件名获得文件类型
	 */
	public String getSuffix(String filePath) {
		// 判断文件类型
		int pointIndex = filePath.lastIndexOf(".");
		String fileType = "jpg";
		if (-1 != pointIndex) {
			fileType = filePath.substring(filePath.lastIndexOf(".") + 1);
			fileType = fileType.toLowerCase(Locale.ENGLISH);
		}
		return fileType;
	}
	
	/**
	 * 打印一个集合的对象
	 * @param c
	 */
	public void printCollection(Collection<? extends Object> c){
		Iterator<?> it = c.iterator();
		while (it.hasNext()) {
			zhp.android.debug.Debug.Log(this.getClass().getName() + "#printCollection()\n", it.next().toString());	
		}
	}
	
	/**
	 * 打印一个ItemValue的对象
	 * @param item
	 */
	public void printItemValue(String where, ItemValue item){
		zhp.android.debug.Debug.Log(where + "\n", "");
		zhp.android.debug.Debug.Log("createTime:\n", item.getCreateTime());
		zhp.android.debug.Debug.Log("targetPath:\n", item.getTargetPath());
		zhp.android.debug.Debug.Log("containPath:\n", item.getContainPath());
		zhp.android.debug.Debug.Log("title:\n", item.getTitle());
		zhp.android.debug.Debug.Log("profile:\n", item.getProfile());
		zhp.android.debug.Debug.Log("type:\n", item.getType());
		zhp.android.debug.Debug.Log(where + "\n", "");
	}

//	/**
//	 * 用于在创建新的Item时保存文件
//	 */
//	public void saveFile(String filePath){
//		final String targetSavePath = FinalValue.FOLDER_BASE_PATH + "arworld/localItem/" + System.currentTimeMillis() + ".jpg";
//		new Utils_SaveBitmap().saveFileAsJpg(filePath, 200, 200, targetSavePath, new OnFinishListener() {
//			
//			@Override
//			public void onFinish() {
//				Toast.makeText(getApplication(), targetSavePath, Toast.LENGTH_LONG).show();
//				Intent intent = new Intent(Activity_NewItem_Target.this, Activity_NewItem_Contain.class);
//				intent.putExtra("targetPath", targetSavePath);
//				startActivity(intent);
//				finish();
//			}
//		});
//	}
}
