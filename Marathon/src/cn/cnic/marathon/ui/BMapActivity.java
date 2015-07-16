package cn.cnic.marathon.ui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;
import cn.cnic.marathon.R;
import cn.cnic.marathon.adapter.HorizontalListViewAdapter;
import cn.cnic.marathon.base.BaseApplication;
import cn.cnic.marathon.handler.MeetingPlaceChooseHandler;
import cn.cnic.marathon.http.NetWork;
import cn.cnic.marathon.http.NetWork.CallBack;
import cn.cnic.marathon.http.request.MeetRequest;
import cn.cnic.marathon.http.request.Request;
import cn.cnic.marathon.http.response.Response;
import cn.cnic.marathon.mapinterface.BDMapLocationListenner;
import cn.cnic.marathon.mapinterface.MarkClickListener;
import cn.cnic.marathon.mapinterface.PathRecommend;
import cn.cnic.marathon.rescript.CustomOverlay;
import cn.cnic.marathon.rescript.CustomOverlayTypes;
import cn.cnic.marathon.rescript.HorizontalListView;
import cn.cnic.marathon.rescript.UserStatus;
import cn.cnic.marathon.service.AlarmReceiver;
import cn.cnic.marathon.service.FriendsInfoRequestService;
import cn.cnic.marathon.service.FriendsPositionUpdateService;
import cn.cnic.marathon.service.PositionUploadService;
import cn.cnic.marathon.service.PushService;
import cn.cnic.marathon.service.PushServiceReciver;
import cn.cnic.marathon.sql.BusDao;
import cn.cnic.marathon.sql.DBManager;
import cn.cnic.marathon.sql.EvacuDao;
import cn.cnic.marathon.sql.HelperDao;
import cn.cnic.marathon.tools.AddPolylineTool;
import cn.cnic.marathon.tools.MyFriendTool;
import cn.cnic.marathon.tools.Popuwd;
import cn.cnic.marathon.tools.UtilTool;
import cn.cnic.marathon.util.BDOverlayStorage;
import cn.cnic.marathon.util.DistanceUtils;
import cn.cnic.marathon.util.ItUtils;
import cn.cnic.marathon.util.Utils;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.utils.SpatialRelationUtil;

/**
 * app主页，包括百度地图、好友会面、好友留言、赛事路线、疏散路线、POI等功能
 * 
 * @author cuixipengs
 */
public class BMapActivity extends Activity implements OnClickListener,
		OnItemClickListener {
	// 百度地图定位相关
	LocationClient mLocClient;
	private LocationMode mCurrentMode;
	private RelativeLayout settingBar, poiBar;
	private BitmapDescriptor mCurrentMarker;
	private MapView mapView;
	private BaiduMap mBaiduMap;
	private Button sendMessageBtn, sendMessageCancelBtn;
	private RelativeLayout zoomIn, zoomOut, mLocationView;
	public static HorizontalListView scheduleListView;
	static GeoCoder geoCoder, mStartGeoCoder;
	private String mDeviceID;
	public MeetingPlaceChooseHandler chooseMeetingPlaceHandler;
	public BaseApplication baseApplication;
	private RelativeLayout friendMessageLayout;
	private EditText messageContent;
	private RadioButton evacuatePathBtn, appPathBtn;
	private long exitTime = 0;
	public static String friendUid;
	// 查询数据库获取日程信息
	List<Map<String, Object>> mList;
	// 用户的点
	LatLng myLatLng = new LatLng(Utils.latitude, Utils.longitude);
	/**
	 * 自己实现 Handler 处理消息更新UI
	 * 
	 * @author mark
	 */
	double lat, lon;
	// 搜索相关
	RoutePlanSearch mSearch = null; // 搜索模块，也可去掉地图模块独立使用
	// POI按钮列表
	PopupWindow poiListPopup;
	BDOverlayStorage storage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 初始化用户状态
		new UserStatus(getApplicationContext());
		Utils.appFontTypeface = Typeface.createFromAsset(getAssets(),
				"iconfont.ttf");
		// 在使用SDK各组件之前初始化context信息，传入ApplicationContext
		// 注意该方法要再setContentView方法之前实现
		SDKInitializer.initialize(getApplicationContext());
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_map);
		// 地理位置转化搜索，注册事件监听
		geoCoder = GeoCoder.newInstance();
		mStartGeoCoder = GeoCoder.newInstance();
		mDeviceID = Secure.getString(this.getContentResolver(),
				Secure.ANDROID_ID);
		baseApplication = (BaseApplication) getApplication();
		mDeviceID = Secure.getString(getContentResolver(), Secure.ANDROID_ID);
		Utils.resources = this.getResources();
		// GPSLocationReceiver.initGPSLocation(BMapActivity.this);
	}

	/**
	 * 启动推送服务
	 */
	private void startPushService() {
		// 未登录不启动推送服务
		if (!UserStatus.isLogined())
			return;
		Editor editor = getSharedPreferences(PushService.TAG, MODE_PRIVATE)
				.edit();
		editor.putString("deviceID", mDeviceID);
		editor.commit();
		Context application = getApplicationContext();
		// Log.i("application", "application before");
		PushService.actionStart(application);
		// Log.i("application", "application end");
	}

	@Override
	protected void onStart() {
		super.onStart();
		initView();
		initMap();
		storage = BDOverlayStorage.getInstance(getApplicationContext(),
				mBaiduMap);
		drawOverlay();
		initBDMapLocation();// 百度地图定位
		initNetRequestService(getApplicationContext());
		startPushService();

	}

	/**
	 * 绘制图层
	 */
	private void drawOverlay() {
		AddPolylineTool.drawEventRoute(getApplicationContext(), mBaiduMap);
		List<float[]> ps = new BusDao().getAllPoints();
		// 公交
		List<CustomOverlay> cs = new ArrayList<CustomOverlay>();
		Bitmap bm = BitmapFactory
				.decodeResource(getResources(), R.drawable.bus);
		bm = MyFriendTool.small(bm);
		Bundle bundle = new Bundle();
		bundle.putString("bus", "4");
		BitmapDescriptor descriptor = BitmapDescriptorFactory.fromBitmap(bm);
		for (float[] p : ps) {
			MarkerOptions options = new MarkerOptions()
					.position(new LatLng(p[1], p[0])).icon(descriptor)
					.extraInfo(bundle).anchor(.5f, .5f);
			cs.add(new CustomOverlay(CustomOverlayTypes.MARATHON, options));
		}
		storage.addMultiOverLay(cs);
		// 地铁
		List<float[]> subCs = new EvacuDao().getAllPoints();
		List<CustomOverlay> subList = new ArrayList<CustomOverlay>();
		Bitmap subBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.subway);
		subBitmap = MyFriendTool.small(subBitmap);
		Bundle subBundle = new Bundle();
		subBundle.putString("subway", "5");
		BitmapDescriptor subDescriptor = BitmapDescriptorFactory
				.fromBitmap(subBitmap);
		for (float[] p : subCs) {
			MarkerOptions options = new MarkerOptions()
					.position(new LatLng(p[1], p[0])).icon(subDescriptor)
					.extraInfo(subBundle);
			subList.add(new CustomOverlay(CustomOverlayTypes.MARATHON, options));
		}
		storage.addMultiOverLay(subList);
	}

	/**
	 * 初始化请求网络服务
	 * 
	 * @param context
	 */
	private void initNetRequestService(Context context) {
		// 开启用户位置上传服务
		new PositionUploadService(context);
		if (UserStatus.isLogined()) {
			new PushServiceReciver(BMapActivity.this, scheduleListView,
					mBaiduMap);// 接收推送的广播
			// 启动好友位置拉取服务
			new FriendsPositionUpdateService(BMapActivity.this);
			new FriendsInfoRequestService(BMapActivity.this);
		}
	}

	protected void initView() {
		findViewbyId();
		setListeners();
		initScheduleData();
	}

	protected void findViewbyId() {
		settingBar = (RelativeLayout) findViewById(R.id.settingBar);
		poiBar = (RelativeLayout) findViewById(R.id.poi_layout);
		messageContent = (EditText) findViewById(R.id.meet_edit);
		mapView = (MapView) findViewById(R.id.bmapView);
		zoomIn = (RelativeLayout) findViewById(R.id.zoomin);
		zoomOut = (RelativeLayout) findViewById(R.id.zoomout);
		mLocationView = (RelativeLayout) findViewById(R.id.locationview);
		friendMessageLayout = (RelativeLayout) findViewById(R.id.meet_message_rela);
		sendMessageBtn = (Button) findViewById(R.id.meet_sure);
		sendMessageCancelBtn = (Button) findViewById(R.id.meet_cancle);
		scheduleListView = (HorizontalListView) findViewById(R.id.horizontalListView);
		evacuatePathBtn = (RadioButton) findViewById(R.id.evacuate);
		appPathBtn = (RadioButton) findViewById(R.id.app_path);
	}

	protected void setListeners() {
		zoomIn.setOnClickListener(this);
		zoomOut.setOnClickListener(this);
		mLocationView.setOnClickListener(this);
		scheduleListView.setOnItemClickListener(this);
		settingBar.setOnClickListener(this);
		poiBar.setOnClickListener(this);
		sendMessageBtn.setOnClickListener(this);
		sendMessageCancelBtn.setOnClickListener(this);
		evacuatePathBtn.setOnClickListener(this);
		appPathBtn.setOnClickListener(this);
	}

	/**
	 * 定时提醒,先获取数据库表中的所有日程开始时间，然后对每一个时间根据calendar设置提前的时间，得到需要提前定时的时间，设置定时功能
	 */
	public void initScheduleData() {
		mList = HelperDao.RawQuery(getApplicationContext(), "calendar");
		HorizontalListViewAdapter mAdapter = new HorizontalListViewAdapter(
				mList, getApplicationContext());
		scheduleListView.setAdapter(mAdapter);
		List<Object> days;
		for (int i = 0; i < mList.size(); i++) {
			String timing = mList.get(i).get("calendar").toString();// 定时的时间段
			String title = mList.get(i).get("event_name").toString();// 定时的时间段
			String context = mList.get(i).get("event_memo").toString();// 定时的时间段
			if (timing.equals("")) {

			} else {
				String dtime = mList.get(i).get("dtime").toString();
				// 转化成日期标准格式
				String dtimeString = dtime.replace("/", "-");
				String day = "d(\\d)";
				Pattern pattern = Pattern.compile(day);
				Matcher matcher = pattern.matcher(timing);
				days = new ArrayList<Object>();
				while (matcher.find()) {
					// 得到定时的具体day
					days.add(matcher.group(1));
				}
				for (int j = 0; j < days.size(); j++) {
					// 对日期进行减法函数
					getDateStr(dtimeString,
							Integer.valueOf((String) days.get(j)), j, title,
							context);
				}
			}
		}
	}

	/**
	 * 获取指定日后 后 dayAddNum 天的 日期
	 * 
	 * @param day
	 *            日期，格式为String："2015-6-27";
	 * @param dayAddNum
	 *            提前天数 格式为int;
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public String getDateStr(String day, int dayAddNum, int j, String title,
			String context) {
		Date d0 = Utils.getDefaultDateFromString(day);
		Date d1 = new Date(d0.getTime() - dayAddNum * 24 * 60 * 60 * 1000);
		String alarmTime = Utils.getDefaultStringFromDate(d1);
		// 根据提前的时间设置提醒
		setAlarm(alarmTime, j, title, context);
		return alarmTime;
	}

	/**
	 * 设置定时
	 * 
	 * @param datatime
	 * @param j
	 * @param title
	 * @param context
	 */
	public void setAlarm(String datatime, int j, String title, String context) {
		Intent intent = new Intent(BMapActivity.this, AlarmReceiver.class);
		intent.putExtra("title", title);
		intent.putExtra("content", context);
		PendingIntent sender = PendingIntent.getBroadcast(BMapActivity.this, j,
				intent, 0);
		Calendar calendar = Calendar.getInstance();
		Date alarmTime = Utils.getDefaultDateFromString(datatime);
		// 判断需要定时的时间与现在的时间,如果定时过期则取消定时
		if (new Date().after((alarmTime))) {
			// Log.i("DATA", "定时过期:" + datatime);
			return;
		}
		calendar.setTime(alarmTime);// datatime为定时的时间
		calendar.add(Calendar.SECOND, 10);
		AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
		manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
		// Log.i("DATA", "定时成功:" + datatime);
	}

	/**
	 * 在地图上显示马拉松起点、半程、终点位置
	 */
	public void setSHEPoint() {
		LatLng startLatLng = new LatLng(39.909141, 116.405985);
		BitmapDescriptor bitmap = BitmapDescriptorFactory
				.fromResource(R.drawable.start_point);
		// 构建MarkerOption，用于在地图上添加Marker
		OverlayOptions mStart = new MarkerOptions().position(startLatLng).icon(
				bitmap);
		storage.addOverLay(new CustomOverlay(CustomOverlayTypes.START, mStart));
		LatLng halfLatLng = new LatLng(39.981717, 116.324626);
		BitmapDescriptor bitmap1 = BitmapDescriptorFactory
				.fromResource(R.drawable.middle_point);
		// 构建MarkerOption，用于在地图上添加Marker
		OverlayOptions mHalf = new MarkerOptions().position(halfLatLng).icon(
				bitmap1);
		storage.addOverLay(new CustomOverlay(CustomOverlayTypes.MIDDLE, mHalf));
		LatLng endLatLng = new LatLng(40.002672, 116.40175);
		BitmapDescriptor bitmap2 = BitmapDescriptorFactory
				.fromResource(R.drawable.end_point);
		// 构建MarkerOption，用于在地图上添加Marker
		OverlayOptions mEnd = new MarkerOptions().position(endLatLng).icon(
				bitmap2);
		storage.addOverLay(new CustomOverlay(CustomOverlayTypes.END, mEnd));
	}

	/**
	 * 对地图初始化操作
	 */
	public void initMap() {
		mBaiduMap = mapView.getMap();
		// mBaiduMap.setTrafficEnabled(true);
		// 设定地图缩放比例百度地图缩放范围（3-19），12两公里
		mBaiduMap.getUiSettings().setZoomGesturesEnabled(true);// 是否允許縮放手勢
		// mMapView.removeViewAt(1);
		mapView.showZoomControls(false);// 隐藏百度地图的缩放控件
		mCurrentMode = LocationMode.NORMAL;
		mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
				mCurrentMode, true, mCurrentMarker));
		chooseMeetingPlaceHandler = new MeetingPlaceChooseHandler(mBaiduMap,
				friendMessageLayout, geoCoder, mStartGeoCoder,
				getApplicationContext());
		mBaiduMap.setOnMarkerClickListener(new MarkClickListener(
				BMapActivity.this, mBaiduMap, friendMessageLayout,
				baseApplication, chooseMeetingPlaceHandler));
		MapStatusUpdate s = MapStatusUpdateFactory.newLatLngZoom(new LatLng(
				39.999166, 116.4031505), 18.0f);
		mBaiduMap.setMapStatus(s);
		mBaiduMap.getUiSettings().setCompassEnabled(false);// 不显示指南针
		// 初始化搜索模块，注册事件监听
		mSearch = RoutePlanSearch.newInstance();
		mSearch.setOnGetRoutePlanResultListener(new PathRecommend(mBaiduMap,
				mSearch, getApplicationContext()));
	}

	/**
	 * 开启地图定位
	 */
	public void initBDMapLocation() {
		// 开启定位图层
		mBaiduMap.setMyLocationEnabled(true);
		mLocClient = new LocationClient(this);
		// 定位初始化
		mLocClient.registerLocationListener(new BDMapLocationListenner(mapView,
				mBaiduMap, geoCoder, getApplicationContext()));
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// 打开gps
		option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 设置定位模式
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(1000 * 10);// 定位时间间隔
		option.setIsNeedAddress(true); // 返回的定位结果包含地址信息
		mLocClient.setLocOption(option);
		mLocClient.start();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.set:// 设置
			ItUtils.intent(BMapActivity.this, MenuAcitvity.class);
			break;
		case R.id.poi_layout:// poi
			MyFriendTool.poiPopupWindow(BMapActivity.this, poiBar, mBaiduMap);
			break;
		case R.id.zoomin:// 放大
			MapStatusUpdate u = MapStatusUpdateFactory.zoomIn();
			mBaiduMap.animateMapStatus(u);
			break;
		case R.id.zoomout:// 缩小
			// 缩放
			MapStatusUpdate uu = MapStatusUpdateFactory.zoomOut();
			mBaiduMap.animateMapStatus(uu);
			break;
		case R.id.locationview:// 定位到我的位置
			LatLng location = new LatLng(Utils.latitude, Utils.longitude);
			MapStatusUpdate lu = MapStatusUpdateFactory.newLatLng(location);
			mBaiduMap.animateMapStatus(lu);
			break;
		case R.id.settingBar:
			ItUtils.intent(BMapActivity.this, MenuAcitvity.class);
			break;
		case R.id.meet_sure:
			meetSendMessage();
			break;
		case R.id.meet_cancle:
			if (Utils.isOnlySendMessageToFriend) {
				friendMessageLayout.setVisibility(View.GONE);
			} else {
				friendMessageLayout.setVisibility(View.GONE);
				storage.deleteOverLayByType(CustomOverlayTypes.MEETING);
				Utils.routeOverlay.removeFromMap();
			}
			break;
		case R.id.app_path:
			Utils.CHOICE_FLAG = false;// 记录点击的是场馆路线 还是疏散路线
			storage.deleteOverLayByType(CustomOverlayTypes.EVACUATELINE);
			MapStatusUpdate s = MapStatusUpdateFactory.newLatLngZoom(
					new LatLng(39.999166, 116.4031505), 18.0f);
			mBaiduMap.setMapStatus(s);
			// drawOverlay();
			break;
		case R.id.evacuate:
			Utils.CHOICE_FLAG = true;// 记录点击的是场馆路线 还是疏散路线
			storage.deleteOverLayByType(CustomOverlayTypes.FRIEND);
			if (UtilTool.isOnline(BMapActivity.this)) {
				Utils.ToastMSG(BMapActivity.this, "请点击屏幕选择要去的地点");
				// 疏散路线
				showEvacuatePath();
			} else {
				Utils.ToastMSG(BMapActivity.this, "无网络连接，无法查看疏散路线");
			}
			// boundaries();
			break;
		}
	}

	@Override
	protected void onPause() {
		mapView.onPause();// 停止
		super.onPause();
	}

	@Override
	protected void onResume() {
		mapView.onResume();// 阻塞
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// 退出时销毁定位
		mLocClient.stop();
		// 关闭定位图层
		mBaiduMap.setMyLocationEnabled(false);
		mapView.onDestroy();
		mapView = null;
		geoCoder.destroy();
		super.onDestroy();
		// 注销服务
	}

	/**
	 * 屏幕下方日程列表点击弹出pop
	 */
	@Override
	public void onItemClick(AdapterView<?> view, View convertview,
			int position, long id) {
		String title = mList.get(position).get("event_name").toString();
		// Log.d("title", title);
		String time = mList.get(position).get("dtime").toString();
		String subtitle = mList.get(position).get("sub_title").toString();
		String contexts = mList.get(position).get("event_memo").toString();
		Popuwd.showWindow(scheduleListView, BMapActivity.this, title, time,
				contexts, subtitle);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(getApplicationContext(), "再按一次退出程序",
						Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				finish();
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 会面/发消息处理
	 */
	public void meetSendMessage() {
		float lats;
		float lons;
		if (Utils.isOnlySendMessageToFriend) {// 发消息
			lats = 0f;
			lons = 0f;
		} else {
			SharedPreferences preferences = getSharedPreferences("bourn",
					Context.MODE_PRIVATE);
			String lat = preferences.getString("bournLat", "");
			String lon = preferences.getString("bournLon", "");
			lats = Float.parseFloat(lat);
			lons = Float.parseFloat(lon);
		}
		String message = messageContent.getText().toString();
		Request request = new MeetRequest(UserStatus.getUser().getUid(),
				Utils.friendUid, message, lats, lons);
		NetWork.initNetWork(request, BMapActivity.this,
				new MeetRequestCallback());
	}

	/**
	 * 判断是在开始中途还是终点给出最佳疏散路线
	 */
	public void boundaries() {
		// 测试
		showEvacuatePath();
		// if (needEvacuate()) {// 开始
		// showEvacuatePath();
		// }
	}

	/**
	 * 是否需要疏散 疏散分为三个大的区域：起点、半程、终点 起点分为三个小的区域 如果用户位置处于这五个区域内则提供疏散路线
	 * 
	 * @return
	 */
	private boolean needEvacuate() {
		List<LatLng> startList = new ArrayList<LatLng>();
		// 起点处的矩形
		LatLng startX = new LatLng(39.901895, 116.399139);
		LatLng startY = new LatLng(39.908276, 116.407635);
		startList.add(startX);
		startList.add(startY);
		boolean mIsAtStartBound = SpatialRelationUtil.isPolygonContainsPoint(
				startList, myLatLng);
		List<LatLng> halfList = new ArrayList<LatLng>();
		// 中点处的矩形
		LatLng halfX = new LatLng(39.975724, 116.311437);
		LatLng halfY = new LatLng(39.976398, 116.324938);
		startList.add(halfX);
		startList.add(halfY);
		boolean mIsAtHalfBound = SpatialRelationUtil.isPolygonContainsPoint(
				halfList, myLatLng);
		List<LatLng> endList = new ArrayList<LatLng>();
		// 终点处的矩形
		LatLng endX = new LatLng(40.002812, 116.40661);
		LatLng endY = new LatLng(39.99538, 116.393141);
		startList.add(endX);
		startList.add(endY);
		boolean mIsAtEndBound = SpatialRelationUtil.isPolygonContainsPoint(
				endList, myLatLng);
		return mIsAtStartBound || mIsAtHalfBound || mIsAtEndBound;
	}

	/**
	 * 画出最佳疏散路线
	 */
	List<Map<String, Object>> listMaps;

	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	public void showEvacuatePath() {
		List points = new EvacuDao().getAllPoints();
		float[] lonlat = getShortestPoint(points);
		// PathRecommend.pathSearchProgress("walking", new LatLng(lonlat[1],
		// lonlat[0]));
		mBaiduMap.setOnMapClickListener(new OnMapClickListener() {
			@Override
			public boolean onMapPoiClick(MapPoi arg0) {
				return false;
			}

			@Override
			public void onMapClick(LatLng point) {
				if (!Utils.CHOICE_FLAG) {
					return;
				}
				// addTipMarker(point.latitude, point.longitude);
				MeetingPlaceChooseHandler.pathSearchByCoord(Utils.latitude,
						Utils.longitude, point.latitude, point.longitude);
				Utils.CHOICE_FLAG = false;
			}
		});
		if (true)
			return;
		listMaps = HelperDao.RawQuery(getApplicationContext(),
				DBManager.EMAPTH_DAO);
		Double[] d = new Double[listMaps.size()];
		LatLng lng = null;
		double lat, lon;
		// pos的值代表最短的距离在数据库中id值为pos
		int pos = 0;
		for (int i = 0; i < listMaps.size(); i++) {
			lat = Double.valueOf(listMaps.get(i).get("lat").toString());
			lon = Double.valueOf(listMaps.get(i).get("lon").toString());
			lng = new LatLng(lat, lon);
			double distance = DistanceUtils.getDistance(myLatLng, lng);// 2个点之间的距离
			d[i] = distance;
			double min = d[0];
			if (d[i] < min) {
				min = d[i];
				pos = i;
			}
		}
		String shortPathString = listMaps.get(pos).get("shortest").toString();
		String latAndLon[] = shortPathString.split(" ");// 根据空格拆分字符串
		String l[] = null;
		LatLng[] latLng = new LatLng[latAndLon.length];
		for (int i = 0; i < latAndLon.length; i++) {
			l = latAndLon[i].split(",");
			latLng[i] = new LatLng(Double.parseDouble(l[1]),
					Double.parseDouble(l[0]));
		}
		AddPolylineTool.addPolyEvacuateline(getApplicationContext(), mBaiduMap,
				latLng);
	}

	/**
	 * 最近点
	 * 
	 * @param points
	 * @return
	 */
	private float[] getShortestPoint(List<float[]> points) {
		double len = 0;
		float[] p = new float[2];
		for (float[] point : points) {
			double tl = DistanceUtils.getDistance(point[0], point[1]);
			if (len == 0 || len > tl) {
				len = tl;
				p = point;
			}
		}
		return p;
	}

	/**
	 * 请求网络结果 邀请好友见面
	 * 
	 * @author cuixipeng
	 */
	class MeetRequestCallback implements CallBack {
		@Override
		public void onRequestComplete(Response result) {
			// Map<String, Object> content = result.getContent();
			Boolean isSuccess = result.isSuccess();
			// Object data = content.get("time");
			if (isSuccess) {
				Utils.ToastMSG(getApplicationContext(), "发送成功");
				friendMessageLayout.setVisibility(View.GONE);
				AddPolylineTool.drawEventRoute(getApplicationContext(),
						mBaiduMap);
			} else {
				Utils.ToastMSG(getApplicationContext(), "发送失败");
			}
		}
	}
}
