package cn.cnic.marathon.http.request;

import cn.cnic.marathon.R;
import cn.cnic.marathon.util.Utils;
/**
 * 响应位置共享请求
 * @author cuixipeng
 */
public class LocationShareRequest extends Request{

	public LocationShareRequest(String uid, String fid,String accept) {
		this.type = Utils.resources.getString(R.string.location_share);
		this.path = R.string.friendPositonURL;
		content.put("uid", uid);
		content.put("fid", fid);
		content.put("accept", accept);
		this.url = getUrl();
	}
}
