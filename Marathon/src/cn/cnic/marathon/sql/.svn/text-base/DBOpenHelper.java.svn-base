package cn.cnic.marathon.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
	public static final String DBName = "marathon.db";
	public static final int DB_VERSION = 1;
	public static final String SCHEDU_DAO = "schedule";// 日程安排
	public static final String MAP_MARK_DAO = "mark";// 地图标注
	public static final String PLAYER_DAO = "runner";// 运动员
	public static final String CHEER_DAO = "cheer";// 拉拉隊
	public static final String PARTNER_DAO = "partner";// 合作伙伴
	public static final String USER_DAO = "user";// 用戶
	public static final String POSITION_DAO = "position";// 用戶
	Context context;

	public DBOpenHelper(Context context) {
		super(context, DBName, null, DB_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}

}
