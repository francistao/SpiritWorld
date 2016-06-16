package zhp.iyalee2;

import zhp.android.handlers.StrategyHandler;
import zhp.android.strategies.IStrategy;
import zhp.iyalee2.utils.Utils_SignUp;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SignUpTest extends Activity implements OnClickListener{

	StrategyHandler handler = new StrategyHandler();
	EditText et;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout layout = new LinearLayout(this);
		layout.setBackgroundColor(Color.rgb(200, 200, 200));
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
		
		et = new EditText(this);
		et.setHint("请在这里输入用户名，密码自动设为123456");
		layout.addView(et);
		et.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
		
		Button btn = new Button(this);
		btn.setText("注册");
		layout.addView(btn);
		et.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
		btn.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v){
		android.util.Log.i("郑海鹏", "SignUpTest#onClick(): " + "按钮点击");
		String userName = et.getText().toString();
		
		IStrategy ifSuccess = new IStrategy() {
			
			@Override
			public void run() {
				Toast.makeText(getApplication(), "注册成功！", Toast.LENGTH_LONG).show();
			}
		};
		
		new Utils_SignUp(handler).signUp(getApplication(), userName, "123456", ifSuccess);
	}
	
}
