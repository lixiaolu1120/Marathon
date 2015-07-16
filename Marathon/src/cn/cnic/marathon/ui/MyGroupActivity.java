package cn.cnic.marathon.ui;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import cn.cnic.marathon.R;
import cn.cnic.marathon.adapter.FriendsGridViewAdapter;
import cn.cnic.marathon.adapter.GroupListAdapter;
import cn.cnic.marathon.base.BaseActivity;
import cn.cnic.marathon.sql.HelperDao;
import cn.cnic.marathon.util.ItUtils;

public class MyGroupActivity extends BaseActivity implements OnClickListener {
	private Button mAddButton, mDeleteButton;
	private List<Map<String, Object>> mGroupList = new ArrayList<Map<String, Object>>();
	private ListView mListView;
	private GroupListAdapter mListAdapter;
	private TextView mBack;
	private List<Map<String, Object>> mList = new ArrayList<Map<String, Object>>();
	private FriendsGridViewAdapter mGridAdapter;
	private GridView mGridView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mygroup);
		initView();
	}

	@Override
	protected void initView() {
		findViewbyId();
		setListeners();
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
		mAddButton = (Button) findViewById(R.id.addGroup);
		mDeleteButton = (Button) findViewById(R.id.deleteGroup);
		mListView = (ListView) findViewById(R.id.mygroup_listview);
		mBack = (TextView) findViewById(R.id.back);
		mGridView = (GridView) findViewById(R.id.Mygroup_friend_gridview);
	}

	@Override
	protected void setListeners() {
		mAddButton.setOnClickListener(this);
		mBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.addGroup:
			ItUtils.intent(MyGroupActivity.this, GroupActivity.class);
			break;
		case R.id.back:
			finish();
			break;
		}
	}

}
