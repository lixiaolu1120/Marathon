package cn.cnic.marathon.http.request;

import java.util.Map;

import cn.cnic.marathon.R;
import cn.cnic.marathon.util.Utils;

/**
 * 活动规则 请求参数
 * 
 * @author cuixipeng
 * 
 */
public class ActiveRule extends Request {

	private Map<String, Object> content;

	public ActiveRule(String time) {
		this.type = Utils.resources.getString(R.string.scheduleRequest);
		this.path = R.string.scheduleURL;
		content.put("time", time);
		this.url = getUrl();
	}
}
