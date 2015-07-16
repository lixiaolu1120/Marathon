package cn.cnic.marathon.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.cnic.marathon.R;

public class SurroundGridAdapter extends BaseAdapter {
	private List<String> mList;
	private Context context;
	LayoutInflater mInflater;

	public SurroundGridAdapter(List<String> mList, Context context) {
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
			convertview = mInflater.inflate(R.layout.item_surround_grid_item,
					null);
			holder = new viewHolder();
			holder.textView = (TextView) convertview
					.findViewById(R.id.surround_grid_text);
			convertview.setTag(holder);
		} else {
			holder = (viewHolder) convertview.getTag();
		}

		holder.textView.setText(mList.get(position).toString());
		return convertview;
	}

	public static class viewHolder {
		public TextView textView;
	}
}
