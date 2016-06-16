package zhp.iyalee2.beans.modeldata.parent;

import com.metaio.sdk.jni.Rotation;
import com.metaio.sdk.jni.Vector3d;

/**
 * 模型的基本数据
 * 
 * @author 郑海鹏
 * @since 2015年7月4日
 */
public class GeometryData {
	/**
	 * 这个模型在所在Activity的ID, ID应该从0开始计数，而不是1
	 */
	public int id;

	/**
	 * 这个模型在Asset中的绝对路径
	 */
	public String modelFilePath;

	/**
	 * 这个模型在显示时应该缩放的比例
	 */
	public float scale;

	/**
	 * 这个模型在显示时应该调整的角度
	 */
	public Rotation rotation;

	/**
	 * 这个模型在显示时应该平移的向量
	 */
	public Vector3d vector3d;

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
	 */
	public GeometryData(int id, String modelFilePath, float scale, Rotation rotation, Vector3d vector3d) {
		this.id = id;
		this.modelFilePath = modelFilePath;
		this.scale = scale;
		this.rotation = rotation;
		this.vector3d = vector3d;
	}

}
