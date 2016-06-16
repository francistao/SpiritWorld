package zhp.android.widgets;

import zhp.library.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 一个图标下面有文字的控件
 */
public class TextBellowImageView extends RelativeLayout {
	// ===========================================
	// 变量
	// ===========================================
	Context context;
	TextView mTextView;
	ImageView mImageView;
	RelativeLayout bgLayout;

	// ===========================================
	// 构造函数
	// ===========================================
	public TextBellowImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;

		LayoutInflater.from(context).inflate(R.layout.widget_text_bellow_image, this, true);
		mTextView = (TextView) findViewById(R.id.widget_text_bellow_image_textView);
		mImageView = (ImageView) findViewById(R.id.widget_text_bellow_image_imageView);
		bgLayout = (RelativeLayout) findViewById(R.id.widget_text_bellow_image_bgLayout);
	}
	
	public TextBellowImageView(Context context, ImageView imageView, String text) {
		this(context, null);
		mImageView = imageView;
		setText(text);
	}

	// ===========================================
	// 包装的方法
	// ===========================================
	/**
	 * 设置要显示的文字
	 */
	public void setText(String text) {
		this.mTextView.setText(text);
	}

	/**
	 * 设置要显示的文字
	 */
	public void setText(int resId) {
		this.mTextView.setText(resId);
	}

	/**
	 * 设置要显示的文字的颜色
	 */
	public void setTextColor(int color) {
		this.mTextView.setTextColor(color);
	}

	/**
	 * 设置要显示的文字的大小，单位sp
	 */
	public void setTextSizeBySp(float sp) {
		this.mTextView.setTextSize(sp);
	}

	/**
	 * 设置要显示的文字的大小，单位px
	 */
	public void setTextSizeByPx(int px) {
		this.mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, px);
	}

	/**
	 * 设置要显示的文字的大小
	 */
	public void setTextSize(int unit, float size) {
		this.mTextView.setTextSize(unit, size);
	}

	/**
	 * 设置要显示的图片
	 */
	public void setImageResource(int resId) {
		this.mImageView.setImageResource(resId);
	}

	/**
	 * 设置要显示的图片
	 */
	public void setImageDrawable(Drawable drawable) {
		this.mImageView.setImageDrawable(drawable);
	}

	/**
	 * 设置要显示的图片
	 */
	public void setImageBitmap(Bitmap bitmap) {
		this.mImageView.setImageBitmap(bitmap);
	}

	/**
	 * 设置整个视图的背景，即bgLayout的背景
	 */
	@Override
	public void setBackground(Drawable background) {
		this.bgLayout.setBackground(background);
	}

	/**
	 * 设置整个视图的背景，即bgLayout的背景
	 */
	@Override
	public void setBackgroundColor(int color) {
		this.bgLayout.setBackgroundColor(color);
	}

	/**
	 * 设置整个视图的背景，即bgLayout的背景
	 */
	@Override
	public void setBackgroundResource(int resid) {
		this.bgLayout.setBackgroundResource(resid);
	}

	/**
	 * 设置整个视图的X坐标，即bgLayout的X坐标
	 */
	public void setX(float x) {
		this.bgLayout.setX(x);
	}

	/**
	 * 设置整个视图的Y坐标，即bgLayout的Y坐标
	 */
	public void setY(float y) {
		this.bgLayout.setY(y);
	}

}
