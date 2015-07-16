package cn.cnic.marathon.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import cn.cnic.marathon.R;
import cn.cnic.marathon.adapter.CheerListAdapter;
import cn.cnic.marathon.adapter.FriendListAdapter;
import cn.cnic.marathon.base.BaseActivity;
import cn.cnic.marathon.sql.CheerDao;

public class CheerActivity extends BaseActivity implements OnClickListener,
		OnValueChangeListener, TextWatcher {

	private LinearLayout backCheer;
	private CustomEditView search;
	private ListView cheerList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheer);
	}

	@Override
	protected void findViewbyId() {
		backCheer = (LinearLayout) findViewById(R.id.back_cheer);
		search = (CustomEditView) findViewById(R.id.search_cheer);
		cheerList = (ListView) findViewById(R.id.cheer_list);
	}

	@Override
	protected void setListeners() {
		backCheer.setOnClickListener(this);
		search.addTextChangedListener(this);
	}

	@Override
	protected void initData() {
		Cursor cursor = new CheerDao(getApplication()).getAllCheers();
		CursorAdapter adapter = new CheerListAdapter(getApplication(), cursor);
		cheerList.setAdapter(adapter);
	}

	@Override
	public void afterTextChanged(Editable arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onValueChange(NumberPicker arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.back_cheer:
			finish();
			break;
		default:
			break;
		}
	}

}
