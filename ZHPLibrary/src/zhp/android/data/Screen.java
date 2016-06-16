package zhp.android.data;

import android.app.Activity;
import android.util.DisplayMetrics;

public class Screen {
	// ========================================
	// Fields
	// ========================================

	/**
	 * 屏幕的宽度，单位像素
	 */
	private static int widthPx;

	/**
	 * 屏幕的高度，单位像素
	 */
	private static int heightPx;

	/**
	 * 像素密度, 是一个单位。在MX4 pro上 1dp = 4px。
	 */
	private static float dp;

	/**
	 * 比例像素，是一个单位。
	 */
	private static float sp;

	/**
	 * 是否为竖屏
	 */
	public static boolean isPortrait;

	private static int stateBarHeight;

	// ========================================
	// Methods
	// ========================================
	/**
	 * 初始化屏幕参数
	 */
	public static void initScreenMsg(Activity activity) {
		if (heightPx != 0)
			return;
		
		// 获得屏幕参数
		DisplayMetrics m = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(m);
		setScreenMsg(m.widthPixels, m.heightPixels);
		dp = m.density;
		sp = m.scaledDensity;

		// 获得状态栏的高度
//		Rect rect = new Rect();
//		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
//		stateBarHeight = rect.top;
	}

	/**
	 * 设置屏幕参数
	 * 
	 * @param width_Px
	 * @param height_Px
	 */
	public static void setScreenMsg(int width_Px, int height_Px) {
		widthPx = width_Px;
		heightPx = height_Px;
		isPortrait = height_Px > width_Px ? true : false;
	}

	/**
	 * 获得屏幕的宽度
	 */
	public static int getWidth() {
		return widthPx;
	}

	/**
	 * 获得屏幕的高度
	 */
	public static int getHeight() {
		return heightPx;
	}

	/**
	 * 获得较短一边的长度，单位像素
	 */
	public static int getShort() {
		return widthPx < heightPx ? widthPx : heightPx;
	}

	/**
	 * 获得较长一边的长度，单位像素
	 */
	public static int getLong() {
		return widthPx > heightPx ? widthPx : heightPx;
	}

	/**
	 * @param scale
	 *            乘数
	 * @return 返回 sp * scale
	 */
	public static float sp(float scale) {
		return sp * scale;
	}

	/**
	 * @param scale
	 *            乘数
	 * @return 返回 dp * scale
	 */
	public static float dp(float scale) {
		return dp * scale;
	}

	/**
	 * @param scale
	 *            乘数， 在 [0, 100]之间
	 * @return 屏幕高度 * scale%
	 */
	public static float perHeight(int scale) {
		return heightPx * scale * 0.01f;
	}

	/**
	 * @param scale
	 *            乘数， 百分比, 在 [0, 100]之间
	 * @return 屏幕高度 * scale%
	 */
	public static float perWidth(int scale) {
		return widthPx * scale * 0.01f;
	}

	/**
	 * 获得状态栏的高度
	 */
	public static int getStateBarHeight() {
		return stateBarHeight;
	}
}
