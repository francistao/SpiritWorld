package tc.lingjingworld.mycontent;

import java.util.List;

import tc.lingjingworld.views.CardView_Item_Local;
import zhp.iyalee2.beans.ItemValue;
import zhp.iyalee2.beans.ItemValue.ContainType;
import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class Adapter_MyItemsRecyclerView extends RecyclerView.Adapter<ViewHolder_Item> {
	private Activity activity;
	private List<ItemValue> mDatas;

	public Adapter_MyItemsRecyclerView(Activity paramContext, List<ItemValue> paramList) {
		this.activity = paramContext;
		this.mDatas = paramList;
	}

	public int getItemCount() {
		return mDatas.size();
	}

	public void onBindViewHolder(final ViewHolder_Item viewHolder, int index) {
		viewHolder.itemView.setData(mDatas.get(index));
	}

	public ViewHolder_Item onCreateViewHolder(ViewGroup paramViewGroup, int viewType) {
		CardView_Item_Local itemView = new CardView_Item_Local(activity, new ItemValue("", "", "", "", ContainType.Picture, ""));
		ViewHolder_Item holder = new ViewHolder_Item(activity, itemView, this);
		return holder;
	}

	@Deprecated
	public void removeData(int paramInt) {
		this.mDatas.remove(paramInt);
		notifyItemRemoved(paramInt);
	}

	public void deleteItem(ItemValue item){
		for(int i = 0; i < mDatas.size(); i++){
			if(mDatas.get(i).getCreateTime().equals(item.getCreateTime())){
				mDatas.remove(i);
				notifyItemRemoved(i);
				return;
			}			
		}
		android.util.Log.i("郑海鹏", "MyContentAdapter#deleteItem(): " + "没有成功移除！");
	}
	
	public static abstract interface OnItemClickLitener {
		public abstract void onItemClick(View paramView, int paramInt);

		public abstract boolean onItemLongClick(View paramView, int paramInt);
	}
}
