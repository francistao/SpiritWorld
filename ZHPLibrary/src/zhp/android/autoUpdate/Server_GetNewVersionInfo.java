package zhp.android.autoUpdate;

import java.util.ArrayList;

import zhp.java.exceptions.Exception_ReturnNull;
import zhp.java.server.Param;
import zhp.java.server.Post;
import zhp.java.utils.Utils_Analyzer;

/**
 * 连接服务器获得新版本信息。
 * @author 郑海鹏
 * @since 2015年5月29日
 */
public class Server_GetNewVersionInfo {
	// <reply><info>这是新版本的特性...</info><url>这里是下载地址...</url></reply>
	final String SERVER_SUBMIT_VALUE = "getNewVersionInfo";
	
	public UpdataInfo getUpdateInfo(String appName) throws Exception_ReturnNull{
		// 构造传给服务器的参数列表
		ArrayList<Param> params = new ArrayList<Param>();
		params.add(new Param(Final_Data.SERVER_APP_NAME, appName));
		params.add(new Param(Final_Data.SERVER_SUBMIT_KEY, SERVER_SUBMIT_VALUE));

		// 发送消息到服务器并获得返回结果
		String reply = "";
		try {
			reply = Post.sendPost(Final_Data.serverUrl, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception_ReturnNull("可能无法连接服务器。");
		}
		return getNewVersion(reply);
	}
	
	/**
	 * 解析获得的新版本信息
	 */
	private UpdataInfo getNewVersion(String reply) throws Exception_ReturnNull{
		// 解析新版本的新特性
		ArrayList<String> infos = Utils_Analyzer.analyze(reply, "<info>", "</info>");
		if (infos.size() == 0){
			throw new Exception_ReturnNull("收到的新版本信息无法解析");
		}
		String info = infos.get(0);
		info = info.replace("<br>", "\n");
		
		// 解析新版本的下载地址
		ArrayList<String> urls = Utils_Analyzer.analyze(reply, "<url>", "</url>");
		if (urls.size() == 0){
			throw new Exception_ReturnNull("收到的新版本信息无法解析");
		}
		String url = urls.get(0);
		
		// 返回新版本信息
		UpdataInfo updataInfo = new UpdataInfo(info, url, "" + Server_NeedUpdate.newVersionCode);
		return updataInfo;
	}
	
}
