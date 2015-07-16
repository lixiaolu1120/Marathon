package cn.cnic.marathon.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import cn.cnic.marathon.R;
import cn.cnic.marathon.base.BaseActivity;

public class AboutUsActivity extends BaseActivity{
	LinearLayout mBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aboutus);
		
	}

	@Override
	protected void findViewbyId() {
		mBack=(LinearLayout)findViewById(R.id.back);
	}

	@Override
	protected void setListeners() {
		mBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
}
