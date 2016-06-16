package zhp.iyalee2.database;

import java.util.ArrayList;

import zhp.android.data.CurrentUser;
import zhp.iyalee2.beans.ItemValue;
import zhp.iyalee2.beans.ItemValue.ContainType;
import zhp.iyalee2.database.tables.Table_LocalItems;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBOperation_LocalItem {
	
	// =================================================
	// 常量
	// =================================================
	final Context context;
	
	// =================================================
	// 变量
	// =================================================
	SQLiteDatabase db;
	MySQLiteOpenHelper helper;

	// =================================================
	// 构造方法
	// =================================================
	public DBOperation_LocalItem(Context context) {
		this.context = context;
		helper = new MySQLiteOpenHelper(context);
	}
	
	// =================================================
	// 方法
	// =================================================
	public void openDataBase(){
		db = helper.getWritableDatabase();
	}
	
	public void closeDataBase(){
		helper.close();
	}
	
	// =================================================
	// 对数据库进行操作的方法
	// =================================================
	/**
	 * 获得数据库中的所有项目
	 */
	public ArrayList<ItemValue> getItems(){
		ArrayList<ItemValue> result = new ArrayList<ItemValue>();
		
		final String sql = "select * from "+ Table_LocalItems.TABLE_NAME;
		String targetPath = "";
		String profile = "";
		String title = "";
		String containPath = "";
		ContainType type = null;
		String createTime = "";
//		int id;
		
		Cursor c = db.rawQuery(sql, null);
		if(c != null){
			if(c.moveToFirst()){
				do {
					targetPath = c.getString(c.getColumnIndex(Table_LocalItems.TARGET_PATH));
					profile = c.getString(c.getColumnIndex(Table_LocalItems.PROFILE));
					title = c.getString(c.getColumnIndex(Table_LocalItems.TITLE));
					containPath = c.getString(c.getColumnIndex(Table_LocalItems.CONTAIN_PATH));
					String strType = c.getString(c.getColumnIndex(Table_LocalItems.CONTAIN_TYPE));
					type = ContainType.toContainType(strType);
					createTime = c.getString(c.getColumnIndex(Table_LocalItems.CREATE_TIME));
					result.add(new ItemValue(targetPath, title, profile, containPath, type, createTime));
				} while (c.moveToNext());
			}
		}
		return result;
	}
	
	public void addItem(ItemValue data){
		// 获得用户名
		String userName = "null";
		if(CurrentUser.isLogin){
			// 如果登录了，用户名为已登录的用户名。
			userName = new String(CurrentUser.getUserName());
		}
		
		// 添加进数据库
		ContentValues values = new ContentValues();
		values.put(Table_LocalItems.TARGET_PATH, data.getTargetPath());
		values.put(Table_LocalItems.PROFILE, data.getProfile());
		values.put(Table_LocalItems.TITLE, data.getTitle());		
		values.put(Table_LocalItems.CONTAIN_PATH, data.getContainPath());
		values.put(Table_LocalItems.CONTAIN_TYPE, data.getType().toString());
		values.put(Table_LocalItems.USER_NAME, userName);
		values.put(Table_LocalItems.CREATE_TIME, data.getCreateTime());
		try {
			db.insert(Table_LocalItems.TABLE_NAME, null, values);
		} catch (Exception e) {
			zhp.android.debug.Debug.Log(this.getClass().getName() + "#addNote()", "添加数据异常！");
		}
	}
	
	/**
	 * 根据笔记的id删除笔记
	 */
	public void deleteItem(String createTime) {
		String deleteSql = "delete from " + Table_LocalItems.TABLE_NAME + " where " + Table_LocalItems.CREATE_TIME
				+ " = \"" + createTime + "\";";
		db.execSQL(deleteSql);
	}
}
