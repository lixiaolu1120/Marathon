package cn.cnic.marathon.http.request;

import cn.cnic.marathon.R;
import cn.cnic.marathon.util.Utils;
/**
 * 用户完善信息 请求参数
 * @author cuixipeng
 *
 */
public class UserUpdate extends Request {
	public UserUpdate(String uid,byte[] avatar, String u_type,String description) {
		this.type = Utils.resources.getString(R.string.userUpdateRequest);
		this.path = R.string.userUpdateURL;
		content.put("uid", uid);
		content.put("avatar", avatar);
		content.put("u_type", u_type);
		content.put("description", description);
		this.url = getUrl();
	}
}
