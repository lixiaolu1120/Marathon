package cn.cnic.marathon.http.request;

import java.util.List;
import java.util.Map;

import cn.cnic.marathon.R;
import cn.cnic.marathon.util.Utils;

public class RequestLocShare extends Request {
	public RequestLocShare(String uid, List<Map<String, Object>> mList) {
		this.type = Utils.resources.getString(R.string.locshare);
		this.path = R.string.acceptfrienduploadURL;
		content.put("uid", uid);
		content.put("shares", mList);
		this.url = getUrl();
	}
}
