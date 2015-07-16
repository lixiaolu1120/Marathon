//package cn.cnic.marathon.ui;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import android.app.ProgressDialog;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.text.format.DateUtils;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//import cn.cnic.marathon.R;
//import cn.cnic.marathon.adapter.ScheduAdapter;
//import cn.cnic.marathon.base.BaseActivity;
//import cn.cnic.marathon.http.NetUtils;
//import cn.cnic.marathon.sql.HelperDao;
//import cn.cnic.marathon.util.ItUtils;
//import cn.cnic.marathon.util.Utils;
//
//import com.android.volley.DefaultRetryPolicy;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response.ErrorListener;
//import com.android.volley.Response.Listener;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonArrayRequest;
//import com.android.volley.toolbox.Volley;
//import com.baidu.mapapi.utils.CoordinateConverter;
//import com.handmark.pulltorefresh.library.PullToRefreshBase;
//import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
//import com.handmark.pulltorefresh.library.PullToRefreshListView;
//
///**
// * 广告
// * 
// * @author cuixipeng
// * 
// */
//public class AdvertActivity extends BaseActivity {
//	private PullToRefreshListView mPullRefreshListView;
//	private TextView mSystem, mUserdefined;// tab切换
//	private TextView mBackText;
//	private ScheduAdapter mAdapter;
//	List<Map<String, Object>> mList = new ArrayList<Map<String, Object>>();
//	Map<String, Object> map;
//	private RequestQueue mQueue;
//	String url;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_advert);
//		findViewbyId();
//		initView();
//		setListeners();
//	}
//
//	/**
//	 * 初始化list集合 系统日程
//	 */
//	String id;
//
//	public void initSystemGet(String url) {
//		mQueue = Volley.newRequestQueue(getApplicationContext());
//		final ProgressDialog progressDialog = ProgressDialog.show(this, "",
//				"loading...");
//		JsonArrayRequest request = new JsonArrayRequest(url,
//				new Listener<JSONArray>() {
//
//					@Override
//					public void onResponse(JSONArray array) {
//						// Log.d("name", array+"");
//						if (progressDialog.isShowing()
//								&& progressDialog != null) {
//							progressDialog.dismiss();
//						}
//						JSONObject jsonObject;
//						// mList = new ArrayList<Map<String, Object>>();
//						for (int i = 0; i < array.length(); i++) {
//							try {
//								jsonObject = array.getJSONObject(i);
//								// Log.d("hahahah", jsonObject+"");
//
//								map = new HashMap<String, Object>();
//								map.put(Utils.Name, jsonObject.getString("title"));
//								id = jsonObject.getJSONObject("_id").getString(
//										"inc");
//								Utils.adList.add(id);
//								mList.add(map);
//							} catch (JSONException e) {
//								e.printStackTrace();
//							}
//
//						}
//						mAdapter = new ScheduAdapter(mList,
//								getApplicationContext());
//						mPullRefreshListView.setAdapter(mAdapter);
//					}
//				}, new ErrorListener() {
//
//					@Override
//					public void onErrorResponse(VolleyError error) {
//						progressDialog.dismiss();
//						Toast.makeText(getApplicationContext(), "请检查您的网络",
//								Toast.LENGTH_SHORT).show();
//					}
//				});
//		request.setRetryPolicy(new DefaultRetryPolicy(1 * 1000, 1, 1.0f));
//		mQueue.add(request);
//	}
//
//	@Override
//	protected void initView() {
//		 
//			// 没有存入数据库，从网络获取，并且判断网络是否开启
//			if (NetUtils.isOnline(getApplicationContext())) {
////				initSystemGet(URLUtils.SCHEDU_URL_SYSTEM);
//			} else {
//				Toast.makeText(getApplicationContext(), "网络未连接",
//						Toast.LENGTH_SHORT).show();
//			}
//			// 查询数据库,通过id获取存的数据通过adapter添加到listview中
//		   
//
//	}
//
//	@Override
//	protected void findViewbyId() {
//		mBackText = (TextView) findViewById(R.id.back);
//		mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.advert_pull_refresh_list);
//	}
//
//	@Override
//	protected void setListeners() {
//		// 返回按鈕
//		mBackText.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				ItUtils.intent(AdvertActivity.this, MainActivity.class);
//			}
//		});
//		// 下拉刷新点击事件
//		mPullRefreshListView
//				.setOnRefreshListener(new OnRefreshListener<ListView>() {
//
//					@Override
//					public void onRefresh(
//							PullToRefreshBase<ListView> refreshView) {
//						String label = DateUtils.formatDateTime(
//								getApplicationContext(),
//								System.currentTimeMillis(),
//								DateUtils.FORMAT_SHOW_TIME
//										| DateUtils.FORMAT_SHOW_DATE
//										| DateUtils.FORMAT_ABBREV_ALL);
//
//						// Update the LastUpdatedLabel
//						refreshView.getLoadingLayoutProxy()
//								.setLastUpdatedLabel(label);
//
//						// Do work to refresh the list here.
//						new GetDataTask().execute();
//					}
//				});
//		mPullRefreshListView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//					long arg3) {
//				ItUtils.intent(AdvertActivity.this, ScheduContentActivity.class);
//			}
//		});
//	}
//
//	private class GetDataTask extends AsyncTask<Void, Void, String> {
//
//		// 后台处理部分
//		@Override
//		protected String doInBackground(Void... params) {
//			// Simulates a background job.
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//			}
//			String str = "Added after refresh...I add";
//			return str;
//		}
//
//		// 这里是对刷新的响应，可以利用addFirst（）和addLast()函数将新加的内容加到LISTView中
//		// 根据AsyncTask的原理，onPostExecute里的result的值就是doInBackground()的返回值
//		@Override
//		protected void onPostExecute(String result) {
//			// 在头部增加新添内容
//			// mListItems.addFirst(result);
//
//			// 通知程序数据集已经改变，如果不做通知，那么将不会刷新mListItems的集合
//			mAdapter.notifyDataSetChanged();
//			// Call onRefreshComplete when the list has been refreshed.
//			mPullRefreshListView.onRefreshComplete();
//
//			super.onPostExecute(result);
//		}
//	}
//
//}
