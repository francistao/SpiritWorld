package zhp.iyalee2;

import zhp.android.activities.SlidingFinishActionBarActivity;
import zhp.android.handlers.StrategyHandler;
import zhp.android.strategies.IStrategy;
import zhp.iyalee2.utils.Utils_AddFriend;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class AddFriend extends SlidingFinishActionBarActivity implements OnClickListener{

	StrategyHandler handler = new StrategyHandler();
	EditText et_userName, et_password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout layout = new LinearLayout(this);
		layout.setBackgroundColor(Color.rgb(200, 200, 200));
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
		
		et_userName = new EditText(this);
		et_userName.setHint("请在这里输入用户名");
		layout.addView(et_userName);
		et_userName.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
		
		et_password = new EditText(this);
		et_password.setHint("请在这里输入好友名");
		layout.addView(et_password);
		et_password.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
		
		Button btn = new Button(this);
		btn.setText("添加");
		layout.addView(btn);
		btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		String userName = et_userName.getText().toString();
		String friendName = et_password.getText().toString();
		
		IStrategy ifSuccess = new IStrategy() {
			
			@Override
			public void run() {
				android.util.Log.i("郑海鹏", "AddFriend#run(): " + "添加好友成功！");
				Toast.makeText(getApplication(), "添加好友成功！", Toast.LENGTH_LONG).show();
			}
		};
		
		new Utils_AddFriend(handler).addFriend(getApplication(), userName, friendName, ifSuccess);
	}
	
}
