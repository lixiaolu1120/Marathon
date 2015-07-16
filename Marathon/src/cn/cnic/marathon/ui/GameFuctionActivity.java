//package cn.cnic.marathon.ui;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import cn.cnic.marathon.R;
//import cn.cnic.marathon.adapter.FriendsGridViewAdapter;
//import cn.cnic.marathon.adapter.NearGridViewAdapter;
//import cn.cnic.marathon.base.BaseActivity;
//import cn.cnic.marathon.util.ItUtils;
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.MotionEvent;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.GridView;
//
///**
// * 赛程，包含功能模块：参加马拉松活动的运动员列表及详情。 为马拉松喝彩的啦啦队列表及详情。 与马拉松举办方有合作关系的合作伙伴列表及详情。
// * 为马拉松举办方提供资源的广告商列表及详情 马拉松实时赛事
// * 
// * @author cuixipeng
// * 
// */
//public class GameFuctionActivity extends BaseActivity {
//	private NearGridViewAdapter adapter;
//	private List<String> mList = new ArrayList<String>();
//	private GridView mGridView;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.dialog_game);
//		findViewbyId();
//		initView();
//		setListeners();
//	}
//
//	@Override
//	protected void initView() {
//		//初始化。图片数组等
//		int[] image = { R.drawable.arow, R.drawable.arow, R.drawable.arow,
//				R.drawable.arow, R.drawable.arow, R.drawable.arow, };
//		adapter = new NearGridViewAdapter(image, getApplicationContext());
//		mGridView.setAdapter(adapter);
//	}
//
//	@Override
//	protected void findViewbyId() {
//		//findid
//		mGridView = (GridView) findViewById(R.id.gridviews);
//	}
////gridview 每一个item的点击事件
//	@Override
//	protected void setListeners() {
//		mGridView.setOnItemClickListener(new OnItemClickListener() {
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1,
//					int position, long id) {
//				if (position == 0) {
//					ItUtils.intent(GameFuctionActivity.this,
//							ScheduActivity.class);
//					finish();
//				}
//				if (position == 1) {
//					ItUtils.intent(GameFuctionActivity.this,
//							PlayerActivity.class);
//					finish();
//				}
//				if (position == 2) {
//					ItUtils.intent(GameFuctionActivity.this,
//							CheerleadersActivity.class);
//					finish();
//				}
//				if (position == 3) {
//					ItUtils.intent(GameFuctionActivity.this,
//							PartnersActivity.class);
//					finish();
//				}
//				if (position == 4) {
//					ItUtils.intent(GameFuctionActivity.this,
//							AdvertActivity.class);
//					finish();
//				}
//				if (position == 5) {
//					ItUtils.intent(GameFuctionActivity.this,
//							ActuallyActivity.class);
//					finish();
//				}
//
//			}
//		});
//
//	}
//
//	/**
//	 * 设置点击窗体外，关闭activity
//	 */
//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		finish();
//		return true;
//	}
//}
