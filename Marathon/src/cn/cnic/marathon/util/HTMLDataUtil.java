package cn.cnic.marathon.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通过网页url获取图片
 * 
 * @author cuixipeng
 * 
 */
@Deprecated
public class HTMLDataUtil {
	public static String HtmlData(String url) throws Exception {
		String TAG = "image-content";
		// String imgUrl =
		// "http://map.baidu.com/detail?qt=ninf&uid=3911da81ab7ef510d7851fc9&detail=cater";
		URL urls = new URL(url);
		HttpURLConnection con = (HttpURLConnection) urls.openConnection();
		InputStream is = con.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));

		String line = null;
		String content = "";
		while ((line = reader.readLine()) != null) {
			content += line;
		}

		String pstr = "\"imgUrl\"\\:\\\"(.*?)\",\"cn_name\"";

		System.out.println(pstr);
		Pattern p = Pattern.compile(pstr);
		Matcher m = p.matcher(content);
		String u = null;

		while (m.find()) {
			u = m.group(1);
			break;
		}
		String imgurl = u.replace("\\", "");
		return imgurl;
	}
}
