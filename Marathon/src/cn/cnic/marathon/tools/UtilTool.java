package cn.cnic.marathon.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.cnic.marathon.rescript.UserStatus;
import cn.cnic.marathon.ui.BMapActivity;
import cn.cnic.marathon.ui.UserInfoActivity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 *网络是否开启
 * @param locationManager
 * @return
 */
public class UtilTool {
	/**
	 * 方法描述： 判断手机是否连接网络 </br> 创 建 人： YAR</br> 创建时间： 2015-3-3</br> 返回类型： true 或
	 * false </br>
	 * */
	public static boolean isOnline(Context context) {
		boolean flag = false;
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();

			if (mNetworkInfo != null) {
				mNetworkInfo.isAvailable();
				flag = true;
			} else {
				flag = false;
			}
		}
		return flag;
	}
	/**
	 * 网络是否开启
	 * @param locationManager
	 * @return
	 */
	public static boolean isGpsEnabled(LocationManager locationManager) {
		boolean isOpenGPS = locationManager
				.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER);
		boolean isOpenNetwork = locationManager
				.isProviderEnabled(android.location.LocationManager.NETWORK_PROVIDER);
		if (isOpenGPS || isOpenNetwork) {
			return true;
		}
		return false;
	}
	/**
	 * 判断是否是手机号
	 * @param mobiles
	 * @return
	 */
		public static boolean isMobileNum(String mobiles) {
			Pattern p = Pattern
					.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
			Matcher m = p.matcher(mobiles);
			System.out.println(m.matches() + "---");
			return m.matches();

		}
		/**
		 * 注销确认
		 */
		public static void unLoginConfirm(final Context context) {
			AlertDialog.Builder builder = new Builder(context);
			builder.setMessage("确认注销吗？");
			builder.setTitle("提示");
			builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					Intent intent = new Intent(context,
							BMapActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);// 它可以关掉所要到的界面中间的activity
					context.startActivity(intent);
					UserStatus.deleteUser();
				}
			});
			builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			builder.create().show();
		}
}
