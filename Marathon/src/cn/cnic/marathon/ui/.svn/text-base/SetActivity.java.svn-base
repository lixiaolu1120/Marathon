package cn.cnic.marathon.ui;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.cnic.marathon.R;
import cn.cnic.marathon.base.BaseActivity;
import cn.cnic.marathon.http.NetWork;
import cn.cnic.marathon.http.NetWork.CallBack;
import cn.cnic.marathon.util.ItUtils;

public class SetActivity extends BaseActivity implements OnClickListener {
	private TextView mSetBack;
	private RelativeLayout layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set);
		initView();

	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void initView() {
		findViewbyId();
		setListeners();
	}

	@Override
	protected void findViewbyId() {
		mSetBack = (TextView) findViewById(R.id.setback);
		layout = (RelativeLayout) findViewById(R.id.user);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.user:
			ItUtils.intent(SetActivity.this, LoginActivity.class);
			break;
		case R.id.setback:
			finish();
			break;
		}
	}

	@Override
	protected void setListeners() {
		layout.setOnClickListener(this);
		mSetBack.setOnClickListener(this);
	}

}
