package cn.cnic.marathon.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import cn.cnic.marathon.R;
import cn.cnic.marathon.adapter.FriendsGridViewAdapter;
import cn.cnic.marathon.adapter.GroupListAdapter;
import cn.cnic.marathon.base.BaseActivity;
import cn.cnic.marathon.sql.HelperDao;

/**
 * 位置共享
 * 
 * @author cuixipeng
 */
public class SharedPlacesActivity extends BaseActivity implements
		OnClickListener {
	private List<Map<String, Object>> mGroupList = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> mList = new ArrayList<Map<String, Object>>();
	private FriendsGridViewAdapter mGridAdapter;
	private GridView mGridView;
	private GroupListAdapter mListAdapter;
	private ListView mListView;
	private Map<String, Object> map;
	private TextView mBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shared_place);
		findViewbyId();
		initView();
		setListeners();
	}

	@Override
	protected void initView() {
//		for (int i = 0; i < 8; i++) {
//			map = new HashMap<String, Object>();
//			map.put("name", "第" + i + "个好友");
//			mList.add(map);
//		}
		String volumns[]={"avatar"};
		String values[]={"default"};
		String volumn[]={"share","share"};
		String value[]={"0","1"};
		
		mGroupList=HelperDao.orRawQuery(getApplicationContext(), "groups", volumn, value);
		mList=HelperDao.orRawQuery(getApplicationContext(), "friends", volumns, values);
		
		mGridAdapter = new FriendsGridViewAdapter(mList, getApplicationContext());
		mListAdapter = (GroupListAdapter) new GroupListAdapter(mGroupList,
				getApplicationContext());
		
		mGridView.setAdapter(mGridAdapter);
		mListView.setAdapter(mListAdapter);
	}

	@Override
	protected void findViewbyId() {
		mGridView = (GridView) findViewById(R.id.friend_gridview);
		mListView = (ListView) findViewById(R.id.group_listview);
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

		}
	}

}
