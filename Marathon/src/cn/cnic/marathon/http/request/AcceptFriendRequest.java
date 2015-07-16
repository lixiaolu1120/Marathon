package cn.cnic.marathon.http.request;

import java.util.List;

import cn.cnic.marathon.R;
import cn.cnic.marathon.util.Utils;
/**
 * 是否接收好友的请求
 * @author cuixipeng
 */
public class AcceptFriendRequest extends Request{
	public AcceptFriendRequest(String uid, String friend,String accept) {
		this.type = Utils.resources.getString(R.string.acceptfriendupload);
		this.path = R.string.acceptfrienduploadURL;
		content.put("uid", uid);
		content.put("fid", friend);
		content.put("accept", accept);
		this.url = getUrl();
	}
}
