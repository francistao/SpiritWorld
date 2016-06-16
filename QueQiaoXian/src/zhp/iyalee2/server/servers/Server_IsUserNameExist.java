package zhp.iyalee2.server.servers;

import java.util.ArrayList;

import zhp.iyalee2.server.IServer;
import zhp.iyalee2.server.ServerDebug;
import zhp.iyalee2.server.results.Result_IsUserNameExist;
import zhp.java.server.Param;
import zhp.java.server.Post;
import zhp.java.utils.Utils_Analyzer;

/**
 * 判断一个用户名是否已经被使用了
 * 
 * @author 郑海鹏
 * @since 2015/4/15 16:59
 */
public class Server_IsUserNameExist implements IServer {
	final String SERVER_SUBMIT_VALUE = "isExist";

	public Result_IsUserNameExist getServer(String userName) {
		if (ServerDebug.isDebug) {
			return Result_IsUserNameExist.NOT_EXIST;
		}
		// 构造传给服务器的参数列表
		ArrayList<Param> params = new ArrayList<Param>();
		params.add(new Param(server_userName, userName));
		params.add(new Param(server_submit_name, SERVER_SUBMIT_VALUE));

		// 发送消息到服务器并获得返回结果
		String reply = "";
		try {
			reply = Post.sendPost(domain, params);
		} catch (Exception e) {
			e.printStackTrace();
			return Result_IsUserNameExist.CAN_NOT_CONNECT_TO_SERVER;
		}

		return analyze(reply);
	}
	
	/**
	 * 解析返回的结果
	 */
	public Result_IsUserNameExist analyze(String reply) {
		// 对结果解析。正确情况下，结果放在<result>标签内
		ArrayList<String> temp = Utils_Analyzer.analyze(reply, "<result>", "</result>");
		// 如果没有提取到值，返回未知结果
		if (temp.size() == 0)
			return Result_IsUserNameExist.UNKNOW_RESULT;
		String result = temp.get(0);

		// 根据不同的结果返回对应的结果标记
		switch (result) {
		case "no":
			return Result_IsUserNameExist.NOT_EXIST;

		case "yes":
			return Result_IsUserNameExist.EXIST;

		default:
			return Result_IsUserNameExist.UNKNOW_RESULT;
		}
	}
}
