package cn.cnic.marathon.sql;

import android.content.Context;
import android.database.Cursor;

public class PartnerDao {
	private DBManager manager;

	public PartnerDao(Context context) {
		manager = DBManager.getInstance(context);
	}

	/**
	 * 读取所有好友列表
	 * 
	 * @return
	 */
	public Cursor getAllPartners() {
		return manager.queryBySql("select id as _id,* from "
				+ DBManager.PARTNER_DAO, null);
	}

	/**
	 * 显示好友位置在地图上
	 */
	public void showMarkInMap(int id) {
		manager.updateBySQL("update " + DBManager.FRIEND_DAO
				+ " set share = 1 where id = ?", new Integer[] { id });
	}

	/**
	 * 不显示好友位置在地图上
	 */
	public void hideMarkInMap(int id) {
		manager.updateBySQL("update " + DBManager.FRIEND_DAO
				+ " set share = 0 where id = ?", new Integer[] { id });
	}
}
