package cn.cnic.marathon.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.cnic.marathon.R;
import cn.cnic.marathon.http.NetWork;
import cn.cnic.marathon.http.NetWork.CallBack;
import cn.cnic.marathon.http.request.MeetRequest;
import cn.cnic.marathon.http.request.Request;
import cn.cnic.marathon.http.response.Response;
import cn.cnic.marathon.rescript.UserStatus;
import cn.cnic.marathon.util.Utils;

/**
 * 给好友发送消息弹出框
 * 
 * @author <email>liuxiaoer@cnic.cn</email>
 * 
 */
public class FriendMeetingSendmessagePopupwindow extends RelativeLayout
		implements OnClickListener {

	private Context context;
	private ImageView friendAvater;
	private TextView friendName, title;
	private Button submit, cancle;
	private EditText message;
	private String friendId;

	public FriendMeetingSendmessagePopupwindow(Context context) {
		super(context);
		this.context = context;
		initializeView(context);
		initalizeListener();
	}

	public FriendMeetingSendmessagePopupwindow(Context context,
			AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	public FriendMeetingSendmessagePopupwindow(Context context,
			AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
	}

	void initializeView(Context context) {
		RelativeLayout.inflate(context, R.layout.meeting_send_message_popupwindow, this);
		friendAvater = (ImageView) findViewById(R.id.friend_avatar);
		friendName = (TextView) findViewById(R.id.friend_name);
		title = (TextView) findViewById(R.id.title);
		submit = (Button) findViewById(R.id.submit);
		cancle = (Button) findViewById(R.id.cancle);
		message = (EditText) findViewById(R.id.message);
	}

	void initalizeListener() {
		submit.setOnClickListener(this);
		cancle.setOnClickListener(this);
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

	public void setFriendAvater(Bitmap bitmap) {
		this.friendAvater.setImageBitmap(bitmap);
	}

	public void setFriendAvater(int resid) {
		this.friendAvater.setBackgroundResource(resid);
	}

	public void setFriendName(String friendName) {
		this.friendName.setText(friendName);
	}

	public void setTitle(String title) {
		this.title.setText(title);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.submit:
			String msg = message.getText().toString();
			Request request = new MeetRequest(UserStatus.getUser().getUid(),
					friendId, msg, 0f, 0f);
			NetWork.initNetWork(request, context, new MeetRequestCallback());
			break;
		case R.id.cancle:
			setVisibility(View.GONE);
			break;
		default:
			break;
		}
	}

	/**
	 * 请求网络结果 邀请好友见面
	 * 
	 * @email <email>liuxiaoer@cnic.cn</email>
	 */
	class MeetRequestCallback implements CallBack {
		@Override
		public void onRequestComplete(Response result) {
			Boolean isSuccess = result.isSuccess();
			if (isSuccess) {
				setVisibility(View.GONE);
				Utils.ToastMSG(context.getApplicationContext(), "发送成功");
			} else {
				Utils.ToastMSG(context.getApplicationContext(), "发送失败");
			}
		}
	}
}
