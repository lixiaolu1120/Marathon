package cn.cnic.marathon.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import cn.cnic.marathon.R;
import cn.cnic.marathon.base.BaseActivity;

public class GameIntroductionActivity extends BaseActivity implements
		OnClickListener {

	LinearLayout back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_introduction);
	}

	@Override
	protected void findViewbyId() {
		back = (LinearLayout) findViewById(R.id.back_introduction);
	}

	@Override
	protected void setListeners() {
		back.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.back_introduction:
			Intent intent = new Intent(GameIntroductionActivity.this,
					BMapActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}
}
