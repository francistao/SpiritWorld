package zhp.iyalee2.factorypattern.factories.interfaces;

import zhp.iyalee2.factorypattern.attributes.Attribute_Picture;

/**
 * 产生一个基本ARViewActivity的参数, 即{@linkplain Attribute_Picture}
 * @author 郑海鹏
 * @since 2015年7月3日
 */
public interface IFactory_Attribute_Picture extends IFactory_Attribute {

	@Override
	public Attribute_Picture createAttribute();
	
}
