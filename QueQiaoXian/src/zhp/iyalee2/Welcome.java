package zhp.iyalee2;

import java.io.IOException;

import tc.lingjingworld.Activity_Start;
import zhp.android.activities.WelcomeActivity;
import zhp.iyalee2.classes.LogoAnimation;
import zhp.java.listeners.OnFinishListener;
import zhp.java.threads.Thread_withCallBack;
import android.content.Intent;
import android.os.Bundle;

import com.metaio.tools.io.AssetsManager;

public class Welcome extends WelcomeActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		loadSource();
		new LogoAnimation().logoAnimation(this, bgLayout, 40, 4, 2500);
	}

	@Override
	protected void setJumpIntent() {
		this.jumpIntent = new Intent(this, Activity_Start.class);
	}
	
	@Override
	protected void setOptions() {
		super.setOptions();
		setDelay(3000);
		setText("灵境世界", " ");
		setTextSize(12, 4);
		setBgColor(getResources().getColor(R.color.colorPrimaryDark));
	}

	long startTime;
	long endTime;
	/**
	 * 加载资源
	 */
	private void loadSource() {
		startTime = System.currentTimeMillis();
		endTime = startTime;
		// 加载完成监听器
		OnFinishListener listener = new OnFinishListener() {
			@Override
			public void onFinish() {
				endTime = System.currentTimeMillis();
			}
		};

		// 子线程中加载
		Thread_withCallBack thread = new Thread_withCallBack(new Runnable() {

			@Override
			public void run() {
				try {
					// 在添加新的模型进去时，请用true。
					// 在调试云服务时，请使用false。
					// 最终发布的时候使用false。
					AssetsManager.extractAllAssets(Welcome.this, true);
				} catch (IOException e) {
					zhp.android.debug.Debug.Log(this.getClass().getName(), "加载资源出错。");
				}
			}
		}, listener);
		thread.start();
	}
	
	@Override
	protected void doThingWhenDelay() {
//		delay -= (endTime - startTime);
//		if(delay < 0){
//			delay = 0;
//		}
	}
}
