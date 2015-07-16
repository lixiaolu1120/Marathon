package cn.cnic.marathon.http.response;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.cnic.marathon.util.Utils;

/**
 * 用户轨迹和某人轨迹
 * 
 * @author cuixipeng
 * 
 */
public class UserPath extends Response {
	private Map<String, Object> content;

	public UserPath(JSONObject result) {
		try {
			String code = result.getString("code");
			JSONObject content = content(result);
			JSONArray array = content.getJSONArray("data");
			this.code = code;
			this.content.put("time", content.getString("time"));
			this.content.put("data", content.getJSONArray("data"));
		} catch (JSONException e) {
			Utils.log4(e.getMessage());
		}
	}
}
