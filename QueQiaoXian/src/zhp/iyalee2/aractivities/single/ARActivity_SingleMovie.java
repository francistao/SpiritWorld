package zhp.iyalee2.aractivities.single;

import zhp.iyalee2.aractivities.base.MovieARViewActivity;
import zhp.iyalee2.factorypattern.factories.single.Factory_Attribute_Movie_Single;
import android.os.Bundle;

/**
 * 单独打开一个视频
 * @author 郑海鹏
 * @since 2015年7月4日
 */
public class ARActivity_SingleMovie extends MovieARViewActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		attribute = new Factory_Attribute_Movie_Single().createAttribute();
	}

}
