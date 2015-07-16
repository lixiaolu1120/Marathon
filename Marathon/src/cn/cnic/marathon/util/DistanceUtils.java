package cn.cnic.marathon.util;

import java.util.List;

import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.baidu.mapapi.utils.DistanceUtil;

public class DistanceUtils {

	/**
	 * 计算polyline的长度，单位是米
	 * 
	 * @param pl
	 * @return
	 */
	public static double getDistance(Polyline pl) {
		double distance = 0.0;
		List<LatLng> points = pl.getPoints();
		int size = points.size();
		for (int i = 0; i < size; i++) {
			int next = i + 1;
			if (next < size) {
				distance += DistanceUtil.getDistance(points.get(i),
						points.get(next));
			}
		}
		return distance;
	}

	/**
	 * 计算两点的距离，单位是米
	 * 
	 * @param pl
	 * @return
	 */
	public static double getDistance(LatLng start, LatLng end) {
		return DistanceUtil.getDistance(start, end);
	}
	
	public static double getDistance(float lon, float lat){
		LatLng start = new LatLng(Utils.latitude, Utils.longitude);
		LatLng end = new LatLng(lat, lon);
		return DistanceUtil.getDistance(start, end);
	}
}
