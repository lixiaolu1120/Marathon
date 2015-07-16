package cn.cnic.marathon.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.R.integer;
import android.R.string;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import cn.cnic.marathon.R;

public class NearGridViewAdapter extends BaseAdapter {
	private int[] image;
	private boolean isChice[];
	private Context context;

	public NearGridViewAdapter(int[] im, Context context) {
		this.image = im;
		isChice = new boolean[im.length];
		for (int i = 0; i < im.length; i++){
			isChice[i] = false;
		}
		this.context = context;
	}

	@Override
	public int getCount() {
		return image.length;

	}

	@Override
	public Object getItem(int position) {
		return image[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertview, ViewGroup arg2) {
		viewHolder holder = null;
		if (convertview == null) {
			holder = new viewHolder();
			convertview = LayoutInflater.from(context).inflate(
					R.layout.near_grid_item, null);
			holder.imageView = (ImageView) convertview
					.findViewById(R.id.img_view);
			convertview.setTag(holder);
		} else {
			holder = (viewHolder) convertview.getTag();
		}
		holder.imageView.setImageDrawable(getView(position));
		return convertview;
	}

	static class viewHolder {
		ImageView imageView;
	}

	Map<String, Object> map = new HashMap<String, Object>();
	List<Map<String, Object> > mList = new ArrayList<Map<String, Object> >();

	// 叠加图片，主要功能代码在这里，在这里。
	private LayerDrawable getView(int post) {

		Bitmap bitmap = ((BitmapDrawable) context.getResources().getDrawable(
				image[post])).getBitmap();
		Bitmap bitmap2 = null;
		LayerDrawable la = null;
		if (isChice[post] == true) {
			bitmap2 = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.icon_choice);
		}
		if (bitmap2 != null) {
			Drawable[] array = new Drawable[2];
			array[0] = new BitmapDrawable(bitmap);
			array[1] = new BitmapDrawable(bitmap2);
			la = new LayerDrawable(array);
			la.setLayerInset(0, 0, 0, 0, 0); // 第几张图离各边的间距
			la.setLayerInset(1, 0, 65, 65, 0);
		} else {
			Drawable[] array = new Drawable[1];
			array[0] = new BitmapDrawable(bitmap);
			la = new LayerDrawable(array);
			la.setLayerInset(0, 0, 0, 0, 0);
		}
		return la; // 返回叠加后的图
	}

	/**
	 * 单选
	 * 
	 * @param post
	 */
	public void chiceState(int post) {
		isChice[post] = isChice[post] ? false : true;
		this.notifyDataSetChanged();
	}

	/**
	 * 全选
	 * 
	 * @param post
	 */
	public void chiceStates(int post) {
		isChice[post] = isChice[post] ? true : true;
		this.notifyDataSetChanged();
	}

	/**
	 * 反选
	 * 
	 * @param post
	 */
	public void invertChiceState(int post) {
		isChice[post] = !isChice[post] ? true : false;
		this.notifyDataSetChanged();
	}

	/**
	 * 清除
	 * 
	 * @param post
	 */
	public void clearChiceState(int post) {
		isChice[post] = isChice[post] ? false : false;
		this.notifyDataSetChanged();
	}
}
