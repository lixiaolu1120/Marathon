package cn.cnic.marathon.ui;

import java.util.List;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import cn.cnic.marathon.R;
import cn.cnic.marathon.adapter.TeamListAdapter;
import cn.cnic.marathon.base.BaseActivity;
import cn.cnic.marathon.sql.PlayerDao;
import cn.cnic.marathon.sql.TeamDao;
import cn.cnic.marathon.util.ItUtils;

public class TeamActivity extends BaseActivity implements OnClickListener {
	private List<String> list;
	private LinearLayout mBack;
	private ListView mTeamList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team);
	}

	@Override
	protected void findViewbyId() {
		mBack = (LinearLayout) findViewById(R.id.back_team);
		mTeamList = (ListView) findViewById(R.id.team_listview);
	}

	@Override
	protected void setListeners() {
		mBack.setOnClickListener(this);
//		mTeamList.setOnItemClickListener(new OnItemClickListener() {
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//					long arg3) {
//				ItUtils.intent(TeamActivity.this, TeamContentActivity.class);
//				
//			}
//		});
	}

	@Override
	protected void initData() {
		// Cursor cursor = new RunnerDao(getApplication()).getAllRunners();
		// 更换数据库名称，就ok了
		Cursor cursor = new TeamDao(getApplication()).getAllTeam();
		CursorAdapter adapter = new TeamListAdapter(getApplication(), cursor);
		mTeamList.setAdapter(adapter);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.back_team:
			finish();
			break;
		}
	}
}
