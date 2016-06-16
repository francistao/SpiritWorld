package zhp.android.autoUpdate;

import zhp.android.handlers.StrategyHandler;
import zhp.java.exceptions.Exception_ReturnNull;
import android.app.Activity;

/**
 * @author 郑海鹏
 * @since 2015年5月29日
 */
public class Thread_UpdateWithStrategyHandler extends Thread {
	public static final int SHOW_DIALOG = 500;
	
	StrategyHandler handler;
	String appName;
	int currentVersionCode;
	Activity activity;
	
	public Thread_UpdateWithStrategyHandler(Activity activity, StrategyHandler handler, String appName, int currentVersion) {
		super();
		this.handler = handler;
		this.appName = appName;
		this.currentVersionCode = currentVersion;
		this.activity = activity;
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
		zhp.android.debug.Debug.Log(this.getClass().getName(), "准备更新。");
		// 获得新版本信息
		Server_GetNewVersionInfo serverInfo = new Server_GetNewVersionInfo();
		UpdataInfo info = null;
		try {
			info = serverInfo.getUpdateInfo(this.appName);
		} catch (Exception_ReturnNull e) {
			e.printStackTrace();
		}
		
		// 将新版本信息装进消息中
		Strategy_Update strategy = new Strategy_Update(activity, info, appName);
		handler.execStrategy(strategy);
	}
	
}
