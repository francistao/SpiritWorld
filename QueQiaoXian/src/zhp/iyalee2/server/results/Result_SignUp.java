package zhp.iyalee2.server.results;

import zhp.java.server.IResult;

public enum Result_SignUp implements IResult {

	/**
	 * 用户名已存在
	 */
	USER_NAME_EXIST,

	/**
	 * 注册成功
	 */
	SIGN_UP_SUCCESS,
	
	/**
	 * 未知的注册结果
	 */
	UNKNOW_RESULT,
	
	/**
	 * 无法连接服务器 
	 */
	CAN_NOT_CONNECT_TO_SERVER,
	
	/**
	 * 注册失败
	 */
	SIGN_UP_FAIL;
	
}
