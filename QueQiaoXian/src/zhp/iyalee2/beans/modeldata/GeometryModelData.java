package zhp.iyalee2.beans.modeldata;

import zhp.iyalee2.beans.modeldata.parent.GeometryData;

import com.metaio.sdk.jni.Rotation;
import com.metaio.sdk.jni.Vector3d;

/**
 * 模型的属性, 包含该模型在Activity的ID、在Asset中的绝对路径、在显示时应该调整的角度和平移量，以及点击时播放的音效
 * 
 * @author 郑海鹏
 * @since 2015年7月3日
 */
public class GeometryModelData extends GeometryData {

	/**
	 * 触摸这个模型时发出的声音的资源id，如R.raw.xxx.mp3
	 */
	public int soundResId;

	/**
	 * 构造方法
	 * 
	 * @param id
	 *            这个模型在所在Activity的ID, ID应该从0开始计数，而不是1
	 * @param modeFilePath
	 *            这个模型在Asset中的绝对路径
	 * @param scale
	 *            这个模型在显示时应该缩放的比例
	 * @param rotation
	 *            这个模型在显示时应该调整的角度
	 * @param vector3d
	 *            这个模型在显示时应该平移的向量
	 * @param soundResId
	 *            触摸这个模型时发出的声音的资源id，如R.raw.xxx.mp3
	 */
	public GeometryModelData(int id, String modelFilePath, float scale, Rotation rotation,
			Vector3d vector3d, int soundResId) {
		super(id, modelFilePath, scale, rotation, vector3d);
		this.soundResId = soundResId;
	}

}
