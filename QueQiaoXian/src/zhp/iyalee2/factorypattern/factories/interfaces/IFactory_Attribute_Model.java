package zhp.iyalee2.factorypattern.factories.interfaces;

import zhp.iyalee2.factorypattern.attributes.Attribute_Model;

/**
 * 产生一个基本ARViewActivity的参数, 即{@linkplain Attribute_Model}
 * @author 郑海鹏
 * @since 2015年7月3日
 */
public interface IFactory_Attribute_Model extends IFactory_Attribute {

	@Override
	public Attribute_Model createAttribute();
	
}
