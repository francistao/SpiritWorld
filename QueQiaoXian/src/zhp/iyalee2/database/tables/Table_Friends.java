package zhp.iyalee2.database.tables;

import android.database.sqlite.SQLiteDatabase;
import zhp.android.database.ITable;

/**
 * 表-好友列表
 * @author 郑海鹏
 * @since 2015年9月15日
 */
public class Table_Friends implements ITable{
	public static final String TABLE_NAME = "table_friends";
	public static final String USER_NAME = "userName";
	public static final String FRIEND_NAME = "friendName";
	public static final String ICON_PATH = "iconPath";
	public static final String PROFILE = "profile";

	@Override
	public void createTable(SQLiteDatabase db) {
		String sql = "create table " + TABLE_NAME + " ( "
				+ USER_NAME + " NVARCHAR, " 
				+ FRIEND_NAME  + " NVARCHAR, "
				+ PROFILE  + " NVARCHAR, "
				+ ICON_PATH + " NVARCHAR);";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
