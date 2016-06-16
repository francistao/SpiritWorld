package zhp.iyalee2.aractivities;

import zhp.iyalee2.aractivities.base.ModelARViewActivity;
import zhp.iyalee2.factorypattern.factories.Factory_Attribute_Model_Building;
import android.os.Bundle;

/**
 * 静态房屋模型
 * @author 郑海鹏
 * @since 2015年7月4日
 */
public class ARActivity_Buildings extends ModelARViewActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		attribute = new Factory_Attribute_Model_Building().createAttribute();
	}

}
