//package cn.cnic.marathon.service;
//
//import java.util.ArrayList;
//
//import android.app.Service;
//import android.content.Intent;
//import android.location.Location;
//import android.os.IBinder;
//
//public class GPSLocationService extends Service {
//	ArrayList<CellInfo> cellIds = null;
//	private Gps gps = null;
//	private boolean threadDisable = false;
//
//	@Override
//	public void onCreate() {
//		super.onCreate();
//
//		gps = new Gps(GPSLocationService.this);
//		cellIds = UtilTool.init(GPSLocationService.this);
//
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while (!threadDisable) {
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//
//					if (gps != null) { // 当结束服务时gps为空
//						// 获取经纬度
//						Location location = gps.getLocation();
//						if (location == null) {
//						}
//
//						// 发送广播
//						Intent intent = new Intent();
//						intent.putExtra("lat",
//								location == null ? "" : location.getLatitude()
//										+ "");
//						intent.putExtra("lon",
//								location == null ? "" : location.getLongitude()
//										+ "");
//						intent.setAction("cn.cnic.marathon.service.LocationService");
//						sendBroadcast(intent);
//					}
//
//				}
//			}
//		}).start();
//
//	}
//
//	@Override
//	public void onDestroy() {
//		threadDisable = true;
//		if (cellIds != null && cellIds.size() > 0) {
//			cellIds = null;
//		}
//		if (gps != null) {
//			gps.closeLocation();
//			gps = null;
//		}
//		super.onDestroy();
//	}
//
//	@Override
//	public IBinder onBind(Intent arg0) {
//		return null;
//	}
//
//}