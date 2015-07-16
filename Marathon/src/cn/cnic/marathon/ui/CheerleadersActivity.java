package cn.cnic.marathon.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.widget.Toast;
import cn.cnic.marathon.R;
import cn.cnic.marathon.adapter.ScheduAdapter;
import cn.cnic.marathon.base.BaseActivity;
import cn.cnic.marathon.http.NetWork;
import cn.cnic.marathon.http.NetWork.CallBack;
import cn.cnic.marathon.http.request.Cheer;
import cn.cnic.marathon.http.request.Request;
import cn.cnic.marathon.http.response.Response;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class CheerleadersActivity extends BaseActivity {
	private PullToRefreshListView mListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheer);
		String time = "";
		if (time.equals("")) {
			Toast.makeText(getApplicationContext(), "接口暂未接通",
					Toast.LENGTH_SHORT).show();
			return;
		}
		Request request = new Cheer(time);
		NetWork.initNetWork(request, CheerleadersActivity.this,
				new cheerRuleCallback());
	}

	@Override
	protected void initView() {
		
	}

	@Override
	protected void findViewbyId() {
		//mListView = (PullToRefreshListView) findViewById(R.id.cheer_pull_refresh_list);
	}

	@Override
	protected void setListeners() {

	}

	List<Map<String, Object>> mList = new ArrayList<Map<String, Object>>();;
	Map<String, Object> map;
	private ScheduAdapter mAdapter;

	class cheerRuleCallback implements CallBack {
		@Override
		public void onRequestComplete(Response result) {
			String code = result.getCode();
			Map content = result.getContent();
			JSONArray array = (JSONArray) content.get("data");
			for (int i = 0; i < array.length(); i++) {
				map = new HashMap<String, Object>();
				JSONObject object2;
				try {
					object2 = array.getJSONObject(i);
					String title = object2.getString("title");
					String trigger = object2.getString("trigger_at");// 日程时间
					String contents = object2.getString("content");// 日程内容
					map.put("title", title);
					map.put("trigger", trigger);
					map.put("contents", contents);
					mList.add(map);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			mAdapter = new ScheduAdapter(mList, getApplicationContext());
			mListView.setAdapter(mAdapter);

		}
	}
}
