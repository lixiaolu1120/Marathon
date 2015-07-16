package cn.cnic.marathon.http.request;

import java.util.List;

import cn.cnic.marathon.R;
import cn.cnic.marathon.util.Utils;

public class DeleteFriendRequest extends Request{
	public DeleteFriendRequest(String uid, List<String> fids) {
		this.type = Utils.resources.getString(R.string.removefriend);
		this.path = R.string.addFriendsURL;
		content.put("uid", uid);
		content.put("fids", fids);
		this.url = getUrl();
	}
}
