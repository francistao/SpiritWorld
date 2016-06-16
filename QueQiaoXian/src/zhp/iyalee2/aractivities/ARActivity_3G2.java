package zhp.iyalee2.aractivities;

import zhp.iyalee2.aractivities.base.MovieARViewActivity;
import zhp.iyalee2.factorypattern.factories.Factory_Attribute_Movie_3G2;
import android.os.Bundle;

/**
 * 静态动物模型
 * @author 郑海鹏
 * @since 2015年7月4日
 */
public class ARActivity_3G2 extends MovieARViewActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		attribute = new Factory_Attribute_Movie_3G2().createAttribute();
	}

}
