package cn.cnic.marathon.http.request;

import cn.cnic.marathon.R;
import cn.cnic.marathon.util.Utils;

public class MeetRespond extends Request {
	public MeetRespond(String uid, String friend, String lon, String lat,
			Boolean accept) {
		this.type = Utils.resources.getString(R.string.acceptinvatefriend);
		this.path = R.string.acceptinvatefriendURL;
		content.put("uid", uid);
		content.put("fid", friend);
		content.put("lon", lon);
		content.put("lat", lat);
		content.put("accept", accept);
		this.url = getUrl();
	}
}
