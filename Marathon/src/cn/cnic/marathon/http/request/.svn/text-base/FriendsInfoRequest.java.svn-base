package cn.cnic.marathon.http.request;

import cn.cnic.marathon.R;
import cn.cnic.marathon.util.Utils;

public class FriendsInfoRequest extends Request {

	public FriendsInfoRequest(String uid, String time) {
		this.type = Utils.resources.getString(R.string.friendInfoRequest);
		this.path = R.string.friendInfoURL;
		content.put("uid", uid);
		content.put("time", time);
		this.url = getUrl();
	}

}
