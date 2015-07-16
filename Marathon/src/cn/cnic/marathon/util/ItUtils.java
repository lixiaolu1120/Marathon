package cn.cnic.marathon.util;

import android.content.Context;
import android.content.Intent;

public class ItUtils {

	@SuppressWarnings("static-access")
	public static void intent(Context context, Class<?> activity) {

		Intent intent = new Intent();

		intent.setClass(context, activity);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
		context.startActivity(intent);
	}
	public static void putData(Context context, Class<?> activity,String key,String value) {
		Intent intent = new Intent();
		intent.setClass(context, activity);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
		intent.putExtra(key, value);
		context.startActivity(intent);
	}
}
