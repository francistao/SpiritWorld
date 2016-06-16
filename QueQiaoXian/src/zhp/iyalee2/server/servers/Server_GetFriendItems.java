package zhp.iyalee2.server.servers;

import java.util.ArrayList;

import zhp.iyalee2.beans.ItemValue;
import zhp.iyalee2.beans.ItemValue.ContainType;
import zhp.iyalee2.server.IServer;
import zhp.iyalee2.server.ServerDebug;
import zhp.java.server.Param;
import zhp.java.server.Post;
import zhp.java.utils.Utils_Analyzer;

/**
 * 获得好友的项目列表
 * 
 * @author 郑海鹏
 * @since 2015年9月19日
 */
public class Server_GetFriendItems implements IServer {
	final String SERVER_SUBMIT_VALUE = "getFriendItem";
	final String SERVER_URL = "http://1.iyalee.sinaapp.com/arworld/GetFriendItem.php";

	public ArrayList<ItemValue> getServer(String userName) {
		if (ServerDebug.isDebug) {
			return null;
		}

		// 构造传给服务器的参数列表
		ArrayList<Param> params = new ArrayList<Param>();
		params.add(new Param(server_userName, userName));
		params.add(new Param(server_submit_name, SERVER_SUBMIT_VALUE));

		// 发送消息到服务器并获得返回结果
		String reply = "";
		try {
			reply = Post.sendPost(SERVER_URL, params);
			zhp.android.debug.Debug.Log(this.getClass().getName() + "#getServer()", "收到回复：" + reply);
			return analyze(reply);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	private ArrayList<ItemValue> analyze(String reply) {
		try {
			ArrayList<ItemValue> result = new ArrayList<ItemValue>();
			ArrayList<String> items = Utils_Analyzer.analyze(reply, "<item>", "</item>");
			for (int i = 0; i < items.size(); i++) {
				String createTime = Utils_Analyzer.analyze(items.get(i), "<cT>", "</cT>").get(0);				
				String targetPath = Utils_Analyzer.analyze(items.get(i), "<tU>", "</tU>").get(0);				
				String title = Utils_Analyzer.analyze(items.get(i), "<title>", "</title>").get(0);				
				String profile = Utils_Analyzer.analyze(items.get(i), "<profile>", "</profile>").get(0);				
				String containPath = Utils_Analyzer.analyze(items.get(i), "<cU>", "</cU>").get(0);				
				String type = Utils_Analyzer.analyze(items.get(i), "<type>", "</type>").get(0);
				
				ItemValue item = new ItemValue(targetPath, title, profile, containPath, ContainType.toContainType(type), createTime);
				result.add(item);
			}
			return result;
		} catch (Exception e) {
			zhp.android.debug.Debug.Log(this.getClass().getName() + "#analyze()", "出现异常！");
			return null;
		}

	}

}
