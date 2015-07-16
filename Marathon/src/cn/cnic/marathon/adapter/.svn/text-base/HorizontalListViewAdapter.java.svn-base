package cn.cnic.marathon.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.cnic.marathon.R;

public class HorizontalListViewAdapter extends BaseAdapter {
	private List<Map<String, Object>> mList;
	LayoutInflater mInflater;
	String[] colors = new String[] { "fbbe3c", "f6a356", "f48265", "c92634",
			"1c99d4", "0173b" };

	public HorizontalListViewAdapter(List<Map<String, Object>> mList,
			Context context) {
		super();
		this.mList = mList;
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
	public View getView(final int position, View convertview, ViewGroup view) {
		viewHolder holder = null;
		if (convertview == null) {
			convertview = mInflater.inflate(R.layout.horizontallistview_item,
					null);
			holder = new viewHolder();
			holder.mTitle = (TextView) convertview
					.findViewById(R.id.title_view);
			holder.layout = (LinearLayout) convertview
					.findViewById(R.id.horizontall_layout);
			convertview.setTag(holder);
		} else {
			holder = (viewHolder) convertview.getTag();
		}
		holder.mTitle.setText(mList.get(position).get("dtime").toString());
		holder.layout.setBackgroundColor(Color.parseColor("#"
				+ colors[position]));
		return convertview;
	}

	public static class viewHolder {
		public TextView mTitle, mTitleView;
		public LinearLayout layout;
	}

}