package cn.cnic.marathon.tools;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.cnic.marathon.R;
import cn.cnic.marathon.ui.ScheduActivity;
import cn.cnic.marathon.util.ItUtils;

/**
 * 弹出popuwindow
 * 
 * @author cuixipeng
 */
public class Popuwd {
	public static PopupWindow popupWindow;
	public static View view;

	/**
	 * 点击赛程弹出的pop
	 * 
	 * @param parent
	 * @param context
	 */
	public static void showWindow(View parent, final Context context, final String title,String time,final String contextString,String subtitle){
		TextView dateTitle = null;
		TextView timeText = null;
		TextView subTitle;
		RelativeLayout layout;
		if (popupWindow == null) {
			LayoutInflater layoutInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = layoutInflater.inflate(R.layout.pop_schedu, null);
			popupWindow = new PopupWindow(view, parent.getWidth() / 5*4,
					LayoutParams.WRAP_CONTENT, true);
		}
		layout=(RelativeLayout)view.findViewById(R.id.date_notice);
		dateTitle = (TextView) view
				.findViewById(R.id.date_title);
		subTitle = (TextView) view
				.findViewById(R.id.schedu_sub_title);
		timeText = (TextView) view
				.findViewById(R.id.date_time);
		subTitle.setText(subtitle);
		dateTitle.setText(title);
		timeText.setText(time);
		layout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent();
				intent.setClass(context, ScheduActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
				intent.putExtra("title", title);
				intent.putExtra("context", contextString);//传递的内容
				context.startActivity(intent);
			}
		});
		// 使其聚集
		popupWindow.setFocusable(true);
		// 设置允许在外点击消失
		popupWindow.setOutsideTouchable(false);
		// 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		int xPos = windowManager.getDefaultDisplay().getHeight()/2;
		int[] location = new int[2];
		parent.getLocationOnScreen(location);
		popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, parent.getHeight()+20);

	}
}
