package zhp.android.activities.user;

import zhp.android.classs.InputFilter_Black;
import zhp.android.classs.InputFilter_NumAndLetter;
import zhp.android.data.Screen;
import zhp.library.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

/**
 * 用户登录类
 * 
 * @author 郑海鹏
 * @since 2015/3/29
 */
public abstract class LoginActivity extends AppCompatActivity {

	// ==========================================
	// 变量
	// ==========================================
	/**
	 * 最底层布局
	 */
	public RelativeLayout bgLayout;

	/**
	 * 文本编辑框，用户名
	 */
	public EditText editText_UserName;

	/**
	 * 文本编辑框，密码
	 */
	public EditText editText_Password;

	/**
	 * 按钮，登录
	 */
//	public Button button_logIn;

	// ==========================================
	// 继承、实现的方法
	// ==========================================
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_log_in);
		setTitle(R.string.title_login);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		bgLayout = (RelativeLayout) findViewById(R.id.library_logIn_bgLayout);
		editText_UserName = (EditText) findViewById(R.id.library_logIn_userName);
		editText_Password = (EditText) findViewById(R.id.library_logIn_password);
//		button_logIn = (Button) findViewById(R.id.library_logIn_logIn);
		editText_UserName.getLayoutParams().width = (int) Screen.perWidth(50);
		editText_Password.getLayoutParams().width = (int) Screen.perWidth(50);
//		button_logIn.getLayoutParams().width = (int) Screen.perWidth(50);

		// 用户名中不能输入以下字符
		char[] blackList = { '/', '[', ']' };
		InputFilter_Black blackFilter = new InputFilter_Black(blackList);
		editText_UserName.setFilters(new InputFilter[] { blackFilter });

		// 密码中只能输入数字和字母
		editText_Password.setFilters(new InputFilter[] { new InputFilter_NumAndLetter() });
	}
	
	// ==========================================
	// 方法
	// ==========================================
	/**
	 * @return 获得输入的用户名
	 */
	public String getUserName(){
		return editText_UserName.getText().toString();
	}
	
	/**
	 * 获得输入的密码
	 */
	public String getPassword(){
		return editText_Password.getText().toString();
	}

	/**
	 * 点击登录按钮之后要执行的方法
	 */
	public abstract void onLogInButtonClick(View v);
	
	/**
	 * 点击新用户注册要执行的方法
	 */
	public abstract void onNewUserSignUpClick(View v);
}
