package zhp.android.database;

import android.database.sqlite.SQLiteDatabase;

public interface ITable {
	
	/**
	 * 创建本表
	 * @param db， 把本表加入到哪个数据库
	 */
	public void createTable(SQLiteDatabase db);
	
	/** 
     * 升级数据库的代码 
     * */  
    void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);
}
