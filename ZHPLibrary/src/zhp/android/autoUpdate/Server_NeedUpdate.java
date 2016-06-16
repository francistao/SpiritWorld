package zhp.android.autoUpdate;

import java.util.ArrayList;

import zhp.java.exceptions.Exception_ReturnNull;
import zhp.java.server.Param;
import zhp.java.server.Post;
import zhp.java.utils.Utils_Analyzer;

/**
 * 连接服务器，判断是否需要更新。
 * @author 郑海鹏
 * @since 2015年5月29日
 */
public class Server_NeedUpdate {
	protected static int newVersionCode = -1;
	// <reply><version>2</version></reply>
	final String SERVER_SUBMIT_VALUE = "getNewVerCode";
	
	public boolean isNeedUpadte(String appName, int currentVersionCode){

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
			return false;
		}

		// 解析新的版本号
		int newVersion = -1;
		try {
			newVersion = getNewVersion(reply);
		} catch (Exception_ReturnNull e) {
			e.printStackTrace();
			return false;
		}
		
		// 比较，返回结果
		if(newVersion > currentVersionCode){
			newVersionCode = newVersion;
			return true;
		}else{
			return false;
		}
	}
	
	private int getNewVersion(String reply) throws Exception_ReturnNull{
		ArrayList<String> temp = Utils_Analyzer.analyze(reply, "<version>", "</version>");
		if (temp.size() == 0){
			throw new Exception_ReturnNull("收到的新版本信息无法解析");
		}
		int newVersion;
		
		try {
			newVersion = Integer.valueOf(temp.get(0));
		} catch (NumberFormatException e) {
			throw new Exception_ReturnNull("收到的新版本信息无法解析");
		}
		
		return newVersion;
	}
	
}
