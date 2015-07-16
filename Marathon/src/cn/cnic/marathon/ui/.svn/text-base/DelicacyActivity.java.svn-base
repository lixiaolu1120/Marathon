package cn.cnic.marathon.ui;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import cn.cnic.marathon.R;
import cn.cnic.marathon.adapter.DelicacyAdapter;
import cn.cnic.marathon.base.BaseActivity;
import cn.cnic.marathon.util.Utils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * 百度地图周边 1.美食酒店景點等
 * 
 * @author cuixipeng 2015年5月6日15:45:20
 */
public class DelicacyActivity extends BaseActivity {
	private static final String TAG = DelicacyActivity.class.getSimpleName();
	private PullToRefreshListView mListView;
	private DelicacyAdapter mAdapter;
	Map<String, Object> map;
	// listview填充的数据集合
	List<Map<String, Object>> mList = new ArrayList<Map<String, Object>>();
	// 定位
	LocationClient locationClient = null;
	//back 
	private TextView mDelicacyBack;
	int page_num = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.activity_delicacy);
		final String nameVaule = getIntent().getStringExtra(Utils.KEY);
		// // 初始化搜索模块，注册搜索事件监听
		initView();
		try {
			//start network
			initData(nameVaule, Utils.latitude, Utils.longitude);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param 开启网络
	 */
	List<String> mLists = new ArrayList<String>();
	List<String> list = new ArrayList<String>();

	public void initData(String nameVaule, double latitude, double longitude)
			throws Exception {
		final ProgressDialog progressDialog = ProgressDialog.show(this, "",
				"loading...");
		RequestQueue requestQueue = Volley
				.newRequestQueue(getApplicationContext());
		String query = URLEncoder.encode(nameVaule);
		String url = "http://api.map.baidu.com/place/v2/search?query=" + query
				+ "&&location=" + latitude + "," + longitude + "&radius=2000"
				+ "&output=json&ak=IEXPk9ZrTXdAA3Egt1DhXMiN&page_num="
				+ page_num + "&scope=2";
		JsonObjectRequest request = new JsonObjectRequest(url, null,
				new Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						if (progressDialog.isShowing()
								&& progressDialog != null) {
							progressDialog.dismiss();
						}
						try {
							JSONArray array = response
									.getJSONArray(Utils.Results);
							for (int i = 0; i < array.length(); i++) {
								map = new HashMap<String, Object>();
								JSONObject obj = array.getJSONObject(i);
								String name = obj.getString(Utils.Name);
								JSONObject detail = obj
										.getJSONObject(Utils.Detail_info);
								String price = null;
								if (detail.has(Utils.Price)) {
									price = detail.getString(Utils.Price);
									map.put(Utils.Price, price);
								} else {
									map.put(Utils.Price, Utils.TAB);
								}
								String rating;
								if (detail.has(Utils.Overall_rating)) {
									rating = detail
											.getString(Utils.Overall_rating);
									map.put(Utils.Rating, rating);
								} else {
									map.put(Utils.Rating, Utils.TAB);

								}
								String tag;

								if (detail.has(Utils.Tag)) {
									tag = detail.getString(Utils.Tag);
									map.put(Utils.Tag, tag);
								} else {
									map.put(Utils.Tag, Utils.TAB);

								}
//								/**
//								 * 获取商户详情的HTML页面--开始
//								 */
//								final String detail_url = detail
//										.getString("detail_url");
//								Log.d("detail_url", detail_url);
//								final String imgUrl = null;
//								new Thread(new Runnable() {
//									@Override
//									public void run() {
//										try {
//											Log.d("asdasdasd", "开启");
//
////											String imgurl = HTMLDataUtil
////													.HtmlData(detail_url);
////											map.put("imgUrl", imgurl);
////											Log.d("asdasdasd", imgurl);
//
//										} catch (Exception e) {
//											e.printStackTrace();
//										}
//
//									}
//								}).start();
//								/**
//								 * 获取商户详情的HTML页面---结束
//								 */
								map.put(Utils.Name, name);
								mList.add(map);
								Log.d("asdsda", mList + "");
							}
							mAdapter = new DelicacyAdapter(mList,
									getApplicationContext());
							mListView.setAdapter(mAdapter);
							mListView.setMode(Mode.PULL_FROM_END);
						} catch (JSONException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError arg0) {
						progressDialog.dismiss();
						Utils.Toast(getApplicationContext());
					}
				});
		request.setRetryPolicy(new DefaultRetryPolicy(1  * 1000, 1, 1.0f));
		requestQueue.add(request);
	}

	/**
	 * init
	 */
	@Override
	protected void initView() {
		findViewbyId();
		setListeners();
	}

	/**
	 * find id
	 */
	@Override
	protected void findViewbyId() {
		mListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_delicaty_listview);
		mDelicacyBack = (TextView) findViewById(R.id.back);
	}

	@Override
	protected void setListeners() {
		mDelicacyBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		// 上拉刷新点击事件
		mListView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				String label = DateUtils.formatDateTime(
						getApplicationContext(), System.currentTimeMillis(),
						DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE
								| DateUtils.FORMAT_ABBREV_ALL);
				// 设置刷新标签
				refreshView.getLoadingLayoutProxy().setRefreshingLabel("正在加载");
				// 设置下拉标签
				refreshView.getLoadingLayoutProxy().setPullLabel("上拉加载更多");
				// 设置释放标签
				refreshView.getLoadingLayoutProxy().setReleaseLabel("释放开始加载");
				// 设置上次刷新额提示标签
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
				// Do work to refresh the list here.
				new GetDataTask().execute();
			}
		});
		// listview点击事件
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View convertview,
					int position, long id) {
				// ItUtils.putData(DelicacyActivity.this,
				// ScheduContentActivity.class, "title",
				// mList.get(position).get("name").toString());
			}
		});
	}

	/**
	 * 异步处理下拉刷新时间
	 */
	private class GetDataTask extends AsyncTask<Void, Void, String> {
		// 后台处理部分
		@Override
		protected String doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			String str = "Added after refresh...I add";
			return str;
		}

		// 这里是对刷新的响应，可以利用addFirst（）和addLast()函数将新加的内容加到LISTView中
		// 根据AsyncTask的原理，onPostExecute里的result的值就是doInBackground()的返回值
		@Override
		protected void onPostExecute(String result) {
			// 在头部增加新添内容
			// mListItems.addFirst(result);
			// 通知程序数据集已经改变，如果不做通知，那么将不会刷新mListItems的集合
			mAdapter.notifyDataSetChanged();
			// Call onRefreshComplete when the list has been refreshed.
			mListView.onRefreshComplete();
			super.onPostExecute(result);
		}
	}

}
