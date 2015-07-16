//package cn.cnic.marathon.mapinterface;
//
//import android.content.Context;
//import android.util.Log;
//import android.widget.Toast;
//import cn.cnic.marathon.util.Utils;
//
//import com.baidu.mapapi.search.core.SearchResult;
//import com.baidu.mapapi.search.geocode.GeoCodeResult;
//import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
//import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
//
//public class GetGeoCoderResult implements OnGetGeoCoderResultListener {
//	private Context context;
//	String mMyLocation;// 当前坐标转化的位置
//	public GetGeoCoderResult(Context context) {
//		super();
//		this.context = context;
//	}
//	/**
//	 * 反地理
//	 */
//	@Override
//	public void onGetGeoCodeResult(GeoCodeResult result) {
//		
//	}
//
//	@Override
//	public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
//		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
//			Toast.makeText(context, "抱歉，未能找到结果", Toast.LENGTH_LONG).show();
//			return;
//		}
//			// 通过反地理转换得到当前地址信息
//			mMyLocation = result.getAddress();
////			Utils.ToastMSG(context, "您选中的位置："+mMyLocation);
//			Log.d("位置终点：" ,mMyLocation);
//			PathRecommend.pathSearchProgress("walking", mMyLocation.toString());
//	}
//}
