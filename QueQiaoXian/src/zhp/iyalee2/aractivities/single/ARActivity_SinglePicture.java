package zhp.iyalee2.aractivities.single;

import zhp.iyalee2.aractivities.base.PictureARViewActivity;
import zhp.iyalee2.factorypattern.factories.single.Factory_Attribute_Picture_Single;
import android.os.Bundle;

/**
 * 单独打开一个图片
 * @author 郑海鹏
 * @since 2015年7月4日
 */
public class ARActivity_SinglePicture extends PictureARViewActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		attribute = new Factory_Attribute_Picture_Single().createAttribute();
	}

}
