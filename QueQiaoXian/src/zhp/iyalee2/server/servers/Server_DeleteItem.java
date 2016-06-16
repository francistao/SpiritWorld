package zhp.iyalee2.server.servers;

import java.util.ArrayList;

import zhp.iyalee2.beans.ItemValue;
import zhp.iyalee2.server.IServer;
import zhp.java.server.Param;
import zhp.java.server.Post;

/**
 * 删除云端item
 * @author 郑海鹏
 * @since 2015年9月17日
 */
public class Server_DeleteItem implements IServer {

	final String SERVER_SUBMIT_VALUE = "deleteItem";
	final String SERVER_ITEM_CREATE_TIME = "createTime";
	final String SERVER_URL = "http://1.iyalee.sinaapp.com/arworld/deleteItem.php";

	public void getServer(String userName, String password, ItemValue item) {
		// 构造传给服务器的参数列表
		ArrayList<Param> params = new ArrayList<Param>();
		params.add(new Param(server_userName, userName));
		params.add(new Param(server_password, password));
		params.add(new Param(SERVER_ITEM_CREATE_TIME, item.getCreateTime()));
		params.add(new Param(server_submit_name, SERVER_SUBMIT_VALUE));

		// 发送消息到服务器并获得返回结果
		String reply = "";
		try {
			reply = Post.sendPost(SERVER_URL, params);
		} catch (Exception e) {
			e.printStackTrace();
		}

		zhp.android.debug.Debug.Log(this.getClass().getName(), reply);

//		// 对结果解析。正确情况下，结果放在<result>标签内
//		ArrayList<String> temp = Utils_Analyzer.analyze(reply, "<result>", "</result>");
//		// 如果没有提取到值，返回未知结果
//		if (temp.size() == 0)
//			return new Result_String(null);
//		String result = temp.get(0);
//		android.util.Log.i("郑海鹏", "Server_GetUserIcon#getServer(): " + result);
//		return new Result_String(result);
	}

}
