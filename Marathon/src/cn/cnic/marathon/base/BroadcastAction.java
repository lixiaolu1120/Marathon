package cn.cnic.marathon.base;

public class BroadcastAction {

	/**
	 * 好友请求消息推送
	 */
	public static final String PUSH_ADD_FRIEND = "add_friend";
	/**
	 * 推送，响应加您为好友
	 */
	public static final String PUSH_ADD_FRIEND_ECHO = "add_friend_echo";

	/**
	 * 删除好友
	 */
	public static final String PUSH_DELETE_FRIENDS = "delete_friend";

	/**
	 * 位置共享/取消共享请求
	 */
	public static final String PUSH_POSITION_SHARE = "position_share";

	/**
	 * 响应位置共享请求
	 */
	public static final String PUSH_POSITION_SHARE_ECHO = "position_share_echo";

	/**
	 * 好友会面请求消息推送
	 */
	public static final String PUSH_FRIEND_MEETING = "meeting";

	/**
	 * 响应好友会面消息推送
	 */
	public static final String PUSH_MEETING_ECHO = "meeting_echo";

	/**
	 * 好友发消息请求消息推送
	 */
	public static final String PUSH_FRIEND_SEND_MESSAGE = "send_message";

	/**
	 * 突发事件广播
	 */
	public static final String PUSH_EMERGENCY_MESSAGE = "emergency_message";
	
	/**
	 * 疏散路线
	 */
	public static final String PUSH_EMERGENCY_PATH = "emergency_path";
}
