package zhp.iyalee2.server.servers;

import java.util.ArrayList;

import zhp.iyalee2.server.IServer;
import zhp.iyalee2.server.ServerDebug;
import zhp.iyalee2.server.results.Result_ChangePassword;
import zhp.java.server.Param;
import zhp.java.server.Post;
import zhp.java.utils.Utils_Analyzer;

public class Server_ChangePassword implements IServer{
	// 返回格式： <reply><result>success|fail</result></reply>
	final String SERVER_SUBMIT_VALUE = "changePassword";
	final String SERVER_NEW_PASSWORD = "newPassword";
	
	public Result_ChangePassword getServer(String userName, String oldPassword, String newPassword){
		if (ServerDebug.isDebug) {
			return Result_ChangePassword.CHANGE_SUCCESS;
		}
		
		ArrayList<Param> params = new ArrayList<Param>();
		params.add(new Param(server_userName, userName));
		params.add(new Param(server_password, oldPassword));
		params.add(new Param(SERVER_NEW_PASSWORD, newPassword));
		params.add(new Param(server_submit_name, SERVER_SUBMIT_VALUE));
		
		String reply = "";
		try {
			reply = Post.sendPost(domain, params);
		} catch (Exception e) {
			e.printStackTrace();
			return Result_ChangePassword.CAN_NOT_CONNECT_TO_SERVER;
		}
		
		return analyze(reply);
	}
	
	/**
	 * 解析结果
	 */
	private Result_ChangePassword analyze(String reply){
		ArrayList<String> temp = Utils_Analyzer.analyze(reply, "<result>", "</result>");
		// 如果没有提取到值，返回未知结果
		if (temp.size() == 0)
			return Result_ChangePassword.UNKNOW_RESULT;
		String result = temp.get(0);
		
		switch (result) {
		case "success":
			return Result_ChangePassword.CHANGE_SUCCESS; 

		case "fail":
			return Result_ChangePassword.CHANGE_FAIL;

		default:
			return Result_ChangePassword.UNKNOW_RESULT;
		}
	}
	
}
