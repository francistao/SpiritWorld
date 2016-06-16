package zhp.android.autoUpdate;

import zhp.android.data.HandlerMsg;

import zhp.java.exceptions.Exception_ReturnNull;
import android.os.Handler;
import android.os.Message;

/**
 * @author 郑海鹏
 * @since 2015年5月29日
 * @deprecated 该类没有使用StrategyHandler，耦合性太高。请使用Thread_UpdateWithStrategyHandler代替。
 */
@Deprecated
public class Thread_Update extends Thread {
	public static final int SHOW_DIALOG = 500;
	
	Handler handler;
	String appName;
	int currentVersionCode;
	
	public Thread_Update(Handler handler, String appName, int currentVersion) {
		super();
		this.handler = handler;
		this.appName = appName;
		this.currentVersionCode = currentVersion;
	}

	@Override
	public void run() {
		// 检查是否需要更新
		Server_NeedUpdate server = new Server_NeedUpdate();
		boolean needUpdate = server.isNeedUpadte(this.appName, this.currentVersionCode);
		// 如果需要更新，获得更新信息
		if (needUpdate) {
			zhp.android.debug.Debug.Log(this.getClass().getName(),
					"need Update----------------------");
			ifNeedUpdate();
		}
	}
	
	private void ifNeedUpdate() {
		// 获得新版本信息
		Server_GetNewVersionInfo serverInfo = new Server_GetNewVersionInfo();
		UpdataInfo info = null;
		try {
			info = serverInfo.getUpdateInfo(this.appName);			
		} catch (Exception_ReturnNull e) {
			e.printStackTrace();
		}
		// 将新版本信息装进消息中
		Message msg = new Message();
		msg.obj = info;
		msg.what = HandlerMsg.GET_THE_NEW_VERSION_INFO_FINISH;
		handler.sendMessage(msg);
		zhp.android.debug.Debug.Log(this.getClass().getName(), "已向MA的handler发送信息！");
	}
	
}
