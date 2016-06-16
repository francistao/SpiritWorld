package tc.lingjingworld;

import java.util.Locale;

import zhp.android.activities.SlidingFinishActionBarActivity;
import zhp.android.data.FinalValue;
import zhp.android.utils.Utils_Activity;
import zhp.android.utils.Utils_File;
import zhp.android.utils.Utils_Http;
import zhp.android.utils.Utils_SaveBitmap;
import zhp.iyalee2.R;
import zhp.iyalee2.beans.ItemValue;
import zhp.iyalee2.beans.ItemValue.ContainType;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Activity_NewItem_Contain extends SlidingFinishActionBarActivity {
	private Button btn_choosesecond;
	private int REQUEST_CODE_FILE_CHOOSE = 100;
	private Toolbar toolbar;
	private String targetPath;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mycontent_add_newcontent_third);
		initView();
		targetPath = getIntent().getExtras().getString("targetPath");
	}

	private void initView() {
		initChooseButton();
		initToolbar();
	}

	private void initToolbar() {
		toolbar = (Toolbar) findViewById(R.id.activity_add_contain_toolBar);
		toolbar.setTitle("选择增强内容");
		toolbar.setTitleTextColor(Color.rgb(255, 255, 255));
		if(toolbar != null){
			setSupportActionBar(toolbar);
		}
		
		Utils_Activity.getInstance().setToolBarBellowStateBar(this, toolbar);
		Utils_Activity.getInstance().setStateBarColor(this, getResources().getColor(R.color.colorPrimaryDark));
	}

	private void initChooseButton() {
		btn_choosesecond = (Button) findViewById(R.id.btn_choosesecond);
		btn_choosesecond.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				choseFile();
			}
		});
	}

	private void choseFile() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		try {
			startActivityForResult(Intent.createChooser(intent, "请选择目标图片："), REQUEST_CODE_FILE_CHOOSE);
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(this, "找不到文件管理器", Toast.LENGTH_SHORT).show();
		}
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == Activity.RESULT_OK){
			// 获得选中文件的路径
			Uri uri = data.getData();
			Utils_Http utils = new Utils_Http();
			// 选中的文件路径
			final String filePath = utils.getRealFilePath(this, uri);
			if(null == filePath){
				zhp.android.debug.Debug.Log(this.getClass().getName(), "filePath == null !");
				return;
			}
			
			// 判断文件类型
			int pointIndex = filePath.lastIndexOf(".");
			String fileType = "jpg";
			if(-1 != pointIndex){
				fileType = filePath.substring(filePath.lastIndexOf(".") + 1);
				fileType = fileType.toLowerCase(Locale.ENGLISH);
			}
			
			// 由文件类型获得ContainType
			ContainType type = null;
			boolean isImage = fileType.equals("png") || fileType.equals("jpg") || fileType.equals("jpeg") || 
					fileType.equals("gif");
			if(isImage){
				type = ContainType.Picture;
			}else if(fileType.equals("3gp") || fileType.equals("3g2")){
				type = ContainType.Video;
			}else if(fileType.equals("zip")){
				type = ContainType.Model;
			}
			
			
			
			// 在新线程中保存文件
			final String containSavePath = FinalValue.FOLDER_BASE_PATH + "arworld/localItem/" + System.currentTimeMillis() + "." + fileType;
			
			final ItemValue item = new ItemValue(targetPath, "", "", containSavePath, type);
			
			new AsyncTask<Void, Void, Void>() {

				@Override
				protected void onPreExecute() {
					Toast.makeText(getApplication(), "文件检测中，请稍后！", Toast.LENGTH_LONG).show();
				}

				@Override
				protected Void doInBackground(Void... params) {
					if(item.getType() == ContainType.Picture){
						new Utils_SaveBitmap().saveFileAsJpg(filePath, 500, 500, containSavePath, null);
					}else{
						Utils_File.getInstance().copyFile(filePath, containSavePath);
					}					
					return null;
				}

				@Override
				protected void onPostExecute(Void result) {
					Intent intent = new Intent(Activity_NewItem_Contain.this, Activity_NewItem_TitleAndProfile.class);
					intent.putExtra("item", item);
					startActivity(intent);
					finish();
				}
			}.execute();
			
		}
	}
	
}
