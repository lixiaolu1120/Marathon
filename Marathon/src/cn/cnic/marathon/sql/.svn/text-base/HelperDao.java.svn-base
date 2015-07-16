package cn.cnic.marathon.sql;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.Log;

public class HelperDao {
	public HelperDao(Context context) {
		DBOpenHelper dbManager = new DBOpenHelper(context);
		dbManager.getReadableDatabase();
		dbManager.getWritableDatabase();
	}

//	/**
//	 * 合作伙伴dao
//	 * 
//	 * @param id
//	 * @param name
//	 * @param description
//	 * @param create_at
//	 * @param del_flag
//	 * @param logo
//	 * @param context
//	 */
//	public static void insertPartnet(String id, String name,
//			String description, String create_at, String del_flag, String logo,
//			Context context) {
//		String sql = "insert into "
//				+ DBOpenHelper.PARTNER_DAO
//				+ "(id,name,description,create_at,del_flag,logo) values(?,?,?,?,?,?)";
//		Object[] bindArgs = { id, name, description, create_at, del_flag, logo };
//		DBManager manager = DBManager.getInstance(context);
//		manager.updateBySQL(sql, bindArgs);
//	}
//
//	public static void insertPosition(String lon, String lat, Context context) {
//		String sql = "insert into " + DBOpenHelper.POSITION_DAO
//				+ "(username,password) values(?,?)";
//		Object[] bindArgs = { lon, lat };
//		DBManager manager = DBManager.getInstance(context);
//		manager.updateBySQL(sql, bindArgs);
//	}
//
	public static void inserFriend(String uid, String nickname,String message,String time,
			Context context) {
		String sql = "insert into " + DBManager.FRIEND_DAO
				+ "(uid,nickname,message,time) values(?,?,?,?)";
		Object[] bindArgs = { uid,nickname,message,time};
		DBManager manager = DBManager.getInstance(context);
		manager.updateBySQL(sql, bindArgs);
	}

	/**
	 * 查询数据库到list集合中
	 * 
	 * @param context
	 * @param dao
	 * @return
	 */

	public static List<Map<String, Object>> andRawQuery(Context context,
			String dao, String[] volumns, String[] values) {
		String sql = "select *   from " + dao + " where 1=1";
		for (String key : volumns) {
			sql += " and " + key + "=?";
		}
		DBManager manager = DBManager.getInstance(context);
		List<Map<String, Object>> list = manager.queryMultiMaps(sql, values);
		return list;
	}

	/**
	 * 查询所有
	 * 
	 * @param context
	 * @param dao
	 * @return
	 */
	public static List<Map<String, Object>> RawQuery(Context context, String dao) {
		String sql = "select *   from " + dao;
		DBManager manager = DBManager.getInstance(context);
		List<Map<String, Object>> list = manager.queryMultiMaps(sql, null);
		return list;
	}

	/**
	 * 查询数据库到list集合中
	 * 
	 * @param context
	 * @param dao
	 * @return
	 */

	public static List<Map<String, Object>> orRawQuery(Context context,
			String dao, String[] volumns, String[] values) {
		String sql = "select *   from " + dao + " where 1=1";
		if (volumns.length > 0) {
			sql += " and " + volumns[0] + "=?";
		}
		for (int i = 1; i < volumns.length; i++) {
			sql += " or " + volumns[i] + "=?";
		}
		DBManager manager = DBManager.getInstance(context);
		List<Map<String, Object>> list = manager.queryMultiMaps(sql, values);
		return list;
	}
}
