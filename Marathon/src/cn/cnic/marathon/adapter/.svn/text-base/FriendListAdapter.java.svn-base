package cn.cnic.marathon.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.cnic.marathon.R;
import cn.cnic.marathon.adapter.ScheduAdapter.viewHolder;
import cn.cnic.marathon.base.Regexps;
import cn.cnic.marathon.base.UserInfo;
import cn.cnic.marathon.http.NetWork;
import cn.cnic.marathon.http.NetWork.CallBack;
import cn.cnic.marathon.http.request.Login;
import cn.cnic.marathon.http.request.Request;
import cn.cnic.marathon.http.request.RequestLocShare;
import cn.cnic.marathon.http.response.Response;
import cn.cnic.marathon.rescript.UserStatus;
import cn.cnic.marathon.service.PushService;
import cn.cnic.marathon.sql.FriendDao;
import cn.cnic.marathon.tools.UtilTool;
import cn.cnic.marathon.ui.CustomTextView;
import cn.cnic.marathon.ui.LoginActivity;
import cn.cnic.marathon.util.Utils;
import cn.sharesdk.facebook.c;

public class FriendListAdapter extends BaseAdapter {
	private List<Map<String, Object>> mList;
	private Context context;
	LayoutInflater mInflater;
	Map<String, Object> map = new HashMap<String, Object>();
	Map<String, Object> map2 = new HashMap<String, Object>();
	Map<String, Object> map3 = new HashMap<String, Object>();
	TextView mComplete;
	CustomTextView addFriend;

	public FriendListAdapter(List<Map<String, Object>> mList, Context context,
			TextView mComplete, CustomTextView addFriend) {
		super();
		this.mList = mList;
		this.context = context;
		this.mComplete = mComplete;
		this.addFriend = addFriend;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int arg0) {
		return mList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(final int position, View convertview, ViewGroup view) {
		viewHolder holder = null;
		if (convertview == null) {
			convertview = mInflater.inflate(R.layout.item_friends, null);
			holder = new viewHolder();
			holder.mTitle = (TextView) convertview
					.findViewById(R.id.friend_name);
			holder.mContent = (TextView) convertview
					.findViewById(R.id.friend_desc);

			holder.avatarImageView = (ImageView) convertview
					.findViewById(R.id.friend_avatar);
			holder.pos = (CustomTextView) convertview
					.findViewById(R.id.show_pos);
			convertview.setTag(holder);
		} else {
			holder = (viewHolder) convertview.getTag();
		}

		holder.mTitle.setText(mList.get(position).get("nickname").toString());
		holder.mContent.setText(mList.get(position).get("description")
				.toString());
		String share = mList.get(position).get("share").toString();
		if ("0".equals(share)) {
			holder.pos.setActivated(false);
			holder.pos.setTextColor(Color.GRAY);
			map2.put(mList.get(position).get("uid").toString(), "0");
			map3.put(mList.get(position).get("uid").toString(), "0");
		} else {
			// 已经点击分享的好友 share=1
			holder.pos.setActivated(true);
			holder.pos.setTextColor(Color.RED);
			map2.put(mList.get(position).get("uid").toString(), "1");
			map3.put(mList.get(position).get("uid").toString(), "1");
		}
		holder.pos.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				boolean isActivated = view.isActivated();
				view.setActivated(!isActivated);
				if (isActivated) {
					map3.put(mList.get(position).get("uid").toString(), "0");
					((CustomTextView) view).setTextColor(Color.GRAY);
				} else {
					// 点击眼睛，记录显示红色的眼睛的用户id
					map3.put(mList.get(position).get("uid").toString(), "1");
					Log.d("map3dianjihou", map3 + "");
					((CustomTextView) view).setTextColor(Color.RED);
				}
				if (map2.equals(map3)) {
					// 集合相等 显示加好友的按钮.
					mComplete.setVisibility(View.GONE);
					addFriend.setVisibility(View.VISIBLE);
				} else {
					addFriend.setVisibility(View.GONE);
					mComplete.setVisibility(View.VISIBLE);
				}
			}
		});

		mComplete.setOnClickListener(new OnClickListener() {
			@SuppressWarnings("rawtypes")
			@Override
			public void onClick(View arg0) {
				// 所有选择分享的好友的集合
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Map<String, Object> m = null;
				Iterator iter = map3.entrySet().iterator();
				Map.Entry entry;
				while (iter.hasNext()) {
					m = new HashMap<String, Object>();
					entry = (Map.Entry) iter.next();
					m.put("fid", entry.getKey());
					m.put("share", entry.getValue().toString());
					list.add(m);
				}
				Request request = new RequestLocShare(UserStatus.getUser()
						.getUid() , list);
				NetWork.initNetWork(request, context, new requestLocShare(list));
				
			}
		});
		return convertview;
	}

	class requestLocShare implements CallBack {
		List<Map<String, Object>> list;
		
		public requestLocShare(List<Map<String, Object>> list) {
			super();
			this.list = list;
		}

		@Override
		public void onRequestComplete(Response result) {
			Boolean isSuccess = result.isSuccess();
			FriendDao dao;
			if (isSuccess) {
				// 成功
				addFriend.setVisibility(View.VISIBLE);
				mComplete.setVisibility(View.GONE);
				dao=new FriendDao(context);
				dao.updateFriendShare(list);
				Utils.ToastMSG(context, "发送成功");
			}
		}
	}
	public static class viewHolder {
		public TextView mTitle, mContent;
		CustomTextView pos;
		ImageView avatarImageView;
	}

}
