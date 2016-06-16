package zhp.android.autoUpdate;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.widget.Toast;
import zhp.android.strategies.IStrategy;
import zhp.android.utils.Utils_Network;

/**
 * 升级策略
 * @author 郑海鹏
 */
public class Strategy_Update implements IStrategy {
	Activity activity;
	UpdataInfo info;
	String appName;
	
	public Strategy_Update(Activity activity, UpdataInfo info, String appName) {
		this.activity = activity;
		this.info = info;
		this.appName = appName;
	}

	@Override
	public void run() {
		zhp.android.debug.Debug.Log(this.getClass().getName(), "Startegy_Update run()");
		initUpdateHintDialog();
	}

	private void initUpdateHintDialog() {
		// 显示对话框
		AlertDialog.Builder builder = new Builder(activity);
		builder.setTitle("有新版本了！");
		builder.setMessage(info.versionInfo);
		builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				boolean isWifi = Utils_Network.getInstance().isWifiConnected(activity);
				if (!isWifi) {
					showDialog_notWifi(info);
				} else {
					downloading();
				}
			}
		});

		builder.setNegativeButton("取消", null);
		builder.create().show();
	}

	/**
	 * 提示用户当前为非wifi环境
	 */
	private void showDialog_notWifi(final UpdataInfo info) {
		AlertDialog.Builder builder = new Builder(activity);
		builder.setTitle("没有连接WIFI哦(^_^)");
		builder.setMessage("确定要在非wifi环境下下载新版本吗？");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				downloading();
			}
		});

		builder.setNegativeButton("取消", null);
		builder.create().show();
	}
	
	/**
	 * 在新线程中下载
	 */
	private void downloading(){
		new Thread_DownloadApk(appName, info.url, activity).start();
		Toast.makeText(activity, "正在后台下载...", Toast.LENGTH_LONG).show();
	}

}
