package tc.lingjingworld;

import zhp.android.activities.SlidingFinishActionBarActivity;
import zhp.android.data.CurrentUser;
import zhp.android.handlers.StrategyHandler;
import zhp.android.strategies.IStrategy;
import zhp.android.utils.Utils_Activity;
import zhp.iyalee2.R;
import zhp.iyalee2.broadcastreciver.BroadcastReceiver_CloaseActivity;
import zhp.iyalee2.utils.Utils_AddFriend;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_AddNewFriend extends SlidingFinishActionBarActivity implements OnClickListener {
	private Button btn_positive;
	private EditText et_friendnumber;
	private StrategyHandler handler = new StrategyHandler();
	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friendlist_add_newfriend);
		android.util.Log.i("郑海鹏", "AddNewFriendActivity#onCreate(): " + "");
		initView();
		btn_positive.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_positive:
			String friendName = et_friendnumber.getText().toString();
			addFriend(friendName);
			break;
		default:
			break;
		}
	}
	
	private void addFriend(String friendName){
		IStrategy strategy = new IStrategy() {
			
			@Override
			public void run() {
				Toast.makeText(getApplication(), "添加好友成功！", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(Activity_AddNewFriend.this, Activity_MyFriends.class);
				sendBroadcast(new Intent(BroadcastReceiver_CloaseActivity.CLOSE_ACTIVITY));
				startActivity(intent);
				finish();
			}
		};
		
		new Utils_AddFriend(handler).addFriend(getApplication(), CurrentUser.getUserName(), friendName, strategy);
	}

	private void initView() {
		btn_positive = (Button) findViewById(R.id.btn_positive);
		et_friendnumber = (EditText) findViewById(R.id.et_friendnumber);
		toolbar = (Toolbar) findViewById(R.id.activity_addfriend_toolBar);
		toolbar.setTitle("添加好友");
		toolbar.setTitleTextColor(Color.rgb(255, 255, 255));
		if(toolbar != null){
			setSupportActionBar(toolbar);
		}
		Utils_Activity.getInstance().setToolBarBellowStateBar(this, toolbar);
		Utils_Activity.getInstance().setStateBarColor(this, getResources().getColor(R.color.colorPrimaryDark));
	}
}
