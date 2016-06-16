package zhp.iyalee2.factorypattern.factories.single;

import java.util.ArrayList;

import zhp.iyalee2.beans.modeldata.GeometryPictureData;
import zhp.iyalee2.factorypattern.attributes.Attribute_Picture;
import zhp.iyalee2.factorypattern.factories.interfaces.IFactory_Attribute_Picture;

/**
 * 产生一个图片ARViewActivity的参数, 即{@linkplain Attribute_Picture}
 * @author 郑海鹏
 * @since 2015年9月7日
 */
public class Factory_Attribute_Picture_Single implements IFactory_Attribute_Picture {

	@Override
	public Attribute_Picture createAttribute(){
		final String trackingConfigFilePath = "single/TrackingData_MarkerlessFast.xml";
		ArrayList<GeometryPictureData> geometryDatas = new ArrayList<GeometryPictureData>();
		
		// 在这里添加新的模型，注意：如果要添加模型的话，记得在跟踪配置文件里添加响应项
		geometryDatas.add(new GeometryPictureData(1, "single/containPicture.jpg", 5, null, null));
		
		Attribute_Picture attribute = new Attribute_Picture(geometryDatas, trackingConfigFilePath);
		return attribute;
	}
	
}
