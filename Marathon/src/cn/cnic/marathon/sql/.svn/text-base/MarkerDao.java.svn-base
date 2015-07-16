package cn.cnic.marathon.sql;

import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class MarkerDao {
	private DBManager manager;

	public MarkerDao(Context context) {
		manager = DBManager.getInstance(context);
	}

	public void getAllMarkers() {
	}

	ContentValues addMarkSql(String name, String descripting, float lon,
			float lat, int type, String create_at) {
		ContentValues values = new ContentValues();
		values.put("name", name);
		values.put("descripting", descripting);
		values.put("lon", lon);
		values.put("lat", lat);
		values.put("type", type);
		values.put("create_at", create_at);
		values.put("del_flag", 0);
		return values;
	}

	public void AddMarker(String name, String description, float lon,
			float lat, int type, String create_at) {
		ContentValues values = addMarkSql(name, description, lon, lat, type,
				create_at);
		manager.insert(DBManager.MAP_MARK_DAO, "name", values);
	}

	/**
	 * 取最新时间
	 * 
	 * @return
	 */
	public String getLastestTime() {
		String time = "";
		String sql = "select create_at from mark order by create_at desc limit 1";
		Cursor cursor = manager.queryBySql(sql, null);
		;
		if (cursor.moveToFirst()) {
			time = cursor.getString(cursor.getColumnIndex("create_at"));
		}
		return time;
	}

	public void AddMarkers(List<Map<String, Object>> marks) {
		for (Map<String, Object> mark : marks) {
			String name = mark.get("name").toString();
			String description = mark.get("name").toString();
			float lon = Float.valueOf(mark.get("lon").toString());
			float lat = Float.valueOf(mark.get("lat").toString());
			int type = Integer.valueOf(mark.get("type").toString());
			String createAt = mark.get("create_at").toString();
			AddMarker(name, description, lon, lat, type, createAt);
		}
	}
}
