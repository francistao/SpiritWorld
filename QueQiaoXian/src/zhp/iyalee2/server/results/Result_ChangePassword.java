package zhp.iyalee2.server.results;

import zhp.java.server.IResult;

public enum Result_ChangePassword implements IResult {

	/**
	 * 改密码成功
	 */
	CHANGE_SUCCESS,

	/**
	 * 改密码失败
	 */
	CHANGE_FAIL,
	
	/**
	 * 未知结果
	 */
	UNKNOW_RESULT,
	
	/**
	 * 无法连接服务器 
	 */
	CAN_NOT_CONNECT_TO_SERVER;
	
}
