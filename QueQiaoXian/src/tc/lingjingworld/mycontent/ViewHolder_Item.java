package tc.lingjingworld.mycontent;

import tc.lingjingworld.Activity_Login;
import tc.lingjingworld.views.CardView_Item_Local;
import zhp.android.data.CurrentUser;
import zhp.iyalee2.beans.ItemValue;
import zhp.iyalee2.utils.Utils_DeleteItem;
import zhp.iyalee2.utils.Utils_OpenSingleItem;
import zhp.iyalee2.utils.Utils_UploadItem;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Toast;

public class ViewHolder_Item extends RecyclerView.ViewHolder {
	CardView_Item_Local itemView;
	Adapter_MyItemsRecyclerView adapter;
	
	int choiced = 0;

	public ViewHolder_Item(final Activity activity, CardView_Item_Local itemView, Adapter_MyItemsRecyclerView adapter) {
		super(itemView);
		this.itemView = itemView;
		this.adapter = adapter;
		
		this.itemView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Utils_OpenSingleItem.startSingleItem(activity, ViewHolder_Item.this.itemView.getItemValue());
			}
		});
		
		this.itemView.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				showLongClickDalog(activity, ViewHolder_Item.this.itemView.getItemValue());
				return true;
			}

		});
	}
	
	private void showLongClickDalog(final Activity activity, final ItemValue item) {
		AlertDialog.Builder builder = new Builder(activity);
		builder.setTitle(item.getTitle());

		final String[] choice = new String[] { "删除", "上传" };
		final int DELETE = 0;
		final int UPLOAD = 1;
		
		builder.setSingleChoiceItems(choice, 0, new android.content.DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				choiced = which;
			}

		});
		
		builder.setNegativeButton("取消", new android.content.DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		builder.setPositiveButton("确定", new android.content.DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (choiced) {
				case DELETE:
					// 删除数据库中的元组
					new Utils_DeleteItem().deleteItem(activity, item);
					// 删除RecyclerView中的item
					adapter.deleteItem(item);
					break;
					
				case UPLOAD:
					if(CurrentUser.isLogin){
						Toast.makeText(activity.getApplication(), "开始上传！", Toast.LENGTH_SHORT).show();
						new Utils_UploadItem(){

							@Override
							public void onUploadItemSuccess() {
								Toast.makeText(activity.getApplication(), "上传成功！", Toast.LENGTH_SHORT).show();
							}
							
						}.uploadItem(activity.getApplication(), CurrentUser.getUserName(), CurrentUser.getPassword(), item);
					}else{
						showLogInDialog(activity);
					}
					
					break;

				default:
					break;
				}
				dialog.dismiss();
			}

		});

		builder.create().show();
	}
	
	private void showLogInDialog(final Activity activity) {
		AlertDialog.Builder builder = new Builder(activity);
		builder.setTitle("提示：");
		builder.setMessage("您还没有登录，请在登录后操作。");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(activity, Activity_Login.class);
				activity.startActivity(intent);
			}
		});

		builder.setNegativeButton("取消", null);
		builder.create().show();
	}

}
