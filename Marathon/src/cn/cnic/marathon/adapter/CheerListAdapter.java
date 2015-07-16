package cn.cnic.marathon.adapter;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.cnic.marathon.R;

public class CheerListAdapter extends CursorAdapter{

	public CheerListAdapter(Context context, Cursor c) {
		super(context, c);
	}

	@Override
	public void bindView(View view, Context context, Cursor c) {
		RelativeLayout layout = (RelativeLayout) view;
		ImageView avatarImageView = (ImageView) layout
				.findViewById(R.id.cheer_avatar);
		// 头像
		//Uri uri = Uri.parse(c.getString(4));
		// 头像上传功能实现后，打开下面一句
		// avatarImageView.setImageURI(uri);
		TextView name = (TextView) view.findViewById(R.id.cheer_name);
		name.setText(c.getString(2));
		TextView desc = (TextView) view.findViewById(R.id.cheer_desc);
		desc.setText(c.getString(3));
	}

	@Override
	public View newView(Context context, Cursor arg1, ViewGroup parent) {
		RelativeLayout friend;
		LayoutInflater inflater = null;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		friend = (RelativeLayout) inflater.inflate(R.layout.item_cheer, null);
		return friend;
	}

}
