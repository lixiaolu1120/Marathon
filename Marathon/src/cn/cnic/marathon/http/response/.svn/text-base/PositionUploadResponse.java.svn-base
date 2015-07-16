package cn.cnic.marathon.http.response;

import org.json.JSONException;
import org.json.JSONObject;

import cn.cnic.marathon.util.Utils;

public class PositionUploadResponse extends Response {
	public PositionUploadResponse(JSONObject result) {
		try {
			String code = result.getString("code");
			this.success = result.getBoolean("is_success");
			JSONObject content = content(result);
			this.code = code;
			this.content.put("time", content.getString("time"));// 返回上传时发过去的时间戳，采取异步http的时候，能知道操作成功所对应的数据
		} catch (JSONException e) {
			Utils.log4(e.getMessage());
		}
	}
}
