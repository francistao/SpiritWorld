package zhp.iyalee2.server.servers;

import java.util.ArrayList;

import zhp.iyalee2.beans.ItemFriend;
import zhp.iyalee2.server.IServer;
import zhp.iyalee2.server.ServerDebug;
import zhp.java.server.Param;
import zhp.java.server.Post;
import zhp.java.utils.Utils_Analyzer;

/**
 * 获得好友列表
 * 
 * @author 郑海鹏
 * @since 2015年9月19日
 */
public class Server_GetFriends implements IServer {
	// 返回示例： "<result>"."<name>".$friendName."</name><url>".$url."</url><profile>".$profile."</profile>"."</result>"

	final String SERVER_SUBMIT_VALUE = "getFriend";
	final String SERVER_URL = "http://1.iyalee.sinaapp.com/arworld/GetFriendInfo.php";

	public ArrayList<ItemFriend> getServer(String userName, String password) {
		if (ServerDebug.isDebug) {
			return null;
		}

		// 构造传给服务器的参数列表
		ArrayList<Param> params = new ArrayList<Param>();
		params.add(new Param(server_userName, userName));
		params.add(new Param(server_password, password));
		params.add(new Param(server_submit_name, SERVER_SUBMIT_VALUE));

		// 发送消息到服务器并获得返回结果
		String reply = "";
		try {
			reply = Post.sendPost(SERVER_URL, params);
			android.util.Log.i("郑海鹏", "Server_GetFriends#getServer(): " + "收到回复：" + reply);
			return analyze(reply);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	private ArrayList<ItemFriend> analyze(String reply) {
		try {
			ArrayList<ItemFriend> result = new ArrayList<ItemFriend>();
			ArrayList<String> items = Utils_Analyzer.analyze(reply, "<item>", "</item>");
			for (int i = 0; i < items.size(); i++) {
				String friendName = Utils_Analyzer.analyze(items.get(i), "<name>", "</name>").get(0);
				String iconUrl = Utils_Analyzer.analyze(items.get(i), "<url>", "</url>").get(0);
				String profile = Utils_Analyzer.analyze(items.get(i), "<profile>", "</profile>").get(0);
				ItemFriend item = new ItemFriend(iconUrl, friendName, profile);
				result.add(item);
			}
			return result;
		} catch (Exception e) {
			zhp.android.debug.Debug.Log(this.getClass().getName() + "#analyze()", "出现异常！");
			return null;
		}

	}

}
