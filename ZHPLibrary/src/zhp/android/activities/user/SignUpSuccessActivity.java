package zhp.android.activities.user;

import zhp.library.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * 注册成功显示的界面
 * @author 郑海鹏
 * @since 2015/2/28 22:38
 */
public abstract class SignUpSuccessActivity extends AppCompatActivity {
	
	protected TextView tv_content;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup_success);
		
		tv_content = (TextView) findViewById(R.id.signUpSuccess_content);
		
		setTitle(R.string.title_signup_success);
	}
	
	/**
	 * 当点击了登录
	 */
	public abstract void onLoginClick(View v);
	
}
