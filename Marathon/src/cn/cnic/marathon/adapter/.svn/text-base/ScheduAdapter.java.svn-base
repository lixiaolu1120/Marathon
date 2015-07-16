package cn.cnic.marathon.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Text;

import cn.cnic.marathon.R;
import cn.cnic.marathon.sql.HelperDao;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ScheduAdapter extends BaseAdapter {
	private List<Map<String, Object>> mList;
	private Context context;
	LayoutInflater mInflater;
	Map<String, Object> map = new HashMap<String, Object>();

	public ScheduAdapter(List<Map<String, Object>> mList, Context context) {
		super();
		this.mList = mList;
		this.context = context;
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
	public View getView(int position, View convertview, ViewGroup view) {
		viewHolder holder = null;
		if (convertview == null) {
			convertview = mInflater.inflate(R.layout.item_schedu, null);
			holder = new viewHolder();
			holder.mTitle = (TextView) convertview
					.findViewById(R.id.schedutitle);
			holder.mTime = (TextView) convertview
					.findViewById(R.id.schedudate);
			holder.mContent = (TextView) convertview
					.findViewById(R.id.scheducontent);
			convertview.setTag(holder);
		} else {
			holder = (viewHolder) convertview.getTag();
		}

		Map schedule = mList.get(position); 
		holder.mTitle.setText(schedule.get("title").toString());
		holder.mTime.setText(schedule.get("trigger_at").toString());
		holder.mContent.setText(schedule.get("content").toString());
		return convertview;
	}

	public static class viewHolder {
		public TextView mTime, mTitle, mContent;
	}
}
