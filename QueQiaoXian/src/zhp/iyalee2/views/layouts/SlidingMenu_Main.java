package zhp.iyalee2.views.layouts;

import zhp.android.data.Screen;
import zhp.android.handlers.StrategyHandler;
import zhp.android.layouts.SlidingMenuLayout;
import zhp.iyalee2.views.others.ThisSlidingMenuItem;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class SlidingMenu_Main extends SlidingMenuLayout{
	// =======================================================
	// 变量
	// =======================================================
	Activity context;
	StrategyHandler handler;
	
	/**
	 * 当前布局是属于哪个菜单的
	 */
	SlidingMenu menu;
	
	/**
	 * 关于选项按钮
	 */
	ThisSlidingMenuItem firendList;
	ThisSlidingMenuItem share;
	ThisSlidingMenuItem myContain;
	ThisSlidingMenuItem about;
	
	// =======================================================
	// 构造方法
	// =======================================================
	@Deprecated
	protected SlidingMenu_Main(Context context) {
		super(context);
	}

	public SlidingMenu_Main(Activity context, StrategyHandler handler, SlidingMenu menu){
		this(context);
		this.context = context;
		this.handler = handler;
		this.menu = menu;
	}
	
	// =======================================================
	// 重写、实现的方法
	// =======================================================
	@Override
	public void setTopLayout(Context context) {
		// 设置topLayout高度为屏幕高度的35%
		topLayout.getLayoutParams().height = (int) Screen.perHeight(35);
		topLayout.setBackgroundColor(Color.rgb(180, 0, 0));
	}

	@Override
	public void setBottomLayout(Context context) {
		addFriendListButton(context);
		addShareButton(context);
		addMyContainButton(context);
		addAboutButton(context);
	}

	// =======================================================
	// 私有的方法
	// =======================================================
	/**
	 * 添加 “好友列表”按钮
	 */
	private void addFriendListButton(Context context) {
		firendList = new ThisSlidingMenuItem(context);
		firendList.setText("好友列表");
		firendList.setOnClickListener(null);
		bottomLayout.addView(firendList);
	}
	
	/**
	 * 添加 “分享内容”按钮
	 */
	private void addShareButton(Context context) {
		share = new ThisSlidingMenuItem(context);
		share.setText("分享内容");
		share.setOnClickListener(null);
		bottomLayout.addView(share);
	}
	
	/**
	 * 添加 “我的内容”按钮
	 */
	private void addMyContainButton(Context context) {
		myContain = new ThisSlidingMenuItem(context);
		myContain.setText("我的内容");
		myContain.setOnClickListener(null);
		bottomLayout.addView(myContain);
	}
	
	/**
	 * 添加 “关于”按钮
	 */
	private void addAboutButton(Context context) {
		about = new ThisSlidingMenuItem(context);
		about.setText("关于");
		about.setOnClickListener(null);
		bottomLayout.addView(about);
	}
}
