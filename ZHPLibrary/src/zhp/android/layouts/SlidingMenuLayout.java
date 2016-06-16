package zhp.android.layouts;

import zhp.library.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public abstract class SlidingMenuLayout extends LinearLayout{
	
	// =======================================
	// 变量
	// =======================================	
	/**
	 * 上下文
	 */
//	protected Context context;
	
	/**
	 * 最底层布局
	 */
	public LinearLayout bgLayout;
	
	/**
	 * 上部分的布局
	 */
	public RelativeLayout topLayout;
	
	/**
	 * 下部分的布局
	 */
	public LinearLayout bottomLayout;
	
	// =======================================
	// 构造方法
	// =======================================	
	protected SlidingMenuLayout(Context context) {
		super(context);
		init(context);
	}

	protected SlidingMenuLayout(final Context context, AttributeSet attrs){
		super(context, attrs);
		if (isInEditMode())
			return;
		init(context);
	}
	
	public void init(Context context){
		LayoutInflater.from(context).inflate(R.layout.layout_slidingmenu, this, true);		
		this.bgLayout = (LinearLayout) findViewById(R.id.SlidingMenuLayout_bgLaout);
		this.topLayout = (RelativeLayout) findViewById(R.id.SlidingMenuLayout_topLayout);
		this.bottomLayout = (LinearLayout) findViewById(R.id.SlidingMenuLayout_bottomLayout);
		
		setTopLayout(context);
		setBottomLayout(context);
	}

	
	// =======================================
	// 抽象方法
	// =======================================	
	/**
	 * 设置上部分布局
	 */
	public abstract void setTopLayout(Context context);
	
	/**
	 * 设置下部分布局
	 */
	public abstract void setBottomLayout(Context context);

	/**
	 * 设置topLayout中的ImageView的图片。
	 */
//	public void setImageViewDrawable(Drawable image){
//		topLayout_ImageView.setImageDrawable(image);
//	}
	// =======================================
	// 重写、实现的方法
	// =======================================	
	@Override
	public void setBackgroundColor(int color) {
		bgLayout.setBackgroundColor(color);
	}

	@Override
	public void setBackgroundResource(int resid) {
		bgLayout.setBackgroundResource(resid);
	}

	@Override
	public void setPadding(int left, int top, int right, int bottom) {
		bgLayout.setPadding(left, top, right, bottom);
	}

	
}
