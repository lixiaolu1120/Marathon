package cn.cnic.marathon.base;

import android.content.BroadcastReceiver;
import android.util.Log;

public abstract class BaseAbstractBroadcastReceiver extends BroadcastReceiver {

	protected String TAG = "BaseBroadCastReceiver";

	public void log(String message) {
		log(message, null);
	}

	public void log(String message, Throwable e) {
		if (e != null) {
			Log.e(TAG, message, e);
			return;
		}
		Log.i(TAG, message);
	}

}
