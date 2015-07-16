package cn.cnic.marathon.service;

import android.content.Context;
import cn.cnic.marathon.http.NetWork;
import cn.cnic.marathon.http.NetWork.CallBack;
import cn.cnic.marathon.http.request.FriendsInfoRequest;
import cn.cnic.marathon.http.response.FriendsInfoResponse;
import cn.cnic.marathon.http.response.Response;
import cn.cnic.marathon.rescript.UserStatus;
import cn.cnic.marathon.sql.FriendDao;

public class FriendsInfoRequestService {
	static FriendDao dao;

	public FriendsInfoRequestService(Context context) {
		dao = new FriendDao(context);
		startTask(context);
	}

	public void startTask(final Context context) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String time = dao.getLastestTime();
				FriendsInfoRequest request = new FriendsInfoRequest(UserStatus
						.getUser().getUid(), time);
				NetWork.serviceRequest(request, context, new FriendsInfoCallback());
			}
		}).start();
	}

	static class FriendsInfoCallback implements CallBack {
		@Override
		public void onRequestComplete(Response result) {
			FriendsInfoResponse friends = (FriendsInfoResponse) result;
			dao.clearAndInsertFriends(friends.getFriendList(),
					friends.getTime());

		}
	}

}
