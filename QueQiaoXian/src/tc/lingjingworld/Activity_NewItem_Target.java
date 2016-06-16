package tc.lingjingworld;

import zhp.android.activities.SlidingFinishActionBarActivity;
import zhp.android.data.FinalValue;
import zhp.android.utils.Utils_Activity;
import zhp.android.utils.Utils_Http;
import zhp.android.utils.Utils_SaveBitmap;
import zhp.iyalee2.R;
import zhp.java.listeners.OnFinishListener;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Activity_NewItem_Target extends SlidingFinishActionBarActivity {
	private Button btnChooseFirst;
	private Toolbar toolbar;
	private int REQUEST_CODE_FILE_CHOOSE = 100;

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.mycontent_add_newcontent_first);
		initViews();
	}

	private void initViews() {
		initChooseButton();
		initToolbar();
	}

	private void initToolbar() {
		toolbar = (Toolbar) findViewById(R.id.activity_add_target_toolBar);
		toolbar.setTitle("选择目标图片");
		toolbar.setTitleTextColor(Color.rgb(255, 255, 255));
		if(toolbar != null){
			setSupportActionBar(toolbar);
		}
		Utils_Activity.getInstance().setToolBarBellowStateBar(this, toolbar);
		Utils_Activity.getInstance().setStateBarColor(this, getResources().getColor(R.color.colorPrimaryDark));		
	}

	private void initChooseButton() {
		btnChooseFirst = ((Button) findViewById(R.id.btn_choosefirst));
		btnChooseFirst.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				choseTarget();
			}
		});
	}

	/**
	 * 选择目标图片
	 */
	private void choseTarget(){
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		try {
			startActivityForResult(Intent.createChooser(intent, "请选择目标图片："), REQUEST_CODE_FILE_CHOOSE);
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(this, "找不到文件管理器", Toast.LENGTH_SHORT).show();
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
			
			// 在新线程中保存图像
			final String targetSavePath = FinalValue.FOLDER_BASE_PATH + "arworld/localItem/" + System.currentTimeMillis() + ".jpg";			
			new Utils_SaveBitmap().saveFileAsJpg(filePath, 200, 200, targetSavePath, new OnFinishListener() {
				
				@Override
				public void onFinish() {
					Toast.makeText(getApplication(), targetSavePath, Toast.LENGTH_LONG).show();
					Intent intent = new Intent(Activity_NewItem_Target.this, Activity_NewItem_Contain.class);
					intent.putExtra("targetPath", targetSavePath);
					startActivity(intent);
					finish();
				}
			});
		}
	}

}
