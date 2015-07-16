package cn.cnic.marathon.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.cnic.marathon.R;
import cn.cnic.marathon.util.Utils;

public class SurroundListAdapter extends BaseAdapter {
	private List<Map<String, String>> mList;
	private Context context;
	LayoutInflater mInflater;
	Map<String, Object> map = new HashMap<String, Object>();

	public SurroundListAdapter(List<Map<String, String>> mList, Context context) {
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
			convertview = mInflater.inflate(
					R.layout.item_surround_listview_item, null);
			holder = new viewHolder();
			holder.nameView = (TextView) convertview
					.findViewById(R.id.surround_list_name);
			holder.addressView = (TextView) convertview
					.findViewById(R.id.surround_list_address);
			convertview.setTag(holder);
		} else {
			holder = (viewHolder) convertview.getTag();
		}
		holder.nameView.setText(mList.get(position).get(Utils.Name).toString());
		holder.addressView.setText(mList.get(position).get(Utils.Tag)
				.toString());
		return convertview;
	}

	public static class viewHolder {
		public TextView nameView, addressView;
	}
}
