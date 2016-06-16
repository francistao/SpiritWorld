package tc.lingjingworld;

import java.util.HashMap;
import java.util.Map;

import zhp.android.autoUpdate.Thread_UpdateWithStrategyHandler;
import zhp.android.data.CurrentUser;
import zhp.android.handlers.StrategyHandler;
import zhp.iyalee2.R;
import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

public class Activity_Start extends Activity implements OnClickListener {

	private RelativeLayout rlFriendList;
	private RelativeLayout rlMycontent;
	private RelativeLayout rlSettings;
	private RelativeLayout rlStart;

	public static SoundPool soundPool;
	public static Map<Integer, Integer> map; 
	
	@SuppressWarnings("deprecation")
	public void initSoundpool() {
		soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		map = new HashMap<Integer, Integer>();
		map.put(1, soundPool.load(this, R.raw.click1, 1));
	}
	
	public void playSound(int sound, int number) {
		AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);// 实例化
		float audioMaxVolum = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);// 音效最大值
		float audioCurrentVolum = am.getStreamVolume(AudioManager.STREAM_MUSIC);
		float audioRatio = audioCurrentVolum / audioMaxVolum;
		soundPool.play(map.get(sound), audioRatio, audioRatio, 1, number, 1);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		findViewsAndInit();
		initSoundpool();
		zhp.android.utils.Utils_Activity.getInstance().setStateBarColor(this, getResources().getColor(R.color.colorPrimaryDark));
		checkUpdate();
	}
	
	boolean updateChecked = false;
	/**
	 * 检查版本更新
	 */
	private void checkUpdate() {
		if (!updateChecked) {
			StrategyHandler shandler = new StrategyHandler();
			Thread_UpdateWithStrategyHandler thread = new Thread_UpdateWithStrategyHandler(this,
					shandler, "arworld", 1);
			thread.start();
			updateChecked = true;
		}
	}
	
	private void findViewsAndInit(){
		rlFriendList = (RelativeLayout) findViewById(R.id.rl_friendlist);
		rlMycontent = (RelativeLayout) findViewById(R.id.rl_mycontent);
		rlSettings = (RelativeLayout) findViewById(R.id.rl_settings);
		rlStart = (RelativeLayout) findViewById(R.id.rl_start);
		rlMycontent.setOnClickListener(this);
		rlFriendList.setOnClickListener(this);
		rlSettings.setOnClickListener(this);
		rlStart.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		
		case R.id.rl_friendlist:
			if (CurrentUser.isLogin) {
				startActivity(new Intent(this, Activity_MyFriends.class));
			} else {
				startActivity(new Intent(Activity_Start.this, Activity_Login.class));
			}
			break;
			
		case R.id.rl_mycontent:
			startActivity(new Intent(Activity_Start.this, Activity_MyItems.class));
			break;
		case R.id.rl_settings:
			startActivity(new Intent(Activity_Start.this, Activity_UserCenter.class));
			break;
		case R.id.rl_start:
			playSound(1, 0);
			startActivity(new Intent(Activity_Start.this, Activity_Classify.class));
			break;
		default:
			break;
		}
	}
}
