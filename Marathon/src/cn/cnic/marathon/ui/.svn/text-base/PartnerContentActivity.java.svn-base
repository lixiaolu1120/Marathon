package cn.cnic.marathon.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import cn.cnic.marathon.base.BaseActivity;

import cn.cnic.marathon.R;/**
 * 合作伙伴详情页
 * @author cuixipeng
 *
 */
public class PartnerContentActivity extends BaseActivity {
	private TextView mBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_partner);
		initView();
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		findViewbyId();
		setListeners();

	}

	@Override
	protected void findViewbyId() {
		// TODO Auto-generated method stub

		mBack = (TextView) findViewById(R.id.back);
	}

	@Override
	protected void setListeners() {
		// TODO Auto-generated method stub
		mBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}

}
