package cn.cnic.marathon.http.request;

import cn.cnic.marathon.R;
import cn.cnic.marathon.util.Utils;

public class Register extends Request {

	public Register(String tel, String passwd) {
		this(tel, passwd, null, "", 0, "", false, "", "", 0, "", "", "");
	}

	public Register(String tel, String passwd, String smsCode, String nickname,
			String verifyCode) {
		this(tel, passwd, null, nickname, 0, "", false, "", "", 0, "", smsCode,
				verifyCode);
	}

	public Register(String tel, String passwd, byte[] avatar, String nickname,
			int age, String birthday, boolean sex, String qq, String email,
			int u_type, String description, String smsCode, String verifyCode) {
		this.type = Utils.resources.getString(R.string.registerRequest);
		this.path = R.string.registerURL;
		content.put("tel", tel);
		content.put("password", Utils.MD5(passwd));
		content.put("avatar", avatar);
		content.put("nickname", nickname);
		// content.put("age", age);
		// content.put("birthday", birthday);
		// content.put("sex", sex);
		// content.put("qq", qq);
		// content.put("email", email);
		content.put("u_type", u_type);
		content.put("description", description);
		content.put("sms_code", smsCode);
		content.put("verify_code", verifyCode);
		this.url = getUrl();
	}
}
