package cn.cnic.marathon.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.util.Log;
import cn.cnic.marathon.http.NetWork;
import cn.cnic.marathon.http.NetWork.CallBack;
import cn.cnic.marathon.http.request.PositionUploadRequest;
import cn.cnic.marathon.http.response.PositionUploadResponse;
import cn.cnic.marathon.http.response.Response;
import cn.cnic.marathon.rescript.UserStatus;
import cn.cnic.marathon.sql.PositionDao;

/**
 * 用户位置上传服务，时间间隔10分钟
 * 
 * @author ll
 * 
 */
public class PositionUploadService {
	PositionDao dao;
	ArrayList<HashMap<String, Object>> positions;
	final long period = 30 * 1000;
	Context context;

	public PositionUploadService(final Context context) {
		this.context = context;
		dao = new PositionDao(context);
		startTask();
	}

	private void startTask() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				if (!UserStatus.isLogined())
					return;
				positions = dao.getAllUnUploadPos();
				String uid = UserStatus.getUser().getUid();
				Log.i("DATA", "userid is " + uid);
				PositionUploadRequest upLoad = new PositionUploadRequest(uid,
						positions);
				NetWork.serviceRequest(upLoad, context,
						new PositionUploadCallBack());
				Log.d("DATA",
						"The size of position upload is " + positions.size());
			}
		};
		Timer timer = new Timer();
		timer.schedule(task, 0, period);
	}

	class PositionUploadCallBack implements CallBack {
		@Override
		public void onRequestComplete(Response result) {
			Log.i("DATA", result == null ? "position upload no result"
					: "position upload result." + result.toString());
			if (result == null)
				return;
			PositionUploadResponse respone = (PositionUploadResponse) result;
			if (respone.isSuccess()) {
				Log.i("DATA", "upload data size is " + positions.size());
				// 开启线程更新数据库
				new Thread(new Runnable() {
					@Override
					public void run() {
						dao.setUploadStatus(positions);
					}
				}).start();
			}
		}
	}
}
