package zhp.iyalee2;

import zhp.android.activities.SlidingFinishActionBarActivity;
import zhp.android.handlers.StrategyHandler;
import zhp.android.strategies.IStrategy;
import zhp.iyalee2.utils.Utils_UploadIcon;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class UploadIcon extends SlidingFinishActionBarActivity implements OnClickListener{

	StrategyHandler handler = new StrategyHandler();
//	EditText et_userName, et_password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout layout = new LinearLayout(this);
		layout.setBackgroundColor(Color.rgb(200, 200, 200));
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
		
//		et_userName = new EditText(this);
//		et_userName.setHint("请在这里输入用户名");
//		layout.addView(et_userName);
//		et_userName.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
//		
//		et_password = new EditText(this);
//		et_password.setHint("请在这里输入好友名");
//		layout.addView(et_password);
//		et_password.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
		
		Button btn = new Button(this);
		btn.setText("添加");
		layout.addView(btn);
		btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) { 

//		String userName = et_userName.getText().toString();
//		String friendName = et_password.getText().toString();
		
		IStrategy ifSuccess = new IStrategy() {
			
			@Override
			public void run() {
				Toast.makeText(getApplication(), "上传头像成功！", Toast.LENGTH_LONG).show();
			}
		};
		
		new Utils_UploadIcon(handler).uploadIcon(getApplication(), "zhp", "123456", zhp.android.data.FinalValue.FOLDER_BASE_PATH + "arworld/a.jpg", ifSuccess);
	}
	
}
