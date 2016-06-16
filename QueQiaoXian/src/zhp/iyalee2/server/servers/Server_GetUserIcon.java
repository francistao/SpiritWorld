package zhp.iyalee2.server.servers;

import java.util.ArrayList;

import zhp.iyalee2.server.IServer;
import zhp.iyalee2.server.ServerDebug;
import zhp.iyalee2.server.results.Result_String;
import zhp.java.server.Param;
import zhp.java.server.Post;
import zhp.java.utils.Utils_Analyzer;

/**
 * 获得用户头像
 * 
 * @author 郑海鹏
 * @since 2015/4/15 16:18
 *
 */
public class Server_GetUserIcon implements IServer {
	// 返回示例： <reply>http:\\dsafgfh.jpg</reply>

	final String SERVER_SUBMIT_VALUE = "getIcon";
	final String SERVER_URL = "http://1.iyalee.sinaapp.com/GetIcon.php";

	public Result_String getServer(String userName) {
		if (ServerDebug.isDebug) {
			return new Result_String(null);
		}

		// 构造传给服务器的参数列表
		ArrayList<Param> params = new ArrayList<Param>();
		params.add(new Param(server_userName, userName));
		params.add(new Param(server_submit_name, SERVER_SUBMIT_VALUE));

		// 发送消息到服务器并获得返回结果
		String reply = "";
		try {
			reply = Post.sendPost(SERVER_URL, params);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result_String(null);
		}

		zhp.android.debug.Debug.Log(this.getClass().getName(), reply);

		// 对结果解析。正确情况下，结果放在<result>标签内
		ArrayList<String> temp = Utils_Analyzer.analyze(reply, "<result>", "</result>");
		// 如果没有提取到值，返回未知结果
		if (temp.size() == 0)
			return new Result_String(null);
		String result = temp.get(0);
		android.util.Log.i("郑海鹏", "Server_GetUserIcon#getServer(): " + result);
		return new Result_String(result);
	}

}
