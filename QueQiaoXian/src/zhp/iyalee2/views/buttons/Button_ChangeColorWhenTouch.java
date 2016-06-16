package zhp.iyalee2.views.buttons;

import zhp.iyalee2.beans.ButtonData;
import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.widget.Button;

public class Button_ChangeColorWhenTouch extends Button{
	int color = Color.rgb(255, 255, 255);
	int colorPressed = Color.rgb(255, 255, 100);
	
	public Button_ChangeColorWhenTouch(Context context) {
		super(context);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			setTextColor(colorPressed);
		}else if(event.getAction() == MotionEvent.ACTION_UP){
			setTextColor(color);
		}
		return super.onTouchEvent(event);
	}
	
	public void setOptions(ButtonData data){
		if (data.backgroundResID != ButtonData.BACKGROUND_INVALID) {
			setBackgroundResource(data.backgroundResID);
		}
		color = data.textColor;
		setTextColor(color);
		colorPressed = data.textColorPressed;
		setText(data.textContain);
		if(data.listener != null){
			setOnClickListener(data.listener);
		}
	}
	

}
