package cn.cnic.marathon.http.response;

import org.json.JSONException;
import org.json.JSONObject;

import cn.cnic.marathon.util.Utils;

public class MeetRequest extends Response {
	public MeetRequest(JSONObject result) {
		super(result);
		try {
			String code = result.getString("code");
			this.code = code;
			this.success = result.getBoolean("is_success");
			JSONObject content = content(result);
			this.content.put("time", content.getString("time"));
		} catch (JSONException e) {
			Utils.log4(e.getMessage());
		}
	}
}
