package zhp.iyalee2.classes;

import zhp.android.data.Screen;
import zhp.iyalee2.R;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class LogoAnimation {

	int PADDING;
	
	public void logoAnimation(Activity activity, RelativeLayout bgLayout, final int squareSize_dp, int padding, int duration){
		Screen.initScreenMsg(activity);
		this.PADDING = padding;
		// 创建四个方块
		ImageView green = getImageView(activity, R.drawable.square_color_primary_dark);
		ImageView blue = getImageView(activity, R.drawable.square_color_blue_5677fc);
		ImageView red = getImageView(activity, R.drawable.square_color_red_e51c23);
		ImageView yellow = getImageView(activity, R.drawable.square_color_yellow_ffeb3b);
		// 添加进布局
		addToLayout(bgLayout, green, blue, red, yellow);
		//  初始化大小
		initSize(squareSize_dp, green, blue, red, yellow);
		// 初始化各自的位置
		final int squareSize_px = (int) Screen.dp(squareSize_dp);
		int greenStartX = Screen.getWidth();
		int greenStartY = 0;
		green.setX(greenStartX);
		green.setY(greenStartY);
		
		int blueStartX = Screen.getWidth() - squareSize_px;
		int blueStartY =Screen.getHeight(); 
		blue.setX(blueStartX);
		blue.setY(blueStartY);
		
		int redStartX = 0;
		int redStartY = -squareSize_px;
		red.setX(redStartX);
		red.setY(redStartY);
		
		int yellowStartX = -squareSize_px;
		int yellowStartY = Screen.getHeight() - squareSize_px; 
		yellow.setX(yellowStartX);
		yellow.setY(yellowStartY);
		
		// 计算常量
		final double X0 = Screen.getWidth() / 2 - Screen.dp(PADDING + squareSize_dp);
		final double X1 = Screen.getWidth() / 2 + Screen.dp(PADDING);
		final double Y0 = Screen.getHeight() / 2 - Screen.dp(PADDING + squareSize_dp);
		final double Y1 = Screen.getHeight() / 2 + Screen.dp(PADDING);
		
		// 开启动画
		startAnimation(green, Type.Green, duration, greenStartX, (int) X0, X0, X1, Y0, Y1, squareSize_px);
		startAnimation(blue, Type.Blue, duration, blueStartX, (int) X1, X0, X1, Y0, Y1, squareSize_px);
		startAnimation(red, Type.Red, duration, redStartX, (int) X0, X0, X1, Y0, Y1, squareSize_px);
		startAnimation(yellow, Type.Yellow, duration, yellowStartX, (int) X1, X0, X1, Y0, Y1, squareSize_px);
	}
	
	/**
	 * 初始化四个方块的大小
	 * @param squareSize_dp
	 * @param imageViews
	 */
	private void initSize(int squareSize_dp, ImageView... imageViews) {
		int size = (int) Screen.dp(squareSize_dp);
		for(int i = 0; i < imageViews.length; i++){
			imageViews[i].getLayoutParams().height = size;
			imageViews[i].getLayoutParams().width = size;
		}
	}

	private enum Type{
		Green, Blue, Red, Yellow;
	}
	
	private void startAnimation(final ImageView imageView, final Type type, final int duration,
			final int startX, final int stopX, final double X0, final double X1,
			final double Y0, final double Y1, final double squareSize_px) {
		final ValueAnimator anim = ValueAnimator.ofFloat(startX, stopX);
		anim.setDuration(duration);
		anim.start();
		anim.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float currentX = (Float) animation.getAnimatedValue();
				imageView.setTranslationX(currentX);
				imageView.setTranslationY((float) getYByType(type, currentX, X0, X1, Y0, Y1, squareSize_px));
			}
		});
		
		PropertyValuesHolder rotation = PropertyValuesHolder.ofFloat("rotation", 0, 1080);
		ObjectAnimator.ofPropertyValuesHolder(imageView, rotation).setDuration(duration).start();
	}
	
	private double getYByType(Type type, double x, double X0, double X1, double Y0, double Y1, double squareSize_px){
		switch(type){
		case Green:
			return getGreenY(x, X0, Y0);
			
		case Blue:
			return getBlueY(x, X1, Y0, squareSize_px);
			
		case Red:
			return getRedY(x, X0, Y1, squareSize_px);
			
		case Yellow:
			return getYellowY(x, X1, Y1, squareSize_px);
		}
		android.util.Log.i("郑海鹏", "MainActivity#getYByType(): " + "这里有问题！");
		return 0;
	}
	
	private double getGreenY(double x, double X0, double Y0){
		return (x - Screen.getWidth()) * Y0 / (X0 - Screen.getWidth());
	}
	
	private double getBlueY(double x, double X1, double Y0, double squareSize_px){
		return (x - Screen.getWidth() + squareSize_px) * (Y0 - Screen.getHeight()) / (X1 - Screen.getWidth() + squareSize_px) + Screen.getHeight();
	}
	
	private double getRedY(double x, double X0, double Y1,  double squareSize_px){
		return (Y1 + squareSize_px) * x / X0 - squareSize_px;
	}
	
	private double getYellowY(double x, double X1, double Y1,  double squareSize_px){
		return ((Y1 - Screen.getHeight() + squareSize_px) / (X1 + squareSize_px)) * (x + squareSize_px) + Screen.getHeight() - squareSize_px;
	}
	
	/**
	 * 由drawable的resId创建一个ImageView
	 */
	private ImageView getImageView(Activity activity, int drawableResId){
		ImageView imageView = new ImageView(activity);
		imageView.setImageResource(drawableResId);
		return imageView;
	}
	
	/**
	 * 将一堆ImageView添加到布局中
	 * @param bgLayout	要添加到的布局
	 * @param imageViews	要添加的ImageView
	 */
	private void addToLayout(RelativeLayout bgLayout, ImageView... imageViews){
		for(int i = 0; i < imageViews.length; i++){
			bgLayout.addView(imageViews[i]);
		}
	}
}

enum Type{
	Green, Blue, Red, Yellow;
}