package zhp.iyalee2.server.servers;

import java.util.ArrayList;

import zhp.iyalee2.server.IServer;
import zhp.iyalee2.server.ServerDebug;
import zhp.iyalee2.server.results.Result_LogIn;
import zhp.java.server.Param;
import zhp.java.server.Post;
import zhp.java.utils.Utils_Analyzer;

/**
 * 用于登录的服务器
 * 
 * @author 郑海鹏
 * @since 2015/4/13 22:33
 *
 */
public class Server_LogIn implements IServer {
	final String SERVER_SUBMIT_VALUE = "login";

	/**
	 * 登录服务。
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @return 登录结果
	 */
	public Result_LogIn getServer(String userName, String password) {
		if (ServerDebug.isDebug) {
			return Result_LogIn.LOG_IN_SUCCESS;
		}

		// 构造传给服务器的参数列表
		ArrayList<Param> params = new ArrayList<Param>();
		params.add(new Param(server_userName, userName));
		params.add(new Param(server_password, password));
		params.add(new Param(server_submit_name, SERVER_SUBMIT_VALUE));

		// 发送消息到服务器并获得返回结果
		String reply = "";
		try {
			reply = Post.sendPost(domain, params);
		} catch (Exception e) {
			e.printStackTrace();
			return Result_LogIn.CAN_NOT_CONNECT_TO_SERVER;
		}

		zhp.android.debug.Debug.Log(this.getClass().getName(), reply);
		
		// 对结果解析。正确情况下，结果放在<result>标签内
		ArrayList<String> temp = Utils_Analyzer.analyze(reply, "<result>", "</result>");
		// 如果没有提取到值，返回未知结果
		if (temp.size() == 0)
			return Result_LogIn.UNKNOW_RESULT;
		String result = temp.get(0);

		// 根据不同的结果返回对应的结果标记
		switch (result) {
		case "fail":
			return Result_LogIn.LOG_IN_FAIL;

		case "success":
			return Result_LogIn.LOG_IN_SUCCESS;

		default:
			return Result_LogIn.UNKNOW_RESULT;
		}
	}

}
