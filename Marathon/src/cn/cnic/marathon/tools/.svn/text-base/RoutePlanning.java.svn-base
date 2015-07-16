package cn.cnic.marathon.tools;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import cn.cnic.marathon.R;
import cn.cnic.marathon.ui.BMapActivity;

import com.baidu.mapapi.overlayutil.DrivingRouteOverlay;
import com.baidu.mapapi.overlayutil.TransitRouteOverlay;
import com.baidu.mapapi.overlayutil.WalkingRouteOverlay;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
@Deprecated
public class RoutePlanning {
//	/**
//	 * 发起路线规划搜索示例
//	 * 
//	 * @param v
//	 */
//	public void SearchButtonProcess(View v) {
//		// 重置浏览节点的路线数据
//		// route = null;
//		mBaiduMap.clear();
//		// 处理搜索按钮响应
//		// 设置起终点信息，对于tranist search 来说，城市名无意义
//		PlanNode stNode = PlanNode.withCityNameAndPlaceName("北京", "知春路");
//		PlanNode enNode = PlanNode.withCityNameAndPlaceName("北京", "天安门");
//
//		// 实际使用中请对起点终点城市进行正确的设定
//		if (v.getId() == R.id.drive) {
//			mSearchs.drivingSearch((new DrivingRoutePlanOption()).from(stNode)
//					.to(enNode));
//
//		} else if (v.getId() == R.id.transit) {
//			mSearchs.transitSearch((new TransitRoutePlanOption()).from(stNode)
//					.city("北京").to(enNode));
//
//		} else if (v.getId() == R.id.walk) {
//			mSearchs.walkingSearch((new WalkingRoutePlanOption()).from(stNode)
//					.to(enNode));
//		}
//	}
//
//	@Override
//	protected void onRestoreInstanceState(Bundle savedInstanceState) {
//		super.onRestoreInstanceState(savedInstanceState);
//	}
//
//	@Override
//	public void onGetWalkingRouteResult(WalkingRouteResult result) {
//		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
//			Toast.makeText(BMapActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT)
//					.show();
//		}
//		if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
//			// 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
//			// result.getSuggestAddrInfo()
//			return;
//		}
//		if (result.error == SearchResult.ERRORNO.NO_ERROR) {
//			WalkingRouteOverlay overlay = new WalkingRouteOverlay(mBaiduMap);
//			mBaiduMap.setOnMarkerClickListener(overlay);
//			routeOverlay = overlay;
//			overlay.setData(result.getRouteLines().get(0));
//			overlay.addToMap();
//			overlay.zoomToSpan();
//		}
//
//	}
//
//	@Override
//	public void onGetTransitRouteResult(TransitRouteResult result) {
//		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
//			Toast.makeText(BMapActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT)
//					.show();
//		}
//		if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
//			// 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
//			// result.getSuggestAddrInfo()
//			return;
//		}
//		if (result.error == SearchResult.ERRORNO.NO_ERROR) {
//			TransitRouteOverlay overlay = new TransitRouteOverlay(mBaiduMap);
//			mBaiduMap.setOnMarkerClickListener(overlay);
//			routeOverlay = overlay;
//			overlay.setData(result.getRouteLines().get(0));
//			overlay.addToMap();
//			overlay.zoomToSpan();
//		}
//	}
//
//	@Override
//	public void onGetDrivingRouteResult(DrivingRouteResult result) {
//		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
//			Toast.makeText(BMapActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT)
//					.show();
//		}
//		if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
//			// 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
//			// result.getSuggestAddrInfo()
//			return;
//		}
//		if (result.error == SearchResult.ERRORNO.NO_ERROR) {
//			DrivingRouteOverlay overlay = new DrivingRouteOverlay(mBaiduMap);
//			routeOverlay = overlay;
//			mBaiduMap.setOnMarkerClickListener(overlay);
//			overlay.setData(result.getRouteLines().get(0));
//			overlay.addToMap();
//			overlay.zoomToSpan();
//		}
//	}
}
