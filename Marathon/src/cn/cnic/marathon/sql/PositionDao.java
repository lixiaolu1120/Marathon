package cn.cnic.marathon.sql;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import cn.cnic.marathon.util.Utils;

public class PositionDao {
	private DBManager manager;

	public PositionDao(Context context) {
		manager = DBManager.getInstance(context);
	}

	/**
	 * 读取所有好友列表
	 * 
	 * @return
	 */
	public ArrayList<HashMap<String, Object>> getAllUnUploadPos() {
		Cursor cursor = manager.queryBySql("select id as _id,* from "
				+ DBManager.POSITION_DAO + " where uploaded = 0", null);
		ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
		for (cursor.moveToFirst(); cursor.moveToNext();) {
			HashMap<String, Object> row = new HashMap<String, Object>();
			row.put("id", cursor.getFloat(cursor.getColumnIndex("id")));
			row.put("lon", cursor.getFloat(cursor.getColumnIndex("lon")));
			row.put("lat", cursor.getFloat(cursor.getColumnIndex("lat")));
			row.put("time", cursor.getString(cursor.getColumnIndex("dtime")));
			result.add(row);
		}
		return result;
	}

	public void addPostion(double lng, double lat) {
		ContentValues values = new ContentValues();
		values.put("lon", lng);
		values.put("lat", lat);
		values.put("uploaded", 0);
		values.put("dtime", Utils.getCurrentTimeString());
		manager.insert(DBManager.POSITION_DAO, "uploaded", values);
	}

	/**
	 * 设置已经上传状态
	 * 
	 * @param positions
	 */
	public void setUploadStatus(ArrayList<HashMap<String, Object>> positions) {
		String[] sqls = new String[positions.size()];
		Object[][] bindArgs = new Object[positions.size()][];
		for (int i = 0; i < positions.size(); i++) {
			HashMap<String, Object> position = positions.get(i);
			sqls[i] = "update " + DBManager.POSITION_DAO
					+ " set uploaded = 1 where id = ?";
			Log.d("DATA", sqls[i] + position.get("id"));
			bindArgs[i] = new Object[] { position.get("id") };
		}
		manager.updateMultilBySQL(sqls, bindArgs);
	}
}
