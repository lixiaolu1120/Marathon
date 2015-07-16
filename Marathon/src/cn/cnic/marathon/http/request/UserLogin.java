package cn.cnic.marathon.http.request;

import java.util.Map;

import cn.cnic.marathon.R;
import cn.cnic.marathon.util.Utils;
/**
 * 用户登录请求参数
 * @author cuixipeng
 *
 */
public class UserLogin extends Request {

	private Map<String, Object> content;

	public UserLogin(String tel, String passwd, byte[] avatar, String nickname,
			int age, String birthday, boolean sex, String qq, String email,
			String u_type, String description) {
		this.type = Utils.resources.getString(R.string.loginRequest);
		this.path = R.string.loginURL;
		content.put("tel", tel);
		content.put("password", Utils.MD5(passwd));
		content.put("avatar", avatar);
		content.put("nickname", nickname);
		content.put("age", age);
		content.put("birthday", birthday);
		content.put("sex", sex);
		content.put("qq", qq);
		content.put("email", email);
		content.put("u_type", u_type);
		content.put("description", description);
		this.url = getUrl();
	}
}
