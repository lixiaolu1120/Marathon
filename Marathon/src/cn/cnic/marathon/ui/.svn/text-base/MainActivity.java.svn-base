//package cn.cnic.marathon.ui;
//
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import com.android.volley.Request.Method;
//import com.android.volley.RequestQueue;
//import com.android.volley.VolleyError;
//import com.android.volley.Response.ErrorListener;
//import com.android.volley.Response.Listener;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.Volley;
//import com.baidu.location.BDLocation;
//import com.baidu.location.BDLocationListener;
//import com.baidu.location.LocationClient;
//import com.baidu.location.LocationClientOption;
//import com.baidu.mapapi.SDKInitializer;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.GridView;
//import android.widget.TextView;
//import android.widget.Toast;
//import cn.cnic.marathon.R;
//import cn.cnic.marathon.adapter.FriendsGridViewAdapter;
//import cn.cnic.marathon.adapter.SurroundListAdapter;
//import cn.cnic.marathon.base.BaseActivity;
//import cn.cnic.marathon.sql.HelperDao;
//import cn.cnic.marathon.util.ItUtils;
//import cn.cnic.marathon.util.Utils;
//
///**
// * 主页 技术点：gridview
// * 
// * @author cuixipeng
// */
//
//public class MainActivity extends BaseActivity implements OnClickListener {
//	private TextView mWeather, mSet;
//	private GridView mGridView;
//	private FriendsGridViewAdapter mHGVAdapter;
//	private List<String> mList;
//	// 定位
//	LocationClient locationClient = null;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		Utils.resources = this.getResources();
//		SDKInitializer.initialize(getApplicationContext());
//		setContentView(R.layout.activity_main);
//		locationClient = new LocationClient(this);
//		LocationClientOption option = new LocationClientOption();
//		option.setOpenGps(true);
//		option.setAddrType("all");// 返回的定位结果包含地址信息
//		option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
//		option.setScanSpan(10000);// 设置发起定位请求的间隔时间为10000ms
//		locationClient.setLocOption(option);
//		// 注册位置监听器
//		locationClient.registerLocationListener(new BDLocationListener() {
//			@Override
//			public void onReceiveLocation(BDLocation location) {
//				if (location == null) {
//					return;
//				}
//				double latitude = location.getLatitude();
//				double longitude = location.getLongitude();
//			}
//		});
//		locationClient.start();
//	}
//
//	@Override
//	protected void onDestroy() {
//		super.onDestroy();
//		if (locationClient != null && locationClient.isStarted()) {
//			locationClient.stop();
//			locationClient = null;
//		}
//	}
//
//	@Override
//	protected void onStart() {
//		super.onStart();
//		// 暂时定义数据集合
//		mList = new ArrayList<String>();
//		mList.add("日常列表");
//		mList.add("地图");
//		mList.add("运动员");
//		mList.add("实况");
//		mList.add("观众");
//		mList.add("广告");
//		mList.add("合作伙伴");
//		mList.add("周边");
//		mList.add("社交");
//
//		initView();
//	}
//
//	/**
//	 * 初始化 开始
//	 */
//	@Override
//	protected void initView() {
//		findViewbyId();
//		setListeners();
//		mHGVAdapter = new FriendsGridViewAdapter(mList, getApplicationContext());
//		mGridView.setAdapter(mHGVAdapter);
//	}
//
//	public void initTimePost(final String lat, final String lon) {
//		final ProgressDialog progressDialog = ProgressDialog.show(this, "",
//				"loading...");
//		RequestQueue requestQueue = Volley
//				.newRequestQueue(getApplicationContext());
//		JsonObjectRequest request = new JsonObjectRequest(Method.POST, "",
//				null, new Listener<JSONObject>() {
//
//					@Override
//					public void onResponse(JSONObject arg0) {
//
//					}
//				}, new ErrorListener() {
//
//					@Override
//					public void onErrorResponse(VolleyError arg0) {
//
//					}
//				}) {
//			@Override
//			protected Map<String, String> getParams() {
//				// 在这里设置需要post的参数
//				Map<String, String> params = new HashMap<String, String>();
//				params.put("", lat);
//				params.put("name2", lon);
//				return params;
//			}
//		};
//		requestQueue.add(request);
//	}
//
//	@Override
//	protected void findViewbyId() {
//		mWeather = (TextView) findViewById(R.id.weather);
//		mSet = (TextView) findViewById(R.id.set);
//		mGridView = (GridView) findViewById(R.id.homegridview);
//
//	}
//
//	/**
//	 * 初始化结束， 点击事件
//	 */
//
//	@Override
//	protected void setListeners() {
//		mWeather.setOnClickListener(this);
//		mSet.setOnClickListener(this);
//		mGridView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> view, View convertview,
//					int position, long id) {
//				TextView textView = (TextView) convertview
//						.findViewById(R.id.homegriditemtext);
//				// String title = textView.getText().toString();
//				if (position == 0) {
//					ItUtils.intent(MainActivity.this, ScheduActivity.class);
//
//				}
//				if (position == 1) {
//					ItUtils.intent(MainActivity.this, BMapActivity.class);
//				}
//				if (position == 2) {
//					ItUtils.intent(MainActivity.this, PlayerActivity.class);
//				}
//				if (position == 3) {
//					ItUtils.intent(MainActivity.this, ActuallyActivity.class);
//				}
//				if (position == 4) {
//					ItUtils.intent(MainActivity.this, AudienceActivity.class);
//				}
//				if (position == 5) {
//					ItUtils.intent(MainActivity.this, AdvertActivity.class);
//				}
//				if (position == 6) {
//					ItUtils.intent(MainActivity.this, PartnersActivity.class);
//				}
//				if (position == 7) {
//					// ItUtils.intent(MainActivity.this,
//					// SurroundActivity.class);
//					Intent intent = new Intent();
//					intent.setClass(MainActivity.this, SurroundActivity.class);
//					// intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
//					// | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//					startActivity(intent);
//				}
//				if (position == 8) {
//					ItUtils.intent(MainActivity.this, SocialActivity.class);
//				}
//
//			}
//		});
//	}
//
//	@Override
//	public void onClick(View v) {
//		switch (v.getId()) {
//		case R.id.weather:
//			Intent weather = new Intent();
//			weather.setClass(this, WeatherActivity.class);
//			startActivity(weather);
//			break;
//		case R.id.set:
//			Intent set = new Intent();
//			set.setClass(this, SetActivity.class);
//			startActivity(set);
//			break;
//
//		}
//
//	}
//
//}
