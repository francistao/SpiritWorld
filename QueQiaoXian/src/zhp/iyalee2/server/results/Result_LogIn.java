package zhp.iyalee2.server.results;

import zhp.java.server.IResult;

public enum Result_LogIn implements IResult{
		
	/**
	 * 无法连接服务器
	 */
	CAN_NOT_CONNECT_TO_SERVER,
	
	/**
	 * 登录成功
	 */
	LOG_IN_SUCCESS,
	
	/**
	 * 用户名或密码错误
	 */
	LOG_IN_FAIL,
	
	/**
	 *	未知登录结果 
	 */
	UNKNOW_RESULT
	
}
