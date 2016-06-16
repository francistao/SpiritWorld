package tc.lingjingworld;

import zhp.android.activities.SlidingFinishActionBarActivity;
import zhp.android.classs.InputFilter_Black;
import zhp.android.classs.InputFilter_NumAndLetter;
import zhp.android.handlers.StrategyHandler;
import zhp.android.strategies.IStrategy;
import zhp.android.utils.Utils_Activity;
import zhp.iyalee2.R;
import zhp.iyalee2.utils.Utils_SignUp;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_Regist extends SlidingFinishActionBarActivity implements OnClickListener {
	private Button btn_signUp;	
	private EditText et_userName, et_password1, et_password2;
	private Toolbar toolbar;
	
	private StrategyHandler handler = new StrategyHandler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friendlist_regist);
		initView();
	}

	private void initView() {
		initToolbar();
		btn_signUp = (Button) findViewById(R.id.btn_regist);
		btn_signUp.setOnClickListener(this);
		
		// 用户名及输入黑名单
		et_userName = (EditText) findViewById(R.id.signUp_userName);
		InputFilter_Black filter = new InputFilter_Black(new char[] { '<', '>', '/' });
		et_userName.setFilters(new InputFilter_Black[] { filter });

		// 密码
		et_password1 = (EditText) findViewById(R.id.signUp_password1);
		et_password2 = (EditText) findViewById(R.id.signUp_password2);
		et_password1.setFilters(new InputFilter_NumAndLetter[] { new InputFilter_NumAndLetter() });
	}
	
	private void initToolbar() {
		toolbar = (Toolbar) findViewById(R.id.activity_signup_toolBar);
		toolbar.setTitle("注册");
		toolbar.setTitleTextColor(Color.rgb(255, 255, 255));
		if(toolbar != null){
			setSupportActionBar(toolbar);
		}
		Utils_Activity.getInstance().setToolBarBellowStateBar(this, toolbar);
		Utils_Activity.getInstance().setStateBarColor(this, getResources().getColor(R.color.colorPrimaryDark));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_regist:
			signUp();
			break;

		default:
			break;
		}
	}
	
	private void signUp(){
		String userName = et_userName.getText().toString();
		String password1 = et_password1.getText().toString();
		String password2 = et_password2.getText().toString();
		
		if(password1 == null || password1.length() < 4){
			Toast.makeText(getApplication(), "您输入的密码长度太短，请重新输入。", Toast.LENGTH_LONG).show();
			return;
		}
		
		if(password1.length() > 16){
			Toast.makeText(getApplication(), "您输入的密码长度大于16位，请重新输入。", Toast.LENGTH_LONG).show();
			return;
		}
		
		if(password1.equals(password2)){
			IStrategy ifSuccess = new IStrategy() {
				
				@Override
				public void run() {
					Toast.makeText(getApplication(), "注册成功！", Toast.LENGTH_SHORT).show();
					finish();
				}
			};
			new Utils_SignUp(handler).signUp(getApplication(), userName, password1, ifSuccess);
		}else{
			Toast.makeText(this, "两次输入的密码不相同！", Toast.LENGTH_SHORT).show();
		}
		
	}
}
