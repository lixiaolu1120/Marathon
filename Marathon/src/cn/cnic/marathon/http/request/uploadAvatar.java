package cn.cnic.marathon.http.request;

import cn.cnic.marathon.R;
import cn.cnic.marathon.util.Utils;

public class uploadAvatar extends Request{
	public uploadAvatar(String uid, byte[] avatar) {
		this.type = Utils.resources.getString(R.string.userUpdateRequest);
		this.path = R.string.uploadavatarURL;
		content.put("uid", uid);
		content.put("avatar", avatar);
		this.url = getUrl();
	}
}
