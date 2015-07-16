package cn.cnic.marathon.http.response;

import org.json.JSONException;
import org.json.JSONObject;

import cn.cnic.marathon.util.Utils;

public class DeleteFriendResponse extends Response{
	public DeleteFriendResponse(JSONObject result) {
		try {
			String code = result.getString("code");
			JSONObject content = content(result);
			this.code = code;
			this.success = result.getBoolean("is_success");
			this.content.put("time", content.getString("time"));
		} catch (JSONException e) {
			Utils.log4(e.getMessage());
		}
	}
}
