package cn.cnic.marathon.http.response;

import org.json.JSONException;
import org.json.JSONObject;

import cn.cnic.marathon.util.Utils;
/**
 * 头像上传response
 * @author cuixipeng
 *
 */
public class uploadAvatar extends Response{
	public uploadAvatar(JSONObject result) {
		try {
			this.success = result.getBoolean("is_success");
		} catch (JSONException e) {
			Utils.log4(e.getMessage());
		}
	}
}
