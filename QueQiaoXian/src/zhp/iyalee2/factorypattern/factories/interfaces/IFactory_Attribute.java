package zhp.iyalee2.factorypattern.factories.interfaces;

import zhp.iyalee2.factorypattern.attributes.parent.Attribute;

/**
 * 所有的ARViewActivityAttribute工厂类都需实现此接口
 * @author 郑海鹏
 * @since 2015年7月3日
 */
public interface IFactory_Attribute {

	public Attribute createAttribute();
	
}
