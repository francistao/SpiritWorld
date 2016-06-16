package zhp.iyalee2;

import zhp.android.activities.SlidingFinishActionBarActivity;
import zhp.android.data.CurrentUser;
import zhp.android.utils.Utils_Activity;
import zhp.iyalee2.utils.Utils_UpdateUserProfile;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_UpdateProfile extends SlidingFinishActionBarActivity {
	Button btn_confirm;
	Toolbar toolbar;
	EditText et_profile;
	String newProfile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_profile);
		findViews();
		initToolBar();
	}

	/**
	 * 找到Views
	 */
	private void findViews() {
		btn_confirm = (Button) findViewById(R.id.activity_updateProfile_confirm);
		toolbar = (Toolbar) findViewById(R.id.activity_updateProfile_toolBar);
		et_profile = (EditText) findViewById(R.id.activity_updateProfile_profile);
	}

	private void initToolBar() {
		toolbar.setTitle("修改签名"); // 需要在setSupportActionBar()之前
		toolbar.setTitleTextColor(Color.rgb(255, 255, 255));
		if (toolbar != null) {
			setSupportActionBar(toolbar);
		}
		Utils_Activity.getInstance().setToolBarBellowStateBar(this, toolbar);
		Utils_Activity.getInstance().setStateBarColor(this,
				getResources().getColor(R.color.colorPrimaryDark));
	}

	public void onConfirmClick(View view) {
		newProfile = et_profile.getText().toString();
		// 如果长度合法
		if (newProfile != null) {
			btn_confirm.setEnabled(false);
			new Utils_UpdateUserProfile(){

				@Override
				public void whenCannotConnToServer() {
					Toast.makeText(getApplication(), "无法连接服务器！", Toast.LENGTH_SHORT).show();
					btn_confirm.setEnabled(true);
				}

				@Override
				public void whenUpdateFinish() {
					Toast.makeText(getApplication(), "修改完成！", Toast.LENGTH_SHORT).show();
					btn_confirm.setEnabled(true);
				}
				
			}.update(CurrentUser.getUserName(), CurrentUser.getPassword(), newProfile);
		}
	}

}
