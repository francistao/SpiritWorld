package zhp.android.widgets;

import zhp.library.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class WaterFallScrollView extends ScrollView {
	public static final int NORMAL = -1;
	LinearLayout leftLayout, rightLayout;
	Position lastPosition = Position.right;

	public WaterFallScrollView(Context context) {
		super(context);
		LayoutInflater.from(context).inflate(R.layout.waterfall, this, true);
		leftLayout = (LinearLayout) findViewById(R.id.waterfall_left);
		rightLayout = (LinearLayout) findViewById(R.id.waterfall_right);
	}
	
	@Override
	public void addView(View view){
		if(lastPosition == Position.left){
			addView(Position.right, view);
		}else{
			addView(Position.left, view);
		}
	}

	public void addView(Position position, final View child) {
		if (position == Position.left) {
			leftLayout.addView(child);
			lastPosition = Position.left;
		} else {
			rightLayout.addView(child);
			lastPosition = Position.right;
		}
	}

	@Override
	public void removeView(View view) {
		leftLayout.removeView(view);
		rightLayout.removeView(view);
	}

	public enum Position{
		left, right
	}
	
}
