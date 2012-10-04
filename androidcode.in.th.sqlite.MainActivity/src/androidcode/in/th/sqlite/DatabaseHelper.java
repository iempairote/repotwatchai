package androidcode.in.th.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{

	private static final String DB_NAME = "memberDB";
	private static final int DB_VERSION = 1;
	
	//คำสั่งในการสร้าง ตารางสำหรับเก็บข้อมูล
	private static final String DB_CREATE = "" +
			"CREATE TABLE member (" +
			"id INTEGER PRIMARY KEY, " +
			"name TEXT NOT NULL, " +
			"surname TEXT NOT NULL, " +
			"age INTEGER NOT NULL);";
	
	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DB_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//พิมพ์ Log เพื่อให้เห็นว่ามีการ Upgrade Database
		Log.w(DatabaseHelper.class.getName(),
				"Upgread database version from version" + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		
		//ลบตาราง member ของเก่าทิ้ง
		db.execSQL("DROP TABLE IF EXISTS member");
		onCreate(db);
	}
}
