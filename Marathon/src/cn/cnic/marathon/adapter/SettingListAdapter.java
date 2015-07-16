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

public class SettingListAdapter extends BaseAdapter {

	private List<Map<String, String>> data;
	private LayoutInflater mInflater;

	public SettingListAdapter(Context context, List<Map<String, String>> data) {
		this.data = data;
		this.mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int index) {
		return this.data.get(index);
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(int position, View view, ViewGroup group) {
		ViewHolder holder = null;
		if (null == view) {
			view = mInflater.inflate(R.layout.setting_item, null);
			holder = new ViewHolder();
			holder.logo = (TextView) view.findViewById(R.id.setting_item_logo);
			holder.title = (TextView) view
					.findViewById(R.id.setting_item_title);
			holder.tool = (TextView) view.findViewById(R.id.setting_item_tool);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		holder.logo.setText(data.get(position).get("logo"));
		holder.title.setText(data.get(position).get("title"));
		holder.tool.setText(data.get(position).get("tool"));
		return view;
	}

	class ViewHolder {
		public TextView logo, title, tool;
	}
}
