package cn.cnic.marathon.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.cnic.marathon.R;
import cn.cnic.marathon.base.BaseActivity;

public class TeamContentActivity extends BaseActivity implements
		OnClickListener {
	private LinearLayout mBack;
	private TextView mTitle, mContent;
	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team_content);
	}

	int[] imgs = { R.drawable.bairen, R.drawable.walunxiya };// 球队图片

	@Override
	protected void initData() {
		Intent intent = getIntent();
		// String dataType = intent.getStringExtra("dtype");
		String name = intent.getStringExtra("name");
		String desc = intent.getStringExtra("desc");
		if ("拜仁慕尼黑".equals(name)) {
			imageView.setImageResource(imgs[0]);
		} else {
			imageView.setImageResource(imgs[1]);
		}
		mTitle.setText(name);
		mContent.setText(desc);
	}

	@Override
	protected void findViewbyId() {
		mTitle = (TextView) findViewById(R.id.team_content_title);
		mContent = (TextView) findViewById(R.id.team_content_context);
		mBack = (LinearLayout) findViewById(R.id.back_team_content);
		imageView = (ImageView) findViewById(R.id.team_content_img);
	}

	@Override
	protected void setListeners() {
		mBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back_team_content:
			finish();
			break;

		}
	}
}
