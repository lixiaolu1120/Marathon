package cn.cnic.marathon.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import cn.cnic.marathon.http.NetWork;
import cn.cnic.marathon.http.NetWork.CallBack;
import cn.cnic.marathon.http.request.Mark;
import cn.cnic.marathon.http.response.MarkResponse;
import cn.cnic.marathon.http.response.Response;
import cn.cnic.marathon.sql.MarkerDao;

public class MarkUpdateService {

	MarkerDao dao;
	ArrayList<HashMap<String, Object>> positions;
	final long period = 1 * 60 * 60 * 1000;
	Context context;

	public MarkUpdateService(final Context context) {
		this.context = context;
		dao = new MarkerDao(context);
		startTask();
	}

	private void startTask() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				String time = dao.getLastestTime();
				Mark mark = new Mark(time);
				NetWork.serviceRequest(mark, context, new MarkUpdateCallBack());
			}
		};
		Timer timer = new Timer();
		timer.schedule(task, 0, period);
	}

	class MarkUpdateCallBack implements CallBack {
		@SuppressWarnings("unchecked")
		@Override
		public void onRequestComplete(Response result) {
			MarkResponse response = (MarkResponse) result;
			if (response.isSuccess()) {
				List<Map<String, Object>> marks = (List<Map<String, Object>>) response
						.getContent().get("data");
				dao.AddMarkers(marks);
			}
		}
	}

}
