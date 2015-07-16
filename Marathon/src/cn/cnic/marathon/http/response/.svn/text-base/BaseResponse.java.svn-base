package cn.cnic.marathon.http.response;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.cnic.marathon.util.Utils;

/**
 * 运动员,拉拉队，合作伙伴返回response 数据类型 response:{ "code":"...",#响应码 "content":{
 * "time":"",#最新数据时间 "data":[{ ..... }] }
 * 
 * @author cuixipeng
 * 
 */
public class BaseResponse extends Response {
	private Map<String, Object> content;

	public BaseResponse(JSONObject result) {
		try {
			String code = result.getString("code");
			JSONObject content = content(result);
			JSONArray array = content.getJSONArray("data");
			this.code = code;
			this.content.put("msg", content.getString("msg"));
			this.content.put("data", content.getJSONArray("data"));
		} catch (JSONException e) {
			Utils.log4(e.getMessage());
		}
	}
}
