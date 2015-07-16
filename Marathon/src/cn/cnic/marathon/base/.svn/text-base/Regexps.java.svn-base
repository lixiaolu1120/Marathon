package cn.cnic.marathon.base;

public class Regexps {

	public static final String UID = "uid\":\"(\\d+\\-\\d+\\-\\d+\\s\\d+\\:\\d+:\\d+\\.\\d+\\s\\+\\d+\\sCST)";

	/**
	 * 推送类型
	 */
	public static final String PUSH_TYPE = "\"type\":\"(\\w+)\"";

	/**
	 * 好友发送消息推送
	 */
	public static final String HANDLE_FRIEND_SENDMESSAGE = "\"fuid\":\"(.*?)\",\"uid\":\"(.*?)\",\"nickname\":\"(.*?)\",\"message\":\"(.*?)\",\"time\":\"(.*?)\"";

	/**
	 * 加好友推送
	 */
	public static final String HANDLE_ADD_FRIEND = "\"uid\":\"(.*?)\",\"share\":([0,1]),\"time\":\"(.*?)\",\"nickname\":\"(.*?)\",\"echo\":([0,1]),\"megid\":\"(.*?)\"";
	/**
	 * 删除好友
	 */
	public static final String HANDLE_DELETE_FRIEND = "\"destuid\":\"(.*?)\",\"uid\":\"(.*?)\",\"nickname\":\"(.*?)\",\"time\":\"(.*?)\"";
	/**
	 * 位置共享请求/取消
	 */
	public static final String HANDLE_POSITION_SHARE = "\"destuid\":\"(.*?)\",\"share\":([0,1]),\"uid\":\"(.*?)\",\"nickname\":\"(.*?)\",\"message\":\"(.*?)\",\"time\":\"(.*?)\"";
	/**
	 * 位置共享响应
	 */
	public static final String HANDLE_POSITION_SHARE_ECHO = "\"destuid\":\"(.*?)\",\"echo\":([0,1]),\"uid\":\"(.*?)\",\"nickname\":\"(.*?)\",\"time\":\"(.*?)\"";
	/**
	 * 突发事件广播
	 */
	public static final String HANDLE_EMERGENCY_MESSAGE = "\"fuid\":\"(.*?)\",\"message\":\"(.*?)\",\"time\":\"(.*?)\"";
	/**
	 * 疏散路线
	 */
	public static final String HANDLE_EMERGENCY_PATH = "\"message\":\"(.*?)\",\"data\":\"(.*?)\",\"time\":\"(.*?)\"";

	/**
	 * 疏散路线数据
	 */
	public static final String HANDLE_EMERGENCY_PATH_DATA = "(\\{.*?\\})";

	/**
	 * 疏散路线数据字段
	 */
	public static final String HANDLE_EMERGENCY_PATH_DATA_ITEM = "\"id\":\"(\\d+)\",\"shortest\":\"(.*?)\",\"shortest_len\":(.*?),\"longer\":\"(.*?)\",\"longer_len\":(.*?),\"lon\":(\\d+\\.\\d+|0),\"lat\":(\\d+\\.\\d+|0)";

	/**
	 * 好友会面
	 */
	public static final String HANDLE_FRIEND_MEETING = "\"fuid\":\"(.*?)\",\"uid\":\"(.*?)\",\"nickname\":\"(.*?)\",\"message\":\"(.*?)\",\"lon\":(\\d+\\.\\d+|0),\"lat\":(\\d+\\.\\d+|0),\"time\":\"(.*?)\"";

	/**
	 * 好友会面响应
	 */
	public static final String HANDLE_FRIEND_MEETING_ECHO = "\"fuid\":\"(.*?)\",\"echo\":([0,1]),\"uid\":\"(.*?)\",\"nickname\":\"(.*?)\",\"lon\":(\\d+\\.\\d+|0),\"lat\":(\\d+\\.\\d+|0),\"time\":\"(.*?)\"";
	/**
	 * 好友请求响应
	 */
	public static final String HANDLE_AGREE_FRIEND = "\"echo\":([0,1]),\"uid\":\"(.*?)\",\"nickname\":\"(.*?)\",\"message\":\"(.*?)\",\"time\":\"(.*?)\"";

}
