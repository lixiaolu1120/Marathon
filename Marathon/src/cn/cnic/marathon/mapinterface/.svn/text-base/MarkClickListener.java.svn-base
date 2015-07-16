package cn.cnic.marathon.mapinterface;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;
import cn.cnic.marathon.base.BaseApplication;
import cn.cnic.marathon.handler.MeetingPlaceChooseHandler;
import cn.cnic.marathon.rescript.CustomOverlayTypes;
import cn.cnic.marathon.sql.BirdsNestDao;
import cn.cnic.marathon.tools.MyFriendTool;
import cn.cnic.marathon.tools.UtilTool;
import cn.cnic.marathon.util.Utils;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.model.LatLng;

public class MarkClickListener implements OnMarkerClickListener {
	private Context context;
	private BaiduMap mBaiduMap;
	private RelativeLayout layout;
	private BaseApplication myAPP;
	private MeetingPlaceChooseHandler handler;

	public MarkClickListener(Context context, BaiduMap mBaiduMap,
			RelativeLayout layout, BaseApplication myAPP,
			MeetingPlaceChooseHandler handler) {
		super();
		this.context = context;
		this.mBaiduMap = mBaiduMap;
		this.layout = layout;
		this.myAPP = myAPP;
		this.handler = handler;
	}

	@Override
	public boolean onMarkerClick(final Marker marker) {
		Bundle bundle = marker.getExtraInfo();
		if (null == bundle)
			return false;
		LatLng ll = new LatLng(marker.getPosition().latitude,
				marker.getPosition().longitude);
		String type = bundle.getString("type");
		Utils.log("Marker", "marker's clicked . marker type is " + type);
		if (CustomOverlayTypes.FRIEND.equals(type)) {
			// 判断layout的状态 0 ，1隐藏 不隐藏
			if (layout.getVisibility() == 0) {
			} else {
				// 百度提供经纬度坐标转化成屏幕坐标+
				Utils.friendUid = bundle.getString("uid");
				Point point = mBaiduMap.getProjection().toScreenLocation(ll);
				MyFriendTool.meetingAndSendMessagePopup(context, layout, mBaiduMap,
						point.x, point.y, marker, myAPP, handler);
			}
		}
		if (BirdsNestDao.TYPE_PARKING.equals(type)) {// 点击停车场调用百度地图推荐路线
			if (!UtilTool.isOnline(context)) {
				Toast.makeText(context, "请检查您的网络", Toast.LENGTH_SHORT).show();
				return false;
			}
			LatLng loc = new LatLng(Utils.latitude, Utils.longitude);
			PathRecommend.pathSearchProgress("walking", loc, ll);
		}
		if (BirdsNestDao.BUS.equals(type)) {// 点击停车场调用百度地图推荐路线
			if (!UtilTool.isOnline(context)) {
				Toast.makeText(context, "请检查您的网络", Toast.LENGTH_SHORT).show();
				return false;
			}
			LatLng loc = new LatLng(Utils.latitude, Utils.longitude);
			PathRecommend.pathSearchProgress("walking", loc, ll);

		}
		if (BirdsNestDao.SUBWAY.equals(type)) {// 点击停车场调用百度地图推荐路线
			if (!UtilTool.isOnline(context)) {
				Toast.makeText(context, "请检查您的网络", Toast.LENGTH_SHORT).show();
				return false;
			}
			LatLng loc = new LatLng(Utils.latitude, Utils.longitude);
			PathRecommend.pathSearchProgress("walking", loc, ll);
		}
		return false;
	}

}
