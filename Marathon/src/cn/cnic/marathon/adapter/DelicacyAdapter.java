package cn.cnic.marathon.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.cnic.marathon.R;

public class DelicacyAdapter extends BaseAdapter {
	private List<Map<String, Object>> mList;
	private List<Map<String, Object>> mListDetailList;
	private Context context;
	LayoutInflater mInflater;
	Map<String, Object> map = new HashMap<String, Object>();

	public DelicacyAdapter(List<Map<String, Object>> mList, Context context) {
		super();
		this.mList = mList;
		this.context = context;
		mInflater = LayoutInflater.from(context);
		// this.mListDetailList = mListDetailList;
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

	@SuppressWarnings("static-access")
	@Override
	public View getView(int position, View convertview, ViewGroup view) {
		viewHolder holder = null;
		if (convertview == null) {
			convertview = mInflater.inflate(R.layout.item_delicacy_listview,
					null);
			holder = new viewHolder();
			holder.nameView = (TextView) convertview
					.findViewById(R.id.delicacy_name);
			holder.addressView = (TextView) convertview
					.findViewById(R.id.delicacy_address);
			holder.priceView = (TextView) convertview
					.findViewById(R.id.delicacy_price);
			holder.percapitaView = (TextView) convertview
					.findViewById(R.id.delicacy_percapita);
			holder.imageView = (ImageView) convertview
					.findViewById(R.id.image_view);
			convertview.setTag(holder);
		} else {
			holder = (viewHolder) convertview.getTag();
		}
		holder.nameView.setText(mList.get(position).get("name").toString());
		holder.addressView.setText(mList.get(position).get("tag").toString());
		holder.priceView.setText(mList.get(position).get("price").toString());
		holder.percapitaView.setText(mList.get(position).get("rating")
				.toString());

		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return convertview;
	}

	public static class viewHolder {
		public TextView nameView, addressView, priceView, percapitaView;
		public ImageView imageView;
	}

}
