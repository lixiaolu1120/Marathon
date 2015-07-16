package cn.cnic.marathon.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.cnic.marathon.R;
import cn.cnic.marathon.base.BaseActivity;

public class DetailActivity extends BaseActivity {

	LinearLayout back;
	TextView title, content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
	}

	@Override
	protected void findViewbyId() {
		back = (LinearLayout) findViewById(R.id.back_detail);
		title = (TextView) findViewById(R.id.detail_title);
		content = (TextView) findViewById(R.id.detail_content);
	}

	@Override
	protected void initData() {
		Intent intent = getIntent();
		String dataType = intent.getStringExtra("dtype");
		String name = intent.getStringExtra("name");
		String desc = intent.getStringExtra("desc");
		title.setText(name);
		content.setText(desc);
	}

	@Override
	protected void setListeners() {

	}

}
