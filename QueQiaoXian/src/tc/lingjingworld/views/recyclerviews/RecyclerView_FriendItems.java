package tc.lingjingworld.views.recyclerviews;

import java.util.ArrayList;

import zhp.iyalee2.beans.ItemValue;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class RecyclerView_FriendItems extends RecyclerView{

	public Adapter_FriendItems adapter;
	
	public RecyclerView_FriendItems(Activity context) {
		super(context);
	}
	
	public RecyclerView_FriendItems(String friendName, Activity context, ArrayList<ItemValue> datas){
		this(context);
		setHasFixedSize(true);
		RecyclerView.LayoutManager manger = new LinearLayoutManager(context);
		setLayoutManager(manger);
		adapter = new Adapter_FriendItems(friendName, datas, context);
		setAdapter(adapter);
	}
	
}
