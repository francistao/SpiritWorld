package zhp.iyalee2;

import zhp.android.data.Screen;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * 带侧边栏的Activity。
 * @author 郑海鹏
 * @since 2015年10月7日
 */
public class SlidingMenuMainActivity extends AppCompatActivity{
	// ===========================================
	//	成员变量
	// ===========================================
	SlidingMenu menu;
	
	// ===========================================
	//	实现和重写的方法
	// ===========================================
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Screen.initScreenMsg(this);
		initSlidingMenu();
	}
	
	// ===========================================
	//	私有的方法
	// ===========================================
	/**
	 * 初始化侧边栏SlidingMenu
	 */
	private void initSlidingMenu(){
		// 新建一个SlidingMenu对象
		menu = new SlidingMenu(this);
		// 从左边出来
		menu.setMode(SlidingMenu.LEFT);
		// 触摸模式
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

		// 阴影宽度
		menu.setShadowWidth((int) Screen.perWidth(5));
		// 阴影
		menu.setShadowDrawable(R.drawable.shadow);

		// 设置离屏幕的偏移量
		menu.setBehindOffset((int) Screen.dp(56));
		// 设置渐入渐出效果的值
		menu.setFadeDegree(0.8f);
		// 是否包含ActionBar
		menu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
	}
	
	// ===========================================
	//	公有的方法
	// ===========================================
	/**
	 * 设置Menu的视图
	 */
	public void setMenuView(View menuView){
		menu.setMenu(menuView);
	}

	
	
}
