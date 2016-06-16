package zhp.iyalee2;

import zhp.android.activities.SlidingFinishActionBarActivity;
import zhp.android.utils.Utils_Activity;
import zhp.iyalee2.utils.Thread_FeedBack;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Activity_FeedBack extends SlidingFinishActionBarActivity {
	RelativeLayout bgLayout;
	EditText et_contains;
	Toolbar toolbar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);
		
		bgLayout = (RelativeLayout) findViewById(R.id.activity_feedBack_bgLayout);
		et_contains = (EditText) findViewById(R.id.activity_feedBack_contains);
		
		toolbar = (Toolbar) findViewById(R.id.activity_feedBack_toolBar);
		toolbar.setTitle("意见反馈");
		toolbar.setTitleTextColor(Color.rgb(255, 255, 255));
		if(toolbar != null){
			setSupportActionBar(toolbar);
		}
		Utils_Activity.getInstance().setToolBarBellowStateBar(this, toolbar);
		Utils_Activity.getInstance().setStateBarColor(this, getResources().getColor(R.color.colorPrimaryDark));
	}
	
	/**
	 * 用户点击了完成按钮
	 */
	public void onFinishButtonClick(View view){
		// 获得输入的内容
		String contains = et_contains.getText().toString();
		
		// 在新线程中发送反馈
		new Thread_FeedBack(contains).start();
		Toast.makeText(getApplication(), "感谢您的反馈！", Toast.LENGTH_SHORT).show();
		finish();
	}
	
}
