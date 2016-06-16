package zhp.iyalee2.views.others;

import zhp.android.utils.Utils_PhoneInfo;
import zhp.iyalee2.R;
import zhp.iyalee2.beans.OwnItemData;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CardView_OwnItem extends CardView{
	public OwnItemData data;
	public TextView title, profile;
	public ImageView image;
	public LinearLayout bgLayout;

	CardView_OwnItem(Context context) {
		super(context);
	}

	public CardView_OwnItem(Context context, OwnItemData data) {
		this(context);
		setRootView(context);
		if(Utils_PhoneInfo.getInstance().getAndroidVersionCode() >= 21){
			setBackgroundResource(R.drawable.bg_cardview);
		}
		title = (TextView) findViewById(R.id.cardview_localitem_title);
		profile = (TextView) findViewById(R.id.cardview_localitem_profile);
		float size = title.getTextSize() * 1.3f;
		title.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
		
		image = (ImageView) findViewById(R.id.cardview_localitem_image);
		bgLayout = (LinearLayout) findViewById(R.id.cardview_localitem_bglayout);
		setData(data);
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		
	}
	
	void setRootView(Context context){
		LayoutInflater.from(context).inflate(R.layout.cardview_own_item, this, true);
	}
	
	public void setData(OwnItemData data){
		title.setText(data.getTitle());
		profile.setText(data.getProfile());
		image.setBackgroundResource(data.getPictureId());
		this.data = data;
	}
	
	@Override
	public void setOnClickListener(OnClickListener l) {
		this.image.setOnClickListener(l);
	}
	
}
