package cn.cnic.marathon.http;

import java.net.URLEncoder;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import cn.cnic.marathon.http.request.Request;
import cn.cnic.marathon.http.response.AcceptFriendResponse;
import cn.cnic.marathon.http.response.ActiveRule;
import cn.cnic.marathon.http.response.AddOrUpLoadFriendResponse;
import cn.cnic.marathon.http.response.BaseResponse;
import cn.cnic.marathon.http.response.CheckUser;
import cn.cnic.marathon.http.response.DeleteFriendResponse;
import cn.cnic.marathon.http.response.FriendPositionResponse;
import cn.cnic.marathon.http.response.FriendsInfoResponse;
import cn.cnic.marathon.http.response.LocationShareResponse;
import cn.cnic.marathon.http.response.Login;
import cn.cnic.marathon.http.response.MarkResponse;
import cn.cnic.marathon.http.response.MeetRespond;
import cn.cnic.marathon.http.response.PositionUploadResponse;
import cn.cnic.marathon.http.response.Register;
import cn.cnic.marathon.http.response.Response;
import cn.cnic.marathon.http.response.ResponseLocShare;
import cn.cnic.marathon.http.response.UserUpdate;
import cn.cnic.marathon.util.Utils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

public class NetWork {
	public interface CallBack {
		// 回调函数
		void onRequestComplete(Response result);
	}

	public static void initNetWork(final Request request,
			final Context context, final CallBack callBack) {
		final String CODE = "code";
		// 请求队列
		RequestQueue mQueue = Volley.newRequestQueue(context);
		final ProgressDialog progressDialog = ProgressDialog.show(context, "",
				"loading...");
		Utils.log("Net", "request is " + request.toString());
		SelfOjbectRequest r = new SelfOjbectRequest(Method.POST,
				request.getUrl(), request.toString(),
				new Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Utils.log("response", response.toString());
						progressDialog.dismiss();
						String code = null;
						try {
							code = response.getString(CODE);
						} catch (JSONException e) {
							e.printStackTrace();
						}
						Response result = null;
						switch (ResponseCode.getCode(code)) {

						case USERCHECK:
							result = new CheckUser(response);
							break;
						case USERREGISTER:
							result = new Register(response);
							break;
						case USERUPDATE:
							result = new UserUpdate(response);
							break;
						case USERLOGIN:
							result = new Login(response);
							break;
						case ADD_FRIEND_ECHO:
							result = new AcceptFriendResponse(response);// 加好友响应
							break;
						case POSITIONUPLOAD:
							result = new PositionUploadResponse(response);
							break;
						case MARKS:
							result = new MarkResponse(response);
							break;
						case ATHLETE:
							result = new BaseResponse(response);
							break;
						case CHEER:
							result = new BaseResponse(response);
							break;
						case PATERNER:
							result = new BaseResponse(response);
							break;
						case SCHEDULE:
							result = new ActiveRule(response);
							break;
						case FRIENDCOUNT:

							break;
						case USERPATH:
							break;
						case ONEPATH:
							break;
						case FRIENDLOCATION:
							result = new FriendPositionResponse(response);
							break;
						case FRIENDUPLOAD:
							result = new AddOrUpLoadFriendResponse(response);
							break;
						case LOCASHARE:
							result = new ResponseLocShare(response);
							break;
						case DELETE:
							result = new DeleteFriendResponse(response);
							break;
						case FRIENDINFO:
							result = new FriendsInfoResponse(response);
							break;
						case CALLORMESSAGE:
							result = new MeetRespond(response);
							break;
						case LOCATIONSHARE:
							result = new LocationShareResponse(response);
							break;
						case MEETINGRESPONSE:
							result = new MeetRespond(response);

						}
						callBack.onRequestComplete(result);
					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError response) {
						progressDialog.dismiss();
						Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT)
								.show();

					}
				});
		// 设置超时时间
		r.setRetryPolicy(new DefaultRetryPolicy(Utils.OUT_TIME, 1, 1.0f));
		mQueue.add(r);
	}

	/**
	 * 后台服务请求没有提示框
	 * 
	 * @param request
	 * @param context
	 * @param callBack
	 */
	public static void serviceRequest(final Request request,
			final Context context, final CallBack callBack) {
		final String CODE = "code";
		// 请求队列
		Utils.log("Net", "request is " + request.toString());
		RequestQueue mQueue = Volley.newRequestQueue(context);
		SelfOjbectRequest r = new SelfOjbectRequest(Method.POST,
				request.getUrl(), request.toString(),
				new Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Utils.log("response", response.toString());
						String code = null;
						try {
							code = response.getString(CODE);
						} catch (JSONException e) {
							e.printStackTrace();
						}
						Response result = null;
						switch (ResponseCode.getCode(code)) {
						case POSITIONUPLOAD:
							result = new PositionUploadResponse(response);
							break;
						case MARKS:
							result = new MarkResponse(response);
							break;
						case FRIENDLOCATION:
							result = new FriendPositionResponse(response);
							break;
						case FRIENDINFO:
							result = new FriendsInfoResponse(response);
						default:
							break;
						}
						callBack.onRequestComplete(result);
					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError response) {
					}
				});
		// 设置超时时间
		r.setRetryPolicy(new DefaultRetryPolicy(Utils.OUT_TIME, 1, 1.0f));
		mQueue.add(r);
	}

	/**
	 * get请求
	 * 
	 * @param url
	 * @param params
	 * @param context
	 */
	public static void getRequest(String url, Map<String, String> params,
			Context context) {
		StringBuilder param = new StringBuilder();
		param.append("?");
		for (String key : params.keySet()) {
			param.append(key);
			param.append("=");
			param.append(URLEncoder.encode(URLEncoder.encode(params.get(key))));
			Log.d("params.get(key)", params.get(key));
			param.append("&");
		}
		String p = param.toString();
		p = p.substring(0, p.length() - 1);
		// 请求队列
		RequestQueue mQueue = Volley.newRequestQueue(context);
		SelfOjbectRequest r = new SelfOjbectRequest(Method.GET, url + p, null,
				new Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Log.i("DATA", "request success");
					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError response) {
						Log.i("DATA", "request error");
					}
				});
		// 设置超时时间
		r.setRetryPolicy(new DefaultRetryPolicy(Utils.OUT_TIME, 1, 1.0f));
		mQueue.add(r);
	}
}
