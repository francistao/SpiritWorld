package zhp.iyalee2.factorypattern.factories;

import java.util.ArrayList;

import zhp.iyalee2.beans.modeldata.GeometryPictureData;
import zhp.iyalee2.factorypattern.attributes.Attribute_Picture;
import zhp.iyalee2.factorypattern.factories.interfaces.IFactory_Attribute_Picture;

import com.metaio.sdk.jni.Rotation;

/**
 * 产生一个图片ARViewActivity的参数, 即{@linkplain Attribute_Picture}
 * @author 郑海鹏
 * @since 2015年7月3日
 */
public class Factory_Attribute_Picture_MJ implements IFactory_Attribute_Picture {

	@Override
	public Attribute_Picture createAttribute(){
		final String trackingConfigFilePath = "pictures/TrackingData_MarkerlessFast.xml";
		ArrayList<GeometryPictureData> geometryDatas = new ArrayList<GeometryPictureData>();
		
		// 在这里添加新的模型，注意：如果要添加模型的话，记得在跟踪配置文件里添加响应项
		geometryDatas.add(new GeometryPictureData(1, "pictures/mj.jpg", 5, new Rotation(90, 0, 0), null));
		geometryDatas.add(new GeometryPictureData(2, "pictures/mj2.png", 5, null, null));
		
		Attribute_Picture attribute = new Attribute_Picture(geometryDatas, trackingConfigFilePath);
		return attribute;
	}
	
}
