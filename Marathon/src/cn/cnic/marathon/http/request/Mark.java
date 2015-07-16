package cn.cnic.marathon.http.request;

import cn.cnic.marathon.R;
import cn.cnic.marathon.util.Utils;
/**
 * 标注信息
 * @author cuixipeng
 *
 */
public class Mark extends Request {
	public Mark(String time) {
		this.type = Utils.resources.getString(R.string.identifyRequest);
		this.path = R.string.identifyURL;
		content.put("time", time);
		this.url = getUrl();
	}
}
