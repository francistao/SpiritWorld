package tc.lingjingworld;

import java.util.ArrayList;
import java.util.List;

import tc.lingjingworld.aboutuser.Adapter_MyFriendRecyclerView;
import tc.lingjingworld.utils.DividerItemDecoration;
import zhp.android.activities.SlidingFinishActionBarActivity;
import zhp.android.data.CurrentUser;
import zhp.android.utils.Utils_Activity;
import zhp.android.widgets.FloatingActionButton;
import zhp.iyalee2.R;
import zhp.iyalee2.beans.ItemFriend;
import zhp.iyalee2.broadcastreciver.BroadcastReceiver_CloaseActivity;
import zhp.iyalee2.utils.Utils_General;
import zhp.iyalee2.utils.Utils_GetFriends;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class Activity_MyFriends extends SlidingFinishActionBarActivity {
	private FloatingActionButton btn_add;
	private List<ItemFriend> itemfriendList;
	private Adapter_MyFriendRecyclerView mAdapter;
	private RecyclerView mRecyclerView;
	private Toolbar toolbar;

	private BroadcastReceiver_CloaseActivity receiver;
	
	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.friendlist_ui);
		initViews();
		initDatas();
	}

	private void initDatas() {
		new Utils_GetFriends() {
			
			@Override
			public void onDownloadFinish(ArrayList<ItemFriend> friends) {
				new Utils_General().printCollection(friends);
				itemfriendList = friends;
				initRecyclerView();
			}
		}.getFriends(getApplication(), CurrentUser.getUserName(), CurrentUser.getPassword());
	}

	private void initViews() {
		// Toolbar
		this.toolbar = (Toolbar) findViewById(R.id.activity_friendlist_toolBar);
		toolbar.setTitle("好友列表");
		toolbar.setTitleTextColor(Color.rgb(255, 255, 255));
		if (toolbar != null) {
			setSupportActionBar(toolbar);
		}
		Utils_Activity.getInstance().setToolBarBellowStateBar(this, toolbar);
		Utils_Activity.getInstance().setStateBarColor(this, getResources().getColor(R.color.colorPrimaryDark));

		// 添加好友按钮
		this.btn_add = ((FloatingActionButton) findViewById(R.id.btn_add));
		this.btn_add.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				registeBroadcastReceiver();
				startActivity(new Intent(Activity_MyFriends.this, Activity_AddNewFriend.class));
			}
		});
	}
	
	void initRecyclerView() {
		// RecyclerView
		this.mRecyclerView = new RecyclerView(this);
		((LinearLayout) findViewById(R.id.activity_friendist_aboveLayout))
				.addView(this.mRecyclerView);
		mAdapter = new Adapter_MyFriendRecyclerView(this, this.itemfriendList);
		this.mRecyclerView.setAdapter(this.mAdapter);
		mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
		LinearLayoutManager localLinearLayoutManager = new LinearLayoutManager(this, 1, false);
		mRecyclerView.setLayoutManager(localLinearLayoutManager);
	}

	private void registeBroadcastReceiver() {
		receiver = new BroadcastReceiver_CloaseActivity(this);
		IntentFilter filter = new IntentFilter();
		filter.addAction(BroadcastReceiver_CloaseActivity.CLOSE_ACTIVITY);
		registerReceiver(receiver, filter);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		try {
			if(receiver != null){
				unregisterReceiver(receiver);
			}
		} catch (Exception e) {
			Log.e("郑海鹏", "错误！错误！" + getClass().getName() + "onDestroy()");
		}
		
		
	}

	
	
}
