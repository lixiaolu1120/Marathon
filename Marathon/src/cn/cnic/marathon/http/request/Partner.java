package cn.cnic.marathon.http.request;

import java.util.Map;

import cn.cnic.marathon.R;
import cn.cnic.marathon.util.Utils;

public class Partner extends Request {
	public Partner(String time) {
		this.type = Utils.resources.getString(R.string.partnerRequest);
		this.path = R.string.partnerURL;
		content.put("time", time);
		this.url = getUrl();
	}
}
