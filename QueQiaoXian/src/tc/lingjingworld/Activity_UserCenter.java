package tc.lingjingworld;

import zhp.android.activities.SlidingFinishActionBarActivity;
import zhp.android.data.CurrentUser;
import zhp.android.data.FinalValue;
import zhp.android.data.Screen;
import zhp.android.handlers.StrategyHandler;
import zhp.android.utils.Utils_Activity;
import zhp.android.utils.Utils_Http;
import zhp.android.utils.Utils_SaveBitmap;
import zhp.iyalee2.Activity_FeedBack;
import zhp.iyalee2.Activity_UpdateProfile;
import zhp.iyalee2.ChangePassword;
import zhp.iyalee2.R;
import zhp.iyalee2.utils.Utils_GetUserIcon;
import zhp.iyalee2.utils.Utils_GetUserProfile;
import zhp.iyalee2.utils.Utils_UploadIcon;
import zhp.java.listeners.OnFinishListener;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_UserCenter extends SlidingFinishActionBarActivity implements OnClickListener{
	private Toolbar toolbar;
	private Button btn_setIcon, btn_changePassword, btn_feedback, btn_exitAccount, btn_updateProfile;
	private ImageView imageView;
	private TextView tv_profile;
	private StrategyHandler handler = new StrategyHandler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_center);
		initViews();
		
		if(CurrentUser.isLogin){
			// 加载头像
			Utils_GetUserIcon utils = new Utils_GetUserIcon(){
				
				@Override
				public void onDownloadFinish(Bitmap bitmap) {
					if(imageView != null && bitmap != null){
						imageView.setImageBitmap(bitmap);
					}
				}

				@Override
				public void onDownloadFail() {
				}
			};
			utils.getUserIcon(CurrentUser.getUserName());
		}
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if (CurrentUser.isLogin) {
			// 加载签名
			Utils_GetUserProfile utils2 = new Utils_GetUserProfile() {

				@Override
				public void onGetProfileFinish(String profile) {
					if (tv_profile != null) {
						tv_profile.setText(profile);
					}
				}
			};
			utils2.getUserProfile(CurrentUser.getUserName());
		}
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.usercenter_setIcon:
			onSetIconClick();
			break;

		case R.id.usercenter_exitAccount:
			onExitAccountClick();
			break;
			
		case R.id.usercenter_changePassword:
			onChangePasswordClick();
			break;
			
		case R.id.usercenter_feedback:
			startActivity(new Intent(this, Activity_FeedBack.class));
			break;
			
		case R.id.usercenter_updateProfile:
			onUpdateProfileClick();
			break;
			
		default:
			break;
		}
	}
	
	private void initViews() {
		initToolbar();
		btn_setIcon = (Button) findViewById(R.id.usercenter_setIcon);
		btn_changePassword = (Button) findViewById(R.id.usercenter_changePassword);
		btn_exitAccount = (Button) findViewById(R.id.usercenter_exitAccount);
		btn_feedback = (Button) findViewById(R.id.usercenter_feedback);
		btn_updateProfile = (Button) findViewById(R.id.usercenter_updateProfile);
		imageView = (ImageView) findViewById(R.id.usercenter_imageView);
		tv_profile = (TextView) findViewById(R.id.usercenter_textview_profile);
		setImageViewProperties();
		
		imageView.setOnClickListener(this);
		btn_changePassword.setOnClickListener(this);
		btn_exitAccount.setOnClickListener(this);
		btn_setIcon.setOnClickListener(this);
		btn_feedback.setOnClickListener(this);
		btn_updateProfile.setOnClickListener(this);
	}

	private void initToolbar() {
		toolbar = (Toolbar) findViewById(R.id.activity_usercenter_toolBar);
		
		String title = "设置";
		if(CurrentUser.isLogin){
			title = CurrentUser.getUserName() + "的用户中心";
		}
		
		toolbar.setTitle(title);
		toolbar.setTitleTextColor(Color.rgb(255, 255, 255));
		if(toolbar != null){
			setSupportActionBar(toolbar);
		}		
		Utils_Activity.getInstance().setToolBarBellowStateBar(this, toolbar);
		Utils_Activity.getInstance().setStateBarColor(this, getResources().getColor(R.color.colorPrimaryDark));
	}
	
	/**
	 * 设置头像的参数
	 */
	private void setImageViewProperties(){
		Screen.initScreenMsg(this);
		imageView.getLayoutParams().height = (int) Screen.perHeight(30);
	}

	// ---------------------------------------------------------------------------
	// 点击事件的处理
	// ---------------------------------------------------------------------------
	/**
	 * 点击了设置头像按钮
	 */
	private void onSetIconClick(){
		if(CurrentUser.isLogin){
			Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
			intent.setType("image/*");
			intent.addCategory(Intent.CATEGORY_OPENABLE);
			try {
				startActivityForResult(Intent.createChooser(intent, "请选择新的头像"), 100);
			} catch (android.content.ActivityNotFoundException ex) {
				Toast.makeText(this, "无法找到文件管理器", Toast.LENGTH_SHORT).show();
			}
		}else{
			Toast.makeText(this, "您还没有登录！", Toast.LENGTH_SHORT).show();
		}
	}
	
	private void onUpdateProfileClick() {
		if(CurrentUser.isLogin){
			startActivity(new Intent(this, Activity_UpdateProfile.class));
		}else{
			Toast.makeText(this, "您还没有登录！", Toast.LENGTH_SHORT).show();
		}
	}
	
	/**
	 * 选择图片后返回
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == Activity.RESULT_OK){
			// 获得选中文件的路径
			Uri uri = data.getData();
			Utils_Http utils = new Utils_Http();
			// 选中的文件路径
			String filePath = utils.getRealFilePath(this, uri);
			if(null == filePath){
				zhp.android.debug.Debug.Log(this.getClass().getName(), "filePath == null !");
				return;
			}
			
			Bitmap icon = new zhp.android.utils.Utils_TLCBitmapLoader().loadBitmapLocal(filePath, 400, 400);
			imageView.setImageBitmap(icon);
			
			final String targetSavePath = FinalValue.FOLDER_BASE_PATH + "arworld/icon/" + System.currentTimeMillis() + ".jpg";			
			new Utils_SaveBitmap().saveFileAsJpg(filePath, 400, 400, targetSavePath, new OnFinishListener() {
				
				@Override
				public void onFinish() {
					Utils_UploadIcon utils = new Utils_UploadIcon(handler);
					utils.uploadIcon(getApplication(), CurrentUser.getUserName(), CurrentUser.getPassword(), targetSavePath, null);
				}
			});
		}
	}
	
	/**
	 * 单击了退出账户按钮
	 */
	private void onExitAccountClick(){
		if(CurrentUser.isLogin){
			CurrentUser.exitAccount();
			finish();			
		}else{
			Toast.makeText(this, "您还没有登录！", Toast.LENGTH_SHORT).show();
		}
	}
	
	/**
	 * 点击了更换密码
	 */
	private void onChangePasswordClick(){
		if(CurrentUser.isLogin){
			Intent intent = new Intent(this, ChangePassword.class);
			startActivity(intent);
		}else{
			Toast.makeText(this, "您还没有登录！", Toast.LENGTH_SHORT).show();
		}
	}

}
