package cn.cnic.marathon.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import cn.cnic.marathon.R;
import cn.cnic.marathon.base.MarkerTypes;
import cn.cnic.marathon.rescript.CustomOverlay;
import cn.cnic.marathon.rescript.CustomOverlayTypes;
import cn.cnic.marathon.sql.BirdsNestDao;
import cn.cnic.marathon.util.BDOverlayStorage;
import cn.cnic.marathon.util.MrathonPath;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.GroundOverlayOptions;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;

public class AddPolylineTool {
	/**
	 * 绘置相关活动的图
	 */
	public static void drawEventRoute(Context context, BaiduMap mBaiduMap) {
		drawBirdsNest(context, mBaiduMap);
	}

	/**
	 * 鸟巢相关底图
	 * @param context
	 * @param mBaiduMap
	 */
	static List<CustomOverlay> markers ;
	static void drawBirdsNest(Context context, BaiduMap mBaiduMap) {
		 markers = new ArrayList<CustomOverlay>();
		//markers.add(drawBirdsNestBackground(context, mBaiduMap,context.getResources()));
//		Resources res = context.getResources();
		BirdsNestDao dao = new BirdsNestDao(context);
		List<Map<String, String>> result = dao.getAllData();
		for (Map<String, String> row : result) {
			String type = row.get("type");
			String path = row.get("path");
			if (BirdsNestDao.TYPE_LINE.equals(type)) {
				String[] lonlats = path.split(" ");
				List<LatLng> latLngs = new ArrayList<LatLng>();
				for (int i = 0; i < lonlats.length; i++) {
					String[] lonlat = lonlats[i].split(",");
					LatLng e = new LatLng(Double.parseDouble(lonlat[1]),
							Double.parseDouble(lonlat[0]));
					latLngs.add(e);
				}
				markers.add(drawLine(context, mBaiduMap, latLngs));
			} else if (BirdsNestDao.TYPE_POINT.equals(type)) {
				String[] lonlat = path.split(",");
				String name = row.get("name");
				LatLng latLng = new LatLng(Double.parseDouble(lonlat[1]),
						Double.parseDouble(lonlat[0]));
				markers.add(drawMarker(context, mBaiduMap,latLng, name,
						R.drawable.exit));
				Log.i("DATA", "add point");
			} else if (BirdsNestDao.TYPE_PARKING.equals(type)) {
				String[] lonlat = path.split(",");
				String name = row.get("name");
				LatLng latLng = new LatLng(Double.parseDouble(lonlat[1]),
						Double.parseDouble(lonlat[0]));
				markers.add(drawMarker(context, mBaiduMap,latLng, name,
						R.drawable.parking));
			}
		}
		 storage = BDOverlayStorage.getInstance(context,
				mBaiduMap);
		storage.addMultiOverLay(markers);
	}
	/**
	 * 画出鸟巢的活动图
	 */
	static Bitmap mNiaoChaoBm;
	static BitmapDescriptor descriptor;
	static LatLng ur;
	static LatLng bl;
	static LatLngBounds bounds;
	static GroundOverlayOptions options;
	private static CustomOverlay drawBirdsNestBackground(Context context,
			BaiduMap mBaiduMap, Resources res) {
		mNiaoChaoBm = BitmapFactory.decodeResource(res, R.drawable.niaochao);
//		Bitmap bitmap = BitmapCompressTools.decodeSampledBitmapFromResource(
//				context.getResources(), R.drawable.niaochao, 100, 100);
		 descriptor = BitmapDescriptorFactory.fromBitmap(mNiaoChaoBm);
		  ur = new LatLng(40.000585, 116.404498);
		  bl = new LatLng(39.997747, 116.401803);
		 bounds = new LatLngBounds.Builder().include(ur)
				.include(bl).build();
		 options = new GroundOverlayOptions().image(
				descriptor).positionFromBounds(bounds);
		return new CustomOverlay(CustomOverlayTypes.MARATHON, options);
	}

	private static CustomOverlay drawMarker(Context context,
			BaiduMap mBaiduMap, LatLng latLng, String title,
			int iconid) {
		MarkerOptions options = new MarkerOptions();
		options.title(title);
		options.position(latLng);
		Bundle info = new Bundle();
		info.putString("type", MarkerTypes.BIRDS_NEST_PARKING);
		options.extraInfo(info);
		Bitmap bm = BitmapFactory.decodeResource(context.getResources(), iconid);
//		Log.d("size start",bm.getByteCount()+"");
		BitmapDescriptor descriptor = BitmapDescriptorFactory
				.fromBitmap(small(bm));
		options.icon(descriptor).anchor(.5f, .5f);
		return new CustomOverlay(CustomOverlayTypes.MARATHON, options);
	}
	private static void drawPoint(Context context, BaiduMap mBaiduMap,
			LatLng latLng) {
		CircleOptions options = new CircleOptions();
		options.center(latLng);
		options.fillColor(Color.WHITE);
		options.radius(10);
		Stroke stroke = new Stroke(Color.BLUE, 2);
		options.stroke(stroke);
		 storage = BDOverlayStorage.getInstance(context,
				mBaiduMap);
		storage.addOverLay(new CustomOverlay(CustomOverlayTypes.MARATHON,
				options));
	}

	private static void drawText(Context context, BaiduMap mBaiduMap,
			LatLng latLng, String text) {
		TextOptions options = new TextOptions();
		options.position(latLng);
		options.fontColor(Color.BLACK);
		options.fontSize(14);
		options.text(text);
		 storage = BDOverlayStorage.getInstance(context,
				mBaiduMap);
		storage.addOverLay(new CustomOverlay(CustomOverlayTypes.MARATHON,
				options));
	}

	private static CustomOverlay drawLine(Context context, BaiduMap mBaiduMap,
			List<LatLng> latLngs) {
		PolylineOptions lo = new PolylineOptions();
		lo.points(latLngs);
		lo.visible(true);
		lo.width(3);
		lo.color(Color.BLUE);
		return new CustomOverlay(CustomOverlayTypes.MARATHON, lo);
	}

	/**
	 * 绘置马拉松路线图
	 * 
	 * @param mBaiduMap
	 */
	void addMarathonPath(Context context, BaiduMap mBaiduMap) {
		LatLng[] latLng = MrathonPath.latLng;
		List<LatLng> latLngs = new ArrayList<LatLng>();
		for (int i = 0; i < latLng.length; i++) {
			latLngs.add(latLng[i]);
		}
		PolylineOptions lo = new PolylineOptions();
		lo.points(latLngs);
		lo.visible(true);
		lo.width(3);
		lo.color(Color.BLUE);

		 storage = BDOverlayStorage.getInstance(context,
				mBaiduMap);
		storage.addOverLay(new CustomOverlay(CustomOverlayTypes.MARATHON, lo));
	}

	/**
	 * 疏散路线图
	 */
	static BDOverlayStorage storage;
	public static void addPolyEvacuateline(Context context, BaiduMap mBaiduMap,
			LatLng[] latLng) {
		List<LatLng> latLngs = new ArrayList<LatLng>();
		for (int i = 0; i < latLng.length; i++) {
			latLngs.add(latLng[i]);
		}
		PolylineOptions lo = new PolylineOptions();
		lo.points(latLngs);
		lo.visible(true);
		lo.width(5);
		lo.color(Color.RED);
		 storage = BDOverlayStorage.getInstance(context,
				mBaiduMap);
		storage.addOverLay(new CustomOverlay(CustomOverlayTypes.EVACUATELINE,
				lo));
	}

	private static Bitmap small(Bitmap bitmap) {
		Matrix matrix = new Matrix();
		matrix.postScale(0.3f, 0.3f); // 长和宽放大缩小的比例
		Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
				bitmap.getHeight(), matrix, true);
		return resizeBmp;
	}
	
}
