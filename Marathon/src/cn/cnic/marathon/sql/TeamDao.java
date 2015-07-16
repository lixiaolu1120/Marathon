package cn.cnic.marathon.sql;

import android.content.Context;
import android.database.Cursor;

public class TeamDao {
	private DBManager manager;

	public TeamDao(Context context) {
		manager = DBManager.getInstance(context);
	}

	/**
	 * 读取所有好友列表
	 * 
	 * @return
	 */
	public Cursor getAllTeam() {
		return manager.queryBySql("select id as _id,* from "
				+ DBManager.TEAM_DAO, null);
	}
}