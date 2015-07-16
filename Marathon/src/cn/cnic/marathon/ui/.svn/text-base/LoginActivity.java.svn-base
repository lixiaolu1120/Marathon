package cn.cnic.marathon.ui;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.cnic.marathon.R;
import cn.cnic.marathon.base.BaseActivity;
import cn.cnic.marathon.base.Regexps;
import cn.cnic.marathon.base.UserInfo;
import cn.cnic.marathon.http.NetWork;
import cn.cnic.marathon.http.NetWork.CallBack;
import cn.cnic.marathon.http.request.Login;
import cn.cnic.marathon.http.request.Request;
import cn.cnic.marathon.http.response.Response;
import cn.cnic.marathon.rescript.UserStatus;
import cn.cnic.marathon.service.FriendsInfoRequestService;
import cn.cnic.marathon.service.PushService;
import cn.cnic.marathon.service.RegisterPushService;
import cn.cnic.marathon.tools.UtilTool;
import cn.cnic.marathon.util.ItUtils;
import cn.cnic.marathon.util.Utils;

public class LoginActivity extends BaseActivity implements OnClickListener {

	private EditText tvTel;
	private EditText tvPassword;
	private Button btnLogin, btnRegister;
	private TextView tvBack;
	String name, password;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		findViewbyId();
		setListeners();
	}

	@Override
	protected void initView() {
		findViewbyId();
		setListeners();
	}

	@Override
	protected void findViewbyId() {
		btnLogin = (Button) findViewById(R.id.login_btn);
		btnRegister = (Button) findViewById(R.id.register_btn);
		tvTel = (EditText) findViewById(R.id.tel_login);
		tvPassword = (EditText) findViewById(R.id.password_login);
		tvBack = (TextView) findViewById(R.id.back);
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.login_btn:
			String phone = tvTel.getText().toString();
			String password = tvPassword.getText().toString();
			if ("".equals(phone) || "".equals(password)) {
				Utils.ToastMSG(getApplicationContext(), "用户名或密码不能为空");
				return;
			}
			// 验证是否为手机号
			if (!Utils.isMobileNO(phone)) {
				Utils.ToastMSG(getApplicationContext(), "手机号格式不正确");
				return;
			}
			if (UtilTool.isOnline(getApplicationContext())) {
				Request request = new Login(phone, password);
				doPost(request);
			} else {
				Toast.makeText(getApplicationContext(), "请检查您的网络~",
						Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.register_btn:
			ItUtils.intent(LoginActivity.this, RegisterActivity.class);
		case R.id.back:
			finish();
			break;
		default:
			break;
		}
	}

	public void doPost(Request request) {
		NetWork.initNetWork(request, LoginActivity.this, new LoginCallback());
	}

	class LoginCallback implements CallBack {
		@Override
		public void onRequestComplete(Response result) {
			Map<String, Object> content = result.getContent();
			Boolean isSuccess = result.isSuccess();
			Object data = content.get("data");
			if (isSuccess && null != data) {
				Log.i("DATA", "response text is " + data.toString());
				Pattern p = Pattern.compile(Regexps.UID);
				Matcher m = p.matcher(data.toString());
				String uid = "";
				if (m.find()) {
					uid = m.group(1);
				}
				Log.i("DATA", "response uid is " + uid);
				Log.d("myuid", "response uid is " + Utils.MD5(uid));
				UserInfo user = new UserInfo(uid, tvTel.getText().toString());
				UserStatus.putUser(user);
				PushService.actionStart(getApplicationContext());
				new Thread(new Runnable() {
					@Override
					public void run() {
						RegisterPushService.register(getApplicationContext());
					}
				}).start();
				new FriendsInfoRequestService(LoginActivity.this);
				Utils.ToastMSG(getApplicationContext(), "登录成功");
				finish();
			} else {
				Utils.ToastMSG(getApplicationContext(), "用户名密码不正确");
			}
		}
	}

	@Override
	protected void setListeners() {
		btnLogin.setOnClickListener(this);
		btnRegister.setOnClickListener(this);
		tvBack.setOnClickListener(this);
	}
}
