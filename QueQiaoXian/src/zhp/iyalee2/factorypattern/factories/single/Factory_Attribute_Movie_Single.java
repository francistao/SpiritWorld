package zhp.iyalee2.factorypattern.factories.single;

import java.util.ArrayList;

import com.metaio.sdk.jni.Rotation;

import zhp.iyalee2.beans.modeldata.GeometryMovieData;
import zhp.iyalee2.factorypattern.attributes.Attribute_Movie;
import zhp.iyalee2.factorypattern.factories.interfaces.IFactory_Attribute_Movie;

/**
 * 产生一个视频ARViewActivity的参数, 即{@linkplain Attribute_Movie}
 * @author 郑海鹏
 * @since 2015年7月3日
 */
public class Factory_Attribute_Movie_Single implements IFactory_Attribute_Movie {

	@Override
	public Attribute_Movie createAttribute(){
		final String trackingConfigFilePath = "single/TrackingData_MarkerlessFast.xml";
		ArrayList<GeometryMovieData> geometryDatas = new ArrayList<GeometryMovieData>();
		
		// 在这里添加新的模型，注意：如果要添加模型的话，记得在跟踪配置文件里添加响应项
		geometryDatas.add(new GeometryMovieData(1, "single/containMovie.3gp", 3, new Rotation(0, 0, (float) -Math.PI / 2), null, false));
		
		Attribute_Movie attribute = new Attribute_Movie(geometryDatas, trackingConfigFilePath);
		return attribute;
	}
	
}
