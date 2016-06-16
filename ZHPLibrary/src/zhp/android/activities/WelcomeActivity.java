package zhp.android.activities;

import zhp.android.data.Screen;
import zhp.library.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

/**
 * @author 郑海鹏
 * @since 2015/3/22 21:19
 * @email 284967632@qq.com
 * @encode utf-8
 */
public abstract class WelcomeActivity extends Activity {
	// ========================================
	// Fields
	// ========================================
	/**
	 * 字体
	 */
	protected Typeface font;

	/**
	 * 界面文字的颜色
	 */
	protected int textColor;

	/**
	 * Title文字, 软件名
	 */
	protected String titleText;

	/**
	 * Title的位置Y， 单位像素
	 */
	protected int titlePositionY;

	/**
	 * Title文本的大小。
	 */
	protected float titleTextSize;

	/**
	 * Banner文本的大小
	 */
	protected float bannerTextSize;

	/**
	 * Banner的位置Y, 单位像素
	 */
	protected int bannerPositionY;

	/**
	 * Banner文字
	 */
	protected String bannerText;

	/**
	 * 是否具有banner
	 */
	protected boolean bannerEnable = true;

	/**
	 * 整个Activity界面的布局
	 */
	protected RelativeLayout bgLayout;

	/**
	 * bgLayout的背景色
	 */
	protected int bgColor;

	/**
	 * 跳转时长
	 */
	protected long delay;

	/**
	 * 跳转意图
	 */
	protected Intent jumpIntent;

	/**
	 * 跳转离场动画
	 */
	protected int exitAnim = R.anim.alpha_dec;

	/**
	 * 跳转进场动画
	 */
	protected int enterAnim = R.anim.alpha_inc;

	// ========================================
	// Methods from Activity
	// ========================================
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(android.R.style.Theme_Holo_Light_NoActionBar_Fullscreen);
		super.onCreate(savedInstanceState);
		bgLayout = new RelativeLayout(this);
		setContentView(bgLayout);

		// 初始化屏幕参数，必须放在setOptions();之前
		Screen.initScreenMsg(this);

		// 设置所有的属性，必须放在Screen.initScreenMsg(this);之后
		setOptions();

		// 设置控件属性
		bgLayout.setBackgroundColor(bgColor);
		addTitle();
		addBanner();

		// 跳转
		jump();
	}

	// ========================================
	// setters
	// ========================================
	/**
	 * 获得背景布局
	 */
	protected RelativeLayout getBgLayout() {
		return this.bgLayout;
	}

	// ========================================
	// setters
	// ========================================
	/**
	 * 设置文字字体，包含Title和Banner的字体。
	 */
	protected void setFont(Typeface font) {
		this.font = font;
	}
	
	/**
	 * 设置文本内容
	 * @param titleText	Title的内容
	 * @param bannerText	Banner的内容
	 */
	protected void setText(String titleText, String bannerText) {
		this.titleText = titleText;
		this.bannerText = bannerText;
	}
	
	/**
	 * 设置是否有banner
	 */
	protected void setBannerEnable(boolean bannerEnable) {
		this.bannerEnable = bannerEnable;
	}

	/**
	 * 设置背景颜色
	 */
	protected void setBgColor(int bgColor) {
		this.bgColor = bgColor;
	}

	/**
	 * 设置文本颜色
	 */
	protected void setTextColor(int textColor) {
		this.textColor = textColor;
	}

	/**
	 * 设置跳转的延迟时间
	 */
	protected void setDelay(long delay) {
		this.delay = delay;
	}

	/**
	 * 设置离场动画
	 */
	protected void setExitAnim(int exitAnim) {
		this.exitAnim = exitAnim;
	}

	/**
	 * 设置入场动画
	 */
	protected void setEnterAnim(int enterAnim) {
		this.enterAnim = enterAnim;
	}

	/**
	 * 参数的单位都是百分比， 范围在 [0, 100]
	 * @param titlePositionY	Title的位置Y
	 * @param bannerPositionY	Banner的位置Y
	 */
	protected void setTextPosition(int titlePositionY, int bannerPositionY) {
		this.titlePositionY = (int) Screen.perHeight(titlePositionY);
		this.bannerPositionY = (int) Screen.perHeight(bannerPositionY);
	}
	
	/**
	 * 设置文字的大小，单位是短边的百分比
	 * @param titleTextSize_perShort	标题的文字大小，范围[1, 100]
	 * @param bannerTextSize_perShort banner的文字大小，范围[1, 100]
	 */
	protected void setTextSize(int titleTextSize_perShort, int bannerTextSize_perShort) {
		this.titleTextSize = Screen.perWidth(titleTextSize_perShort);
		this.bannerTextSize = Screen.perWidth(bannerTextSize_perShort);
	}

	/**
	 * 例如：this.jumpIntent = new Intent(this, MainActivity.class);
	 */
	protected abstract void setJumpIntent();

	// ========================================
	// Methods
	// ========================================
	/**
	 * 设置属性，子类所有的设置都应该重写该方法实现。并且super.setOptions()应该放在第一行。
	 */
	protected void setOptions() {
		setFont(Typeface.DEFAULT);
		setText("Title", "This is a banner");
		setTextPosition(24, 35);
		setTextSize(12, 4);
		setBannerEnable(true);
		setBgColor(Color.rgb(255, 0, 0));
		setTextColor(Color.rgb(255, 255, 255));
		setDelay(2000);
		setJumpIntent();
	}

	/**
	 * 添加Title
	 */
	private void addTitle() {
		TextView title = new TextView(this);
		bgLayout.addView(title);

		// 设置文本内容和字体
		title.setText(titleText);
		title.setTypeface(font);

		// 文字大小
		title.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize);

		// 位置
		title.setX(0);
		title.setY(titlePositionY);

		// 设置 match_parent, wrap_content
		title.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		title.setTextColor(textColor);

		// 文字居中
		title.setGravity(Gravity.CENTER);
	}

	/**
	 * 添加banner
	 */
	private void addBanner() {
		if (!bannerEnable)
			return;

		TextView banner = new TextView(this);
		bgLayout.addView(banner);

		// 设置文本内容和字体
		banner.setText(bannerText);
		banner.setTypeface(font);

		// 位置
		banner.setX(0);
		banner.setY(bannerPositionY);

		// 设置文字大小
		banner.setTextSize(TypedValue.COMPLEX_UNIT_PX, bannerTextSize);

		// 设置match_parent, wrap_content
		banner.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		banner.setTextColor(android.graphics.Color.WHITE);

		// 文字居中
		banner.setGravity(Gravity.CENTER);
	}

	/**
	 * 在等待跳转的时候执行本方法
	 */
	protected abstract void doThingWhenDelay();
	
	/**
	 * 延迟及跳转
	 */
	private void jump() {
		// 开始时间
		long startTime = System.currentTimeMillis();

		// 执行等待事件
		try {
			doThingWhenDelay();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 执行所花费的时间
		long castTime = System.currentTimeMillis() - startTime;

		if (castTime < delay) {
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					startActivity(jumpIntent);
					WelcomeActivity.this.overridePendingTransition(enterAnim, exitAnim);
					finish();
				}
			}, delay - castTime);
		} else {
			startActivity(jumpIntent);
			WelcomeActivity.this.overridePendingTransition(enterAnim, exitAnim);
			finish();
		}
	}

}
