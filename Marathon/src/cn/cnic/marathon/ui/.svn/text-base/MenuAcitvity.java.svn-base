package cn.cnic.marathon.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.cnic.marathon.R;
import cn.cnic.marathon.adapter.SettingListAdapter;
import cn.cnic.marathon.base.BaseActivity;
import cn.cnic.marathon.base.UserInfo;
import cn.cnic.marathon.rescript.UserStatus;
import cn.cnic.marathon.util.ItUtils;
import cn.cnic.marathon.util.Utils;

public class MenuAcitvity extends BaseActivity implements OnTouchListener,
		OnGestureListener, OnClickListener, OnItemClickListener {
	private RelativeLayout userSetting, unLogin;
	private LinearLayout logined;
	private ListView settingList;
	private ImageView avatar;
	private TextView userName;
	private GestureDetector mGestureDetector;
	private int verticalMinDistance = 180;
	private int minVelocity = 0;
	private LinearLayout layout;
	// 菜单列表数据
	List<Map<String, String>> data;
	// 菜单列表类型
	public final int FRIEND_ITEM = 1, INTRODUCTION = 2, SCHEDULE_ITEM = 9,
			TEAM = 3, RUNNER_ITEM = 4, CHEER_ITEM = 5, PARTNER_ITEM = 6,
			REAL_TIME = 7, ABOUT_US_TIME = 8;

	private void initGesture() {
		mGestureDetector = new GestureDetector((OnGestureListener) this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		initGesture();
	}

	@Override
	protected void initData() {
		// 设置用户是否登录
		if (UserStatus.isLogined()) {
			Log.i("DATA", "user logined");
			unLogin.setVisibility(View.GONE);
			userSetting.setVisibility(View.VISIBLE);

			UserInfo user = UserStatus.getUser();
			userName.setText(user.getNickname() == "" ? user.getTel() : user
					.getNickname());
			if (!"".equals(user.getAvatar())) {
				Bitmap bm = new BitmapFactory().decodeFile(user.getAvatar());
				avatar.setImageBitmap(bm);
			}
		} else {
			userSetting.setVisibility(View.GONE);
			unLogin.setVisibility(View.VISIBLE);
		}
		initSettingList();
	}

	private void initSettingList() {
		data = new ArrayList<Map<String, String>>();
		Map<String, String> friendMgr = new HashMap<String, String>();
		friendMgr.put("logo", Utils.resources.getString(R.string.haoyou));
		friendMgr.put("title",
				Utils.resources.getString(R.string.friends_manage));
		friendMgr.put("tool", Utils.resources.getString(R.string.arrow_list));
		friendMgr.put("type", "" + FRIEND_ITEM);
		Map<String, String> introduction = new HashMap<String, String>();
		introduction.put("logo", Utils.resources.getString(R.string.guize));
		introduction.put("title",
				Utils.resources.getString(R.string.introduction));
		introduction
				.put("tool", Utils.resources.getString(R.string.arrow_list));
		introduction.put("type", "" + INTRODUCTION);
		Map<String, String> team = new HashMap<String, String>();
		team.put("logo", Utils.resources.getString(R.string.team));
		team.put("title", Utils.resources.getString(R.string.team_info));
		team.put("tool", Utils.resources.getString(R.string.arrow_list));
		team.put("type", "" + TEAM);
		Map<String, String> runner = new HashMap<String, String>();
		runner.put("logo", Utils.resources.getString(R.string.yundongyuan));
		runner.put("title", Utils.resources.getString(R.string.runner_info));
		runner.put("tool", Utils.resources.getString(R.string.arrow_list));
		runner.put("type", "" + RUNNER_ITEM);
		Map<String, String> about = new HashMap<String, String>();
		about.put("logo", Utils.resources.getString(R.string.guanyu));
		about.put("title", Utils.resources.getString(R.string.about_us));
		about.put("tool", Utils.resources.getString(R.string.arrow_list));
		about.put("type", "" + ABOUT_US_TIME);
		data.add(friendMgr);
		data.add(introduction);
		data.add(team);
		data.add(runner);
		data.add(about);
		ListAdapter adapter = new SettingListAdapter(getApplicationContext(),
				data);
		settingList.setAdapter(adapter);
	}

	@Override
	protected void findViewbyId() {
		userSetting = (RelativeLayout) findViewById(R.id.user_setting);
		unLogin = (RelativeLayout) findViewById(R.id.unlogin);
		settingList = (ListView) findViewById(R.id.setting_list);
		avatar = (ImageView) findViewById(R.id.user_avatar);
		userName = (TextView) findViewById(R.id.user_name);
		// layout = (LinearLayout) findViewById(R.id.menu_linear_width);

	}

	@Override
	protected void setListeners() {
		userSetting.setOnClickListener(this);
		unLogin.setOnClickListener(this);
		settingList.setOnItemClickListener(this);
	}

	@Override
	public boolean onDown(MotionEvent arg0) {
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		if (e1.getX() - e2.getX() > verticalMinDistance
				&& Math.abs(velocityX) > minVelocity) {
			finish();
		}
		return false;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent arg0) {

	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		return false;
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		return false;
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		mGestureDetector.onTouchEvent(ev);
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public void onClick(View view) {
		Log.i("click", view.getId() + "");
		switch (view.getId()) {
		case R.id.user_setting:
			ItUtils.intent(MenuAcitvity.this, UserInfoActivity.class);
			finish();
			break;
		case R.id.unlogin:
			ItUtils.intent(MenuAcitvity.this, LoginActivity.class);
			finish();
			break;
		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view,
			int position, long id) {
		int type = Integer.parseInt(data.get(position).get("type"));
		switch (type) {
		case FRIEND_ITEM:
			if (UserStatus.isLogined()) {
				ItUtils.intent(MenuAcitvity.this, FriendMangerActivity.class);
				finish();
			} else {
				ItUtils.intent(MenuAcitvity.this, LoginActivity.class);
				finish();
			}
			break;
		case INTRODUCTION:
			ItUtils.intent(MenuAcitvity.this, GameIntroductionActivity.class);
			finish();
			break;
		case TEAM:
			ItUtils.intent(MenuAcitvity.this, TeamActivity.class);
			finish();
			break;
		case RUNNER_ITEM:
			ItUtils.intent(MenuAcitvity.this, StarRunnerActivity.class);
			finish();
			break;
		case CHEER_ITEM:
			ItUtils.intent(MenuAcitvity.this, CheerActivity.class);
			finish();
			break;
		case PARTNER_ITEM:
			ItUtils.intent(MenuAcitvity.this, PartnersActivity.class);
			finish();
			break;
		case SCHEDULE_ITEM:
			ItUtils.intent(MenuAcitvity.this, ScheduActivity.class);
			finish();
			break;
		case ABOUT_US_TIME:
			ItUtils.intent(MenuAcitvity.this, AboutUsActivity.class);
			finish();
			break;

		default:
			break;
		}
	}

}
