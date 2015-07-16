package cn.cnic.marathon.adapter;

import cn.cnic.marathon.R;
import cn.cnic.marathon.ui.AvatarChoseActivity;
import cn.cnic.marathon.ui.DetailActivity;
import cn.cnic.marathon.ui.TeamContentActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TeamListAdapter extends CursorAdapter {
	private Context context;
	int[] imgs = { R.drawable.bairen, R.drawable.walunxiya };// 球队图片

	public TeamListAdapter(Context context, Cursor c) {
		super(context, c);
		this.context = context;
	}

	@Override
	public void bindView(View view, final Context context, final Cursor c) {
		Log.i("DATA", c.getString(2));
		ImageView avatar = (ImageView) view.findViewById(R.id.team_avatar);
		// 头像
		// Uri uri = Uri.parse(c.getString(6));
		// 头像上传功能实现后，打开下面一句
		// avatar.setImageURI(uri);
		avatar.setImageResource(imgs[c.getPosition()]);
		final TextView name = (TextView) view.findViewById(R.id.team_name);
		name.setText(c.getString(2));
		final TextView desc = (TextView) view.findViewById(R.id.team_desc);
		desc.setText(c.getString(3));
		view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(context, TeamContentActivity.class);
				intent.putExtra("dtype", "partner");
				intent.putExtra("name", name.getText().toString());
				intent.putExtra("desc", desc.getText().toString());
//				intent.putExtra("img", imgs[c.getPosition()]);
//				Log.d("imgs", c.getPosition()+ "");
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);
			}
		});
	}

	@Override
	public View newView(Context context, Cursor arg1, ViewGroup parent) {
		RelativeLayout friend;
		LayoutInflater inflater = null;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		friend = (RelativeLayout) inflater.inflate(R.layout.item_team, null);
		// friend.setOnClickListener(this);
		return friend;
	}

	// @Override
	// public void onClick(View view) {
	// TextView name = (TextView) view.findViewById(R.id.team_name);
	// TextView desc = (TextView) view.findViewById(R.id.team_desc);
	// ImageView avatar = (ImageView) view.findViewById(R.id.team_avatar);
	// Intent intent = new Intent(context, TeamContentActivity.class);
	// intent.putExtra("dtype", "partner");
	// intent.putExtra("name", name.getText().toString());
	// intent.putExtra("desc", desc.getText().toString());
	// intent.putExtra("img",avatar.);
	// intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	// context.startActivity(intent);
	// }
}
