package cn.cnic.marathon.http;

import java.io.UnsupportedEncodingException;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;

public class SelfOjbectRequest extends JsonRequest<JSONObject> {
	public SelfOjbectRequest(int method, String url, String requestBody,
			Listener<JSONObject> listener, ErrorListener errorListener) {
		super(method, url, requestBody, listener, errorListener);
	}

	@Override
	protected com.android.volley.Response<JSONObject> parseNetworkResponse(
			NetworkResponse response) {
		try {
			String jsonString = new String(response.data,
					HttpHeaderParser.parseCharset(response.headers));
			return com.android.volley.Response.success(new JSONObject(
					jsonString), HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return com.android.volley.Response.error(new ParseError(e));
		} catch (JSONException je) {
			return com.android.volley.Response.error(new ParseError(je));
		}

	}
}
