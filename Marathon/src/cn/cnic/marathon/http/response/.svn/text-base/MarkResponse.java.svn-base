package cn.cnic.marathon.http.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.cnic.marathon.util.Utils;

public class MarkResponse extends Response {

	public MarkResponse(JSONObject result) {
		super(result);
		try {
			JSONObject content = content(result);
			this.content.put("msg", content.getString("msg"));
			JSONArray data = content.getJSONArray("data");
			List<Map<String, Object>> marks = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < data.length(); i++) {
				Map<String, Object> mark = new HashMap<String, Object>();
				JSONObject d = data.getJSONObject(i);
				mark.put("name", d.getString("name"));
				mark.put("lon", d.getString("lon"));
				mark.put("lat", d.getString("lat"));
				mark.put("description", d.getString("description"));
				mark.put("type", d.getString("type"));
				marks.add(mark);
			}
			this.content.put("data", marks);
		} catch (JSONException e) {
			Utils.log4(e.getMessage());
		}
	}

}
