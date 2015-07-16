package cn.cnic.marathon.service;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import cn.cnic.marathon.R;
import cn.cnic.marathon.ui.BMapActivity;

public class AlarmReceiver extends BroadcastReceiver {
	// private NotificationManager manager;

	@Override
	public void onReceive(Context context, Intent intent) {
		nm = (NotificationManager) context
				.getSystemService(context.NOTIFICATION_SERVICE);
		Intent intents = new Intent(context, BMapActivity.class);
		intents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		pd = PendingIntent.getActivity(context, 0, intents, 0);
		// 新建状态栏通知
		baseNF = new Notification();
		// 设置通知在状态栏显示的图标
		baseNF.icon = R.drawable.ic_launcher;
		// 通知时在状态栏显示的内容
		baseNF.tickerText = "通知显示的内容";
		// 通知的默认参数 DEFAULT_SOUND, DEFAULT_VIBRATE, DEFAULT_LIGHTS.
		// 如果要全部采用默认值, 用 DEFAULT_ALL.
		// 此处采用默认声音
		baseNF.defaults |= Notification.DEFAULT_SOUND;
		baseNF.defaults |= Notification.DEFAULT_VIBRATE;
		baseNF.defaults |= Notification.DEFAULT_LIGHTS;
		// 让声音、振动无限循环，直到用户响应
		// baseNF.flags |= Notification.FLAG_INSISTENT;
		// 通知被点击后，自动消失
		baseNF.flags |= Notification.FLAG_AUTO_CANCEL;
		// // 点击'Clear'时，不清楚该通知(QQ的通知无法清除，就是用的这个)
		// baseNF.flags |= Notification.FLAG_NO_CLEAR;
		// 第二个参数 ：下拉状态栏时显示的消息标题 expanded message title
		// 第三个参数：下拉状态栏时显示的消息内容 expanded message text

		// 第四个参数：点击该通知时执行页面跳转
		String titleString = intent.getExtras().getString("title");
		String contextString = intent.getExtras().getString("title");

		baseNF.setLatestEventInfo(context, titleString, contextString, pd);
		// 发出状态栏通知
		// The first parameter is the unique ID for the Notification
		// and the second is the Notification object.
		nm.notify(Notification_ID_BASE, baseNF);
	}

	/**
	 * 初始化
	 */
	// 通知管理器
	private NotificationManager nm;
	// 通知显示内容
	private PendingIntent pd;
	// BASE Notification ID
	private int Notification_ID_BASE = 110;
	private Notification baseNF;
	AlarmReceiver receiver;
}
