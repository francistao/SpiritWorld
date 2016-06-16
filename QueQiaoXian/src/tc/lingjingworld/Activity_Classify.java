package tc.lingjingworld;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.View;
import android.view.View.OnClickListener;
import zhp.iyalee2.R;
import zhp.iyalee2.aractivities.ARActivity_3G2;
import zhp.iyalee2.aractivities.ARActivity_Animals;
import zhp.iyalee2.aractivities.ARActivity_Buildings;
import zhp.iyalee2.aractivities.ARActivity_Human;
import zhp.iyalee2.beans.OwnItemData;
import zhp.iyalee2.views.others.CardView_OwnItem;

/**
 * 内置分类界面
 */
public class Activity_Classify extends FourBoxActivity {
	CardView_OwnItem animals, building, movies, human;
	
	public static SoundPool soundPool;
	public static Map<Integer, Integer> map; 
	@SuppressWarnings("deprecation")
	public void initSoundpool() {
		soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		map = new HashMap<Integer, Integer>();
		map.put(1, soundPool.load(this, R.raw.magic, 1));
	} 
	
	public void playSound(int sound, int number) {
		AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);// 实例化
		float audioMaxVolum = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);// 音效最大值
		float audioCurrentVolum = am.getStreamVolume(AudioManager.STREAM_MUSIC);
		float audioRatio = audioCurrentVolum / audioMaxVolum;
		soundPool.play(map.get(sound), audioRatio, audioRatio, 1, number, 1);
	}
	
	@Override
	protected void findViews() {
		super.findViews();
		addViews();
		initSoundpool();
	}
	
	void addViews(){
		animals = new CardView_OwnItem(this, new OwnItemData("动物", "3D模型动物的展示", R.drawable.animals));
		building = new CardView_OwnItem(this, new OwnItemData("建筑", "3D模型建筑的展示", R.drawable.building));
		human = new CardView_OwnItem(this, new OwnItemData("人物", "3D模型人物的展示", R.drawable.human));
		movies = new CardView_OwnItem(this, new OwnItemData("视频", "月球漫步", R.drawable.pingzi));
	
		row1_col1.addView(animals);
		row1_col2.addView(building);
		row2_col1.addView(human);
		row2_col2.addView(movies);
		
		animals.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				playSound(1, 0);
				startActivity(new Intent(Activity_Classify.this, ARActivity_Animals.class));
			}
		});
		
		building.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				playSound(1, 0);
				startActivity(new Intent(Activity_Classify.this, ARActivity_Buildings.class));
			}
		});
		
		human.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				playSound(1, 0);
				startActivity(new Intent(Activity_Classify.this, ARActivity_Human.class));
			}
		});
		
		movies.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				playSound(1, 0);
				startActivity(new Intent(Activity_Classify.this, ARActivity_3G2.class));
			}
		});
	}
	
}
