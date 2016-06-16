package zhp.iyalee2.server.servers;

import java.util.ArrayList;

import zhp.iyalee2.server.IServer;
import zhp.java.server.Param;
import zhp.java.server.Post;

/**
 * 删除某个云端的笔记
 */
public class Server_FeedBack implements IServer{
	final String SERVER_APP_NAME = "appName";
	final String SERVER_APP_VALUE = "arworld";
	final String SERVER_SUBMIT_VALUE = "feedback";
	final String SERVER_FEEDBACK_CONATAINS = "contain";

	public void getServer(String feedbackContains){
		// 构造传给服务器的参数列表
		ArrayList<Param> params = new ArrayList<Param>();
		params.add(new Param(SERVER_FEEDBACK_CONATAINS, feedbackContains));
		params.add(new Param(SERVER_APP_NAME, SERVER_APP_VALUE));
		params.add(new Param(server_submit_name, SERVER_SUBMIT_VALUE));

		// 发送消息到服务器
		try {
			Post.sendPost(domain, params);
		} catch (Exception e) {
			e.printStackTrace();
			zhp.android.debug.Debug.Log(this.getClass().getName(), "可能无法连接服务器。");
		}
	}
}
