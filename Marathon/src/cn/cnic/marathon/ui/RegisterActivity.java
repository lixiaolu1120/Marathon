package cn.cnic.marathon.ui;

import java.util.Map;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.cnic.marathon.R;
import cn.cnic.marathon.base.BaseActivity;
import cn.cnic.marathon.base.UserInfo;
import cn.cnic.marathon.http.NetWork;
import cn.cnic.marathon.http.NetWork.CallBack;
import cn.cnic.marathon.http.request.CheckUser;
import cn.cnic.marathon.http.request.Login;
import cn.cnic.marathon.http.request.Register;
import cn.cnic.marathon.http.request.Request;
import cn.cnic.marathon.http.response.Response;
import cn.cnic.marathon.rescript.UserStatus;
import cn.cnic.marathon.tools.UtilTool;
import cn.cnic.marathon.util.ItUtils;
import cn.cnic.marathon.util.Utils;

public class RegisterActivity extends BaseActivity implements OnClickListener,
		OnFocusChangeListener {

	private EditText mPhone, mPassword, mRepassword, smsCode, nickName;
	private Button mRegister, smsButton;

	String phone, currentPhone = "", password, repassword;
	private TextView mBack;
	boolean accessRegister = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initView();
	}

	public void doPost(Request request) {
		NetWork.initNetWork(request, RegisterActivity.this,
				new RegisterCallback());
	}

	public void CheckUserPost(Request request) {
		NetWork.initNetWork(request, RegisterActivity.this,
				new CheckUserCallback());
	}

	@Override
	protected void initView() {
		findViewbyId();
		setListeners();
	}

	@Override
	protected void findViewbyId() {
		mPhone = (EditText) findViewById(R.id.register_phone);
		mPassword = (EditText) findViewById(R.id.register_password);
		mRepassword = (EditText) findViewById(R.id.register_repassword);
		mRegister = (Button) findViewById(R.id.btn_register);
		mPhone.setInputType(InputType.TYPE_CLASS_PHONE);
		mBack = (TextView) findViewById(R.id.back);
		smsCode = (EditText) findViewById(R.id.edittext_code);
		smsButton = (Button) findViewById(R.id.sms_code_btn);
		nickName = (EditText) findViewById(R.id.nickname);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_register:
			// 检查能否注册
			if (!checkAccessRegister())
				return;
			// 如果输入的密码有问题
			if (!checkPassword())
				return;
			if (UtilTool.isOnline(getApplicationContext())) {
				Request request = new Register(phone, password, smsCode
						.getText().toString(), nickName.getText().toString(),
						"");
				doPost(request);
			} else {
				Toast.makeText(getApplicationContext(), "请检查您的网络~",
						Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.back:
			finish();
			break;
		}
	}

	@Override
	protected void setListeners() {
		mPhone.setOnFocusChangeListener(this);
		mPassword.setOnFocusChangeListener(this);
		mRepassword.setOnFocusChangeListener(this);
		mRegister.setOnClickListener(this);
		mBack.setOnClickListener(this);
	}

	class RegisterCallback implements CallBack {
		@Override
		public void onRequestComplete(Response result) {
			boolean isSuccess = result.isSuccess();
			if (!isSuccess)
				return;
			Map<String, Object> content = result.getContent();
			String uid = content.get("uid").toString();
			// 设置用户登录状态
			UserStatus.putUser(new UserInfo(uid, phone));
			ItUtils.intent(RegisterActivity.this, BMapActivity.class);
		}
	}

	class CheckUserCallback implements CallBack {
		@Override
		public void onRequestComplete(Response result) {
			// boolean isSuccess = result.isSuccess();
			// if (!isSuccess)
			// return;
			boolean isLegal = Boolean.parseBoolean(result.getContent()
					.get("is_legal").toString());
			Log.d("is_legal", isLegal + "");
			if (isLegal) {
				Utils.ToastMSG(getApplicationContext(), "恭喜该账号可以注册");
				accessRegister = true;
			} else {
				Utils.ToastMSG(getApplicationContext(), "该账号已被注册");
				accessRegister = false;
			}
		}
	}

	/**
	 * 检查能否注册
	 * 
	 * @return
	 */
	private boolean checkAccessRegister() {
		if (!accessRegister) {
			Utils.ToastMSG(getApplicationContext(), "手机号已经被注册");
		}
		return accessRegister;
	}

	/**
	 * 检查密码正确性
	 * 
	 * @return
	 */
	private boolean checkPassword() {
		password = mPassword.getText().toString();
		repassword = mRepassword.getText().toString();
		if ("".equals(password)) {
			Utils.ToastMSG(getApplicationContext(), "请输入密码");
			return false;
		}
		if ("".equals(repassword)) {
			Utils.ToastMSG(getApplicationContext(), "请再次输入密码");
			return false;
		}
		if (!password.equals(repassword)) {
			Utils.ToastMSG(getApplicationContext(), "两次密码不一致");
			return false;
		}
		return true;
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// 不考虑获取焦点事件
		if (hasFocus)
			return;
		switch (v.getId()) {
		case R.id.register_password:
			password = ((TextView) v).getText().toString();
			break;
		case R.id.register_repassword:
			repassword = ((TextView) v).getText().toString();
			break;
		case R.id.register_phone:
			phone = ((TextView) v).getText().toString();
			// 如果手机号没有修改或者没填就不去请求验证
			if (phone.equals(currentPhone) || "".equals(phone))
				return;
			// 验证是否为手机号
			if (!Utils.isMobileNO(phone)) {
				Utils.ToastMSG(getApplicationContext(), "手机号不正确");
				return;
			}
			if ("".equals(currentPhone))
				currentPhone = phone;
			else {
				// 更换手机号验证设置是否可注册状态
				if (currentPhone != phone) {
					accessRegister = false;
				}
			}
			CheckUser request = new CheckUser(phone);
			CheckUserPost(request);
			break;
		default:
			break;
		}
	}
}
