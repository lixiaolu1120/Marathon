package cn.cnic.marathon.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.cnic.marathon.R;

public class FriendsGridViewAdapter extends BaseAdapter {
	private List<Map<String, Object>> mList;
	LayoutInflater mInflater;
	private Context context;

	public FriendsGridViewAdapter(List<Map<String, Object>> mList,
			Context context) {
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
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertview, ViewGroup view) {
		viewHolder holder = null;
		if (convertview == null) {
			convertview = mInflater.inflate(R.layout.friend_grid_item, null);
			holder = new viewHolder();
			holder.mName = (TextView) convertview
					.findViewById(R.id.friend_name);
			convertview.setTag(holder);
		} else {
			holder = (viewHolder) convertview.getTag();
		}
		holder.mName.setText(mList.get(position).get("nickname").toString());
		return convertview;
	}

	public static class viewHolder {
		public TextView mName;
	}

}
