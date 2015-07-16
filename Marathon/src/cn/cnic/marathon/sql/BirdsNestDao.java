package cn.cnic.marathon.sql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;

public class BirdsNestDao {
	public static final String TYPE_LINE = "1";
	public static final String TYPE_POINT = "2";
	public static final String TYPE_PARKING = "3";
	public static final String BUS = "4";
	public static final String SUBWAY = "5";
	private DBManager manager;

	public BirdsNestDao(Context context) {
		manager = DBManager.getInstance(context);
	}

	/**
	 * 读取所有calendar列表
	 * 
	 * @return
	 */
	public List<Map<String, String>> getAllData() {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		Cursor cursor = manager.queryBySql("select id as _id,* from "
				+ DBManager.BIRDSNEST_DAO, null);
		Map<String, String> row;
		for (cursor.moveToFirst(); !cursor.isLast(); cursor.moveToNext()) {
			 row = new HashMap<String, String>();
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String path = cursor.getString(cursor.getColumnIndex("path"));
			String type = cursor.getString(cursor.getColumnIndex("type"));
			row.put("name", name);
			row.put("path", path);
			row.put("type", type);
			result.add(row);
		}
		return result;
	}
}
