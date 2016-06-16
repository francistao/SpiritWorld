package zhp.iyalee2.server.results;

import zhp.java.server.IResult;

public enum Result_IsUserNameExist implements IResult{
	EXIST, 
	NOT_EXIST,
	UNKNOW_RESULT,
	CAN_NOT_CONNECT_TO_SERVER;
}
