package zhp.iyalee2.factorypattern.factories;

import java.util.ArrayList;

import com.metaio.sdk.jni.Rotation;
import com.metaio.sdk.jni.Vector3d;

import zhp.iyalee2.R;
import zhp.iyalee2.beans.modeldata.GeometryModelData;
import zhp.iyalee2.factorypattern.attributes.Attribute_Model;
import zhp.iyalee2.factorypattern.factories.interfaces.IFactory_Attribute_Model;

/**
 * 产生一个基本ARViewActivity的参数, 即{@linkplain Attribute_Model}
 * @author 郑海鹏
 * @since 2015年7月3日
 */
public class Factory_Attribute_Model_Animal implements IFactory_Attribute_Model {

	@Override
	public Attribute_Model createAttribute(){
		final String trackingConfigFilePath = "animals/TrackingData_MarkerlessFast.xml";
		ArrayList<GeometryModelData> geometryDatas = new ArrayList<GeometryModelData>();
		
		// 在这里添加新的模型，注意：如果要添加模型的话，记得在跟踪配置文件里添加响应项
		// panda scale = 1
		// fh = 6
		// lh = 35
		// hz = 25
		// 角度是按逆时针旋转的
		geometryDatas.add(new GeometryModelData(1, "animals/panda.zip", 1.5f, new Rotation((float)(Math.PI / 2.0), 0, 0), null, R.raw.panda));
		geometryDatas.add(new GeometryModelData(2, "animals/fh.zip", 5, new Rotation(0, 0, (float)(Math.PI)), new Vector3d(0, 90, 50), R.raw.anivia));
		geometryDatas.add(new GeometryModelData(3, "animals/hz.zip", 20, new Rotation((float)(Math.PI / 2.0), 0, (float)(-Math.PI / 2.0)), null, R.raw.monkey)); 
		geometryDatas.add(new GeometryModelData(4, "animals/lh.zip", 35, new Rotation((float)(Math.PI / 2.0), 0, (float)(Math.PI / 2.0)), null, R.raw.tiger)); 
//		geometryDatas.add(new GeometryModelData(4, "animals/building.zip", 1, null, null, R.raw.fenghuang));
		
		Attribute_Model attribute = new Attribute_Model(geometryDatas, trackingConfigFilePath);
		return attribute;
	}
	
}
