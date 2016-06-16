package zhp.iyalee2.database;

import java.util.ArrayList;

import zhp.iyalee2.beans.ItemValue;
import zhp.iyalee2.beans.ItemValue.ContainType;
import zhp.iyalee2.database.tables.Table_FriendsItems;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class DBOperation_FriendsItems extends DBOperation{
	// =================================================
	// 构造方法
	// =================================================
	public DBOperation_FriendsItems(Context context) {
		super(context);
	}

	// =================================================
	// 对数据库进行操作的方法
	// =================================================
	/**
	 * 获得数据库中的所有项目
	 */
	public ArrayList<ItemValue> getItems(String userName){
		ArrayList<ItemValue> result = new ArrayList<ItemValue>();
		
		final String sql = "select * from "+ Table_FriendsItems.TABLE_NAME + " where " + Table_FriendsItems.USER_NAME + " = \"" + userName + "\";";
		String targetPath = "";
		String profile = "";
		String title = "";
		String containPath = "";
		ContainType type = null;
		String createTime = "";
		
		Cursor c = db.rawQuery(sql, null);
		if(c != null){
			if(c.moveToFirst()){
				do {
					targetPath = c.getString(c.getColumnIndex(Table_FriendsItems.TARGET_PATH));
					profile = c.getString(c.getColumnIndex(Table_FriendsItems.PROFILE));
					title = c.getString(c.getColumnIndex(Table_FriendsItems.TITLE));
					containPath = c.getString(c.getColumnIndex(Table_FriendsItems.CONTAIN_PATH));
					String strType = c.getString(c.getColumnIndex(Table_FriendsItems.CONTAIN_TYPE));
					type = ContainType.toContainType(strType);
					createTime = c.getString(c.getColumnIndex(Table_FriendsItems.CREATE_TIME));
					result.add(new ItemValue(targetPath, title, profile, containPath, type, createTime));
				} while (c.moveToNext());
			}
		}
		return result;
	}
	
	public void addItem(String userName, ItemValue data){
		// 添加进数据库
		ContentValues values = new ContentValues();
		values.put(Table_FriendsItems.TARGET_PATH, data.getTargetPath());
		values.put(Table_FriendsItems.PROFILE, data.getProfile());
		values.put(Table_FriendsItems.TITLE, data.getTitle());		
		values.put(Table_FriendsItems.CONTAIN_PATH, data.getContainPath());
		values.put(Table_FriendsItems.CONTAIN_TYPE, data.getType().toString());
		values.put(Table_FriendsItems.USER_NAME, userName);
		values.put(Table_FriendsItems.CREATE_TIME, data.getCreateTime());
		try {
			db.insert(Table_FriendsItems.TABLE_NAME, null, values);
		} catch (Exception e) {
			zhp.android.debug.Debug.Log(this.getClass().getName() + "#addItem()", "添加数据异常！");
		}
	}
	
	/**
	 * 根据笔记的id删除笔记
	 */
	public void deleteItem(String userName, String createTime) {
		String deleteSql = "delete from " + Table_FriendsItems.TABLE_NAME + " where " + Table_FriendsItems.CREATE_TIME
				+ " = \"" + createTime + "\" and " + Table_FriendsItems.USER_NAME + " = \"" + userName + "\";";
		db.execSQL(deleteSql);
	}
}
