package cn.cnic.marathon.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.cnic.marathon.R;
import cn.cnic.marathon.adapter.PartnerAdapter.viewholder;

public class RunnerListAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater mInflater;
	private List<Map<String, Object>> mList;
	int[] imgs = { R.drawable.bairen, R.drawable.walunxiya };// 球队图片

	public RunnerListAdapter(Context context, List<Map<String, Object>> mList) {
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
			convertview = mInflater.inflate(R.layout.item_runner, null);
			holder = new viewholder();
			holder.textView = (TextView) convertview
					.findViewById(R.id.runner_name);
			holder.imageView = (ImageView) convertview
					.findViewById(R.id.runner_avatar);
			convertview.setTag(holder);
		} else {
			holder = (viewholder) convertview.getTag();
		}
		holder.textView
				.setText(mList.get(position).get("team_name").toString());
		holder.imageView.setImageResource(imgs[position]);
		return convertview;
	}

	public static class viewholder {
		TextView textView;
		ImageView imageView;
	}

	// public RunnerListAdapter(Context context, Cursor c) {
	// super(context, c);
	// this.context = context;
	// }

	// @Override
	// public void bindView(View view, Context context, Cursor c) {
	// Log.i("DATA", c.getString(2));
	// RelativeLayout layout = (RelativeLayout) view;
	// ImageView avatarImageView = (ImageView) layout
	// .findViewById(R.id.runner_avatar);
	// // 头像
	// // Uri uri = Uri.parse(c.getString(6));
	// // 头像上传功能实现后，打开下面一句
	// // avatarImageView.setImageURI(uri);
	// TextView name = (TextView) view.findViewById(R.id.runner_name);
	// name.setText(c.getString(2));
	// TextView desc = (TextView) view.findViewById(R.id.runner_desc);
	// desc.setText(c.getString(3));
	// }
	//
	// @Override
	// public View newView(Context context, Cursor cursor, ViewGroup parent) {
	// RelativeLayout friend;
	// LayoutInflater inflater = null;
	// inflater = (LayoutInflater) context
	// .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	// friend = (RelativeLayout) inflater.inflate(R.layout.item_runner, null);
	// Map<String, String> tag = new HashMap<String, String>();
	// // tag.put("title", cursor.getString(cursor.getColumnIndex("title")));
	// tag.put("name", cursor.getString(cursor.getColumnIndex("team_name")));
	// // tag.put("picture",
	// cursor.getString(cursor.getColumnIndex("picture")));
	// // tag.put("description",
	// cursor.getString(cursor.getColumnIndex("description")));
	// friend.setTag(tag);
	// return friend;
	// }
}
