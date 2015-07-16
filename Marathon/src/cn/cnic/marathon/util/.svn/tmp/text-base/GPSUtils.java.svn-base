package cn.cnic.marathon.util;

import java.util.Iterator;

import android.location.Criteria;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.location.GpsStatus.Listener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class GPSUtils {
	public static LocationListener locationListener(final LocationManager lm){
		// 位置监听
		  LocationListener locationListener = new LocationListener() {

			/**
			 * 位置信息变化时触发
			 */
			public void onLocationChanged(Location location) {
				updateView(location);
				Log.i("location", "时间：" + location.getTime());
//				Log.i(TAG, "经度：" + location.getLongitude());
//				Log.i(TAG, "纬度：" + location.getLatitude());
//				Log.i(TAG, "海拔：" + location.getAltitude());
			}

			/**
			 * GPS状态变化时触发
			 */
			public void onStatusChanged(String provider, int status, Bundle extras) {
				switch (status) {
				// GPS状态为可见时
				case LocationProvider.AVAILABLE:
					break;
				// GPS状态为服务区外时
				case LocationProvider.OUT_OF_SERVICE:
					break;
				// GPS状态为暂停服务时
				case LocationProvider.TEMPORARILY_UNAVAILABLE:
					break;
				}
			}

			/**
			 * GPS开启时触发
			 */
			public void onProviderEnabled(String provider) {
				Location location = lm.getLastKnownLocation(provider);
				updateView(location);
			}

			/**
			 * GPS禁用时触发
			 */
			public void onProviderDisabled(String provider) {
				updateView(null);
			}

		};
		return locationListener;
	}
	// 狀態監聽
	public  static GpsStatus.Listener gpsListener(final LocationManager lm){
		GpsStatus.Listener listener = new Listener() {

			@Override
			public void onGpsStatusChanged(int event) {
				switch (event) {
				// 第一次定位
				case GpsStatus.GPS_EVENT_FIRST_FIX:
					break;
				// 卫星状态改变
				case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
					// 获取当前状态
					GpsStatus gpsStatus = lm.getGpsStatus(null);
					// 获取卫星颗数的默认最大值
					int maxSatellites = gpsStatus.getMaxSatellites();
					// 创建一个迭代器保存所有卫星
					Iterator<GpsSatellite> iters = gpsStatus.getSatellites()
							.iterator();
					int count = 0;
					while (iters.hasNext() && count <= maxSatellites) {
						GpsSatellite s = iters.next();
						count++;
					}
					System.out.println("搜索到：" + count + "颗卫星");
					break;
				// 定位启动
				case GpsStatus.GPS_EVENT_STARTED:
					break;
				// 定位结束
				case GpsStatus.GPS_EVENT_STOPPED:
					break;
				}
			}
		};
		return listener;
	}
	/**
	 * 获取
	 * 
	 */
	public static void updateView(Location location) {
		if (location != null) {
			
		} else {
			// 清空EditText对象
		}
	}

	/**
	 * 返回查询条件
	 */
	@SuppressWarnings("unused")
	public static Criteria getCriteria() {

		Criteria criteria = new Criteria();
		// 设置定位精确度 Criteria.ACCURACY_COARSE比较粗略，Criteria.ACCURACY_FINE则比较精细
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		// 设置是否要求速度
		criteria.setSpeedRequired(false);
		// 设置是否允许运营商收费
		criteria.setCostAllowed(false);
		// 设置是否需要方位信息
		criteria.setBearingRequired(false);
		// 设置是否需要海拔信息
		criteria.setAltitudeRequired(false);
		// 设置对电源的需求
		criteria.setPowerRequirement(Criteria.POWER_LOW);
		return criteria;
	}

}
