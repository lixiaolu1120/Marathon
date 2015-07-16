package cn.cnic.marathon.ui;

import java.util.ArrayList;
import java.util.List;

import android.R.bool;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.GridView;
import cn.cnic.marathon.R;
import cn.cnic.marathon.adapter.NearGridViewAdapter;
import cn.cnic.marathon.base.BaseActivity;
import cn.cnic.marathon.util.Utils;
/**
 * 附近界面,activity实现类似dialog功能 包含：附近的里程点、饮料、饮水、食物补给站、洗手间、收容站、手机电站、医疗点选项卡
 * 
 * @author cuixipeng
 * 
 */
public class NearlyActivity extends BaseActivity implements
		OnCheckedChangeListener, OnItemClickListener, OnClickListener {
	GridView gridView;
	List<String> mList = new ArrayList<String>();
	NearGridViewAdapter mAdapter;
	private CheckBox all, invert, clear;
	private Button mSure, mCancle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_nearly);
		findViewbyId();
		initView();
		setListeners();
	}

	@Override
	protected void initView() {
		int[] image = { R.drawable.arow, R.drawable.arow, R.drawable.arow,
				R.drawable.arow, R.drawable.arow, R.drawable.arow };
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		mAdapter = new NearGridViewAdapter(image, getApplicationContext());
		gridView.setAdapter(mAdapter);
		all.setOnCheckedChangeListener(this);
		invert.setOnCheckedChangeListener(this);
		clear.setOnCheckedChangeListener(this);
	}

	@Override
	protected void findViewbyId() {
		gridView = (GridView) findViewById(R.id.gridview);
		all = (CheckBox) findViewById(R.id.all_select);
		invert = (CheckBox) findViewById(R.id.invert_select);
		clear = (CheckBox) findViewById(R.id.non_select);
		mSure = (Button) findViewById(R.id.sure);
		mCancle = (Button) findViewById(R.id.cancle);
	}

	@Override
	protected void setListeners() {
		gridView.setOnItemClickListener(this);
		mSure.setOnClickListener(this);
		mCancle.setOnClickListener(this);

	}

	@Override
	public void onCheckedChanged(CompoundButton v, boolean arg1) {
		switch (v.getId()) {
		case R.id.all_select:
			Boolean check = all.isChecked();
			if (check) {
				invert.setChecked(false);
				clear.setChecked(false);
				for (int i = 0; i < 6; i++) {
					mAdapter.chiceStates(i);
				}
			}
			break;
		case R.id.invert_select:
			Boolean checks = invert.isChecked();
			if (checks) {
				all.setChecked(false);
				clear.setChecked(false);
				for (int i = 0; i < 6; i++) {
					mAdapter.invertChiceState(i);
				}
			}
			break;
		case R.id.non_select:
			Boolean noncheck = clear.isChecked();
			if (noncheck) {
				all.setChecked(false);
				invert.setChecked(false);
				for (int i = 0; i < 6; i++) {
					mAdapter.clearChiceState(i);
				}
			}
			break;
		}
	}

	ArrayList<Integer> list = new ArrayList<Integer>();
	boolean status[] = { false, false, false, false, false, false };
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long arg3) {
		all.setChecked(false);
		clear.setChecked(false);
		invert.setChecked(false);
		mAdapter.chiceState(position);
		status[position] = !status[position];
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.sure:
			// 提交请求
			for (int i = 0; i < status.length; i++) {
				if (status[i])
					list.add(i);
				// 通过选中的位置position在地图上标注mark
//				Utils.ToastMSG(getApplicationContext(), "您选中了第" + list.get(i)
//						+ "个位置");
			}
			Intent intent = new Intent(NearlyActivity.this, BMapActivity.class);
			intent.putIntegerArrayListExtra("items", list);
			
			NearlyActivity.this.startActivity(intent);
			NearlyActivity.this.finish();
			Utils.MARK_FLAG = true;
			break;
		case R.id.cancle:
			finish();
			break;
		}
	}
}
