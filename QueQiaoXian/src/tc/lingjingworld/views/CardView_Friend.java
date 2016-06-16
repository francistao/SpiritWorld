package tc.lingjingworld.views;

import zhp.android.utils.Utils_TLCBitmapLoader;
import zhp.android.widgets.CircleImageView;
import zhp.iyalee2.R;
import zhp.iyalee2.beans.ItemFriend;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.widget.TextView;

public class CardView_Friend extends CardView{
	
	public ItemFriend friend;
	public CircleImageView targetImage;
	public TextView tv_userName, tv_profile;
	
	CardView_Friend(Context context) {
		super(context);
		setRootView(context);
		// 实例化子视图
		targetImage = (CircleImageView) findViewById(R.id.myfriendlist_image);
		tv_userName = (TextView) findViewById(R.id.myfriendlist_name);
		
		tv_profile = (TextView) findViewById(R.id.myfriendlist_says);
		
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
	}
	
	public CardView_Friend(Context context, ItemFriend item){
		this(context);
		setData(item);
	}

	public void setData(final ItemFriend friend) {
		this.friend = friend;
		
		new AsyncTask<Void, Void, Bitmap>() {

			@Override
			protected Bitmap doInBackground(Void... params) {
				zhp.android.debug.Debug.Log(this.getClass().getName() + "#doInBackground()", "头像路径：" + friend.getIconPath());
				return new Utils_TLCBitmapLoader().loadBitmapByUrl(friend.getIconPath(), "sdcard/mzdxl/icon/", 400, 400);
			}

			@Override
			protected void onPostExecute(Bitmap result) {
				if(result == null){
					targetImage.setImageResource(R.drawable.ic_launcher);
				}else{
					targetImage.setImageBitmap(result);
				}				
			}
		}.execute();
		
		tv_userName.setText(friend.getUserName());
		tv_profile.setText(friend.getProfile());
	}

	private void setRootView(Context context) {
		LayoutInflater.from(context).inflate(R.layout.cardview_friend, this, true);
	}

	public ItemFriend getItemValue(){
		return this.friend;
	}
}
