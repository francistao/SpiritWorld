package zhp.iyalee2.database;

import java.util.ArrayList;

import zhp.android.database.ITable;
import zhp.iyalee2.database.tables.Table_Friends;
import zhp.iyalee2.database.tables.Table_FriendsItems;
import zhp.iyalee2.database.tables.Table_LocalItems;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper{
	// =================================================
	// 常量
	// =================================================
	static final String DATABASE_NAME = "arworld.db";
	static final int VERSION = 1;
	
	// =================================================
	// 变量
	// =================================================
	ArrayList<ITable> tables = new ArrayList<ITable>();	
	
	// =================================================
	// 构造方法
	// =================================================
	public MySQLiteOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
	}

	// =================================================
	// 继承的方法
	// =================================================
	/**
	 * 这个方法只有在第一次创建数据库的时候才会执行。
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// 在这里添加所有的表
		tables.add(new Table_LocalItems());
		tables.add(new Table_FriendsItems());
		tables.add(new Table_Friends());
		
		try {
			int tableNum = tables.size();
			for(int i = 0; i < tableNum; i++){
				tables.get(i).createTable(db);
			}
		} catch (Exception e) {
			zhp.android.debug.Debug.Log(this.getClass().getName(), "创建数据库异常！");
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
