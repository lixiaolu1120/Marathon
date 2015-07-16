package cn.cnic.marathon.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.baidu.mapapi.overlayutil.OverlayManager;

import cn.cnic.marathon.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.Log;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat")
public class Utils {
	private static boolean DEBUG = true;
	public static boolean MARK_FLAG = false;
	public static boolean isOnlySendMessageToFriend = false;
	public static OverlayManager routeOverlay;
	public static String friendUid;
	/**
	 * 选择约见地点的flag
	 */
	public static boolean CHOICE_FLAG = false;
	/**
	 * 好友列表刷新flag
	 */
	public static boolean LISTVIEW_REFRESH = false;
	/**
	 * 判断好友的pop marker是否被点击了
	 */
	public static boolean Flag_Friend = false;

	/**
	 * 网络超时code
	 */
	public static int OUT_TIME = 4 * 1000;

	/**
	 * 周边
	 */
	// 美食
	public static int FOOD_CODE = 0;
	public static String FOOD = "美食";

	// 酒店
	public static int HOTEL_CODE = 1;
	public static String HOTEL = "酒店";
	// 影院
	public static int MOVIE_CODE = 2;
	public static String MOVIE = "电影";
	// 景区
	public static int SCENIC_SPOT_CODE = 3;
	public static String SCENIC_SPOT = "景区";
	public static final String Price = "price";
	public static final String Name = "name";
	public static final String Tag = "tag";
	public static final String Rating = "rating";
	public static final String TAB = "暂无";
	public static final String Detail_info = "detail_info";
	public static final String Results = "results";
	public static final String Overall_rating = "overall_rating";
	public static final String KEY = "key";
	public static double latitude = 0.0;
	public static double longitude = 0.0;
	public static String imgUrl = null;

	public static String TIMEFORMATE = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 日程安排 list集合
	 */
	public static List<String> list = new ArrayList<String>();
	/**
	 * 运动员 list集合
	 */
	public static List<String> playerList = new ArrayList<String>();
	/**
	 * 观众 list集合
	 */
	public static List<String> audienceList = new ArrayList<String>();
	/**
	 * 广告 list集合
	 */
	public static List<String> adList = new ArrayList<String>();
	/**
	 * 广告 list集合
	 */
	public static List<String> partnerList = new ArrayList<String>();
	/**
	 * 卫生间 经度集合
	 */
	public static List<String> latList = new ArrayList<String>();
	/**
	 * 收容所经度
	 */
	public static List<String> acceptlatList = new ArrayList<String>();
	/**
	 * 饮料站经度
	 */
	public static List<String> drinklatList = new ArrayList<String>();
	/**
	 * 饮水用水站经度
	 */
	public static List<String> waterlatList = new ArrayList<String>();
	/**
	 * 收容车站经度
	 */
	public static List<String> acceptecarlatList = new ArrayList<String>();
	/**
	 * shiwu站经度
	 */
	public static List<String> foodlatList = new ArrayList<String>();

	/**
	 * log
	 * 
	 * @param tag
	 * @param msg
	 */
	public static void log(String tag, String msg) {
		if (null == msg)
			msg = "null";
		if (DEBUG)
			Log.i(tag, msg);
	}

	/**
	 * toast
	 * 
	 */
	public static String MSGs = "请检查您的网络";
	public static String USER_DETECTION = "用户已注册";
	public static String REGISTER = "注册成功";

	/**
	 * Toast
	 * 
	 * @param context
	 * @param msg
	 */
	public static void Toast(Context context) {
		Toast.makeText(context, MSGs, Toast.LENGTH_SHORT).show();
	}

	public static void ToastMSG(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 记录日志
	 */
	public static void log4(String msg) {

	}

	public static Resources resources;
	public static Typeface appFontTypeface;

	public static String MD5(String msg) {
		byte[] buf = msg.getBytes();
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update(buf);
			buf = md5.digest();
			StringBuilder sBuilder = new StringBuilder();
			for (byte b : buf) {
				sBuilder.append(Integer.toHexString(b & 0xff));
			}
			return sBuilder.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 把时间转换成指定字符串格式
	 * 
	 * @param date
	 *            需要转换的时间
	 * @param format
	 *            时间输出格式
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getStringFromDate(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 把时间转换成指定字符串格式
	 * 
	 * @param date
	 *            需要转换的时间
	 * @return
	 */
	public static String getDefaultStringFromDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(Utils.TIMEFORMATE);
		return sdf.format(date);
	}

	/**
	 * 把当前时间转换成默认格式
	 * 
	 * @return
	 */
	public static String getCurrentTimeString() {
		SimpleDateFormat sdf = new SimpleDateFormat(Utils.TIMEFORMATE);
		return sdf.format(new Date());
	}

	/**
	 * 把指定时间字符串格式转换成时间
	 * 
	 * @param date
	 *            需要转换的时间
	 * @param format
	 *            时间输出格式
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static Date getDateFromString(String date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 把指定时间字符串格式转换成时间
	 * 
	 * @param date
	 *            需要转换的时间
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static Date getDefaultDateFromString(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(Utils.TIMEFORMATE);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 把当前时间转换成指定字符串格式
	 * 
	 * @param format
	 *            时间输出格式
	 * @return
	 */
	public static String getCurrentDateString(String format) {
		return getStringFromDate(new Date(), format);
	}

	// 验证是否为手机号的正则表达式
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * 读取string-array中item为资源文件中的值时返回item中资源文件的Id
	 * 
	 * <string name="test">test</string> <string-array>
	 * <item>@string/test</item> </string-array>
	 * 
	 * 返回R.string.test
	 * 
	 * @param context
	 * @return
	 */
	public static int[] getIntArrayFromStringArrray(Context context,
			int resourceId) {
		TypedArray array = context.getResources().obtainTypedArray(resourceId);
		int value[] = new int[array.length()];
		for (int i = 0; i < array.length(); i++) {
			value[i] = array.getResourceId(i, 0);
		}
		array.recycle();
		return value;
	}
}
