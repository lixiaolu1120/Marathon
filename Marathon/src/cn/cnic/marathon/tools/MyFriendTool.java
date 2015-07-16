package cn.cnic.marathon.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import cn.cnic.marathon.R;
import cn.cnic.marathon.adapter.PopListViewAdapter;
import cn.cnic.marathon.base.BaseApplication;
import cn.cnic.marathon.handler.MeetingPlaceChooseHandler;
import cn.cnic.marathon.rescript.CustomOverlay;
import cn.cnic.marathon.rescript.CustomOverlayTypes;
import cn.cnic.marathon.rescript.UserStatus;
import cn.cnic.marathon.sql.HelperDao;
import cn.cnic.marathon.ui.DatePlaceChoseActivity;
import cn.cnic.marathon.ui.LoginActivity;
import cn.cnic.marathon.util.BDOverlayStorage;
import cn.cnic.marathon.util.DistanceUtils;
import cn.cnic.marathon.util.Utils;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;

/**
 * 好友模块的pop工具类
 * 
 * @author cuixipeng
 * 
 */
public class MyFriendTool {

	private static View poiItem;
	static List<Map<String, Object>> mList;
	static Map<String, Object> map;
	static Boolean mChange = false;
	private static List<Map<String, Object>> marks = new ArrayList<Map<String, Object>>();// 存放标注的集合
	static OverlayOptions mMarks, mMarkText;
	static TextView close, meet, sendMessage, name, mDistance;
	static ImageView mImg;
	static PopupWindow meetingAndSendMessage, poiPop;
	static BDOverlayStorage storage;

	/**
	 * 点击POI按钮显示POI菜单
	 */
	public static PopupWindow poiPopupWindow(final Context context,
			View parent, final BaiduMap mBaiduMap) {
		// 如果已经存在直接显示
		if (poiPop != null) {
			// 使其聚集
			poiPop.setFocusable(true);
			// 设置允许在外点击消失
			poiPop.setOutsideTouchable(false);
			// 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
			poiPop.setBackgroundDrawable(new BitmapDrawable());
			poiPop.showAsDropDown(parent);
			return poiPop;
		}
		storage = BDOverlayStorage.getInstance(context, mBaiduMap);
		if (poiPop == null) {
			LayoutInflater layoutInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			poiItem = layoutInflater.inflate(R.layout.poi_item_pop, null);
			poiPop = new PopupWindow(poiItem, parent.getWidth(),
					LayoutParams.WRAP_CONTENT);
		}
		ListView poiMenuList = (ListView) poiItem
				.findViewById(R.id.poi_menu_list);
		int text[] = Utils.getIntArrayFromStringArrray(context,
				R.array.poi_items_icon);
		for (int t : text)
			Log.i("DATA", t + "");
		String name[] = context.getResources().getStringArray(
				R.array.poi_items_title);

		int type[] = context.getResources().getIntArray(R.array.poi_items_type);
		int background[] = Utils.getIntArrayFromStringArrray(context,
				R.array.poi_tiems_background);

		mList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < text.length; i++) {
			map = new HashMap<String, Object>();
			map.put("icon", context.getResources().getString(text[i]));
			map.put("text", name[i]);
			map.put("type", type[i]);
			map.put("background", background[i]);
			map.put("selected", false);
			mList.add(map);
		}
		final PopListViewAdapter adapter = new PopListViewAdapter(mList,
				context, mBaiduMap);
		poiMenuList.setAdapter(adapter);
		poiMenuList.setOnItemClickListener(new OnItemClickListener() {
			@SuppressWarnings("rawtypes")
			@Override
			public void onItemClick(AdapterView<?> arg0, View convertView,
					int position, long id) {
				Log.i("DATA", "poi list click position is " + position);
				Map data = mList.get(position);
				boolean isSelected = Boolean.valueOf(data.get("selected")
						.toString());
				Log.i("DATA", "clicked position is " + position
						+ " and status is " + isSelected);
				if (position == 4) {// 是不是好友按钮
					if (!UserStatus.isLogined()) {// 如果没有登录跳转登录
						Intent intent = new Intent(context, LoginActivity.class);
						context.startActivity(intent);
					}
					mList.get(position).put("selected",
							"true".equals(isSelected + "") ? false : true);
					if (isSelected) {
						storage.deleteOverLayByType(CustomOverlayTypes.FRIEND);
					} else if (UserStatus.isLogined()) {
						addFriendsMark(context, mBaiduMap);
					}
				} else {
					mList.get(position).put("selected",
							"true".equals(isSelected + "") ? false : true);
					if (isSelected) {
						storage.deleteOverLayByType(CustomOverlayTypes.POI
								+ data.get("type"));
					} else {
						showPOIMarksByType(String.valueOf(data.get("type")),
								String.valueOf(data.get("icon")),
								(Integer) data.get("background"), mBaiduMap,
								context);
					}
				}
				adapter.changeSelected(position);
			}

		});
		// 使其聚集
		poiPop.setFocusable(true);
		// 设置允许在外点击消失
		poiPop.setOutsideTouchable(false);
		// 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
		poiPop.setBackgroundDrawable(new BitmapDrawable());
		poiPop.showAsDropDown(parent);
		return poiPop;
	}

	/**
	 * 根据POI类型显示POI位置
	 * 
	 * @param type
	 * @param icon
	 * @param background
	 * @param mBaiduMap
	 * @param context
	 */
	public static void showPOIMarksByType(String type, String icon,
			int background, BaiduMap mBaiduMap, Context context) {
		// mClear.setVisibility(View.VISIBLE);
		// mBaiduMap.clear();
		String volumns[] = { "type" };
		String values[] = { type };
		marks = HelperDao.orRawQuery(context, "mark", volumns, values);
		List<CustomOverlay> poi = new ArrayList<CustomOverlay>();
		for (int i = 0; i < marks.size(); i++) {
			Map<String, Object> mark = marks.get(i);
			// 定义Maker坐标点
			double lat = Double.parseDouble(mark.get("lat").toString());
			double lon = Double.parseDouble(mark.get("lon").toString());
			LatLng point = new LatLng(lat, lon);
			// 构建Marker图标
			Matrix matrix = new Matrix();
			matrix.postScale(.5f, .5f);
			// 构建MarkerOption，用于在地图上添加Marker
			Bitmap bi = BitmapFactory.decodeResource(context.getResources(),
					background);
			bi = Bitmap.createBitmap(bi, 0, 0, bi.getWidth(), bi.getHeight(),
					matrix, true);
			BitmapDescriptor bitmap = BitmapDescriptorFactory.fromBitmap(bi);
			MarkerOptions mMarks = new MarkerOptions().position(point)
					.icon(bitmap).anchor(0.5f, 0.5f);
			TextOptions mMarkText = new TextOptions().position(point)
					.text(icon).fontColor(Color.WHITE).fontSize(18)
					.typeface(Utils.appFontTypeface);
			poi.add(new CustomOverlay(CustomOverlayTypes.POI + type, mMarks));
			poi.add(new CustomOverlay(CustomOverlayTypes.POI + type, mMarkText));
		}
		storage = BDOverlayStorage.getInstance(context, mBaiduMap);
		storage.addMultiOverLay(poi);
	}

	/**
	 * 在地图上显示可见好友的位置
	 * 
	 * @param context
	 * @param mBaiduMap
	 */
	public static Bitmap small(Bitmap bitmap) {
		Matrix matrix = new Matrix();
		matrix.postScale(0.5f, 0.5f); // 长和宽放大缩小的比例
		Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
				bitmap.getHeight(), matrix, true);
		// Log.d(">>>>>", resizeBmp.getHeight()+"and"+resizeBmp.getWidth());
		return resizeBmp;
	}

	public static void addFriendsMark(Context context, BaiduMap mBaiduMap) {

		String volumns[] = { "share" };
		String values[] = { "1" };
		marks = HelperDao.orRawQuery(context, "friends", volumns, values);
		Bundle bundle = new Bundle();
		bundle.putString("type", CustomOverlayTypes.FRIEND);
		List<CustomOverlay> friends = new ArrayList<CustomOverlay>();
		for (int i = 0; i < marks.size(); i++) {
			Map<String, Object> mark = marks.get(i);
			bundle.putString("name", mark.get("nickname").toString());
			bundle.putString("uid", mark.get("uid").toString());
			// 定义Maker坐标点
			double lat = Double.parseDouble(mark.get("lat").toString());
			double lon = Double.parseDouble(mark.get("lon").toString());
			LatLng point = new LatLng(lat, lon);
			Bitmap bm = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.point_friends3);
			// 构建Marker图标
			BitmapDescriptor bitmap = BitmapDescriptorFactory
					.fromBitmap(small(bm));
			// 构建MarkerOption，用于在地图上添加Marker
			MarkerOptions mMarks = new MarkerOptions().position(point)
					.icon(bitmap).extraInfo(bundle);
			friends.add(new CustomOverlay(CustomOverlayTypes.FRIEND, mMarks));
		}
		storage = BDOverlayStorage.getInstance(context, mBaiduMap);
		storage.addMultiOverLay(friends);
	}

	/**
	 * 点击好友marker，弹出的约见和发消息popuwindow
	 * 
	 * @param context
	 * @param parent
	 * @param mBaiduMap
	 * @return
	 */
	static TextView textView;

	public static void meetingAndSendMessagePopup(final Context context,
			final View parent, final BaiduMap mBaiduMap, int x, int y,
			final Marker marker, final BaseApplication mAPP,
			final MeetingPlaceChooseHandler handler) {
		textView = (TextView) parent.findViewById(R.id.meetormessage);
		if (meetingAndSendMessage == null) {
			LayoutInflater layoutInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			poiItem = layoutInflater.inflate(R.layout.infowindow, null);
			meetingAndSendMessage = new PopupWindow(poiItem,
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			mDistance = (TextView) poiItem
					.findViewById(R.id.infowindow_distance);
			mImg = (ImageView) poiItem.findViewById(R.id.infowindow_img);
			name = (TextView) poiItem.findViewById(R.id.infowindow_name);
			close = (TextView) poiItem.findViewById(R.id.infowindow_close);
			meet = (TextView) poiItem.findViewById(R.id.infowindow_meet);
			sendMessage = (TextView) poiItem
					.findViewById(R.id.infowindow_sendmessage);
		}
		LatLng start = new LatLng(Utils.latitude, Utils.longitude);
		LatLng end = new LatLng(marker.getPosition().latitude,
				marker.getPosition().longitude);
		int distanceString = ((int) (DistanceUtils.getDistance(start, end)));
		mDistance.setText(String.valueOf(distanceString));

		name.setText(marker.getExtraInfo().getString("name"));
		sendMessage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				meetingAndSendMessage.dismiss();
				Utils.isOnlySendMessageToFriend = true;// 判断是发送消息还是约人见面
				textView.setText("发消息");
				// 显示消息发送框
				parent.setVisibility(View.VISIBLE);
			}
		});
		meet.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				textView.setText("约见");
				Utils.isOnlySendMessageToFriend = false;// 判断是发送消息还是约人见面
				// 发送共享handler
				mAPP.setHandler(handler);
				Intent intent = new Intent();
				intent.putExtra("lat", marker.getPosition().latitude);
				intent.putExtra("lon", marker.getPosition().longitude);
				intent.putExtra("uid", marker.getExtraInfo().getString("uid"));
				intent.setClass(context, DatePlaceChoseActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_SINGLE_TOP);
				context.startActivity(intent);
				meetingAndSendMessage.dismiss();
			}
		});
		close.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				meetingAndSendMessage.dismiss();
			}
		});
		// // 使其聚集
		meetingAndSendMessage.setFocusable(true);
		// 设置允许在外点击消失
		meetingAndSendMessage.setOutsideTouchable(false);
		// 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
		meetingAndSendMessage.setBackgroundDrawable(new BitmapDrawable());
		meetingAndSendMessage.showAtLocation(parent, Gravity.NO_GRAVITY,
				x - 39, y - 39);
		marker.setToTop();
	}
}
