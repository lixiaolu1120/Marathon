package cn.cnic.marathon.http.response;

import org.json.JSONException;
import org.json.JSONObject;

import cn.cnic.marathon.util.Utils;

public class Register extends Response {

	public Register(JSONObject result) {
		super(result);
		try {
			JSONObject content = content(result);
			this.content.put("uid", content.getString("uid"));
			this.content.put("msg", content.getString("msg"));
		} catch (JSONException e) {
			Utils.log4(e.getMessage());
		}
	}

}
