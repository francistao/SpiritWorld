package tc.lingjingworld.views;

import zhp.android.utils.Utils_DLCBitmapLoader;
import zhp.iyalee2.beans.ItemValue;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

/**
 * 用于本地项目，因为目标图片或者
 * @author 郑海鹏
 * @since 2015年9月20日
 */
public class CardView_Item_Local extends CardView_Item_Base{
	
	public CardView_Item_Local(Context context, ItemValue item) {
		super(context, item);
	}

	@Override
	public void setData(ItemValue item) {
		super.setData(item);
		new AsyncTask<Void, Void, Bitmap>() {

			@Override
			protected Bitmap doInBackground(Void... params) {
				return new Utils_DLCBitmapLoader().loadBitmapFromFile(CardView_Item_Local.this.item.getTargetPath(), 400, 400);
			}

			@Override
			protected void onPostExecute(Bitmap result) {
				targetImage.setImageBitmap(result);
			}
		}.execute();		
	}
	
	
	
}
