package cn.cnic.marathon.ui;

import java.util.List;
import java.util.Map;

import com.baidu.mapapi.map.MapStatusUpdate;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import cn.cnic.marathon.R;
import cn.cnic.marathon.adapter.RunnerListAdapter;
import cn.cnic.marathon.base.BaseActivity;
import cn.cnic.marathon.sql.DBManager;
import cn.cnic.marathon.sql.HelperDao;
import cn.cnic.marathon.sql.TeamDao;

/**
 * 明星球员
 * @author cuixipeng
 */
public class StarRunnerActivity extends BaseActivity implements OnClickListener {
	private List<String> list;
	private LinearLayout backRunners;
	private ListView runnerList;
	List<Map<String, Object>> mList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_runner);
	}

	@Override
	protected void findViewbyId() {
		backRunners = (LinearLayout) findViewById(R.id.back_runner);
		runnerList = (ListView) findViewById(R.id.runner_list);
	}

	@Override
	protected void setListeners() {
		backRunners.setOnClickListener(this);
		runnerList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(StarRunnerActivity.this,
						PlayerActivity.class);
				intent.putExtra("title", mList.get(position).get("team_name")
						.toString());
				if (position == 0) {

					intent.putExtra("id", "2");
				} else if (position == 1) {
					intent.putExtra("id", "3");
				}
				{
				}
				startActivity(intent);
			}
		});
	}

	@Override
	protected void initData() {
		// Cursor cursor = new TeamDao(getApplication()).getAllTeam();
		// CursorAdapter adapter = new RunnerListAdapter(getApplication(),
		// cursor);
		// runnerList.setAdapter(adapter);
		mList = HelperDao.RawQuery(getApplicationContext(), DBManager.TEAM_DAO);
		RunnerListAdapter adapter = new RunnerListAdapter(
				getApplicationContext(), mList);
		runnerList.setAdapter(adapter);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.back_runner:
			finish();
			break;
		}
	}

}
