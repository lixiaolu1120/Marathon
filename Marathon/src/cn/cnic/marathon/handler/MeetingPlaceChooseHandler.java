package cn.cnic.marathon.handler;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import cn.cnic.marathon.R;
import cn.cnic.marathon.mapinterface.PathRecommend;
import cn.cnic.marathon.rescript.CustomOverlay;
import cn.cnic.marathon.rescript.CustomOverlayTypes;
import cn.cnic.marathon.tools.UtilTool;
import cn.cnic.marathon.ui.BMapActivity;
import cn.cnic.marathon.util.BDOverlayStorage;
import cn.cnic.marathon.util.Utils;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;

public class MeetingPlaceChooseHandler extends BaseHandler {

	private BaiduMap mBaiduMap;
	private RelativeLayout friendMessageLayout;
	private GeoCoder mSearch, mStartGeoCoder;
	private Context context;
	private BDOverlayStorage storage;

	public MeetingPlaceChooseHandler(BaiduMap mBaiduMap,
			RelativeLayout friendMessageLayout, GeoCoder mSearch,
			GeoCoder mStartGeoCoder, Context context) {
		super();
		this.mBaiduMap = mBaiduMap;
		this.friendMessageLayout = friendMessageLayout;
		this.mSearch = mSearch;
		this.mStartGeoCoder = mStartGeoCoder;
		this.context = context;
		storage = BDOverlayStorage.getInstance(context, mBaiduMap);
	}
	static double lat ,lon;
	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		BMapActivity.friendUid = msg.getData().getString("uid");
		final SharedPreferences preferences=context.getSharedPreferences("bourn",Context.MODE_PRIVATE);
		final Editor editor=preferences.edit();
		Log.i("DATA", "friend meeting handler");
		if (msg.what == 1) { // 用户点击了到对方地点
			if (!UtilTool.isOnline(context)) {
				Toast.makeText(context, "请检查您的网络", Toast.LENGTH_SHORT).show();
				return;
			}
			double lat = msg.getData().getDouble("lat");
			double lon = msg.getData().getDouble("lon");
			editor.clear();
			editor.putString("bournLat", String.valueOf(lat));
			editor.putString("bournLon", String.valueOf(lon));
			editor.commit();
			addTipMarker(lat, lon);
			pathSearchByCoord(Utils.latitude, Utils.longitude, lat, lon);
		} else if (msg.what == 2) {// 用户点击了到自己的地点
			double lat = msg.getData().getDouble("lat");
			double lon = msg.getData().getDouble("lon");
			editor.clear();
			editor.putString("bournLat", String.valueOf(Utils.latitude));
			editor.putString("bournLon", String.valueOf(Utils.longitude));
			editor.commit();
			addTipMarker(Utils.latitude, Utils.longitude);
			pathSearchByCoord(lat, lon, Utils.latitude, Utils.longitude);
		} else if (msg.what == 3) {// 用户点击了选择约见地点
			Utils.CHOICE_FLAG = true;
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
					editor.clear();
					editor.putString("bournLat", String.valueOf(point.latitude));
					editor.putString("bournLon", String.valueOf(point.longitude));
					editor.commit();
					addTipMarker(point.latitude, point.longitude);
					pathSearchByCoord(Utils.latitude, Utils.longitude,
							point.latitude, point.longitude);
					Utils.CHOICE_FLAG = false;
				}
			});
		}
	}

	/**
	 * 会面地点图标
	 * @param lat
	 * @param lon
	 */
	public void addTipMarker(double lat, double lon) {
		friendMessageLayout.setVisibility(View.VISIBLE);
		LatLng point3 = new LatLng(lat, lon);
		// 构建Marker图标
		BitmapDescriptor bitmap = BitmapDescriptorFactory
				.fromResource(R.drawable.meet_point_6);
		// 构建MarkerOption，用于在地图上添加Marker
		OverlayOptions mMarks = new MarkerOptions().position(point3).icon(
				bitmap);
		storage.addOverLay(new CustomOverlay(CustomOverlayTypes.MEETING, mMarks));
	}

	/**
	 * 得到起始点的经纬度坐标 通过坐标规划路线
	 * 
	 * @param lat
	 * @param lon
	 */
	public static void pathSearchByCoord(double startLat, double startlLn,
			double endLat, double endLon) {
		LatLng fPtCenter = new LatLng(Float.valueOf((float) startLat),
				Float.valueOf((float) startlLn));
		// mSearch.reverseGeoCode(new
		// ReverseGeoCodeOption().location(fPtCenter));
		LatLng StartPtCenter = new LatLng(Float.valueOf((float) endLat),
				Float.valueOf((float) endLon));
		// 规划路线方式
		PathRecommend.pathSearchProgress("walking", fPtCenter, StartPtCenter);
	}

	/**
	 * 经纬度转化地理位置
	 */

	public void lnglatConvert(float lat, float lon) {
		// 反Geo搜索
		// 通过点中的坐标，转化为地理位置名称
		if (String.valueOf(lon).equals("0.0")) {
			return;
		} else {
			LatLng fPtCenter = new LatLng(Float.valueOf((float) lat),
					Float.valueOf((float) lon));
			// 反Geo搜索
			mSearch.reverseGeoCode(new ReverseGeoCodeOption()
					.location(fPtCenter));
		}
	}
}