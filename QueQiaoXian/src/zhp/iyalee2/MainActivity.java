//package zhp.iyalee2;
//
//import zhp.android.data.Screen;
//import zhp.android.handlers.StrategyHandler;
//import zhp.iyalee2.views.layouts.SlidingMenu_Main;
//
//import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//
//public class MainActivity extends Activity {
//	// =======================================================
//	// 变量
//	// =======================================================
//	/**
//	 * SlidingMenu，侧滑菜单
//	 */
//	SlidingMenu menu;
//	
//	/**
//	 * 侧栏的布局
//	 */
//	SlidingMenu_Main newSlidingMenu;
//	
//	/**
//	 * 消息处理
//	 */
//	StrategyHandler handler = new StrategyHandler();
//	
//	
//	// =======================================================
//	// 继承或实现的方法
//	// =======================================================
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
//		
//		initSlidingMenu();
//	}
//	
//	// =======================================================
//	// 私有的的方法
//	// =======================================================
//	/**
//	 * 初始化SlidingMenu
//	 */
//	private void initSlidingMenu() {
//		// 新建一个SlidingMenu对象
//		menu = new SlidingMenu(this);
//		// 从左边出来
//		menu.setMode(SlidingMenu.LEFT);
//		// 触摸模式
//		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
//
//		// 阴影宽度
//		menu.setShadowWidth((int) Screen.perWidth(5));
//		// 阴影
//		menu.setShadowDrawable(R.drawable.shadow);
//
//		// 设置离屏幕的偏移量
//		menu.setBehindOffset((int) Screen.dp(56));
//		// 设置渐入渐出效果的值
//		menu.setFadeDegree(0.8f);
//		// 是否包含ActionBar
//		menu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
//		// 设置SlidingMenu的视图
//		newSlidingMenu = new SlidingMenu_Main(this, handler, menu);
//		menu.setMenu(newSlidingMenu);
//	}
//
//	/**
//	 *	点击了播放按钮
//	 */
//	public void onPlayClick(View v){
//		Intent intent = new Intent(this, ListActivity.class);
//		startActivity(intent);
//	}
//	
//}
