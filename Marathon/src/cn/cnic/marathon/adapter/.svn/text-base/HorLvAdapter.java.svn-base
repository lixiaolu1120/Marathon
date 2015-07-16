//package cn.cnic.marathon.adapter;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import android.content.Context;
//import android.database.Cursor;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.CursorAdapter;
//import android.widget.TextView;
//import cn.cnic.marathon.R;
//
//public class HorLvAdapter extends CursorAdapter {
//	private Context context;
//
//	public HorLvAdapter(Context context, Cursor c) {
//		super(context, c);
//		this.context = context;
//	}
//	@Override
//	public void bindView(View view, Context context, Cursor cursor) {
//		TextView mTimeView = (TextView) view.findViewById(R.id.time);
//		mTimeView.setText(cursor.getString(cursor.getColumnIndex("event_name")));
//		Log.d("calendar:name", cursor.getString(cursor.getColumnIndex("event_name"))+"");
//		
////		String startTime=cursor.getString(cursor.getColumnIndex("dtime"));
////		Log.d("calendar:startTime", startTime+"");
////		
////		String time=cursor.getString(cursor.getColumnIndex("calendar"));
////		String day = "d(\\d)";
////		Pattern pattern = Pattern.compile(day);
////		Matcher matcher = pattern.matcher(time);
////		List<Object> days = new ArrayList<Object>();  
////		while (matcher.find()) {
////			days.add(matcher.group(1));
////		}
////		Log.d("calendar", days+"");
////		Integer d = (Integer)days.get(0);
////		Calendar calendar = Calendar.getInstance();
////		calendar.add(Calendar.HOUR_OF_DAY, d.intValue() * -1);
//	}
//
//	@Override
//	public View newView(Context context, Cursor cursor, ViewGroup parent) {
//		LayoutInflater inflater = null;
//		inflater = (LayoutInflater) context
//				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		View view = inflater.inflate(
//				R.layout.horizontallistview_item, null);
//		return view;
//	}
//
//}
