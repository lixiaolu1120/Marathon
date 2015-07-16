package cn.cnic.marathon.service;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;
import cn.cnic.marathon.R;
import cn.cnic.marathon.base.BaseAbstractBroadcastReceiver;
import cn.cnic.marathon.base.BroadcastAction;
import cn.cnic.marathon.base.Regexps;
import cn.cnic.marathon.http.NetWork;
import cn.cnic.marathon.http.NetWork.CallBack;
import cn.cnic.marathon.http.request.AcceptFriendRequest;
import cn.cnic.marathon.http.request.FriendsInfoRequest;
import cn.cnic.marathon.http.request.LocationShareRequest;
import cn.cnic.marathon.http.request.MeetRespond;
import cn.cnic.marathon.http.request.Request;
import cn.cnic.marathon.http.response.Response;
import cn.cnic.marathon.rescript.CustomOverlay;
import cn.cnic.marathon.rescript.CustomOverlayTypes;
import cn.cnic.marathon.rescript.UserStatus;
import cn.cnic.marathon.sql.FriendDao;
import cn.cnic.marathon.ui.FriendMangerActivity;
import cn.cnic.marathon.util.BDOverlayStorage;
import cn.cnic.marathon.util.Utils;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

/**
 * 处理推送消息
 * 
 * @author liuxiaoer@cnic.cn
 * 
 */
public class PushServiceReciver extends BaseAbstractBroadcastReceiver {
	View parent;
	BaiduMap mBaiduMap;
	public static PushServiceReciver receiver;
	BDOverlayStorage storage;

	public PushServiceReciver(Context context, View parent, BaiduMap mbm) {
		super();
		this.parent = parent;
		this.mBaiduMap = mbm;
		storage = BDOverlayStorage.getInstance(context, mbm);
		initPushReceiver(context, parent);
	}

	// 注册广播
	public void initPushReceiver(Context context, View parent) {
		IntentFilter filter = new IntentFilter();
		filter.addAction(BroadcastAction.PUSH_ADD_FRIEND);
		filter.addAction(BroadcastAction.PUSH_ADD_FRIEND_ECHO);
		filter.addAction(BroadcastAction.PUSH_DELETE_FRIENDS);
		filter.addAction(BroadcastAction.PUSH_POSITION_SHARE);
		filter.addAction(BroadcastAction.PUSH_POSITION_SHARE_ECHO);
		filter.addAction(BroadcastAction.PUSH_FRIEND_MEETING);
		filter.addAction(BroadcastAction.PUSH_MEETING_ECHO);
		filter.addAction(BroadcastAction.PUSH_FRIEND_SEND_MESSAGE);
		filter.addAction(BroadcastAction.PUSH_EMERGENCY_MESSAGE);
		filter.addAction(BroadcastAction.PUSH_EMERGENCY_PATH);
		context.registerReceiver(this, filter);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		log("receive push service");
		Bundle bundle = new Bundle();
		bundle = intent.getExtras();
		String type = intent.getAction();
		log("type is " + type);
		String content = bundle.getString("content");
		if (BroadcastAction.PUSH_ADD_FRIEND.equals(type)) {// 加好友请求
			handleAddFriend(context, content);
			return;
		}
		if (BroadcastAction.PUSH_ADD_FRIEND_ECHO.equals(type)) {// 响应加好友请求
			new FriendMangerActivity().handleFriendAgreeMessage(context,
					content);
			return;
		}
		if (BroadcastAction.PUSH_DELETE_FRIENDS.equals(type)) {// 删除好友
			handlerDeleteFriend(context, content);
			return;
		}
		if (BroadcastAction.PUSH_POSITION_SHARE.equals(type)) {// 位置共享/取消共享请求
			handlerPositionShare(context, content);
			return;
		}
		if (BroadcastAction.PUSH_POSITION_SHARE_ECHO.equals(type)) {// 响应位置共享请求
			handlerPositionShareEcho(context, content);
			return;
		}
		if (BroadcastAction.PUSH_FRIEND_MEETING.equals(type)) {// 好友会面请求消息推送
			handleFriendMeeting(context, parent, content);
			return;
		}
		if (BroadcastAction.PUSH_MEETING_ECHO.equals(type)) {// 响应好友会面消息推送
			handleFriendMeetingResponse(context, parent, content);
			return;
		}

		if (BroadcastAction.PUSH_FRIEND_SEND_MESSAGE.equals(type)) {// 好友发消息请求
			handleFriendSendMessage(context, content);
			return;
		}
		if (BroadcastAction.PUSH_EMERGENCY_MESSAGE.equals(type)) {// 突发事件广播
			handlerEmergencyMessage(context, content);
			return;
		}
		if (BroadcastAction.PUSH_EMERGENCY_PATH.equals(type)) {// 疏散路线
			handlerEmergencyPath(context, content);
			return;
		}

	}

	/**
	 * 处理应急路线推送消息 ,7.18鸟巢足球赛不需要此功能
	 * 
	 * @param context
	 * @param content
	 */
	private void handlerEmergencyPath(Context context, String content) {
		log("handlerEmergencyPath");
		Pattern pf = Pattern.compile(Regexps.HANDLE_EMERGENCY_PATH);
		Matcher mf = pf.matcher(content);
		mf.find();
		String message = mf.group(1);
		String data = mf.group(2);
		Pattern df = Pattern.compile(Regexps.HANDLE_EMERGENCY_PATH_DATA);
		Matcher mdf = df.matcher(data);
		Pattern di = Pattern.compile(Regexps.HANDLE_EMERGENCY_PATH_DATA_ITEM);
		Matcher idf;
		while (mdf.find()) {
			String item = mdf.group(1);
			idf = di.matcher(item);
			idf.find();
			String id = idf.group(1);
			String shortest = idf.group(2);
			String shortest_len = idf.group(3);
			String longer = idf.group(4);
			String longer_len = idf.group(5);
			String lon = idf.group(6);
			String lat = idf.group(7);
		}
		String time = mf.group(3);
		log(data);
		// 此处需要把应急路线更新到本地数据库中
		log("handlerEmergencyPath end");
	}

	/**
	 * 处理紧急消息推送
	 * 
	 * @param context
	 * @param content
	 */
	private void handlerEmergencyMessage(Context context, String content) {
		log("handlerEmergencyMessage begin");
		try {
			JSONObject object = new JSONObject(content);
			String message = object.getString("message");
			Utils.ToastMSG(context, message);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		// Pattern pf = Pattern.compile(Regexps.HANDLE_EMERGENCY_MESSAGE);
		// Matcher mf = pf.matcher(content);
		// mf.find();
		// String message = mf.group(1);
		// String time = mf.group(2);

		// 如果有本地数据库写入数据库
		// 提示用户有紧急事件发生
		log("handlerEmergencyMessage end");

	}

	/**
	 * 请求共享位置响应
	 * 
	 * @param context
	 * @param content
	 */
	private void handlerPositionShareEcho(Context context, String content) {
		log("handlerPositionShareEcho");
		try {
			JSONObject object = new JSONObject(content);
			String uid = object.getString("uid");
			String nickname = object.getString("nickname");
			int share = object.getInt("share");
			int echo = object.getInt("echo");
			if (echo == 1) {
				Utils.ToastMSG(context, nickname + "  同意与您共享位置信息");
			} else {
				Utils.ToastMSG(context, nickname + "  拒绝与您共享位置信息");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		// Pattern pf = Pattern.compile(Regexps.HANDLE_POSITION_SHARE_ECHO);
		// Matcher mf = pf.matcher(content);
		// mf.find();
		// String echo = mf.group(1);
		// String uid = mf.group(2);
		// String nickname = mf.group(3);
		// String time = mf.group(4);
		// if ("YES".equals(echo)) {
		// Utils.ToastMSG(context, nickname + "同意与您共享位置信息");
		// } else {
		// Utils.ToastMSG(context, nickname + "拒绝与您共享位置信息");
		// }
		// 更新是否与好友共享位置
	}

	/**
	 * 处理好友请求位置共享推送消息
	 * 
	 * @param context
	 * @param content
	 */
	PopupWindow mPositionSharePop = null;

	String mPositionShareUid = null;
	TextView mPositionShareName;

	private void handlerPositionShare(final Context context, String content) {
		log("handlerPositionShare");
		JSONObject object;
		String nickname = null, message = null, time;
		try {
			object = new JSONObject(content);
			mPositionShareUid = object.getString("uid");
			nickname = object.getString("nickname");
			message = object.getString("message");
			time = object.getString("time");

		} catch (JSONException e) {
			e.printStackTrace();
		}
		// Pattern pf = Pattern.compile(Regexps.HANDLE_POSITION_SHARE);
		// Matcher mf = pf.matcher(content);
		// mf.find();
		// String share = mf.group(1);
		// String uid = mf.group(2);
		// String nickname = mf.group(3);
		// String message = mf.group(4);
		// String time = mf.group(5);
		if (mPositionSharePop == null) {
			LayoutInflater layoutInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = layoutInflater.inflate(R.layout.item_pop_receive_msg, null);
			mPositionSharePop = new PopupWindow(view,
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			mPositionShareName = (TextView) view
					.findViewById(R.id.position_share_name);
			msg = (TextView) view.findViewById(R.id.position_share_content);
			sure = (Button) view.findViewById(R.id.position_share_sure);
			cancle = (Button) view.findViewById(R.id.position_share_cancle);
		}
		mPositionShareName.setText(nickname == null ? "" : nickname);
		msg.setText(message);
		// 是否同意接受好友请求
		sure.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mPositionSharePop.dismiss();
				PositionShareResponse(context, mPositionShareUid, "1");
			}
		});
		cancle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mPositionSharePop.dismiss();
				PositionShareResponse(context, mPositionShareUid, "0");
			}
		});
		// 使其聚集 mPositionSharePop.setFocusable(false); // 设置允许在外点击消失
		mPositionSharePop.setOutsideTouchable(false); //
		// 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
		mPositionSharePop.setBackgroundDrawable(new BitmapDrawable());
		mPositionSharePop.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
	}

	/**
	 * 处理有好友解除与你的好友关系消息通知
	 * 
	 * @param context
	 * @param content
	 */
	String deleteFriendName;

	private void handlerDeleteFriend(Context context, String content) {
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(content);
			String uid = jsonObject.getString("uid");
			deleteFriendName = jsonObject.getString("nickname");
			String message = jsonObject.getString("message");
			String time = jsonObject.getString("time");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		// 此处需要在本地数据库解除好友关系
		Utils.ToastMSG(context, deleteFriendName + "和您解除好友关系");
		// Pattern pf = Pattern.compile(Regexps.HANDLE_DELETE_FRIEND);
		// Matcher mf = pf.matcher(content);
		// mf.find();
		// String uid = mf.group(1);
		// String nickname = mf.group(2);
		// String time = mf.group(3);
	}

	/**
	 * 处理好友发消息推送请求
	 * 
	 * @param content
	 */
	private void handleFriendSendMessage(Context context, String content) {
		log("handleFriendSendMessage begin");
		JSONObject object;
		try {
			object = new JSONObject(content);
			String uid = object.getString("uid");
			String nickname = object.getString("nickname");
			String message = object.getString("message");
			Utils.ToastMSG(context, nickname + "发您发送消息:" + message);
			log("handleFriendSendMessage end");
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 响应约人见面 好友请求时,推送后弹出pop
	 * 
	 * @param context
	 */
	Button agree, refuse;
	TextView mName, mMessage;
	ImageView imageView;

	public void handleFriendMeeting(final Context context, View parent,
			final String content) {
		log("handleFriendMeeting");
		JSONObject object;
		try {
			object = new JSONObject(content);
			final String uid = object.getString("uid");
			String nickname = object.getString("nickname");
			String message = object.getString("message");
			final String lon = object.getString("lon");
			final String lat = object.getString("lat");
			// 在地图上显示推送过来的见面地点，并添加marker
			LatLng point3 = new LatLng(Double.parseDouble(lat),
					Double.parseDouble(lon));
			// 构建Marker图标
			BitmapDescriptor bitmap = BitmapDescriptorFactory
					.fromResource(R.drawable.meet_point_6);
			// 构建MarkerOption，用于在地图上添加Marker
			OverlayOptions mMarks = new MarkerOptions().position(point3).icon(
					bitmap);
			storage = BDOverlayStorage.getInstance(context, mBaiduMap);
			storage.addOverLay(new CustomOverlay(CustomOverlayTypes.MEETING,
					mMarks));

			LayoutInflater layoutInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View view = layoutInflater.inflate(R.layout.item_pop_date, null);
			final PopupWindow mPop = new PopupWindow(view,
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			agree = (Button) view.findViewById(R.id.request_agree);
			refuse = (Button) view.findViewById(R.id.request_refuse);
			mName = (TextView) view.findViewById(R.id.inviter_name);
			mMessage = (TextView) view.findViewById(R.id.inviter_context);
			imageView = (ImageView) view.findViewById(R.id.inviter_img);
			mName.setText(nickname == null ? "" : nickname);
			mMessage.setText(message == null ? "" : message);
			agree.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					mPop.dismiss();
					respondInvite(context, uid, lon, lat, true);
				}
			});
			refuse.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					mPop.dismiss();
					respondInvite(context, uid, lon, lat, false);
				}
			});
			// 使其聚集
			mPop.setFocusable(true);
			// 设置允许在外点击消失
			mPop.setOutsideTouchable(false);
			// 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
			mPop.setBackgroundDrawable(new BitmapDrawable());
			mPop.showAtLocation(parent, Gravity.NO_GRAVITY,
					(int) Float.parseFloat(lat), (int) Float.parseFloat(lon));
		} catch (Exception e) {
			log("Regexp error", e);
			return;
		}
	}

	/**
	 * 处理好友请求会面推送
	 * 
	 * @param context
	 * @param parent
	 * @param content
	 */
	void handleFriendMeetingResponse(final Context context, View parent,
			final String content) {
		log("handleFriendMeetingResponse begin");

		// Pattern pf = Pattern.compile(Regexps.HANDLE_FRIEND_MEETING_ECHO);
		// Matcher mf = pf.matcher(content);
		// mf.find();
		// String echo = mf.group(1);
		// String uid = mf.group(2);
		// String nickname = mf.group(3);
		// String lon = mf.group(4);
		// String lat = mf.group(5);
		// String time = mf.group(6);
		JSONObject object;
		try {
			object = new JSONObject(content);
			String uid = object.getString("uid");
			String nickname = object.getString("nickname");
			String lon = object.getString("lon");
			String lat = object.getString("lat");
			int echo = object.getInt("echo");
			if (echo == 1) {
				Utils.ToastMSG(context, nickname + "同意与您见面");
			} else {
				Utils.ToastMSG(context, nickname + "拒绝与您见面");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * MeetRespond（）第一个参数为用户id
	 * 
	 * @param context
	 * @param friend请求的友的id
	 *            ，推送过来好的
	 * @param accept是否接收
	 */
	public void respondInvite(Context context, String friend, String lon,
			String lat, Boolean accept) {
		Request request = new MeetRespond(UserStatus.getUser().getUid(),
				friend, lon, lat, accept);
		NetWork.initNetWork(request, context, new ResponseInvateCallBack(
				context));
	}

	class ResponseInvateCallBack implements CallBack {
		Context context;

		public ResponseInvateCallBack(Context context) {
			super();
			this.context = context;
		}

		@Override
		public void onRequestComplete(Response result) {
			Boolean isSuccess = result.isSuccess();
			if (isSuccess) {
				Utils.ToastMSG(context, "发送成功");
			}

		}

	}

	/**
	 * 加好友推送消息
	 * 
	 * @param context
	 * @param parent
	 */
	Button sure, cancle;
	TextView msg, mAddFriendName;
	View view;
	String addFriendUid = null, addFriendname = null, addFriendTime;

	public void handleAddFriend(final Context context, String content) {
		final PopupWindow mAddFriendPop;
		log("handleAddFriend");
		try {
			JSONObject object = new JSONObject(content);
			addFriendUid = object.getString("uid");
			// addFriendname = object.getString("nickname");
			addFriendTime = object.getString("time");
			// Log.d("nickname", addFriendname);
			// mAddFriendName.setText(nameString);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Log.d("myuid", addFriendUid);
		LayoutInflater layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = layoutInflater.inflate(R.layout.pop_add_friend, null);
		mAddFriendPop = new PopupWindow(view, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		msg = (TextView) view.findViewById(R.id.add_friend_content);
		mName = (TextView) view.findViewById(R.id.add_friend_name);
		sure = (Button) view.findViewById(R.id.add_friend_sure);
		cancle = (Button) view.findViewById(R.id.add_friend_cancle);
		// msg.setText("");
		mName.setText(addFriendname == null ? "" : addFriendname);

		// 使其聚集
		mAddFriendPop.setFocusable(false);
		// 设置允许在外点击消失
		mAddFriendPop.setOutsideTouchable(false);
		// 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
		mAddFriendPop.setBackgroundDrawable(new BitmapDrawable());
		mAddFriendPop.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
		// 是否同意接受好友请求
		sure.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mAddFriendPop.dismiss();
				addFriendEcho(context, addFriendUid, "1");
				// 把好友添加到数据库里面暂时没写
				// HelperDao.inserFriend(addFriendUid, addFriendname, "", "",
				// context);
				// 开启网络从服务器获取并更新好友列表
				FriendInfoResponse(context, addFriendTime);

			}
		});
		cancle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mAddFriendPop.dismiss();
				addFriendEcho(context, addFriendUid, "0");
			}
		});
	}

	/**
	 * 加好友响应
	 */
	public void addFriendEcho(Context context, String friend, String accept) {
		Request request = new AcceptFriendRequest(
				UserStatus.getUser().getUid(), friend, accept);
		NetWork.initNetWork(request, context,
				new AddFriendEchoCallback(context));
	}

	class AddFriendEchoCallback implements CallBack {
		Context context;

		public AddFriendEchoCallback(Context context) {
			super();
			this.context = context;
		}

		@Override
		public void onRequestComplete(Response result) {
			Map<String, Object> content = result.getContent();
			Boolean isSuccess = result.isSuccess();
			if (isSuccess) {
				Utils.ToastMSG(context, "发送成功");

			}

		}
	}

	/**
	 * 响应会面请求
	 * 
	 * @param context
	 * @param friend
	 * @param accept
	 */
	public void meetingResponse(Context context, String friend, String lon,
			String lat, Boolean accept) {
		Request request = new MeetRespond(UserStatus.getUser().getUid(),
				friend, lon, lat, accept);
		NetWork.initNetWork(request, context, new MeetingResponseCallback());
	}

	class MeetingResponseCallback implements CallBack {
		@Override
		public void onRequestComplete(Response result) {
			Map<String, Object> content = result.getContent();
			Boolean isSuccess = result.isSuccess();
			if (isSuccess) {
				//
				// Utils.ToastMSG(context, "发送成功");
			}

		}

	}

	/**
	 * 位置共享
	 * 
	 * @param context
	 * @param friend
	 * @param accept
	 */
	public void PositionShareResponse(Context context, String friend,
			String accept) {
		Request request = new LocationShareRequest(UserStatus.getUser()
				.getUid(), friend, accept);
		NetWork.initNetWork(request, context,
				new PositionShareCallback(context));
	}

	class PositionShareCallback implements CallBack {
		Context context;

		public PositionShareCallback(Context context) {
			this.context = context;
		}

		@Override
		public void onRequestComplete(Response result) {
			Map<String, Object> content = result.getContent();
			Boolean isSuccess = result.isSuccess();
			if (isSuccess) {
				Utils.ToastMSG(context, "发送成功");
			}

		}

	}

	/**
	 * 同意加好友之后开启网络更新好友列表
	 * 
	 * @param context
	 * @param friend
	 * @param accept
	 */
	public void FriendInfoResponse(Context context, String time) {
		Request request = new FriendsInfoRequest(UserStatus.getUser().getUid(),
				time);
		NetWork.initNetWork(request, context, new FriendInfoResponseCallback(
				context));
	}

	class FriendInfoResponseCallback implements CallBack {
		Context context;
		FriendDao dao;

		public FriendInfoResponseCallback(Context context) {
			super();
			this.context = context;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void onRequestComplete(Response result) {
			Map<String, Object> content = result.getContent();
			for (String key : content.keySet())
				Log.d("DATA", key);
			List<Map<String, String>> friends = (List<Map<String, String>>) content
					.get("data");
			Boolean isSuccess = result.isSuccess();
			if (isSuccess) {
			}
			Log.d("listssss", friends + "");
			dao = new FriendDao(context);
			dao.clearAndInsertFriends(friends, content.get("time").toString());
			// 通知listview刷新flag
			// Utils.LISTVIEW_REFRESH=true;

		}
	}

}
