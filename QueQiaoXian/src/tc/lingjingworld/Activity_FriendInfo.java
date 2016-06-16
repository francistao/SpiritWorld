package tc.lingjingworld;

import java.util.ArrayList;

import tc.lingjingworld.utils.DividerItemDecoration;
import tc.lingjingworld.views.recyclerviews.RecyclerView_FriendItems;
import zhp.android.activities.SlidingFinishActionBarActivity;
import zhp.android.utils.Utils_Activity;
import zhp.iyalee2.R;
import zhp.iyalee2.beans.ItemValue;
import zhp.iyalee2.utils.Utils_GetFriendItems;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

/**
 * 某个好友的分享
 * 
 * @author 郑海鹏
 * @since 2015年9月20日
 */
public class Activity_FriendInfo extends SlidingFinishActionBarActivity {

	String userName;
	LinearLayout bgLayout;
	Toolbar toolbar;
	RecyclerView_FriendItems recyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_empty_toolbar);
		userName = getIntent().getExtras().getString("friendName");
		initViews();
		initFriendItemInfo();
	}
	
	private void initViews(){
		bgLayout = (LinearLayout) findViewById(R.id.activity_empty_bgLayout);
		
		toolbar = (Toolbar) findViewById(R.id.activity_empty_toolbar);
		toolbar.setTitle("好友" + userName + "的分享");
		toolbar.setTitleTextColor(Color.rgb(255, 255, 255));
		if(toolbar != null){
			setSupportActionBar(toolbar);
		}
		
		Utils_Activity.getInstance().setToolBarBellowStateBar(this, toolbar);
		Utils_Activity.getInstance().setStateBarColor(this, getResources().getColor(R.color.colorPrimaryDark));
	}

	/**
	 * 获得好友的列表
	 */
	private void initFriendItemInfo() {
		new Utils_GetFriendItems() {

			/**
			 * 当已下载的项目以获取
			 */
			@Override
			public void onLocalItemsGot(ArrayList<ItemValue> items) {
				initRecyclerView(items);
			}

			/**
			 * 当未下载的项目已获取
			 */
			@Override
			public void onNotDownloadItemsGot(ArrayList<ItemValue> items) {
				recyclerView.adapter.addDatas(items);
			}

		}.getFriendItems(getApplication(), userName);
	}

	protected void initRecyclerView(ArrayList<ItemValue> friends) {
		recyclerView = new RecyclerView_FriendItems(userName, this, friends);
		recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
		bgLayout.addView(recyclerView);
	}

}
