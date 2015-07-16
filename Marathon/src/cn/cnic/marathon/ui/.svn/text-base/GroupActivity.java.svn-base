package cn.cnic.marathon.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import cn.cnic.marathon.R;
import cn.cnic.marathon.adapter.FriendsGridViewAdapter;
import cn.cnic.marathon.adapter.GroupListAdapter;
import cn.cnic.marathon.base.BaseActivity;
import cn.cnic.marathon.sql.HelperDao;

import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class GroupActivity extends BaseActivity implements OnClickListener {
	private GridView mGridView;
	private FriendsGridViewAdapter mAdapter;
	private List<Map<String, Object>> mList = new ArrayList<Map<String, Object>>();
	private Map<String, Object> map;
	private TextView mBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group);
		findViewbyId();
		initView();
		setListeners();
	}

	@Override
	protected void initView() {
//		for (int i = 0; i < 8; i++) {
//			map = new HashMap<String, Object>();
//			map.put("nickname", "第" + i + "个");
//			mList.add(map);
//		}
		String volumns[]={"avatar"};
		String values[]={"default"};
		mList=HelperDao.orRawQuery(getApplicationContext(), "friends", volumns, values);
		mAdapter = new FriendsGridViewAdapter(mList, GroupActivity.this);
		mGridView.setAdapter(mAdapter);
	}

	@Override
	protected void findViewbyId() {
		mGridView = (GridView) findViewById(R.id.creat_group_grid);
		mBack = (TextView) findViewById(R.id.back);
	}

	@Override
	protected void setListeners() {
		mBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;

		default:
			break;
		}
	}

}
