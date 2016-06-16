package zhp.android.activities;

import zhp.android.data.Screen;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

/**
 * 从左往右滑动结束本Activity。
 * 需要使用主题：ToolBarAndTranslucent。
 * 在onCreate中已初始化Screen。
 * @author 郑海鹏
 * @since 2015年6月16日
 */
public class SlidingFinishActionBarActivity extends AppCompatActivity {
	final int ANIMATION_DELAY = 400;
	
	protected float downPointX, downPointY;
	protected float distanceX = 0, distanceY = 0;
	protected int shadowRed = 0;
	protected int shadowGreen = 0;
	protected int shadowBlue = 0;
	protected int alpha = 255;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 Screen.initScreenMsg(this); 
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float touchPositionX = event.getX();
		float touchPositionY = event.getY();
		float activityPositionX = findViewById(android.R.id.content).getX();
		
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			downPointX = event.getX();
			downPointY = event.getY();
		} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
			if(downPointX > Screen.perWidth(30)){
				return super.onTouchEvent(event);
			}			
			distanceX = touchPositionX - downPointX;
			distanceY = Math.abs(touchPositionY - downPointY);
			// 利用 findViewById(android.R.id.content) 找到最底层的View
			if (distanceX > 0/* && distanceX > distanceY*/) {// 只有从左往右滑才会有效果。
				findViewById(android.R.id.content).setX(distanceX);
				// 设置透明度
				alpha = (int) ((1 - distanceX/Screen.getWidth()) * 255);
				((View)findViewById(android.R.id.content).getParent()).setBackgroundColor(Color.argb(alpha, shadowRed, shadowGreen, shadowBlue));
			}
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			if(downPointX > Screen.perWidth(30)){
				return super.onTouchEvent(event);
			}
			if (distanceX > Screen.perWidth(45)) {
				finshThisActivity(activityPositionX);
			}else{
				rebackToLeft(activityPositionX);
			}
		}
		return super.onTouchEvent(event);
	}

	/**
	 * 当前Activity的退场动画。动画结束后调用onBackPressed();
	 * @param currentX 当前Activity视图的位置
	 */
	private void finshThisActivity(float currentX) {
		// 从当前位置移动到右侧。
		ValueAnimator anim = ValueAnimator.ofFloat(currentX, Screen.getWidth());
		anim.setDuration(ANIMATION_DELAY);
		anim.start();
		
		anim.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				// 位移
				float x = (float) (animation.getAnimatedValue());
				findViewById(android.R.id.content).setX(x);
				// 越来越透明
				alpha = (int) ((1.0 - x / Screen.getWidth()) * 255);
				((View)findViewById(android.R.id.content).getParent()).setBackgroundColor(Color.argb(alpha, shadowRed, shadowGreen, shadowBlue));
			}
		});
		
		anim.addListener(new AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {
			}
			@Override
			public void onAnimationRepeat(Animator animation) {
			}
			@Override
			public void onAnimationEnd(Animator animation) {
				finish();
			}
			@Override
			public void onAnimationCancel(Animator animation) {
			}
		});
	}
	
	
	/**
	 * 子类重写该方法时，需要把super.onBackPressed();放到方法最后。
	 */
	@Override
	public void onBackPressed() {
		float currentX = findViewById(android.R.id.content).getX();
		finshThisActivity(currentX);
	}

	/**
	 * Activity被滑动到中途时，滑回去~
	 */
	private void rebackToLeft(float currentX){
		ObjectAnimator.ofFloat(findViewById(android.R.id.content), "X", currentX, 0).setDuration(300).start();		
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		onTouchEvent(ev);
		return super.dispatchTouchEvent(ev);
	}
}
