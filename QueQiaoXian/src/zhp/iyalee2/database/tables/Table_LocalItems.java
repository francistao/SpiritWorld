package zhp.iyalee2.database.tables;

import android.database.sqlite.SQLiteDatabase;
import zhp.android.database.ITable;

/**
 * 表-本地项目
 * @author 郑海鹏
 * @since 2015年9月15日
 */
public class Table_LocalItems implements ITable{
	public static final String TABLE_NAME = "table_localitems";
	public static final String USER_NAME = "userName";
	public static final String TARGET_PATH = "targetPath";
	public static final String PROFILE = "profile";
	public static final String TITLE = "title";
	public static final String CONTAIN_PATH = "containPath";
	public static final String CONTAIN_TYPE = "containType";
	public static final String CREATE_TIME = "createTime";

	@Override
	public void createTable(SQLiteDatabase db) {
		String sql = "create table " + TABLE_NAME + " ( "
//				+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ CREATE_TIME + " NVARCHAR, " 
				+ USER_NAME + " NVARCHAR, " 
				+ TARGET_PATH  + " NVARCHAR, "
				+ PROFILE  + " NVARCHAR, "
				+ TITLE  + " NVARCHAR, "
				+ CONTAIN_PATH + " NVARCHAR, "
				+ CONTAIN_TYPE + " NVARCHAR);";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
