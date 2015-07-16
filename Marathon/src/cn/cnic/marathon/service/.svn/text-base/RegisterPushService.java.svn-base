package cn.cnic.marathon.service;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import cn.cnic.marathon.R;
import cn.cnic.marathon.http.NetWork;
import cn.cnic.marathon.rescript.UserStatus;
import cn.cnic.marathon.util.Utils;

/**
 * 注册推送服务，注册用户id到推送转发服务器
 * 
 * 用户登录时进行注册
 * @author ll
 * 
 */
public class RegisterPushService {
	
	public static void register(Context context) {
		String url = context.getResources().getString(
				R.string.register_pushserver_url);
		Map<String, String> params = new HashMap<String, String>();
		params.put("uid", UserStatus.getUser().getUid());
		params.put("task", "register");
		params.put("devicetoken", Utils.MD5(UserStatus.getUser().getUid()));
		NetWork.getRequest(url, params, context);
	}

	public static void unregister(Context context) {
		String url = context.getResources().getString(
				R.string.register_pushserver_url);
		Map<String, String> params = new HashMap<String, String>();
		params.put("uid", UserStatus.getUser().getUid());
		params.put("task", "invalid");
		NetWork.getRequest(url, params, context);
	}
}
