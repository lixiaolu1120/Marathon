package cn.cnic.marathon.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.cnic.marathon.R;
import cn.cnic.marathon.ui.DetailActivity;

public class PartnerListAdapter extends CursorAdapter implements
		OnClickListener {
	Context context;

	public PartnerListAdapter(Context context, Cursor c) {
		super(context, c);
		this.context = context;
	}

	@Override
	public void bindView(View view, Context context, Cursor c) {
		RelativeLayout layout = (RelativeLayout) view;
		ImageView avatarImageView = (ImageView) layout
				.findViewById(R.id.partner_logo);
		// 头像
		// Uri uri = Uri.parse(c.getString(3));
		// 头像上传功能实现后，打开下面一句
		// avatarImageView.setImageURI(uri);
		TextView name = (TextView) view.findViewById(R.id.partner_name);
		name.setText(c.getString(c.getColumnIndex("name")));
		TextView desc = (TextView) view.findViewById(R.id.partner_desc);
		desc.setText(c.getString(c.getColumnIndex("description")));
	}

	@Override
	public View newView(Context context, Cursor arg1, ViewGroup parent) {
		RelativeLayout partner;
		LayoutInflater inflater = null;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		partner = (RelativeLayout) inflater
				.inflate(R.layout.item_partner, null);
		partner.setOnClickListener(this);
		return partner;
	}

	@Override
	public void onClick(View view) {
		TextView name = (TextView) view.findViewById(R.id.partner_name);
		TextView desc = (TextView) view.findViewById(R.id.partner_desc);
		Intent intent = new Intent(this.context, DetailActivity.class);
		intent.putExtra("dtype", "partner");
		intent.putExtra("name", name.getText());
		intent.putExtra("desc", desc.getText());
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

}
