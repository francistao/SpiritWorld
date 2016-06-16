package zhp.iyalee2.aractivities.base;

import java.io.File;

import zhp.iyalee2.R;
import zhp.iyalee2.factorypattern.attributes.Attribute_Picture;
import zhp.iyalee2.utils.Utils_Camera;
import android.annotation.SuppressLint;

import com.metaio.sdk.ARViewActivity;
import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.IMetaioSDKCallback;
import com.metaio.tools.io.AssetsManager;

/**
 * 一般用于加载静态、可能需要设置大小、旋转、平移的图片。
 * @警告 需要在onCreate中对attribute进行初始化。如：attribute = new Factory_Attribute_Base_Animal().createAttribute();
 * @author 郑海鹏
 * @since 2015年7月3日
 */
@SuppressLint("UseSparseArrays")
public class PictureARViewActivity extends ARViewActivity {
	// =============================================
	// 变量
	// =============================================
	/**
	 * 一个ARViewActivity应有的参数，包括模型数据{@linkplain zhp.iyalee2.beans.modeldata.GeometryPictureData
	 * GeometryPictureData}、跟踪配置文件等。
	 */
	public Attribute_Picture attribute;

	/**
	 * 该Activity含有的模型数量，在{@linkplain PictureARViewActivity#loadGeometries()
	 * loadGeometries()方法}中被初始化
	 */
	int modelQuantity;

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

		// 设置相机自动对焦
//		setCameraAutoFocus();
		Utils_Camera.getInstance().setCameraAutoFocus(this);
	}

	@Override
	protected void onGeometryTouched(IGeometry geometry) {
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
			android.util.Log.i("郑海鹏", "PictureARViewActivity#loadGeometries(): " + "创建模型中...");
			
			File modelFile = AssetsManager.getAssetPathAsFile(getApplicationContext(),
					attribute.geometryDatas.get(i).modelFilePath);
			IGeometry geometry = metaioSDK.createGeometryFromImage(modelFile);
			
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
//	private void setCameraAutoFocus2() {
////		android.hardware.Camera camera = (android.hardware.Camera) IMetaioSDKAndroid
////				.getCamera(this);
////		android.hardware.Camera.Parameters params = camera.getParameters();
////		params.setFocusMode("continuous-picture");
////		camera.setParameters(params);
//		Utils_Camera.getInstance().setCameraAutoFocus(this);
//	}

}
