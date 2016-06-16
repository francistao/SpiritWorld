package tc.lingjingworld.views.recyclerviews;

import java.util.ArrayList;

import tc.lingjingworld.views.CardView_Item_Cloud;
import zhp.iyalee2.beans.ItemValue;
import zhp.iyalee2.beans.ItemValue.ContainType;
import zhp.iyalee2.beans.ItemValueWithFlag;
import zhp.iyalee2.utils.Utils_OpenCloudItem;
import zhp.iyalee2.utils.Utils_OpenSingleItem;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

public class Adapter_FriendItems extends RecyclerView.Adapter<Adapter_FriendItems.ViewHolder> {
	
	public ArrayList<ItemValueWithFlag> datas;
	public Activity context;
	private String friendName;
	
	public Adapter_FriendItems(String friendName, ArrayList<ItemValue> items, Activity context){
		this.datas = ItemValueWithFlag.castDownloaded(items, true);
		this.context = context;
		this.friendName = friendName;
	}

	@Override
	public int getItemCount() {
		return datas.size();
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int index) {
		holder.cardView.setData(datas.get(index));
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		CardView_Item_Cloud temp = new CardView_Item_Cloud(context, new ItemValueWithFlag("", "", "", "", ContainType.Picture));
		ViewHolder holder = new ViewHolder(temp);
		return holder;
	}
	
	public void addDatas(ArrayList<ItemValue> items){
		ArrayList<ItemValueWithFlag> news = ItemValueWithFlag.castDownloaded(items, false);
		for(int i = 0; i < news.size(); i++){
			datas.add(news.get(i));
			notifyItemInserted(datas.size());
		}
	}
	
	// ===============================================
	// 内部类-ViewHolder
	// ===============================================
	public class ViewHolder extends RecyclerView.ViewHolder {
		CardView_Item_Cloud cardView;
		
		public ViewHolder(CardView_Item_Cloud itemView) {
			super(itemView);
			this.cardView = itemView;
			
			this.cardView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(cardView.item.getIsDownloaded()){
						// 如果是已经下载好的直接打开
						/* ---------------------------------------------------------- */
//						String titles[] = { "createTime", "targetPath", "containPath", "title",
//								"type" };
//						String values[] = { cardView.item.getCreateTime(),
//								cardView.item.getTargetPath(), cardView.item.getContainPath(),
//								cardView.item.getTitle(), cardView.item.getType().toString() };
//						zhp.android.debug.Debug.Log(this.getClass().getName() + "#onClick()",
//								titles, values);
						/* ---------------------------------------------------------- */
						Utils_OpenSingleItem.startSingleItem(context, cardView.item);
					}else{
						// 如果是尚未下载好的，提示下载
						showConfirmDownloadDialog();
					}
				}
			});
			
			this.cardView.setOnLongClickListener(new OnLongClickListener() {
				
				@Override
				public boolean onLongClick(View v) {
					Toast.makeText(context, "长按", Toast.LENGTH_SHORT).show();
					return true;
				}
			});
		}

		protected void showConfirmDownloadDialog() {
			AlertDialog.Builder builder = new Builder(context);
			builder.setTitle("提示：");
			builder.setMessage("该项目尚未下载到本地，是否下载？");
			builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {

					new Utils_OpenCloudItem(context) {

						@Override
						public void doWhenFinish(ItemValue item) {
							Utils_OpenSingleItem.startSingleItem(context, item);
						}
					}.open(friendName, cardView.item);
				}
				
			});

			builder.setNegativeButton("取消", null);
			builder.create().show();
		}
	}
	
	
}
