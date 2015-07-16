package cn.cnic.marathon.http.request;

import java.util.Map;

import cn.cnic.marathon.R;
import cn.cnic.marathon.util.Utils;

public class Cheer extends Request{
	public Cheer(String time) {
		this.type = Utils.resources.getString(R.string.cheerRequest);
		this.path = R.string.cheerURL;
		content.put("time", time);
		this.url = getUrl();
	}
}
