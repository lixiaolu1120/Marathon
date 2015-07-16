package cn.cnic.marathon.http.request;

import java.util.ArrayList;
import java.util.HashMap;

import cn.cnic.marathon.R;
import cn.cnic.marathon.util.Utils;

/**
 * 位置上传
 * 
 * @author cuixipeng
 * 
 */
public class PositionUploadRequest extends Request {

	public PositionUploadRequest(String uid, ArrayList<HashMap<String, Object>> pos) {
		this.type = Utils.resources.getString(R.string.upPostionRequest);
		this.path = R.string.upPostionURL;
		content.put("uid", uid);
		content.put("pos", pos);
		this.url = getUrl();
	}
}
