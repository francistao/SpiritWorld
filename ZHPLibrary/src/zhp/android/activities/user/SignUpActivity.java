package zhp.android.activities.user;

import zhp.android.classs.InputFilter_Black;
import zhp.android.classs.InputFilter_NumAndLetter;
import zhp.android.data.Screen;
import zhp.library.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * 用于登录的Activity
 * @author ZHP
 *
 */
public abstract class SignUpActivity extends AppCompatActivity {

	// ==========================================
	//	变量
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
	 * 文本编辑框，再次输入密码
	 */
	public EditText editText_ConfirmPaaword;
	
	/**
	 * 按钮，注册
	 */
	public Button button_SignUp;

	
	// ==========================================
	//	继承、实现的方法
	// ==========================================
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		setTitle(R.string.title_signup);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		bgLayout = (RelativeLayout) findViewById(R.id.library_signup_bgLayout);
		editText_UserName = (EditText) findViewById(R.id.library_signup_nickname);
		editText_Password = (EditText) findViewById(R.id.library_signup_password);
		editText_ConfirmPaaword = (EditText) findViewById(R.id.library_signup_confirm_password);
		button_SignUp = (Button) findViewById(R.id.library_signup_signup);
		
		Screen.initScreenMsg(this);
		editText_UserName.getLayoutParams().width = (int) Screen.perWidth(50);
		editText_Password.getLayoutParams().width = (int) Screen.perWidth(50);
		editText_ConfirmPaaword.getLayoutParams().width = (int) Screen.perWidth(50);
		button_SignUp.getLayoutParams().width = (int) Screen.perWidth(50);

		// 用户名中不能输入以下字符
		char[] blackList = { '/', '<', '>' };
		InputFilter_Black blackFilter = new InputFilter_Black(blackList);
		editText_UserName.setFilters(new InputFilter[] { blackFilter });

		// 密码中只能输入数字和字母
		editText_Password.setFilters(new InputFilter[] { new InputFilter_NumAndLetter() });
	}


	// ==========================================
	// 方法
	// ==========================================
	/**
	 * @return 获得输入的昵称
	 */
	public String getNickName(){
		return editText_UserName.getText().toString();
	}
	
	/**
	 * 获得输入的密码, 返回null时表示两次输入的密码不相同
	 */
	public String getPassword(){
		String psw1  = editText_Password.getText().toString();
		String psw2 = editText_ConfirmPaaword.getText().toString();
		if(psw1.equals(psw2)){
			return psw1;
		}else{
			Toast.makeText(this, getResources().getString(R.string.two_psw_diffrent), Toast.LENGTH_LONG).show();
			return null;
		}
	}
	
	/**
	 * 点击注册按钮之后要执行的
	 */
	public abstract void onSignUpButtonClick(View view);

	
}
