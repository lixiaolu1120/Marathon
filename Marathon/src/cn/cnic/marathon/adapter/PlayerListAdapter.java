package cn.cnic.marathon.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.util.Log;
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
import cn.cnic.marathon.ui.PlayerInfoActivity;

public class PlayerListAdapter extends CursorAdapter implements OnClickListener {
	Context context;
	int[] img;

	public PlayerListAdapter(Context context, Cursor c, int[] img) {
		super(context, c);
		this.context = context;
		this.img = img;
	}

	@Override
	public void bindView(View view, Context context, Cursor c) {
		Log.i("DATA", c.getString(2));
		RelativeLayout layout = (RelativeLayout) view;
		ImageView avatarImg = (ImageView) layout
				.findViewById(R.id.player_avatar);
		avatarImg.setImageResource(img[c.getPosition()]);
		// 头像
		// Uri uri = Uri.parse(c.getString(6));
		// 头像上传功能实现后，打开下面一句
		// avatarImg.setImageURI(uri);
		TextView name = (TextView) view.findViewById(R.id.player_name);
		name.setText(c.getString(2));
		TextView desc = (TextView) view.findViewById(R.id.player_desc);
		desc.setText(c.getString(6));
		Log.d("desc", c.getString(6));
		view.setOnClickListener(this);
	}

	@Override
	public View newView(Context context, Cursor arg1, ViewGroup parent) {
		RelativeLayout friend;
		LayoutInflater inflater = null;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		friend = (RelativeLayout) inflater.inflate(R.layout.item_player, null);
		return friend;
	}

	@Override
	public void onClick(View view) {
		TextView name = (TextView) view.findViewById(R.id.player_name);
		TextView desc = (TextView) view.findViewById(R.id.player_desc);
		ImageView avatarImg = (ImageView) view.findViewById(R.id.player_avatar);
		avatarImg.setDrawingCacheEnabled(true);
		Bitmap bitmap = Bitmap.createBitmap(avatarImg.getDrawingCache());
		avatarImg.setDrawingCacheEnabled(false);
		Intent intent = new Intent(this.context, PlayerInfoActivity.class);
		// intent.putExtra("dtype", "partner");
		intent.putExtra("name", name.getText().toString());
		intent.putExtra("desc", desc.getText().toString());
		intent.putExtra("bitmap", bitmap);
		Log.d("desc", desc.getText().toString());
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

}