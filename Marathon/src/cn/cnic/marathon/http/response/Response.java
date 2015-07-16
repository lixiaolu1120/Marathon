package cn.cnic.marathon.http.response;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class Response {
	// 请求类型
	String code;

	boolean success = false;

	// 请求参数集合
	Map<String, Object> content = new HashMap<String, Object>();

	public Response() {

	}

	public Response(JSONObject object) {
		try {
			this.code = object.getString("code");
			this.success = object.getBoolean("is_success");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public JSONObject content(JSONObject jsonObject) {
		JSONObject object = null;
		try {
			object = jsonObject.getJSONObject("content");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		return object;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Map<String, Object> getContent() {
		return content;
	}

	public void setContent(Map<String, Object> content) {
		this.content = content;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
