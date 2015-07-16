package cn.cnic.marathon.http.response;

import org.json.JSONException;
import org.json.JSONObject;

import cn.cnic.marathon.util.Utils;

public class Login extends Response{
	public Login(JSONObject result) {
		try {
			String code = result.getString("code");
			JSONObject content = content(result);
			this.code = code;
			this.success = result.getBoolean("is_success");
			this.content.put("data", content.getString("data"));
			this.content.put("msg", content.getString("msg"));
		} catch (JSONException e) {
			Utils.log4(e.getMessage());
		}
	}
	

}
