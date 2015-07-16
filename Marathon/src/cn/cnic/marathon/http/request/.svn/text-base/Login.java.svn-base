package cn.cnic.marathon.http.request;

import cn.cnic.marathon.R;
import cn.cnic.marathon.util.Utils;

public class Login extends Request {

	public Login(String name, String passwd) {
		this.type = Utils.resources.getString(R.string.loginRequest);
		this.path = R.string.loginURL;
		content.put("tel", name);
		content.put("password", Utils.MD5(passwd));
		this.url = getUrl();
	}

}
