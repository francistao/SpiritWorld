package zhp.iyalee2.beans.modeldata;

import zhp.iyalee2.beans.modeldata.parent.GeometryData;

import com.metaio.sdk.jni.Rotation;
import com.metaio.sdk.jni.Vector3d;

/**
 * 模型的属性, 包含该模型在Activity的ID、在Asset中的绝对路径、在显示时应该调整的角度和平移量
 * 
 * @author 郑海鹏
 * @since 2015年7月3日
 */
public class GeometryMovieData extends GeometryData {
	
	/**
	 * 是否含有透明图层
	 */
	public boolean transparent;
	
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
	public GeometryMovieData(int id, String modelFilePath, float scale, Rotation rotation,
			Vector3d vector3d, boolean transparent) {
		super(id, modelFilePath, scale, rotation, vector3d);
		this.transparent = transparent;
	}

}
