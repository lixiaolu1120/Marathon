package cn.cnic.marathon.http.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.cnic.marathon.util.Utils;

public class FriendsInfoResponse extends Response {

	public FriendsInfoResponse(JSONObject result) {
		super(result);
		try {
			JSONObject content = content(result);
			this.content.put("time", content.getString("time"));
			JSONArray data = content.getJSONArray("friends");
			List<Map<String, String>> poss = new ArrayList<Map<String, String>>();
			Map<String, String> pos;
			for (int i = 0; i < data.length(); i++) {
				 pos = new HashMap<String, String>();
				JSONObject d = data.getJSONObject(i);
				pos.put("uid", d.getString("uid"));
				pos.put("nickname", d.getString("nickname"));
//				pos.put("avatar", d.getString("avatar"));
				pos.put("u_type", d.getString("u_type"));
				pos.put("description", d.getString("description"));
				pos.put("share", d.getBoolean("share") ? "1" : "0");
//				pos.put("lon", d.getString("lon"));
//				pos.put("lat", d.getString("lat"));
				poss.add(pos);
			}
			this.content.put("data", poss);
		} catch (JSONException e) {
			Utils.log4(e.getMessage());
		}
	}

	public List<Map<String, String>> getFriendList() {
		return (List<Map<String, String>>) this.content.get("data");
	}

	public String getTime() {
		return (String) this.content.get("time");
	}
}
