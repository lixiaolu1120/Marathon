package cn.cnic.marathon.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import cn.cnic.marathon.R;
import cn.cnic.marathon.adapter.ScheduAdapter;
import cn.cnic.marathon.base.BaseActivity;
import cn.cnic.marathon.http.NetUtils;
import cn.cnic.marathon.http.NetWork.CallBack;
import cn.cnic.marathon.http.response.Response;
import cn.cnic.marathon.sql.DBOpenHelper;
import cn.cnic.marathon.sql.HelperDao;
import cn.cnic.marathon.util.ItUtils;

import com.android.volley.RequestQueue;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * 日程安排
 * 
 * @author cuixipeng
 */
public class ScheduActivity extends BaseActivity {
	private TextView mBackText,title,context;
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedu);
		intent=getIntent();
		findViewbyId();
		initView();
		setListeners();
	}

	/**
	 * 查找id
	 */
	@Override
	protected void findViewbyId() {
		mBackText = (TextView) findViewById(R.id.back);
		title = (TextView) findViewById(R.id.schedu_title);
		title.setText(intent.getExtras().getString("title"));
		context = (TextView) findViewById(R.id.schedu_context);
		context.setText(intent.getExtras().getString("context"));
	}
	/**
	 * 设置监听事件
	 */
	@Override
	protected void setListeners() {
		// 返回按鈕
		mBackText.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}
	
}
