package tc.lingjingworld;

import java.util.List;

import tc.lingjingworld.mycontent.Adapter_MyItemsRecyclerView;
import tc.lingjingworld.utils.DividerItemDecoration;
import zhp.android.activities.SlidingFinishActionBarActivity;
import zhp.android.utils.Utils_Activity;
import zhp.android.widgets.FloatingActionButton;
import zhp.iyalee2.R;
import zhp.iyalee2.beans.ItemValue;
import zhp.iyalee2.broadcastreciver.BroadcastReceiver_CloaseActivity;
import zhp.iyalee2.database.DBOperation_LocalItem;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;

public class Activity_MyItems extends SlidingFinishActionBarActivity{
	private FloatingActionButton btnAdd;
	private List<ItemValue> itemContentList;
	private Adapter_MyItemsRecyclerView mAdapter;
	private RecyclerView mRecyclerView;
	private Toolbar toolbar;
	private BroadcastReceiver_CloaseActivity receiver;

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.activity_myitems);
		initDatas();
		initViews();
		registeBroadcastReceiver();
	}

	private void registeBroadcastReceiver() {
		receiver = new BroadcastReceiver_CloaseActivity(this);
		IntentFilter filter = new IntentFilter();
		filter.addAction(BroadcastReceiver_CloaseActivity.CLOSE_ACTIVITY);
		registerReceiver(receiver, filter);
	}

	/**
	 * 从本地数据库中获得所有自定义的item
	 */
	private void initDatas() {
		DBOperation_LocalItem dbo = new DBOperation_LocalItem(this);
		dbo.openDataBase();
		itemContentList = dbo.getItems();
		dbo.closeDataBase();
	}

	/**
	 * 初始化控件
	 */
	private void initViews() {
		initToolbar();
		initRecyclerView();
		initFAB_Add();
	}

	private void initToolbar() {
		toolbar = (Toolbar) findViewById(R.id.activity_itemlist_toolBar);
		toolbar.setTitle("我的内容列表");
		toolbar.setTitleTextColor(Color.rgb(255, 255, 255));
		if(toolbar != null){
			setSupportActionBar(toolbar);
		}
		
		Utils_Activity.getInstance().setToolBarBellowStateBar(this, toolbar);
		Utils_Activity.getInstance().setStateBarColor(this, getResources().getColor(R.color.colorPrimaryDark));
	}

	private void initFAB_Add() {
		btnAdd = ((FloatingActionButton) findViewById(R.id.btn_add));
		btnAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Activity_MyItems.this, Activity_NewItem_Target.class));
			}
		});}

	private void initRecyclerView() {
		mRecyclerView = ((RecyclerView) findViewById(R.id.rv_mycontent));
		mAdapter = new Adapter_MyItemsRecyclerView(this, itemContentList);
		mRecyclerView.setAdapter(this.mAdapter);
		mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
				DividerItemDecoration.VERTICAL_LIST));
		LinearLayoutManager localLinearLayoutManager = new LinearLayoutManager(this,
				LinearLayoutManager.VERTICAL, false);
		mRecyclerView.setLayoutManager(localLinearLayoutManager);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
	}
}