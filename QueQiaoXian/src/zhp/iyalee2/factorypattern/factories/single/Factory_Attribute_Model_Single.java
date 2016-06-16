package zhp.iyalee2.factorypattern.factories.single;

import java.util.ArrayList;

import com.metaio.sdk.jni.Rotation;

import zhp.iyalee2.R;
import zhp.iyalee2.beans.modeldata.GeometryModelData;
import zhp.iyalee2.factorypattern.attributes.Attribute_Model;
import zhp.iyalee2.factorypattern.factories.interfaces.IFactory_Attribute_Model;

/**
 * 产生一个基本ARViewActivity的参数, 即{@linkplain Attribute_Model}
 * @author 郑海鹏
 * @since 2015年7月3日
 */
public class Factory_Attribute_Model_Single implements IFactory_Attribute_Model {

	@Override
	public Attribute_Model createAttribute(){
		final String trackingConfigFilePath = "single/TrackingData_MarkerlessFast.xml";
		ArrayList<GeometryModelData> geometryDatas = new ArrayList<GeometryModelData>();
		
		// 在这里添加新的模型，注意：如果要添加模型的话，记得在跟踪配置文件里添加响应项
		geometryDatas.add(new GeometryModelData(1, "single/containModel.zip", 1, new Rotation(90, 0, 0), null, R.raw.panda));
		
		Attribute_Model attribute = new Attribute_Model(geometryDatas, trackingConfigFilePath);
		return attribute;
	}
	
}
