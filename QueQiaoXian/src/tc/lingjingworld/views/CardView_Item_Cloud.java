package tc.lingjingworld.views;

import zhp.android.data.FinalValue;
import zhp.android.utils.Utils_TLCBitmapLoader;
import zhp.iyalee2.R;
import zhp.iyalee2.beans.ItemValueWithFlag;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

public class CardView_Item_Cloud extends CardView{
	
	public ItemValueWithFlag item;
	public ImageView targetImage;
	public TextView tv_title, tv_profile;
	
	CardView_Item_Cloud(Context context) {
		super(context);
	}
	
	public CardView_Item_Cloud(Context context, ItemValueWithFlag item){
		this(context);
		setRootView(context);
		// 实例化子视图
		targetImage = (ImageView) findViewById(R.id.cardview_item_targetimage);
		tv_title = (TextView) findViewById(R.id.cardview_item_title);
		
		tv_profile = (TextView) findViewById(R.id.cardview_item_profile);
		
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		setData(item);
	}
	
	/**
	 * 根据item是否已经下载，用不同的方式加载目标图片
	 * @return
	 */
	private Bitmap loadTargetPicture(){
		if(this.item.getIsDownloaded()){
			// 如果是已经下载好的，从本地加载目标图片
			return new Utils_TLCBitmapLoader().loadBitmapLocal(CardView_Item_Cloud.this.item.getTargetPath(), 300, 300);
		}else{
			// 如果是还没有下载好的，从网络中加载图片
			return new Utils_TLCBitmapLoader().loadBitmapByUrl(CardView_Item_Cloud.this.item.getTargetPath(), FinalValue.FOLDER_BASE_PATH + "arworld/localItem/", 300, 300);
		}
	}

	public void setData(ItemValueWithFlag item) {
		this.item = item;

		new AsyncTask<Void, Void, Bitmap>() {

			@Override
			protected Bitmap doInBackground(Void... params) {
				return loadTargetPicture();
			}

			@Override
			protected void onPostExecute(Bitmap result) {
				targetImage.setImageBitmap(result);
			}
		}.execute();

		tv_title.setText(item.getTitle());
		tv_profile.setText(item.getProfile());
	}

	private void setRootView(Context context) {
		LayoutInflater.from(context).inflate(R.layout.cardview_item, this, true);
	}

	public ItemValueWithFlag getItemValueWithFlag(){
		return this.item;
	}
}
