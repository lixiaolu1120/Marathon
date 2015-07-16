package cn.cnic.marathon.adapter;

import java.util.List;
import java.util.Map;

import cn.cnic.marathon.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PartnerAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater mInflater;
	private List<Map<String, Object>> mList;

	public PartnerAdapter(Context context, List<Map<String, Object>> mList) {
		super();
		this.context = context;
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
	public View getView(int position, View convertview, ViewGroup view) {
		viewholder holder = null;
		if (convertview == null) {
			convertview = mInflater.inflate(R.layout.item_partner, null);
			holder = new viewholder();
			//holder.textView = (TextView) convertview.findViewById(R.id.partnertitle);
			convertview.setTag(holder);
		} else {
			holder = (viewholder) convertview.getTag();
		}
		holder.textView.setText(mList.get(position).get("name").toString());
		return convertview;
	}

	public static class viewholder {
		TextView textView;
	}
}
