package tc.lingjingworld;

import zhp.android.activities.SlidingFinishActionBarActivity;
import zhp.android.data.CurrentUser;
import zhp.android.data.Screen;
import zhp.android.handlers.StrategyHandler;
import zhp.android.strategies.IStrategy;
import zhp.iyalee2.R;
import zhp.iyalee2.utils.Utils_LogIn;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Activity_Login extends SlidingFinishActionBarActivity implements OnClickListener {
	private Button btn_login;
	private Button btn_regist;
	private EditText et_password;
	private EditText et_username;
	
	private ImageView cover;
	
	private StrategyHandler handler = new StrategyHandler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
	}

	private void initView() {
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_regist = (Button) findViewById(R.id.btn_regist);
		btn_login.setOnClickListener(this);
		btn_regist.setOnClickListener(this);
		
		et_username = (EditText) findViewById(R.id.login_username);
		et_password = (EditText) findViewById(R.id.login_password);
		
		SharedPreferences sp = Activity_Login.this.getPreferences(Context.MODE_PRIVATE);
		String userName = sp.getString("userName", "");
		String password = sp.getString("password", "");
		et_username.setText(userName);
		et_password.setText(password);
		
		cover = (ImageView) findViewById(R.id.login_cover); 
		cover.getLayoutParams().width = Screen.getWidth();
		cover.getLayoutParams().height = (int) (Screen.getWidth() * 0.75f);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login:
			String userName = et_username.getText().toString();
			String password = et_password.getText().toString();
			btn_login.setClickable(false);
			logIn(userName, password);
			break;
		case R.id.btn_regist:
			startActivity(new Intent(Activity_Login.this, Activity_Regist.class));
			break;
		default:
			break;
		}
	}
	
	private void logIn(final String userName, final String password){
		IStrategy logInSuccess = new IStrategy() {
			
			@Override
			public void run() {
				Toast.makeText(getApplication(), "登录成功！", Toast.LENGTH_SHORT).show();
				CurrentUser.setUserName(userName);
				CurrentUser.setPassword(password);
				
				new AsyncTask<Void, Void, Void>() {

					@Override
					protected Void doInBackground(Void... params) {
						SharedPreferences sp = Activity_Login.this.getPreferences(Context.MODE_PRIVATE);
						SharedPreferences.Editor editor = sp.edit();
						editor.putString("userName", userName);
						editor.putString("password", password);
						editor.commit();
						return null;
					}
				}.execute();
				
				Intent intent = new Intent(Activity_Login.this, Activity_MyFriends.class);
				startActivity(intent);
				Activity_Login.this.finish();
			}
		};
		new Utils_LogIn(handler).logIn(getApplication(), userName, password, btn_login, logInSuccess);
	}
	
}
