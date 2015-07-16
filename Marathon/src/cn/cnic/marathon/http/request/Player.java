package cn.cnic.marathon.http.request;

import cn.cnic.marathon.R;
import cn.cnic.marathon.util.Utils;
/**
 * 运动员请求参数 封装
 * @author cuixipeng
 *
 */
public class Player extends Request{
	public Player(String time) {
		this.type = Utils.resources.getString(R.string.athleteRequest);
		this.path = R.string.athleteURL;
		content.put("time", time);
		this.url = getUrl();
	}
}
