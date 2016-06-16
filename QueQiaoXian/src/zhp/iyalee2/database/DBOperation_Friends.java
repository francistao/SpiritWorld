package zhp.iyalee2.database;

import java.util.ArrayList;

import zhp.android.data.CurrentUser;
import zhp.iyalee2.beans.ItemFriend;
import zhp.iyalee2.database.tables.Table_Friends;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class DBOperation_Friends extends DBOperation{
	// =================================================
	// 构造方法
	// =================================================
	public DBOperation_Friends(Context context) {
		super(context);
	}

	// =================================================
	// 对数据库进行操作的方法
	// =================================================
	/**
	 * 获得数据库中的所有项目
	 */
	public ArrayList<ItemFriend> getItems(){
		ArrayList<ItemFriend> result = new ArrayList<ItemFriend>();
		
		final String sql = "select * from "+ Table_Friends.TABLE_NAME + " where userName = " + CurrentUser.getUserName();
		String friendName = "";
		String iconPath = "";
		String profile = "";
		
		Cursor c = db.rawQuery(sql, null);
		if(c != null){
			if(c.moveToFirst()){
				do {
					friendName = c.getString(c.getColumnIndex(Table_Friends.FRIEND_NAME));
					profile = c.getString(c.getColumnIndex(Table_Friends.PROFILE));
					iconPath = c.getString(c.getColumnIndex(Table_Friends.ICON_PATH));
					result.add(new ItemFriend(iconPath, friendName, profile));
				} while (c.moveToNext());
			}
		}
		return result;
	}
	
	public void addFriend(ItemFriend data){
		// 获得用户名
		String userName = "null";
		if(CurrentUser.isLogin){
			// 如果登录了，用户名为已登录的用户名。
			userName = new String(CurrentUser.getUserName());
		}
		
		// 添加进数据库
		ContentValues values = new ContentValues();
		values.put(Table_Friends.FRIEND_NAME, data.getUserName());
		values.put(Table_Friends.PROFILE, data.getProfile());
		values.put(Table_Friends.ICON_PATH, data.getIconPath());		
		values.put(Table_Friends.USER_NAME, userName);		
		try {
			db.insert(Table_Friends.TABLE_NAME, null, values);
		} catch (Exception e) {
			zhp.android.debug.Debug.Log(this.getClass().getName() + "#addItem()", "添加数据异常！");
		}
	}
	
	/**
	 * 根据笔记的id删除笔记
	 */
	public void deleteItem(String friendName) {
		// 获得用户名
		if(!CurrentUser.isLogin){
			return;
		};
		
		String deleteSql = "delete from " + Table_Friends.TABLE_NAME + " where " + Table_Friends.USER_NAME
				+ " = \"" + CurrentUser.getUserName() + "\" and " + Table_Friends.FRIEND_NAME + " = \" " + friendName + "\" ;";
		db.execSQL(deleteSql);
	}
}
