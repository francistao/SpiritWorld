package tc.lingjingworld.aboutuser;

import tc.lingjingworld.Activity_FriendInfo;
import tc.lingjingworld.views.CardView_Friend;
import zhp.android.data.CurrentUser;
import zhp.iyalee2.beans.ItemFriend;
import zhp.iyalee2.server.servers.Server_DeleteFriend;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

public class ViewHolder_Friend extends RecyclerView.ViewHolder {

	CardView_Friend friendView;
	Adapter_MyFriendRecyclerView adapter;
	
	public ViewHolder_Friend(final Activity activity, CardView_Friend cardView, Adapter_MyFriendRecyclerView adapter) {
		super(cardView);
		this.friendView = cardView;
		this.adapter = adapter;
		
		this.friendView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(activity, Activity_FriendInfo.class);
				intent.putExtra("friendName", friendView.friend.getUserName());
				activity.startActivity(intent);
			}
		});
		
		this.friendView.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				showLongClickDalog(activity, ViewHolder_Friend.this.friendView.getItemValue());
				return true;
			}
		});
	}

	int choiced = 0;
	private void showLongClickDalog(final Activity activity, final ItemFriend friend) {
		AlertDialog.Builder builder = new Builder(activity);
		builder.setTitle(friend.getUserName());

		final String[] choice = new String[] { "删除好友"};
		final int DELETE = 0;
		
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
					adapter.deleteItem(friend);
					// 子线程中告诉服务器删除好友关系
					new Thread(){

						@Override
						public void run() {
							new Server_DeleteFriend().getServer(CurrentUser.getUserName(), friend.getUserName());
						}
						
					}.start();
					break;
				}
				dialog.dismiss();
			}

		});
		builder.create().show();
		
	}
	
}
