package cn.cnic.marathon.http.request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.util.Log;

import cn.cnic.marathon.R;
import cn.cnic.marathon.util.Utils;

public class Request {

	int path;
	String url = "";
	// 请求类型
	String type;
	// 请求参数集合
	Map<String, Object> content = new HashMap<String, Object>();

	public String getUrl() {
		return Utils.resources.getString(R.string.host)
				+ Utils.resources.getString(path);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Map<String, Object> getContent() {
		return content;
	}

	public void setContent(Map<String, Object> content) {
		this.content = content;
	}

	@Override
	public String toString() {
		Map m = new HashMap();
		m.put("type", this.type);
		m.put("content", content);
		Log.d("JSON", new JSONObject(m).toString());
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"type\":");
		sb.append("\"");
		sb.append(this.type);
		sb.append("\",\"content\":{");
		for (String key : content.keySet()) {
			sb.append("\"");
			sb.append(key);
			sb.append("\":");
			Object v = content.get(key);
			if (key.equals("u_type") || "lat".equals(key) || "lon".equals(key)) {
				sb.append(v);
				sb.append(",");
				continue;
			}
			if (v instanceof ArrayList) {
				sb.append("[");
				ArrayList tv = (ArrayList) v;
				boolean isHashMap = false;
				boolean isString = false;
				for (int i = 0; i < tv.size(); i++) {
					StringBuilder s = new StringBuilder();
					Object d = tv.get(i);
					isHashMap = d instanceof HashMap;
					isString = d instanceof String;
					if (isHashMap) {
						s.append("{");
						HashMap<String, Object> td = (HashMap) d;
						for (String k : td.keySet()) {
							s.append("\"");
							s.append(k);
							s.append("\":");
							Object va = td.get(k);
							if (k.equals("u_type") || "lat".equals(k)
									|| "lon".equals(k) || va instanceof Boolean) {
								s.append(va);
								s.append(",");
								continue;
							}
							s.append("\"");
							s.append(va);
							s.append("\",");
						}
						s.append("},");
						String ts = s.toString();
						ts = ts.replace(",}", "}");
						sb.append(ts);
					} else if (isString) {
						sb.append("\"");
						sb.append(d);
						sb.append("\",");
					}
				}
				sb.append("],");
				continue;
			}
			if (v instanceof String)
				sb.append("\"");
			sb.append(v);
			if (v instanceof String)
				sb.append("\",");
			if (null == v || v instanceof Boolean)
				sb.append(",");
		}
		sb.append("}}");
		String rString = sb.toString();
		rString = rString.replace(",]", "]");
		rString = rString.replace(",]}}", "]}}");
		rString = rString.replace("},]}}", "}]}}");
		return rString.replace(",}", "}");
	}
}
