package tc.lingjingworld.aboutuser;

import java.util.List;

import tc.lingjingworld.views.CardView_Friend;
import zhp.iyalee2.beans.ItemFriend;
import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public class Adapter_MyFriendRecyclerView extends RecyclerView.Adapter<ViewHolder_Friend> {
	private Activity mContext;
	private List<ItemFriend> mDatas;

	public Adapter_MyFriendRecyclerView(Activity paramContext, List<ItemFriend> paramList) {
		this.mContext = paramContext;
		this.mDatas = paramList;
	}

	public int getItemCount() {
		return mDatas.size();
	}

	public void deleteItem(ItemFriend item){
		for(int i = 0; i < mDatas.size(); i++){
			if(mDatas.get(i).getUserName().equals(item.getUserName())){
				mDatas.remove(i);
				notifyItemRemoved(i);
				return;
			}			
		}
		zhp.android.debug.Debug.Log(this.getClass().getName() + "#deleteItem()", "没有成功移除！");
	}
	
	public void onBindViewHolder(final ViewHolder_Friend viewHolder, int index) {
		viewHolder.friendView.setData(mDatas.get(index));
	}

	public ViewHolder_Friend onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
		CardView_Friend cardView = new CardView_Friend(this.mContext, new ItemFriend("", "", ""));
		ViewHolder_Friend holder = new ViewHolder_Friend(mContext, cardView, this);
		return holder;
	}

}
