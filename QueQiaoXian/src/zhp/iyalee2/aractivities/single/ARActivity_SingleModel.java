package zhp.iyalee2.aractivities.single;

import zhp.iyalee2.aractivities.base.ModelARViewActivity;
import zhp.iyalee2.factorypattern.factories.single.Factory_Attribute_Model_Single;
import android.os.Bundle;

/**
 * 单独的模型
 * @author 郑海鹏
 * @since 2015年7月4日
 */
public class ARActivity_SingleModel extends ModelARViewActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		attribute = new Factory_Attribute_Model_Single().createAttribute();
	}

}
