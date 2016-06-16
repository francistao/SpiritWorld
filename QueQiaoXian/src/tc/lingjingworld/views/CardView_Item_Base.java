package tc.lingjingworld.views;

import zhp.iyalee2.R;
import zhp.iyalee2.beans.ItemValue;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

public class CardView_Item_Base extends CardView{
	
	public ItemValue item;
	public ImageView targetImage;
	public TextView tv_title, tv_profile;
	
	CardView_Item_Base(Context context) {
		super(context);
	}
	
	public CardView_Item_Base(Context context, ItemValue item){
		this(context);
		setRootView(context);
//		if(Utils_PhoneInfo.getInstance().getAndroidVersionCode() >= 21){
//			setBackgroundResource(R.drawable.bg_cardview);
//		}		
		// 实例化子视图
		targetImage = (ImageView) findViewById(R.id.cardview_item_targetimage);
		tv_title = (TextView) findViewById(R.id.cardview_item_title);
		
		tv_profile = (TextView) findViewById(R.id.cardview_item_profile);
		
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		setData(item);
	}

	public void setData(ItemValue item) {
		this.item = item;
		tv_title.setText(item.getTitle());
		tv_profile.setText(item.getProfile());
	}

	private void setRootView(Context context) {
		LayoutInflater.from(context).inflate(R.layout.cardview_item, this, true);
	}

	public ItemValue getItemValue(){
		return this.item;
	}
}
