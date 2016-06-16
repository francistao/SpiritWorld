package zhp.iyalee2.aractivities.base;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import zhp.android.utils.Utils_PhoneInfo;
import zhp.iyalee2.R;
import zhp.iyalee2.factorypattern.attributes.Attribute_Model;
import zhp.iyalee2.utils.Utils_Camera;
import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.Builder;

import com.metaio.sdk.ARViewActivity;
import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.IMetaioSDKCallback;
import com.metaio.tools.io.AssetsManager;

/**
 * 一般用于加载静态、可能需要设置大小、旋转、平移的模型。
 * @警告 需要在onCreate中对attribute进行初始化。如：attribute = new Factory_Attribute_Base_Animal().createAttribute();
 * @author 郑海鹏
 * @since 2015年7月3日
 */
@SuppressLint("UseSparseArrays")
@SuppressWarnings("deprecation")
public class ModelARViewActivity extends ARViewActivity {
	// =============================================
	// 变量
	// =============================================
	/**
	 * 一个ARViewActivity应有的参数，包括模型数据{@linkplain zhp.iyalee2.beans.modeldata.GeometryModelData
	 * GeometryBaseData}、跟踪配置文件等。
	 */
	public Attribute_Model attribute;

	/**
	 * 音效池
	 */
	SoundPool soundPool;

	/**
	 * 模型和音效ID的对应关系图
	 */
	Map<Integer, Integer> soundMap;

	/**
	 * 该Activity含有的模型数量，在{@linkplain ModelARViewActivity#loadGeometries()
	 * loadGeometries()方法}中被初始化
	 */
	int modelQuantity;

	/**
	 * 用于播放音效
	 */
	AudioManager am = null;

	// =============================================
	// 继承、实现的方法
	// =============================================
	@Override
	protected int getGUILayout() {
		return R.layout.activity_empty;
	}

	@Override
	protected IMetaioSDKCallback getMetaioSDKCallbackHandler() {
		return null;
	}

	@Override
	protected void loadContents() {
		// 先设置追踪配置文件
		setTrackingConfigFile();

		// 加载模型
		loadGeometries();

		// 初始化音效池
		initSoundPool();

		// 设置相机自动对焦
		setCameraAutoFocus();
	}

	@Override
	protected void onGeometryTouched(IGeometry geometry) {
		playSound(geometry.getCoordinateSystemID());
		geometry.startAnimation();
	}

	// =============================================
	// 自定义的方法
	// =============================================
	/**
	 * 根据传入的参数，设置追踪配置文件
	 */
	private void setTrackingConfigFile() {
		// 获得追踪配置文件
		File trackingConfigFile = AssetsManager.getAssetPathAsFile(getApplicationContext(),
				attribute.trackingConfigFilePath);
		// 设置配置文件
		metaioSDK.setTrackingConfiguration(trackingConfigFile);
	}

	/**
	 * 根据传入的参数，加载模型
	 */
	private void loadGeometries() {
		// 模型的数量
		modelQuantity = attribute.geometryDatas.size();

		// 创建模型
		for (int i = 0; i < modelQuantity; i++) {
			File modelFile = AssetsManager.getAssetPathAsFile(getApplicationContext(),
					attribute.geometryDatas.get(i).modelFilePath);
			IGeometry geometry = metaioSDK.createGeometry(modelFile);
			
			// 比例
			geometry.setScale(attribute.geometryDatas.get(i).scale);
			
			// 旋转
			if(attribute.geometryDatas.get(i).rotation != null){
				geometry.setRotation(attribute.geometryDatas.get(i).rotation);
			}
			
			// 平移
			if(attribute.geometryDatas.get(i).vector3d != null){
				geometry.setTranslation(attribute.geometryDatas.get(i).vector3d);
			}
			
			// id
			geometry.setCoordinateSystemID(attribute.geometryDatas.get(i).id);
		}
	}

	/**
	 * 设置摄像头自动对焦
	 */
	private void setCameraAutoFocus() {
//		android.hardware.Camera camera = (android.hardware.Camera) IMetaioSDKAndroid
//				.getCamera(this);
//		android.hardware.Camera.Parameters params = camera.getParameters();
//		params.setFocusMode("continuous-picture");
//		camera.setParameters(params);
		Utils_Camera.getInstance().setCameraAutoFocus(this);
	}

	/**
	 * 初始化音效, 应该在 {@linkplain ModelARViewActivity#modelQuantity modelQuantity}
	 * 被初始化以后才调用本方法。
	 */
	@SuppressLint("NewApi")
	private void initSoundPool() {
		// 创建soundPool
		if(Utils_PhoneInfo.getInstance().getAndroidVersionCode() < 21){
			soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
		}else{
			SoundPool.Builder builder = new Builder();
			builder.setMaxStreams(100);
			soundPool = builder.build();
		}
		
		// put
		soundMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < modelQuantity; i++) {
			zhp.android.debug.Debug.Log(this.getClass().getName(),
					attribute.geometryDatas.get(i).id + ", "
							+ attribute.geometryDatas.get(i).soundResId);
			
			soundMap.put(attribute.geometryDatas.get(i).id,
					soundPool.load(this, attribute.geometryDatas.get(i).soundResId, 1));
		}
	}

	/**
	 * 根据模型的id播放对应的音效，如果该模型没有对应的音效则不播放。
	 * 
	 * @param coordinateSystemID
	 *            模型ID
	 */
	private void playSound(int coordinateSystemID) {
		// 初始化AudioManager
		if (am == null) {
			am = (AudioManager) getSystemService(AUDIO_SERVICE);
		}

		// 根据模型ID获得音效ID
		Integer soundID = soundMap.get(coordinateSystemID);
		
		zhp.android.debug.Debug.Log(this.getClass().getName(), "id：" + coordinateSystemID);
		zhp.android.debug.Debug.Log(this.getClass().getName(), "soundID:" + soundID);
		if (soundID == null) {
			zhp.android.debug.Debug.Log(this.getClass().getName(), "没有找到该模型对应的音效。模型ID = "
					+ coordinateSystemID);
			return;
		}

		// 播放音效
		float audioMaxVolum = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		float audioCurrentVolum = am.getStreamVolume(AudioManager.STREAM_MUSIC);
		float audioRatio = audioCurrentVolum / audioMaxVolum;
		zhp.android.debug.Debug.Log(this.getClass().getName(), "audioRatio = " + audioRatio);
		soundPool.play(soundID, audioRatio, audioRatio, 1, 0, 1);
	}

}
