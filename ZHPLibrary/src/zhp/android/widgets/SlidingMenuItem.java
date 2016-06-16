package zhp.android.widgets;

import zhp.library.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * 一个用于SlidingMenu的控件。
 * 该控件分两个大部分。
 * 左边部分是该item的图标，右侧部分为一个没有任何View的LinearLayout，根据具体情况在子类中设置。
 * 其中左侧的图标放在一个Linearlayout中，图标的长宽为24dp x 24dp。
 * 左右两侧的Layout高度都为56dp，且android:gravity="center_vertical|start"。
 * 左侧Layout的方向为竖向。右侧Layout的方向为横向。
 * 整个控件的高度为 56dp + 2x5dp(paddingTop + paddingBottom) = 66dp。
 * @author 郑海鹏
 * @since 2015/4/30 17:09
 */
public abstract class SlidingMenuItem extends LinearLayout {
	/**
	 * 左侧的图标，它紧靠左边
	 */
	public ImageView icon;

	/**
	 * 最底层布局
	 */
	public LinearLayout bgLayout;

	/**
	 * 左侧的布局，它装着图标，大小固定为 56dp x 56dp，默认layout_gravity="centre_vertical|start"
	 */
	public LinearLayout leftLayout;

	/**
	 * 右侧布局，默认没有装载任何视图，layout_gravity="centre_vertical|start"
	 */
	public LinearLayout rightLayout;

	public SlidingMenuItem(Context context) {
		this(context, null);
	}

	public SlidingMenuItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		if (isInEditMode()) {
			return;
		}

		// 获得视图和布局
		LayoutInflater.from(context).inflate(R.layout.widget_slidingmenu_item, this, true);
		icon = (ImageView) findViewById(R.id.widget_slidingmenu_item_icon);
		bgLayout = (LinearLayout) findViewById(R.id.widget_slidingmenu_item_bgLayout);
		leftLayout = (LinearLayout) findViewById(R.id.widget_slidingmenu_item_leftLayout);
		rightLayout = (LinearLayout) findViewById(R.id.widget_slidingmenu_item_rightLayout);

		setRightLayout(context);
	}

	/**
	 * 设置右边的视图
	 */
	public abstract void setRightLayout(Context context);

	/**
	 * 设置图片内容
	 */
	public void setImage(int resId) {
		icon.setImageResource(resId);
	}

//	@Override
//	/**
//	 * 设置背景。
//	 * bgLayout.setBackgroundResource(resId);
//	 * @param resId
//	 *            drawable 资源id
//	 */
//	public void setBackgroundResource(int resId) {
//		bgLayout.setBackgroundResource(resId);
//	}
//	
//	/**
//	 * 设置背景颜色。
//	 */
//	public void setBackgroundColor(int color){
//		bgLayout.setBackgroundColor(color);
//	}
}
