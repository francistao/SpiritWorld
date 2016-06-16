package zhp.iyalee2.server.servers;

import java.util.ArrayList;

import zhp.iyalee2.server.IServer;
import zhp.iyalee2.server.ServerDebug;
import zhp.iyalee2.server.results.Result_SuccessOrFail;
import zhp.java.netio.upload.Param;
import zhp.java.utils.Utils_Analyzer;

/**
 * 上传头像
 * @author 郑海鹏
 * @since 2015/4/15 16:59
 */
public class Server_UpdateUserProfile implements IServer {
	final String SERVER_SUBMIT_VALUE = "uploadProfile";
	
	public Result_SuccessOrFail getServer(String userName, String password, String profile) {
		if (ServerDebug.isDebug) {
			return Result_SuccessOrFail.SUCCESS;
		}
		// 构造传给服务器的参数列表
		ArrayList<Param> params = new ArrayList<Param>();
		params.add(new Param(server_userName, userName));
		params.add(new Param(server_password, password));
		params.add(new Param("profile", profile));
		params.add(new Param(server_submit_name, SERVER_SUBMIT_VALUE));

		// 发送消息到服务器并获得返回结果
		String reply = "";
		try {
			reply = new zhp.java.netio.upload.Utils_Post().postParam("http://1.iyalee.sinaapp.com/arworld/UpdateUserProfile.php", params);
		} catch (Exception e) {
			e.printStackTrace();
			return Result_SuccessOrFail.CAN_NOT_CONNECT_TO_SERVER;
		}
		return analyze(reply);
	}
	
	/**
	 * 解析返回的结果
	 */
	public Result_SuccessOrFail analyze(String reply) {
		// 对结果解析。正确情况下，结果放在<result>标签内
		ArrayList<String> temp = Utils_Analyzer.analyze(reply, "<result>", "</result>");
		// 如果没有提取到值，返回未知结果
		if (temp.size() == 0)
			return Result_SuccessOrFail.UNKNOW_RESULT;
		String result = temp.get(0);

		// 根据不同的结果返回对应的结果标记
		switch (result) {
		case "success":
			return Result_SuccessOrFail.SUCCESS;

		case "fail":
			return Result_SuccessOrFail.FAIL;

		default:
			return Result_SuccessOrFail.UNKNOW_RESULT;
		}
	}
	
}
