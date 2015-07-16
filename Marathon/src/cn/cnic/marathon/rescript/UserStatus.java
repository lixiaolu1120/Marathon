package cn.cnic.marathon.rescript;

import java.io.IOException;
import java.io.StreamCorruptedException;

import cn.cnic.marathon.base.UserInfo;
import cn.cnic.marathon.util.SerializableUtil;
import cn.cnic.marathon.util.Utils;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class UserStatus {

	private static String UID = "uid";
	private static String USERSTATUS = "app_userstatus";
	private static SharedPreferences preferences;
	public static UserInfo user;
	static String KEY_NAME = "user";

	public UserStatus(Context context) {
		if (preferences == null) {
			preferences = context.getSharedPreferences(USERSTATUS,
					Activity.MODE_PRIVATE);
		}
	}

	/**
	 * 判断是否已经登录
	 * 
	 * @return
	 */
	public static boolean isLogined() {
		String uid = preferences.getString(UID, null);
		Utils.log("UserStatus", "uid is " + uid);
		return uid != null;
	}

	/**
	 * 判断是否已经登录
	 * 
	 * @param uid
	 *            如果uid为null则用户已经注销,如果为uid则用户登录
	 * @return
	 */
	public static void setLoginStatus(String uid) {
		preferences.edit().putString(UID, uid).commit();
	}

	public static synchronized void putUser(UserInfo user) {
		Log.i("DATA", "uerid is " + user.getUid());
		setLoginStatus(user.getUid());
		Editor editor = preferences.edit();
		String str = "";
		try {
			str = SerializableUtil.obj2Str(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
		editor.putString(KEY_NAME, str);
		editor.commit();
		UserStatus.user = user;
	}

	public static synchronized UserInfo getUser() {

		if (UserStatus.user == null || UserStatus.user.getUid() == null) {
			UserStatus.user = new UserInfo();

			if (null == preferences)
				return null;
			// 获取序列化的数据
			String str = preferences.getString(UserStatus.KEY_NAME, "");
			Utils.log("UserStatus", "user serializale string is " + str);
			try {
				Object obj = SerializableUtil.str2Obj(str);
				Utils.log("UserStatus", "UserStatus.user is " + (obj != null));
				if (obj != null) {
					UserStatus.user = (UserInfo) obj;
					Utils.log("UserStatus", "UserStatus.user.uid is "
							+ UserStatus.user.getUid());
				}

			} catch (StreamCorruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return UserStatus.user;
	}

	public static synchronized void deleteUser() {
		Editor editor = preferences.edit();
		editor.putString(KEY_NAME, "");
		setLoginStatus(null);
		editor.commit();
		UserStatus.user = null;
	}
}
