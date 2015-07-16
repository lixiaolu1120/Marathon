package cn.cnic.marathon.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public abstract class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onStart() {
		super.onStart();
		initView();
	}

	protected void initView() {
		findViewbyId();
		setListeners();
		initData();
	}

	protected abstract void findViewbyId();

	protected abstract void setListeners();

	protected void initData() {
	};
}
