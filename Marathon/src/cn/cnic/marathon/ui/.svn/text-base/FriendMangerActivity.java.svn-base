package cn.cnic.marathon.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.Photo;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.cnic.marathon.R;
import cn.cnic.marathon.adapter.FriendListAdapter;
import cn.cnic.marathon.base.BaseActivity;
import cn.cnic.marathon.http.NetWork;
import cn.cnic.marathon.http.NetWork.CallBack;
import cn.cnic.marathon.http.request.AddOrUpLoadFriendRequest;
import cn.cnic.marathon.http.request.DeleteFriendRequest;
import cn.cnic.marathon.http.request.Request;
import cn.cnic.marathon.http.response.Response;
import cn.cnic.marathon.rescript.UserStatus;
import cn.cnic.marathon.service.FriendsInfoRequestService;
import cn.cnic.marathon.sql.DBManager;
import cn.cnic.marathon.sql.HelperDao;
import cn.cnic.marathon.tools.UtilTool;
import cn.cnic.marathon.util.Utils;

public class FriendMangerActivity extends BaseActivity implements
		OnClickListener, OnValueChangeListener, TextWatcher,
		OnItemLongClickListener {
	List<Map<String, Object>> mList = new ArrayList<Map<String, Object>>();;
	private RelativeLayout friendManagerLayout;
	PopupWindow mPop = null;
	private LinearLayout backFriends;
	private CustomTextView addFriend;
	private CustomEditView search;
	private ListView friendList;
	private Button mSearchAddressBook, mTipCancle, mSearchFriend;
	String username, usernumber;
	EditText editText;
	private TextView mComplete;
	boolean removeFlag = false;
	FriendListAdapter adapter;
	RelativeLayout messageLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friend);
	}

	@Override
	protected void findViewbyId() {
		friendManagerLayout = (RelativeLayout) findViewById(R.id.friend_manager_layout);
		backFriends = (LinearLayout) findViewById(R.id.back_friends);
		addFriend = (CustomTextView) findViewById(R.id.add_friends);
		search = (CustomEditView) findViewById(R.id.search_friend);
		friendList = (ListView) findViewById(R.id.friend_list);
		mComplete = (TextView) findViewById(R.id.complete);
		messageLayout = new FriendMeetingSendmessagePopupwindow(this);
		//friendManagerLayout.addView(messageLayout);
	}

	@Override
	protected void setListeners() {
		backFriends.setOnClickListener(this);
		friendList.setOnItemLongClickListener(this);
		addFriend.setOnClickListener(this);
		search.addTextChangedListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.back_friends:
			finish();
			break;
		case R.id.add_friends:
			window(addFriend);
			break;
		}
	}

	@Override
	protected void initData() {
		mList.clear();
		mList = HelperDao.RawQuery(getApplicationContext(),
				DBManager.FRIEND_DAO);
		if (mList.size() == 0) {
			return;
		}
		Log.d("hello", mList + "");
		adapter = new FriendListAdapter(mList, FriendMangerActivity.this,
				mComplete, addFriend);
		friendList.setAdapter(adapter);
	}

	public void click(View v) {
		// startActivityForResult(new Intent(Intent.ACTION_PICK,
		// ContactsContract.Contacts.CONTENT_URI), 0);
		mPop.dismiss();
		TipWindow(addFriend);
	}

	/**
	 * 添加好友的pop
	 * 
	 * @param parent
	 * @return
	 */
	public PopupWindow window(View parent) {
		if (mPop == null) {
			WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
			int width = wm.getDefaultDisplay().getWidth();
			LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View view = layoutInflater.inflate(R.layout.item_pop_message, null);
			mPop = new PopupWindow(view, width / 3 * 2,
					LayoutParams.WRAP_CONTENT);
			mSearchAddressBook = (Button) view
					.findViewById(R.id.search_address_book);
			mSearchFriend = (Button) view.findViewById(R.id.search_friend);
			editText = (EditText) view.findViewById(R.id.search_edit);
		}
		// 搜索好友
		mSearchFriend.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mContactsNumber.clear();
				String pm = editText.getText().toString();
				if (UtilTool.isMobileNum(pm)) {
					mContactsNumber.add(Utils.MD5(pm));
				}
				Request request = new AddOrUpLoadFriendRequest(UserStatus
						.getUser().getUid(), mContactsNumber, "");
				NetWork.initNetWork(request, FriendMangerActivity.this,
						new addOrUpLoadFriend());
			}
		});
		// 使其聚集
		mPop.setFocusable(true);
		// 设置允许在外点击消失
		mPop.setOutsideTouchable(false);
		// 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
		mPop.setBackgroundDrawable(new BitmapDrawable());
		mPop.showAsDropDown(parent);
		return mPop;

	}

	/**
	 * 访问通讯录是的pop提示框
	 * 
	 * @param parent
	 * @return
	 */
	PopupWindow mPopu;

	public void TipWindow(View parent) {
		if (mPopu == null) {
			WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
			int width = wm.getDefaultDisplay().getWidth();
			LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View view = layoutInflater.inflate(R.layout.item_pop_food, null);
			mPopu = new PopupWindow(view, width / 4 * 3,
					LayoutParams.WRAP_CONTENT);
			mSearchAddressBook = (Button) view.findViewById(R.id.tip_continue);
			mTipCancle = (Button) view.findViewById(R.id.tip_cancle);
		}
		mSearchAddressBook.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				getPhoneContacts();
				mPopu.dismiss();
			}
		});
		mTipCancle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mPopu.dismiss();
			}
		});
		// 使其聚集
		mPopu.setFocusable(true);
		// 设置允许在外点击消失
		mPopu.setOutsideTouchable(false);
		// 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
		mPopu.setBackgroundDrawable(new BitmapDrawable());
		mPopu.showAtLocation(parent, Gravity.CENTER, 0, 0);
	}

	/** 获取库Phon表字段 **/
	private static final String[] PHONES_PROJECTION = new String[] {
			Phone.DISPLAY_NAME, Phone.NUMBER, Photo.PHOTO_ID, Phone.CONTACT_ID };
	private ArrayList<String> mContactsNumber = new ArrayList<String>();
	/** 电话号码 **/
	private static final int PHONES_NUMBER_INDEX = 1;

	public void getPhoneContacts() {
		ContentResolver resolver = getContentResolver();
		// 获取手机联系人
		Cursor phoneCursor = resolver.query(Phone.CONTENT_URI,
				PHONES_PROJECTION, null, null, null);
		if (phoneCursor != null) {
			while (phoneCursor.moveToNext()) {
				// 得到手机号码
				String phoneNumber = phoneCursor.getString(PHONES_NUMBER_INDEX);
				// 当手机号码为空的或者为空字段 跳过当前循环
				if (TextUtils.isEmpty(phoneNumber))
					continue;
				if (UtilTool.isMobileNum(phoneNumber)) {
					mContactsNumber.add(Utils.MD5(phoneNumber));
				}
			}
		}
		// 上传好友
		if (mContactsNumber.size() != 0) {
			Request request = new AddOrUpLoadFriendRequest(UserStatus.getUser()
					.getUid(), mContactsNumber, "");
			NetWork.initNetWork(request, FriendMangerActivity.this,
					new addOrUpLoadFriend());
		}
	}

	class addOrUpLoadFriend implements CallBack {
		@Override
		public void onRequestComplete(Response result) {
			Map<String, Object> content = result.getContent();
			Log.d("sssssss", content + "");
			Boolean isSuccess = result.isSuccess();
			if (isSuccess) {
				Utils.ToastMSG(getApplicationContext(), "发送成功");
			}
		}

	}

	@Override
	public void afterTextChanged(Editable arg0) {

	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {

	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

	}

	@Override
	public void onValueChange(NumberPicker arg0, int arg1, int arg2) {

	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View convertview,
			int position, long id) {
		String fidsString = mList.get(position).get("uid").toString();
		List<String> list = new ArrayList<String>();
		list.add(fidsString);
		deleteDialog(list, position);
		return false;
	}

	/**
	 * 删除好友dialog
	 */
	void deleteDialog(final List<String> list, final int position) {
		AlertDialog.Builder builder = new Builder(FriendMangerActivity.this);
		builder.setMessage("确认要删除么？");
		builder.setTitle("提示");
		builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				// 访问网络
				Request request = new DeleteFriendRequest(UserStatus.getUser()
						.getUid(), list);
				NetWork.initNetWork(request, FriendMangerActivity.this,
						new RemoveCallback());
				if (removeFlag) {
					mList.remove(position);
					adapter.notifyDataSetChanged();
					removeFlag = false;
				}
			}
		});

		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
	}

	class RemoveCallback implements CallBack {
		@Override
		public void onRequestComplete(Response result) {
			Map<String, Object> content = result.getContent();
			Boolean isSuccess = result.isSuccess();
			Object data = content.get("data");
			if (isSuccess && null != data) {
				removeFlag = true;
			}
		}
	}

	/**
	 * 响应好友请求
	 */
	public void handleFriendAgreeMessage(Context context, String content) {
		JSONObject object;
		String message = null, uid = null, nickname = null, time = null;
		int echo = 0;
		try {
			object = new JSONObject(content);
			uid = object.getString("uid");
			nickname = object.getString("nickname");
			message = object.getString("message");
			time = object.getString("time");
			echo = object.getInt("echo");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Log.d("nickname", "echo:" + echo);
		if (echo == 1) {
			Utils.ToastMSG(context, nickname + "同意加您为好友");
		} else {
			Utils.ToastMSG(context, nickname + "拒绝加您为好友");
		}
		Log.d("nickname", "nickname:" + nickname);
		// 加入数据库
		// HelperDao.inserFriend(uid, nickname, message, time, context);
		// mList = HelperDao.RawQuery(context, DBManager.FRIEND_DAO);
		// // adapter.notifyDataSetChanged();
	}
}
