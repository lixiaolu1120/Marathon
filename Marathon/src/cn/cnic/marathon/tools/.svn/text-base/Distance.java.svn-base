package cn.cnic.marathon.tools;

import android.location.Location;

/**
 * 把经纬度换算成距离
 * 
 * @param lat1
 *            开始纬度
 *            开始经度
 * @param lat2
 *            结束纬度
 * @param lon2
 *            结束经度
 * @return
 */
public class Distance {

	public static double getDistance(double lat1, double lon1, double lat2,
			double lon2) {
		float[] results = new float[1];
		Location.distanceBetween(lat1, lon1, lat2, lon2, results);
		return results[0];
	}

}
