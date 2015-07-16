package cn.cnic.marathon.http.request;

import java.util.List;

import cn.cnic.marathon.R;
import cn.cnic.marathon.util.Utils;
/**
 * 加好友
 * @author cuixipeng
 *
 */
public class AddOrUpLoadFriendRequest extends Request{
	public AddOrUpLoadFriendRequest(String uid, List<String> phoneNumber,String msg) {
		this.type = Utils.resources.getString(R.string.addFriendsRequest);
		this.path = R.string.addFriendsURL;
		content.put("uid", uid);
		content.put("friends", phoneNumber);
		content.put("message", msg);
		this.url = getUrl();
	}
}
