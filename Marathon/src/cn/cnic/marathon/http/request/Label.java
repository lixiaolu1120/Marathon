package cn.cnic.marathon.http.request;

import java.util.Map;

import cn.cnic.marathon.R;
import cn.cnic.marathon.util.Utils;

public class Label extends Request {

	public Label(String time) {
		this.type = Utils.resources.getString(R.string.identifyRequest);
		this.path = R.string.identifyURL;
		content.put("time", time);
		this.url = getUrl();
	}
}
