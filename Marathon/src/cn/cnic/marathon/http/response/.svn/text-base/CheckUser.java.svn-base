package cn.cnic.marathon.http.response;

import org.json.JSONException;
import org.json.JSONObject;

import cn.cnic.marathon.util.Utils;

public class CheckUser extends Response {
	public CheckUser(JSONObject result) {
		super(result);
		try {
			JSONObject content = content(result);
			this.content.put("is_legal", Boolean.parseBoolean(content
					.getString("is_legal").toString()));
		} catch (JSONException e) {
			Utils.log4(e.getMessage());
		}
	}
}
