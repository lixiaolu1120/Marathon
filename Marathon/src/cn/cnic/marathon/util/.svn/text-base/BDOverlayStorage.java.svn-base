package cn.cnic.marathon.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.util.Log;
import cn.cnic.marathon.rescript.CustomOverlay;

import com.baidu.mapapi.map.BaiduMap;

public class BDOverlayStorage {

	private BaiduMap map;
	// 存储所有要画在地图上的overlay
	private static List<CustomOverlay> storage;
	private static BDOverlayStorage bdOverlay = null;

	private BDOverlayStorage(Context context, BaiduMap map) {
		this.map = map;
		if (storage == null)
			storage = new ArrayList<CustomOverlay>();
	}

	public static synchronized BDOverlayStorage getInstance(Context context,
			BaiduMap map) {
		if (bdOverlay == null) {
			return new BDOverlayStorage(context, map);
		}
		Log.i("DATA", storage.size() + "");
		return bdOverlay;
	}

	// public void addMultiOverLay(List<CustomOverlay> markers) {
	// synchronized (BDOverlayStorage.class) {
	// storage.addAll(markers);
	// }
	// refresh();
	// }
	//
	// public void addOverLay(CustomOverlay marker) {
	// synchronized (BDOverlayStorage.class) {
	// storage.add(marker);
	// }
	// refresh();
	// }
	public void addMultiOverLay(List<CustomOverlay> markers) {
		synchronized (BDOverlayStorage.class) {
			storage.addAll(markers);
		}
		for (CustomOverlay marker : markers) { 
			map.addOverlay(marker.getOptions());
		}
	}

	public void addOverLay(CustomOverlay marker) {
		synchronized (BDOverlayStorage.class) {
			storage.add(marker);
		}
		map.addOverlay(marker.getOptions());
	}

	private void deleteOverLayByTypeWithoutRefresh(String[] types) {
		Iterator<CustomOverlay> i = storage.iterator();
		CustomOverlay marker;
		while (i.hasNext()) {
			marker = i.next();
			for (String type : types) {
				if (type.equals(marker.getType())) {
					// Log.i("DATA", "marker type is " + marker.getType());
					i.remove();
				}
			}
		}
		// Log.i("DATA", "storage delete type " + types + ". storage size is "
		// + storage.size());
	}

	String[] types;

	public void deleteOverLayByType(String type) {
		types = new String[] { type };
		deleteOverLayByTypeWithoutRefresh(types);
		refresh();
	}

	public void deleteOverLayByTypes(String[] types) {
		deleteOverLayByTypeWithoutRefresh(types);
		refresh();
	}

	public void refresh() {
		map.clear();
		for (CustomOverlay marker : storage) {
			map.addOverlay(marker.getOptions());
		}
	}

}
