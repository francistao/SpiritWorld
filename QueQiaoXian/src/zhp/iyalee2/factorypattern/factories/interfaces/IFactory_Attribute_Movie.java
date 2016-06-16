package zhp.iyalee2.factorypattern.factories.interfaces;

import zhp.iyalee2.factorypattern.attributes.Attribute_Movie;
import zhp.iyalee2.factorypattern.attributes.Attribute_Picture;

/**
 * 产生一个基本ARViewActivity的参数, 即{@linkplain Attribute_Picture}
 * @author 郑海鹏
 * @since 2015年7月3日
 * @视频格式	支持3g2、3gp格式的播放；avi只能显示第一帧；mp4、flv格式直接崩溃。
 */
public interface IFactory_Attribute_Movie extends IFactory_Attribute {

	@Override
	public Attribute_Movie createAttribute();
	
}
