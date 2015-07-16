package cn.cnic.marathon.sql;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;
import cn.cnic.marathon.R;

public class DBManager {

	private final int BUFFER_SIZE = 400000;
	public static final int DB_VERSION = 1;
	public static final String SCHEDU_DAO = "schedule";// 日程安排
	public static final String MAP_MARK_DAO = "mark";// 地图标注
	public static final String PLAYER_DAO = "player";// 运动员
	public static final String TEAM_DAO = "teams";// 运动员
	public static final String CHEER_DAO = "cheer";// 拉拉隊
	public static final String PARTNER_DAO = "partner";// 合作伙伴
	public static final String USER_DAO = "user";// 用戶
	public static final String POSITION_DAO = "position";// 用戶
	public static final String FRIEND_DAO = "friends";// 好友
	public static final String CALENDAR_DAO = "calendar";// 好友
	public static final String EMAPTH_DAO = "empath";// 好友
	public static final String BIRDSNEST_DAO = "birdsnest";// 鸟巢结构
	public static final String DB_NAME = "marathon.db"; // 保存的数据库文件名
	public static final String PACKAGE_NAME = "cn.cnic.marathon";// 包名
	public static final String DB_PATH = "/data"
			+ Environment.getDataDirectory().getAbsolutePath() + "/"
			+ PACKAGE_NAME; // 在手机里存放数据库的位置
	private static SQLiteDatabase db;
	private static DBManager manager;
	private Context context;

	private DBManager(Context context) {
		this.context = context;
		openDatabase();
	}

	public static synchronized DBManager getInstance(Context context) {
		if (manager == null)
			manager = new DBManager(context);
		return manager;
	}

	public void openDatabase() {
		if (db == null)
			db = this.openDatabase(DB_PATH + "/" + DB_NAME);
		Log.d("DATA", DB_PATH + "/" + DB_NAME);
	}

	private SQLiteDatabase openDatabase(String dbfile) {
		try {
			if (!(new File(dbfile).exists())) {// 判断数据库文件是否存在，若不存在则执行导入，否则直接打开数据库
				InputStream is = this.context.getResources().openRawResource(
						R.raw.marathon); // 欲导入的数据库
				FileOutputStream fos = new FileOutputStream(dbfile);
				byte[] buffer = new byte[BUFFER_SIZE];
				int count = 0;
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);
				}
				fos.close();
				is.close();
			}
			SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile,
					null);
			return db;
		} catch (FileNotFoundException e) {
			Log.e("Database", "File not found");
			e.printStackTrace();
		} catch (IOException e) {
			Log.e("Database", "IO exception");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 实现对数据库的添加、删除和修改功能
	 * 
	 * @param sql
	 * @param bindArgs
	 * @return
	 */
	public synchronized boolean updateBySQL(String sql, Object[] bindArgs) {
		boolean flag = false;
		Log.d("SQL", sql);
		try {
			db.beginTransaction();
			db.execSQL(sql, bindArgs);
			db.setTransactionSuccessful();
			Log.i("DATA", "更新成功");
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}
		return flag;
	}
	
	public synchronized void delete(String table,String  whereClause, String[] whereArgs){
		db.delete(table, whereClause, whereArgs);
	}

	/**
	 * 更新多条数据
	 * 
	 * @param sql
	 * @param bindArgs
	 * @return
	 */
	public synchronized boolean updateMultilBySQL(String[] sqls,
			Object[][] bindArgs) {
		boolean flag = false;
		try {
			db.beginTransaction();
			for (int i = 0; i < sqls.length; i++)
				db.execSQL(sqls[i], bindArgs[i]);
			db.setTransactionSuccessful();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}
		return flag;
	}

	/**
	 * 
	 * @param sql
	 * @param selectionArgs
	 * @return
	 */
	public Map<String, String> queryBySQL(String sql, String[] selectionArgs) {
		Map<String, String> map = new HashMap<String, String>();
		Cursor cursor = db.rawQuery(sql, selectionArgs);
		int cols_len = cursor.getColumnCount();
		while (cursor.moveToNext()) {
			for (int i = 0; i < cols_len; i++) {
				String cols_name = cursor.getColumnName(i);
				String cols_value = cursor.getString(cursor
						.getColumnIndex(cols_name));
				if (cols_value == null) {
					cols_value = "";
				}
				map.put(cols_name, cols_value);
			}
		}
		cursor.close();
		return map;
	}

	/**
	 * @param sql
	 * @param selectionArgs
	 * @return
	 */
	public List<Map<String, Object>> queryMultiMaps(String sql,
			String[] selectionArgs) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Cursor cursor = db.rawQuery(sql, selectionArgs);
		int cols_len = cursor.getColumnCount();
		while (cursor.moveToNext()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < cols_len; i++) {
				String cols_name = cursor.getColumnName(i);
				String cols_value = cursor.getString(cursor
						.getColumnIndex(cols_name));
				if (cols_value == null) {
					cols_value = "";
				}
				map.put(cols_name, cols_value);
			}
			list.add(map);
		}
		cursor.close();
		return list;
	}

	public List<Map<String, Object>> queryMultiMap(String sql,
			String[] selectionArgs) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Cursor cursor = db.rawQuery(sql, selectionArgs);
		int cols_len = cursor.getColumnCount();
		while (cursor.moveToNext()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < cols_len; i++) {
				String cols_name = cursor.getColumnName(i);
				String cols_value = cursor.getString(cursor
						.getColumnIndex(cols_name));
				if (cols_value == null) {
					cols_value = "";
				}
				map.put(cols_name, cols_value);
			}
			list.add(map);
		}
		cursor.close();
		return list;
	}

	/**
	 * 返回表中所有数据
	 * 
	 * @param table
	 * @return
	 */
	public Cursor queryAllDataByTableName(String table, String[] columns,
			String selection, String[] selectionArgs, String groupBy,
			String having, String orderBy) {
		db.beginTransaction();
		Cursor cursor = null;
		try {
			cursor = db.query(table, columns, selection, selectionArgs,
					groupBy, having, orderBy);
			db.setTransactionSuccessful();
			Log.i("DATA", "查询成功");
		} catch (Exception e) {
			db.endTransaction();
		} finally {
			db.endTransaction();
		}
		return cursor;
	}

	public Cursor queryBySql(String sql, String[] selectionArgs) {
		db.beginTransaction();
		Cursor cursor = null;
		try {
			cursor = db.rawQuery(sql, selectionArgs);
			db.setTransactionSuccessful();
			Log.i("DATA", "查询成功");
		} catch (Exception e) {
			db.endTransaction();
		} finally {
			db.endTransaction();
		}
		return cursor;
	}

	/**
	 * 插入数据
	 * 
	 * @param table
	 * @param nullColumnHack
	 * @param values
	 * @return
	 */
	public long insert(String table, String nullColumnHack, ContentValues values) {
		long r = 0;
		db.beginTransaction();
		try {
			r = db.insert(table, nullColumnHack, values);
			db.setTransactionSuccessful();
		} catch (Exception e) {
			db.endTransaction();
		} finally {
			db.endTransaction();
		}
		Log.i("DATA", r == 0 ? "新增失败" : "新增成功");
		return r;
	}

	public void insertMulti(String table, String nullColumnHack,
			ContentValues[] values) {
		db.beginTransaction();
		try {
			for (ContentValues vs : values)
				db.insert(table, nullColumnHack, vs);
			db.setTransactionSuccessful();
		} catch (Exception e) {
			Log.i("DATA", "新增失败");
		} finally {
			db.endTransaction();
		}
	}
}
