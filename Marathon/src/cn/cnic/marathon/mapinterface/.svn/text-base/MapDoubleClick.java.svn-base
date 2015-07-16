package cn.cnic.marathon.mapinterface;

import android.view.View;

import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.BaiduMap.OnMapDoubleClickListener;
import com.baidu.mapapi.model.LatLng;

/**
 * 地图双击事件
 * @author cuixipeng
 */
public class MapDoubleClick implements OnMapDoubleClickListener {
	boolean mMapDoubleClickFlag = false;
	public View view;

	public MapDoubleClick(View view) {
		super();
		this.view = view;

	}

	@Override
	public void onMapDoubleClick(LatLng lat) {
		int isVisible = view.getVisibility();
		if (View.GONE == isVisible)
			view.setVisibility(View.VISIBLE);
		else {
			view.setVisibility(View.GONE);
		}
	}
}
