package zhp.iyalee2.aractivities;

import zhp.iyalee2.aractivities.base.ModelARViewActivity;
import zhp.iyalee2.factorypattern.factories.Factory_Attribute_Model_Animal;
import android.os.Bundle;

/**
 * 静态动物模型
 * @author 郑海鹏
 * @since 2015年7月4日
 */
public class ARActivity_Animals extends ModelARViewActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		attribute = new Factory_Attribute_Model_Animal().createAttribute();
	}

}
