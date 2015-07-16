package cn.cnic.marathon.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import cn.cnic.marathon.R;
import cn.cnic.marathon.base.BaseActivity;
import cn.cnic.marathon.base.BaseApplication;
import cn.cnic.marathon.handler.MeetingPlaceChooseHandler;

/**
 * 约见地点选择界面
 * @author ll
 *
 */
public class DatePlaceChoseActivity extends BaseActivity implements OnClickListener {
	private TextView mToOther, ToMyPlace, mChoisePlace, mCancle;
	private BaseApplication mAPP;
	private MeetingPlaceChooseHandler mHandler;
	private Intent intent;
	Message message;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTheme(R.style.translucent);
		setContentView(R.layout.activity_chose_date_place);
		intent=getIntent();
		mAPP = (BaseApplication) getApplication();
		// 获得该共享变量实例
		mHandler = (MeetingPlaceChooseHandler) mAPP.getHandler();
		 message=new Message();  
        Bundle bundle=new Bundle();  
        bundle.putDouble("lat",intent.getExtras().getDouble("lat"));
        bundle.putDouble("lon", intent.getExtras().getDouble("lon"));
        bundle.putString("uid", intent.getExtras().getString("uid"));
        message.setData(bundle);
	}

	@Override
	protected void findViewbyId() {
		mToOther = (TextView) findViewById(R.id.to_other_place);
		ToMyPlace = (TextView) findViewById(R.id.to_myplace);
		mChoisePlace = (TextView) findViewById(R.id.choise_meet_place);
		mCancle = (TextView) findViewById(R.id.meet_calcle);
	}
	@Override
	protected void setListeners() {
		mToOther.setOnClickListener(this);
		ToMyPlace.setOnClickListener(this);
		mChoisePlace.setOnClickListener(this);
		mCancle.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.to_other_place:
			mHandler.sendMessage(message);
			message.what=1;
			finish();
			break;
		case R.id.to_myplace:
			//获取当前位置坐标并发送message
			mHandler.sendMessage(message);
			message.what=2;
			finish();
			break;
		case R.id.choise_meet_place:
			mHandler.sendMessage(message);
			message.what=3;
			finish();
			break;
		case R.id.meet_calcle:
			finish();
			break;
		}
	}

}
