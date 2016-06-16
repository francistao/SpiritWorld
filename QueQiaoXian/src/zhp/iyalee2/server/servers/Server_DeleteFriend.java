package zhp.iyalee2.server.servers;

import java.util.ArrayList;

import zhp.iyalee2.server.IServer;
import zhp.java.server.Param;
import zhp.java.server.Post;

/**
 * 删除好友
 * @author 郑海鹏
 * @since 2015/4/15 16:59
 */
public class Server_DeleteFriend implements IServer {
	final String SERVER_SUBMIT_VALUE = "deleteFriend";
	final String SERVER_FRIEND_NAME = "friendName";

	public void getServer(String userName, String friendName) {
		// 构造传给服务器的参数列表
		ArrayList<Param> params = new ArrayList<Param>();
		params.add(new Param(server_userName, userName));
		params.add(new Param(SERVER_FRIEND_NAME, friendName));
		params.add(new Param(server_submit_name, SERVER_SUBMIT_VALUE));

		// 发送消息到服务器并获得返回结果
		try {
			String reply = Post.sendPost(domain, params);
			zhp.android.debug.Debug.Log(this.getClass().getName() + "#getServer()", reply);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
