package cn.cnic.marathon.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import cn.cnic.marathon.R;
import cn.cnic.marathon.adapter.PlayerListAdapter;
import cn.cnic.marathon.base.BaseActivity;
import cn.cnic.marathon.sql.PlayerDao;

/**
 * 球队的运动员
 * 
 * @author cuixipeng
 * 
 */
public class PlayerActivity extends BaseActivity implements OnClickListener {
	private LinearLayout mBack; 
	private TextView mTitle;
	private ListView mListView;
	private Intent intent;
	int Img1[] = { R.drawable.nuoyier, R.drawable.mule, R.drawable.maliao,
			R.drawable.runner, R.drawable.luoben, R.drawable.libeili };
	int Img2[] = { R.drawable.gaizika, R.drawable.runner, R.drawable.aimaer,
			R.drawable.kasanileisi, R.drawable.weisente, R.drawable.runner };
	private int[] img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player);
		intent = getIntent();
	}

	@Override
	protected void findViewbyId() {
		mBack = (LinearLayout) findViewById(R.id.back);
		mTitle = (TextView) findViewById(R.id.player_title);
		mListView = (ListView) findViewById(R.id.player_list);

	}

	@Override
	protected void initData() {
		String titleString = intent.getExtras().getString("title");
		mTitle.setText(titleString);
		String iString = intent.getStringExtra("id");
		if (iString.equals("2")) {
			img = Img1;
		} else {
			img = Img2;
		}
		Cursor cursor = new PlayerDao(getApplication())
				.getRunnersByTeam(iString);
		CursorAdapter adapter = new PlayerListAdapter(getApplication(), cursor,
				img);
		mListView.setAdapter(adapter);
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
