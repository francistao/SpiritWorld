package zhp.iyalee2.server.servers;

import java.util.ArrayList;

import zhp.iyalee2.beans.ItemValue;
import zhp.iyalee2.server.IServer;
import zhp.iyalee2.server.ServerDebug;
import zhp.iyalee2.server.results.Result_SuccessOrFail;
import zhp.iyalee2.utils.Utils_General;
import zhp.java.netio.upload.FileParam;
import zhp.java.netio.upload.Param;
import zhp.java.netio.upload.Utils_Post;
import zhp.java.utils.Utils_Analyzer;

public class Server_UploadItem implements IServer{
	final String SERVER_SUBMIT_VALUE = "uploadItem";
	
	public Result_SuccessOrFail getServer(String userName, String password, ItemValue item) {
		if (ServerDebug.isDebug) {
			return Result_SuccessOrFail.SUCCESS;
		}
		// 构造传给服务器的参数列表
		ArrayList<Param> params = new ArrayList<Param>();
		params.add(new Param(server_userName, userName));
		params.add(new Param(server_password, password));
		params.add(new Param(server_submit_name, SERVER_SUBMIT_VALUE));
		params.add(new Param("createTime", item.getCreateTime()));
		params.add(new Param("title", item.getTitle()));
		params.add(new Param("profile", item.getProfile()));
		params.add(new Param("type", item.getType().toString()));
		
		Utils_General utils = new Utils_General();
		
		ArrayList<FileParam> fileParams = new ArrayList<FileParam>();
		fileParams.add(new FileParam(item.getContainPath(), "contain_" + utils.toAscii(userName)
				+ "_" + item.getCreateTime() + "." + utils.getSuffix(item.getContainPath()),
				"contain"));
		fileParams.add(new FileParam(item.getTargetPath(), "target_" + utils.toAscii(userName)
				+ "_" + item.getCreateTime() + "." + utils.getSuffix(item.getTargetPath()),
				"target"));
		
		// 发送消息到服务器并获得返回结果
		String reply = "";
		try {
			reply = new Utils_Post().post("http://1.iyalee.sinaapp.com/arworld/UploadItem.php", params, fileParams);
		} catch (Exception e) {
			e.printStackTrace();
			return Result_SuccessOrFail.CAN_NOT_CONNECT_TO_SERVER;
		}
		
		android.util.Log.i("郑海鹏", "Server_UploadItem#getServer(): " + reply);
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
	
//	/**
//	 * 用户名转为ASCII码
//	 */
//	private String toAscii(String userName){
//		StringBuilder sb = new StringBuilder();
//		for(int i = 0; i < userName.length(); i++){
//			if(userName.charAt(i) < 255){
//				sb.append(userName.charAt(i));
//			}else{
//				sb.append((int)userName.charAt(i));
//			}
//		}
//		return sb.toString();
//	}
}
