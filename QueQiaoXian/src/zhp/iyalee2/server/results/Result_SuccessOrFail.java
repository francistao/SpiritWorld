package zhp.iyalee2.server.results;

import zhp.java.server.IResult;

public enum Result_SuccessOrFail implements IResult{
		
	/**
	 * 无法连接服务器
	 */
	CAN_NOT_CONNECT_TO_SERVER,
	
	/**
	 * 成功
	 */
	SUCCESS,
	
	/**
	 * 失败
	 */
	FAIL,
	
	/**
	 *	未知登录结果 
	 */
	UNKNOW_RESULT
	
}
