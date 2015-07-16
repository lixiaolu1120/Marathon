package cn.cnic.marathon.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.cnic.marathon.R;
import cn.cnic.marathon.base.BaseActivity;

public class PlayerInfoActivity extends BaseActivity {
	LinearLayout mBack;
	TextView mName;
	ImageView mImageView;
	WebView mWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_playerinfo);
	}

	@Override
	protected void findViewbyId() {
		mBack = (LinearLayout) findViewById(R.id.back);
		mName = (TextView) findViewById(R.id.player_info_name);
		mWebView = (WebView) findViewById(R.id.palyer_info_webview);
		mImageView = (ImageView) findViewById(R.id.player_info_img);
	}

	@Override
	protected void setListeners() {
		mBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}

	@Override
	protected void initData() {
		Intent intent = getIntent();
		// String dataType = intent.getStringExtra("dtype");
		String name = intent.getStringExtra("name");
		mName.setText(name);
		Bitmap bitmap=intent.getParcelableExtra("bitmap");
		mImageView.setImageBitmap(bitmap);
		// mImageView.setImageBitmap(bm);
		String desc = intent.getStringExtra("desc");
		WebSettings ws = mWebView.getSettings();
		ws.setJavaScriptEnabled(false);
		ws.setAllowFileAccess(true);
		ws.setBuiltInZoomControls(false);
		ws.setSupportZoom(false);
		/**
		 * 设置网页布局类型： 1、LayoutAlgorithm.NARROW_COLUMNS ： 适应内容大小
		 * 2、LayoutAlgorithm.SINGLE_COLUMN: 适应屏幕，内容将自动缩放
		 */
		ws.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		ws.setDefaultTextEncodingName("utf-8"); // 设置文本编码
		ws.setAppCacheEnabled(true);
		ws.setCacheMode(WebSettings.LOAD_DEFAULT);// 设置缓存模式</span>
		mWebView.loadDataWithBaseURL(null, desc, "text/html", "utf-8", null);
	}
}
