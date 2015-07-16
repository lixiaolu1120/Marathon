package cn.cnic.marathon.sql;

import android.content.Context;
import android.database.Cursor;

public class PlayerDao {
	private DBManager manager;

	public PlayerDao(Context context) {
		manager = DBManager.getInstance(context);
	}

	/**
	 * 读取所有好友列表
	 * 
	 * @return
	 */
	public Cursor getAllRunners() {
		return manager.queryBySql("select id as _id,* from "
				+ DBManager.PLAYER_DAO, null);
	}

	public Cursor getRunnersByTeam(String teamId) {
		return manager.queryBySql("select id as _id,* from "
				+ DBManager.PLAYER_DAO + " where team_id = ?" , new String[]{teamId});
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