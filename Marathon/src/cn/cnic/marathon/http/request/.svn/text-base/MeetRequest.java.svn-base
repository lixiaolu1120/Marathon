package cn.cnic.marathon.http.request;

import cn.cnic.marathon.R;
import cn.cnic.marathon.util.Utils;

public class MeetRequest extends Request {
	public MeetRequest(String uid, String fid,String message, Float lat, Float lon) {
		this.type = Utils.resources.getString(R.string.invatefriend);
		this.path = R.string.invateFriendURL;
		content.put("uid", uid);
		content.put("fid", fid);
		content.put("message", message);
		content.put("lon", lon);
		content.put("lat", lat);
		this.url = getUrl();
	}
}
