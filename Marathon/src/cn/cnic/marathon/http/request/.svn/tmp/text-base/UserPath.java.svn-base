package cn.cnic.marathon.http.request;

import java.util.Map;

import cn.cnic.marathon.R;
import cn.cnic.marathon.util.Utils;

/**
 * 用户轨迹&某人轨迹
 * 
 * @author cuixipeng
 * 
 */
public class UserPath extends Request {

	private Map<String, Object> content;

	public UserPath(String time, String uid) {
		this.type = Utils.resources.getString(R.string.userPathRequest);
		this.path = R.string.userPathURL;
		content.put("uid", uid);
		content.put("time", time);
		this.url = getUrl();
	}
}
