package cn.cnic.marathon.mapinterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import cn.cnic.marathon.R;
import cn.cnic.marathon.sql.HelperDao;
import cn.cnic.marathon.tools.AddPolylineTool;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapStatusChangeListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;

/**
 * 百度地图状态改变监听 ad：缩放级别改变
 * 
 * @author cuixipeng
 * 
 */
public class BaiduMapStatueChangeListener implements OnMapStatusChangeListener {
	private List<Map<String, Object>> mAllMarks = new ArrayList<Map<String, Object>>();// 存放标注的集合
	private BaiduMap mBaiduMap;
	private Context context;

	public BaiduMapStatueChangeListener(BaiduMap mBaiduMap, Context context) {
		super();
		this.mBaiduMap = mBaiduMap;
		this.context = context;
	}

	@Override
	public void onMapStatusChange(MapStatus arg0) {

	}

	@Override
	public void onMapStatusChangeFinish(MapStatus status) {
		int level = (int) status.zoom;
		if (level > 16) {
			initAllMark();
		} else if (level < 16 && level == 16) {
			mBaiduMap.clear();
			AddPolylineTool.drawEventRoute(context, mBaiduMap);
		}
	}

	@Override
	public void onMapStatusChangeStart(MapStatus arg0) {

	}

	public void initAllMark() {
		mBaiduMap.clear();
		AddPolylineTool.drawEventRoute(context, mBaiduMap);
		String volumns[] = { "type" };
		// String values[] = { "0" };
		// mAllMarks = HelperDao.orRawQuery(context, "mark", volumns, values);
		// for (int i = 0; i < mAllMarks.size(); i++) {
		// Map<String, Object> mark = mAllMarks.get(i);
		// // 定义Maker坐标点
		// double lat = Double.parseDouble(mark.get("lat").toString());
		// double lon = Double.parseDouble(mark.get("lon").toString());
		// LatLng point = new LatLng(lat, lon);
		// // 构建Marker图标
		// BitmapDescriptor bitmap = BitmapDescriptorFactory
		// .fromResource(R.drawable.icon_marka);
		// // 构建MarkerOption，用于在地图上添加Marker
		// MarkerOptions mMarks = new MarkerOptions().position(point).icon(
		// bitmap);
		// mBaiduMap.addOverlay(mMarks);
		// }
		String values1[][] = { { "0" }, { "1" }, { "2" }, { "3" }, { "4" },
				{ "5" }, { "6" } };
		int[] mImgIcon = { R.drawable.icon_marka, R.drawable.icon_markb,
				R.drawable.icon_markc, R.drawable.icon_markd,
				R.drawable.icon_marke, R.drawable.icon_markf,
				R.drawable.icon_markg };
		Map<String, Object> mark;
		for (int j = 0; j < values1.length; j++) {
			mAllMarks = HelperDao.orRawQuery(context, "mark", volumns,
					values1[j]);
			for (int i = 0; i < mAllMarks.size(); i++) {
				mark = mAllMarks.get(i);
				// 定义Maker坐标点
				double lat = Double.parseDouble(mark.get("lat").toString());
				double lon = Double.parseDouble(mark.get("lon").toString());
				LatLng point = new LatLng(lat, lon);
				// 构建Marker图标
				BitmapDescriptor bitmap = BitmapDescriptorFactory
						.fromResource(mImgIcon[j]);
				// 构建MarkerOption，用于在地图上添加Marker
				MarkerOptions mMarks = new MarkerOptions().position(point)
						.icon(bitmap);
				bitmap.recycle();
				mBaiduMap.addOverlay(mMarks);
			}
		}
	}
}
