package cn.cnic.marathon.sql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

public class UserDao {

	private static UserDao dao;
	private DBOpenHelper dbOpenHelper;
	private SQLiteDatabase database;

	// private Context context;

	public UserDao(Context context) {
		// this.context = context;
		dbOpenHelper = new DBOpenHelper(context);
	}

	public static UserDao getInstance(Context context) {
		if (dao == null) {
			dao = new UserDao(context);
		} else {
			if (dao.database != null && dao.database.inTransaction()
					&& dao.database.inTransaction()) {
				return null;
			}
		}
		return dao;
	}

	public void insert(String userid, String password) {
		// Log.d("user", "-------"+userid + password);

		if (TextUtils.isEmpty(password))
			return;
		database = dbOpenHelper.getWritableDatabase();
		database.beginTransaction();
		database.execSQL("insert into " + DBOpenHelper.USER_DAO
				+ "(userid,password) values(?,?)", new Object[] { userid,
				password });
		database.setTransactionSuccessful();
		database.endTransaction();
	}

	// public Cursor query(String userid,String password){
	//
	// database = dbOpenHelper.getWritableDatabase();
	// database.beginTransaction();
	//
	// Cursor cursor;
	// Log.e("SQLite", "----queryByUserId----");
	// cursor =
	// database.rawQuery("select * from  user_dao  where Userid = ? and Password = ?",
	// new String[] { userid },new String[]{password});
	// cursor.moveToNext();
	// return cursor;
	//
	// }

}
