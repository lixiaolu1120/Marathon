package cn.cnic.marathon.http.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.cnic.marathon.util.Utils;

public class FriendPositionResponse extends Response {

	public FriendPositionResponse(JSONObject result) {
		super(result);
		try {
			JSONObject content = content(result);
			List<Map<String, String>> poss = new ArrayList<Map<String, String>>();
			if(null == content){
				this.content.put("time", Utils.getCurrentTimeString());
				this.content.put("data", poss);
				return;
			}
			this.content.put("time", content.getString("time"));
			JSONArray data = content.getJSONArray("data");
			for (int i = 0; i < data.length(); i++) {
				Map<String, String> pos = new HashMap<String, String>();
				JSONObject d = data.getJSONObject(i);
				pos.put("uid", d.getString("uid"));
				pos.put("lon", d.getString("lon"));
				pos.put("lat", d.getString("lat"));
				poss.add(pos);
			}
			this.content.put("data", poss);
		} catch (JSONException e) {
			Utils.log4(e.getMessage());
		}
	}

	public List<Map<String, String>> getFriendPosition() {
		return (List<Map<String, String>>) this.content.get("data");
	}

	public String getTime() {
		return (String) this.content.get("time");
	}

}
