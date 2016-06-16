package zhp.iyalee2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBOperation {
	
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
	public DBOperation(Context context) {
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
	
}
