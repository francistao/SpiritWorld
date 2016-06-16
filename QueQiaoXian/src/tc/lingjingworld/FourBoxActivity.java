package tc.lingjingworld;

import zhp.android.activities.SlidingFinishActionBarActivity;
import zhp.android.utils.Utils_Activity;
import zhp.iyalee2.R;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

/**
 * @author 郑海鹏
 * @since 2015年6月27日
 */
public class FourBoxActivity extends SlidingFinishActionBarActivity {
	protected LinearLayout row1_col1, row1_col2;
	protected LinearLayout row2_col1, row2_col2;
	
	Toolbar toolbar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_empty_with_toolbar_nine_box);
		findViews();
		initToolbar();
	}


	protected void initToolbar() {
		toolbar.setTitleTextColor(Color.rgb(255, 255, 255));
		if(toolbar != null){
			setSupportActionBar(toolbar);
		}
		Utils_Activity.getInstance().setToolBarBellowStateBar(this, toolbar);
		Utils_Activity.getInstance().setStateBarColor(this, getResources().getColor(R.color.colorPrimaryDark));
	}

	protected void findViews() {
		toolbar = (Toolbar) findViewById(R.id.activity_empty_toolBar);
		row1_col1 = (LinearLayout) findViewById(R.id.activity_empty_row1_col1);
		row1_col2 = (LinearLayout) findViewById(R.id.activity_empty_row1_col2);
		row2_col1 = (LinearLayout) findViewById(R.id.activity_empty_row2_col1);
		row2_col2 = (LinearLayout) findViewById(R.id.activity_empty_row2_col2);
	}
}
