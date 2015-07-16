//package cn.cnic.marathon.service;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.location.LocationManager;
//import android.os.Bundle;
//import android.widget.Toast;
//import cn.cnic.marathon.sql.HelperDao;
//import cn.cnic.marathon.tools.UtilTool;
//
///**
// * GPS位置广播接收器
// * 
// * @author cuixipeng
// * 
// */
//public class GPSLocationReceiver extends BroadcastReceiver {
//	@Override
//	public void onReceive(Context context, Intent intent) {
//		Bundle bundle = intent.getExtras();
//		String lon = bundle.getString("lon");
//		String lat = bundle.getString("lat");
//		if (lon != null && !"".equals(lon) && lat != null && !"".equals(lat)) {
////			Toast.makeText(context, "目前经纬度\n经度：" + lon + "\n纬度：" + lat,
////					Toast.LENGTH_SHORT).show();
//			
////			HelperDao.insertPosition(lon, lat, context);
//		} else {
//
//		}
//	}
//
//	/**
//	 * GPS定位 开启service，获取定位信息
//	 */
////	public static GPSLocationReceiver receiver;
////
////	public static void initGPSLocation(Context context) {
////		// 判断GPS是否可用
////		if (!UtilTool.isGpsEnabled((LocationManager) context
////				.getSystemService(Context.LOCATION_SERVICE))) {
////			Toast.makeText(context, "GSP当前已禁用，请在您的系统设置屏幕启动。", Toast.LENGTH_LONG)
////					.show();
////			Intent callGPSSettingIntent = new Intent(
////					android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
////			context.startActivity(callGPSSettingIntent);
////			return;
////		}
////		// 启动服务
////		context.startService(new Intent(context, GPSLocationService.class));
////		// 注册广播
////		receiver = new GPSLocationReceiver();
////		IntentFilter filter = new IntentFilter();
////		filter.addAction("cn.cnic.marathon.service.LocationService");
////		context.registerReceiver(receiver, filter);
////	}
//}
