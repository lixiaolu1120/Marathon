package cn.cnic.marathon.http.request;

import cn.cnic.marathon.R;
import cn.cnic.marathon.util.Utils;

public class FriendPositionRequest extends Request {

	public FriendPositionRequest(String uid, String time) {
		this.type = Utils.resources.getString(R.string.friendPositonRequest);
		this.path = R.string.friendPositonURL;
		content.put("uid", uid);
		content.put("time", time);
		this.url = getUrl();
	}

}
