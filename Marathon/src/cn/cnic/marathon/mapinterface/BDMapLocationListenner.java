package cn.cnic.marathon.mapinterface;

import android.content.Context;
import cn.cnic.marathon.sql.PositionDao;
import cn.cnic.marathon.util.Utils;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;

public class BDMapLocationListenner implements BDLocationListener {
	boolean isFirstLoc = true;// 是否首次定位
	boolean LOC_FLAG = false;// 成功定位的flag
	private MapView mMapView;
	private BaiduMap mBaiduMap;
	private GeoCoder mSearch = null;
	PositionDao positionDao;

	public BDMapLocationListenner(MapView mMapView, BaiduMap mBaiduMap,
			GeoCoder mSearch) {
		super();
		this.mMapView = mMapView;
		this.mBaiduMap = mBaiduMap;
		this.mSearch = mSearch;
	}

	public BDMapLocationListenner(MapView mMapView, BaiduMap mBaiduMap,
			GeoCoder mSearch, Context context) {
		this(mMapView, mBaiduMap, mSearch);
		positionDao = new PositionDao(context);
	}

	@Override
	public void onReceiveLocation(BDLocation location) {
		// map view 销毁后不在处理新接收的位置
		if (location == null || mMapView == null)
			return;
		LOC_FLAG = true;
		Utils.latitude = location.getLatitude();
		Utils.longitude = location.getLongitude();
		collectLocation(location.getLongitude(), location.getLatitude());
		MyLocationData locData = new MyLocationData.Builder()
				.accuracy(location.getRadius())
				// 此处设置开发者获取到的方向信息，顺时针0-360
				.direction(300).latitude(location.getLatitude())
				.longitude(location.getLongitude()).build();
		mBaiduMap.setMyLocationData(locData);
		if (isFirstLoc) {
			isFirstLoc = false;
			LatLng ll = new LatLng(location.getLatitude(),
					location.getLongitude());
			MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
			// mBaiduMap.animateMapStatus(u);
		}
	}

	/**
	 * 收集位置信息 保存到数据库中 由定时服务定时上传到服务器
	 * 
	 * @param lng
	 * @param lat
	 */
	private void collectLocation(double lng, double lat) {
		positionDao.addPostion(lng, lat);
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
