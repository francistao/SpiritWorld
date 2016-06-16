package zhp.iyalee2.factorypattern.attributes;

import java.io.Serializable;
import java.util.ArrayList;

import zhp.iyalee2.beans.modeldata.GeometryMovieData;
import zhp.iyalee2.factorypattern.attributes.parent.Attribute;

/**
 * 一个增强画面为视频的ARViewActivity应该具有的属性，实现了序列化接口，可以放到Intent中。
 * @author 郑海鹏
 * @since 2015年7月4日
 */
public class Attribute_Movie extends Attribute implements Serializable{
	// =============================================
	// 常量
	// =============================================
	/**
	 * 默认类ID，不用管。
	 */
	private static final long serialVersionUID = 1L;

	// =============================================
	// 变量
	// =============================================
	/**
	 * 这个Activity所要展示的模型的属性，建议数量小于5个
	 */
	public ArrayList<GeometryMovieData> geometryDatas;
	
	// =============================================
	// 构造方法
	// =============================================
	/**
	 * 构造方法
	 * @param geometryDatas
	 * @param trackingConfigFilePath
	 */
	public Attribute_Movie(ArrayList<GeometryMovieData> geometryDatas,
			String trackingConfigFilePath) {
		this.geometryDatas = geometryDatas;
		this.trackingConfigFilePath = trackingConfigFilePath;
	}
	
}
