package cn.cnic.marathon.http.response;

import org.json.JSONException;
import org.json.JSONObject;

import cn.cnic.marathon.util.Utils;

public class UserUpdate extends Response {
	public UserUpdate(JSONObject result) {
		try {
			this.code = result.getString("code");
			this.success = result.getBoolean("is_success");
		} catch (JSONException e) {
			Utils.log4(e.getMessage());
		}
	}
}
