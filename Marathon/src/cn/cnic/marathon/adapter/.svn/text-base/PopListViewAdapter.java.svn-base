package cn.cnic.marathon.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.cnic.marathon.R;

import com.baidu.mapapi.map.BaiduMap;

public class PopListViewAdapter extends BaseAdapter {
	private List<Map<String, Object>> mList;
	LayoutInflater mInflater;
	Map<String, Object> map = new HashMap<String, Object>();
	int mSelect = -1; // 选中项

	public void changeSelected(int position) {
		notifyDataSetChanged();
	}

	public PopListViewAdapter(List<Map<String, Object>> mList, Context context,
			BaiduMap mBaiduMap) {
		super();
		this.mList = mList;

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
			convertview = mInflater.inflate(R.layout.pop_listview_item, null);
			holder = new viewHolder();
			holder.iconView = (TextView) convertview
					.findViewById(R.id.pop_icon_text);
			holder.nameView = (TextView) convertview
					.findViewById(R.id.pop_name);
			convertview.setTag(holder);
		} else {
			holder = (viewHolder) convertview.getTag();
		}
		holder.nameView.setText(mList.get(position).get("text").toString());
		holder.iconView.setText(mList.get(position).get("icon").toString());
		boolean isSelected = Boolean.valueOf(mList.get(position)
				.get("selected").toString());
		Log.i("DATA", "position is " + position + " and status is "
				+ isSelected);
		if (isSelected) {
			holder.iconView.setTextColor(Color.RED);
			holder.nameView.setTextColor(Color.RED);
		} else {
			holder.iconView.setTextColor(Color.LTGRAY);
			holder.nameView.setTextColor(Color.LTGRAY);
		}
		return convertview;
	}

	public static class viewHolder {
		public TextView nameView, iconView;
	}
}
