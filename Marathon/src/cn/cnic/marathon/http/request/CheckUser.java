package cn.cnic.marathon.http.request;

import cn.cnic.marathon.R;
import cn.cnic.marathon.util.Utils;
/**
 * 用户名检测 请求参数
 * @author cuixipeng
 *
 */
public class CheckUser extends Request {

	public CheckUser(String tel) {
		this.type = Utils.resources.getString(R.string.checkUserRequest);
		this.path = R.string.checkUserURL;
		content.put("tel", tel);
		this.url = getUrl();
	}
	
}
