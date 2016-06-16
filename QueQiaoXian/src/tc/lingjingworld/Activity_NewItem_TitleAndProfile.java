package tc.lingjingworld;

import zhp.android.activities.SlidingFinishActionBarActivity;
import zhp.android.utils.Utils_Activity;
import zhp.iyalee2.R;
import zhp.iyalee2.beans.ItemValue;
import zhp.iyalee2.broadcastreciver.BroadcastReceiver_CloaseActivity;
import zhp.iyalee2.database.DBOperation_LocalItem;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**'
 * 添加新项目——输入简介和Item名称
 * @since 2015年9月18日
 */
public class Activity_NewItem_TitleAndProfile extends SlidingFinishActionBarActivity implements View.OnClickListener{
	private Button btn_submit;
	EditText et_title, et_profile;
	ItemValue item;
	Toolbar toolbar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mycontent_add_new_message);
		item = (ItemValue) getIntent().getExtras().get("item");
		initViews();
	}

	private void initViews() {
		initToolbar();
		et_profile = (EditText) findViewById(R.id.et_input_profile);
		et_title = (EditText) findViewById(R.id.et_input_title);		
		btn_submit = (Button) findViewById(R.id.btn_submit);
		btn_submit.setOnClickListener(this);
	}

	private void initToolbar() {
		toolbar = (Toolbar) findViewById(R.id.activity_add_title_toolBar);
		toolbar.setTitle("名称和简介");
		toolbar.setTitleTextColor(Color.rgb(255, 255, 255));
		if(toolbar != null){
			setSupportActionBar(toolbar);
		}
		
		Utils_Activity.getInstance().setToolBarBellowStateBar(this, toolbar);
		Utils_Activity.getInstance().setStateBarColor(this, getResources().getColor(R.color.colorPrimaryDark));		
	}

	@Override
	public void onClick(View v) {
		Toast.makeText(this, "正在生成项目…", Toast.LENGTH_SHORT).show();
		String title = et_title.getText().toString();
		String profile = et_profile.getText().toString();
		item.setTitle(title);
		item.setProfile(profile);
		
		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				DBOperation_LocalItem dbo = new DBOperation_LocalItem(Activity_NewItem_TitleAndProfile.this);
				dbo.openDataBase();
				dbo.addItem(item);
				dbo.closeDataBase();
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				sendBroadcast(new Intent(BroadcastReceiver_CloaseActivity.CLOSE_ACTIVITY));
				startActivity(new Intent(Activity_NewItem_TitleAndProfile.this, Activity_MyItems.class));
				finish();
			}
			
		}.execute();
	}
}
