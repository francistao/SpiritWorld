package zhp.iyalee2;

import zhp.android.activities.SlidingFinishActionBarActivity;
import zhp.android.classs.InputFilter_NumAndLetter;
import zhp.android.data.CurrentUser;
import zhp.android.utils.Utils_Activity;
import zhp.iyalee2.utils.Thread_ChangePassword;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends SlidingFinishActionBarActivity {
	public final static int CHANGE_SUCCESS = 201;
	public final static int CHANGE_FAIL = 202;
	public final static int CANNOT_CONN_SERVER = 203;
	public final static int UNKNOW_RESULT = 204;

	Handler handler;
	Button btn_confirm;
	Toolbar toolbar;
	EditText et_oldPassword, et_newPassword;
	String oldPassword, newPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_changepassword);

		findViews();

		et_oldPassword
				.setFilters(new InputFilter_NumAndLetter[] { new InputFilter_NumAndLetter() });
		et_newPassword
				.setFilters(new InputFilter_NumAndLetter[] { new InputFilter_NumAndLetter() });

		initToolBar();
		initHandler();

		Utils_Activity.getInstance().setToolBarBellowStateBar(this, toolbar);
		Utils_Activity.getInstance().setStateBarColor(this,
				getResources().getColor(R.color.colorPrimaryDark));
	}

	/**
	 * 找到Views
	 */
	private void findViews() {
		btn_confirm = (Button) findViewById(R.id.activity_changePassword_confirm);
		toolbar = (Toolbar) findViewById(R.id.activity_changePassword_toolBar);
		et_oldPassword = (EditText) findViewById(R.id.activity_changePassword_oldPassword);
		et_newPassword = (EditText) findViewById(R.id.activity_changePassword_newPassword);
	}

	private void initToolBar() {
		toolbar.setTitle("修改密码"); // 需要在setSupportActionBar()之前
		toolbar.setTitleTextColor(Color.rgb(255, 255, 255));
		if (toolbar != null) {
			setSupportActionBar(toolbar);
		}
	}

	private void initHandler() {
		handler = new Handler(new Handler.Callback() {

			@Override
			public boolean handleMessage(Message msg) {
				switch (msg.what) {
				case ChangePassword.CHANGE_SUCCESS:
					// 如果修改成功
					Toast.makeText(ChangePassword.this, "修改成功", Toast.LENGTH_LONG).show();
					btn_confirm.setEnabled(true);
					// 设置CurrentUser的密码
					CurrentUser.setPassword(newPassword);
					finish();
					break;

				case ChangePassword.CHANGE_FAIL:
					Toast.makeText(ChangePassword.this, "修改失败", Toast.LENGTH_LONG).show();
					btn_confirm.setEnabled(true);
					break;

				case ChangePassword.CANNOT_CONN_SERVER:
					Toast.makeText(ChangePassword.this, "无法连接服务器", Toast.LENGTH_LONG).show();
					btn_confirm.setEnabled(true);
					break;

				case ChangePassword.UNKNOW_RESULT:
					Toast.makeText(ChangePassword.this, "发生未知错误", Toast.LENGTH_LONG).show();
					btn_confirm.setEnabled(true);
					break;

				default:
					zhp.android.debug.Debug.Log(this.getClass().getName(),
							"ChangePassword handler 有未处理的消息！");
					break;
				}
				return false;
			}
		});
	}

	public void onConfirmClick(View view) {
		oldPassword = et_oldPassword.getText().toString();
		newPassword = et_newPassword.getText().toString();
		// 如果长度合法
		boolean isOldPasswordLengthCorrect = oldPassword.length() > 0 && oldPassword.length() < 17;
		boolean isNewPasswordLengthCorrect = newPassword.length() > 0 && newPassword.length() < 17;
		if (isOldPasswordLengthCorrect && isNewPasswordLengthCorrect) {
			btn_confirm.setEnabled(false);
			new Thread_ChangePassword(handler, CurrentUser.getUserName(), oldPassword, newPassword)
					.start();
		} else {
			Toast.makeText(this, "密码长度不符合要求", Toast.LENGTH_LONG).show();
		}
	}

}
