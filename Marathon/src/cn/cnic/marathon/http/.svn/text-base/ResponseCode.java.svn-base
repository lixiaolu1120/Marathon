package cn.cnic.marathon.http;

public enum ResponseCode {
	USERCHECK("ret_check_user"), USERREGISTER("ret_register"), USERUPDATE(
			"ret_user_update"), USERLOGIN("ret_login"), POSITIONUPLOAD(
			"ret_up_pos"), MARKS("ret_identify"), ATHLETE("ret_athe_info"), CHEER(
			"ret_cheer"), PATERNER("ret_partner"), SCHEDULE("ret_calendar"), FRIENDCOUNT(
			"ret_friend"), FRIENDINFO("ret_friend_info"), USERPATH("ret_trace"), ONEPATH(
			"ret_someone_trace"), FRIENDLOCATION("ret_friend_pos"), FRIENDUPLOAD(
			"ret_friend_upload"), LOCASHARE("ret_loc_share"), DELETE(
			"ret_remove_friend"), CALLORMESSAGE("ret_invite_friend"), ADD_FRIEND_ECHO(
			"ret_accept_friend_upload"), LOCATIONSHARE("ret_res_loc_share"), MEETINGRESPONSE(
			"ret_accept_invite_friend");

	private String code;

	private ResponseCode(String code) {
		this.code = code;
	}

	public static ResponseCode getCode(String code) {
		for (ResponseCode c : values()) {
			if (c.code.equals(code)) {
				return c;
			}
		}
		return null;
	}
}
