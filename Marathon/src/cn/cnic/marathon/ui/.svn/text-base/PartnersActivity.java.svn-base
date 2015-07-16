package cn.cnic.marathon.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import cn.cnic.marathon.R;
import cn.cnic.marathon.adapter.FriendListAdapter;
import cn.cnic.marathon.adapter.PartnerListAdapter;
import cn.cnic.marathon.base.BaseActivity;
import cn.cnic.marathon.sql.PartnerDao;

public class PartnersActivity extends BaseActivity implements OnClickListener,
		OnValueChangeListener, TextWatcher {

	private LinearLayout backPartner;
	private CustomEditView search;
	private ListView partnerList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_partners);
	}

	@Override
	protected void findViewbyId() {
		backPartner = (LinearLayout) findViewById(R.id.back_partner);
		search = (CustomEditView) findViewById(R.id.search_partner);
		partnerList = (ListView) findViewById(R.id.partner_list);
	}

	@Override
	protected void setListeners() {
		backPartner.setOnClickListener(this);
		search.addTextChangedListener(this);
	}

	@Override
	protected void initData() {
		Cursor cursor = new PartnerDao(getApplication()).getAllPartners();
		CursorAdapter adapter = new PartnerListAdapter(getApplication(), cursor);
		partnerList.setAdapter(adapter);
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
		case R.id.back_partner:
			finish();
			break;
		default:
			break;
		}
	}

}
