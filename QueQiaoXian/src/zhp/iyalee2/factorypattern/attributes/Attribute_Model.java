package zhp.iyalee2.factorypattern.attributes;

import java.io.Serializable;
import java.util.ArrayList;

import zhp.iyalee2.beans.modeldata.GeometryModelData;
import zhp.iyalee2.factorypattern.attributes.parent.Attribute;

/**
 * 一个增强现实内容为静态模型的ARViewActivity应该具有的属性，实现了序列化接口，可以放到Intent中。
 * @author 郑海鹏
 * @since 2015年7月3日
 */
public class Attribute_Model extends Attribute implements Serializable{
	// =============================================
	// 常量
	// =============================================
	/**
	 * 经Intent传入参数时用到的key值。
	 */
	public static final String BaseARViewActivityAttribute = "Attribute_Base";
	
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
	public ArrayList<GeometryModelData> geometryDatas;
	
	/**
	 * 跟踪配置文件的绝对路径
	 */
	public String trackingConfigFilePath;

	// =============================================
	// 构造方法
	// =============================================
	/**
	 * 构造方法
	 */
	public Attribute_Model(ArrayList<GeometryModelData> geometryDatas,
			String trackingConfigFilePath) {
		this.geometryDatas = geometryDatas;
		this.trackingConfigFilePath = trackingConfigFilePath;
	}
	
}
