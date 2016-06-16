package zhp.iyalee2.views.buttons;

import zhp.iyalee2.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class Button_colorstike_whitetext extends Button{

	public Button_colorstike_whitetext(Context context, AttributeSet attrs) {
		super(context, attrs);
		if(isInEditMode()){
			return;
		}
		setBackgroundResource(R.drawable.btn_round_colorstrike);
		setTextColor(getResources().getColorStateList(R.drawable.font_primarycolor_white));
	}

	Button_colorstike_whitetext(Context context) {
		this(context, null);
	}
	
	
}
