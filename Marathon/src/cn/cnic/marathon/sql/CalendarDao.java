package cn.cnic.marathon.sql;

import android.content.Context;
import android.database.Cursor;

public class CalendarDao {
	private DBManager manager;

	public CalendarDao(Context context) {
		manager = DBManager.getInstance(context);
	}
	/**
	 * 读取所有calendar列表
	 * @return
	 */
	public Cursor getAllCalendar() {
		return manager.queryBySql("select id as _id,* from "
				+ DBManager.CALENDAR_DAO, null);
	}
}
