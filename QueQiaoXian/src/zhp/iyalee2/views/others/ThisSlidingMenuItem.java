package zhp.iyalee2.views.others;

import zhp.android.widgets.SlidingMenuItem;
import zhp.iyalee2.R;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * SlidingMenu的一个item。 左侧图标，右侧文字。
 * 
 * @author 郑海鹏
 * @since 2015/4/26 10:55
 */
public class ThisSlidingMenuItem extends SlidingMenuItem {
	TextView tv;

	public ThisSlidingMenuItem(Context context) {
		this(context, null);
	}

	public ThisSlidingMenuItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		setBackgroundResource(R.drawable.button_white_gray_bottom_line);
	}

	@Override
	public void setRightLayout(Context context) {
		tv = new TextView(context);

		// 设置文字大小
		float textSize = tv.getTextSize();
		textSize *= 1.3f;
		tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

		// 粗体
		tv.setTypeface(Typeface.DEFAULT_BOLD);

		// 颜色
		tv.setTextColor(getResources().getColor(R.color.textColor_gray));
		
		rightLayout.addView(tv);
	}

	/**
	 * 设置Item的文字
	 */
	public void setText(String s) {
		tv.setText(s);
	}
}
