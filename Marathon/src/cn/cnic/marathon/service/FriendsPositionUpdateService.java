package cn.cnic.marathon.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.util.Log;
import cn.cnic.marathon.http.NetWork;
import cn.cnic.marathon.http.NetWork.CallBack;
import cn.cnic.marathon.http.request.FriendPositionRequest;
import cn.cnic.marathon.http.response.FriendPositionResponse;
import cn.cnic.marathon.http.response.Response;
import cn.cnic.marathon.rescript.UserStatus;
import cn.cnic.marathon.sql.FriendDao;

/**
 * 开启定时服务定时更新好友位置
 * @author ll
 *
 */
public class FriendsPositionUpdateService {
	FriendDao dao;
	ArrayList<HashMap<String, Object>> positions;
	final long period = 30 * 1000;
	Context context;

	public FriendsPositionUpdateService(final Context context) {
		this.context = context;
		dao = new FriendDao(context);
		startTask();
	}

	private void startTask() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				if (!UserStatus.isLogined())
					return;
				String time = dao.getLastestTime();
				String uid = UserStatus.getUser().getUid();
				FriendPositionRequest upLoad = new FriendPositionRequest(uid,
						time);
				NetWork.serviceRequest(upLoad, context,
						new FriendsPositionUpdateCallBack());
			}
		};
		Timer timer = new Timer();
		timer.schedule(task, 0, period);
	}

	class FriendsPositionUpdateCallBack implements CallBack {
		@Override
		public void onRequestComplete(Response result) {
			FriendPositionResponse respone = (FriendPositionResponse) result;
			if (respone.isSuccess()) {
				Log.i("DATA", "friend's " + respone.getFriendPosition().size());
				dao.updateFriendPosition(respone.getFriendPosition(),
						respone.getTime());
			}
		}
	}
}
