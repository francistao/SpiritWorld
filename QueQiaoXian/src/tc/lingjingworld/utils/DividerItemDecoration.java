package tc.lingjingworld.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
	private static int[] ATTRS = { android.R.attr.listDivider };
	public static final int HORIZONTAL_LIST = 0;
	public static final int VERTICAL_LIST = 1;
	private Drawable mDivider;
	private int mOrientation;

	public DividerItemDecoration(Context paramContext, int paramInt) {
		TypedArray localTypedArray = paramContext.obtainStyledAttributes(ATTRS);
		this.mDivider = localTypedArray.getDrawable(0);
		localTypedArray.recycle();
		setOrientation(paramInt);
	}

	public void drawHorizontal(Canvas paramCanvas, RecyclerView paramRecyclerView) {
		int i = paramRecyclerView.getPaddingTop();
		int j = paramRecyclerView.getHeight() - paramRecyclerView.getPaddingBottom();
		int k = paramRecyclerView.getChildCount();
		for (int m = 0;; m++) {
			if (m >= k)
				return;
			View localView = paramRecyclerView.getChildAt(m);
			RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams) localView
					.getLayoutParams();
			int n = localView.getRight() + localLayoutParams.rightMargin;
			int i1 = n + this.mDivider.getIntrinsicHeight();
			this.mDivider.setBounds(n, i, i1, j);
			this.mDivider.draw(paramCanvas);
		}
	}

	public void drawVertical(Canvas paramCanvas, RecyclerView paramRecyclerView) {
		int i = paramRecyclerView.getPaddingLeft();
		int j = paramRecyclerView.getWidth() - paramRecyclerView.getPaddingRight();
		int k = paramRecyclerView.getChildCount();
		for (int m = 0;; m++) {
			if (m >= k)
				return;
			View localView = paramRecyclerView.getChildAt(m);
			new RecyclerView(paramRecyclerView.getContext());
			RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams) localView
					.getLayoutParams();
			int n = localView.getBottom() + localLayoutParams.bottomMargin;
			int i1 = n + this.mDivider.getIntrinsicHeight();
			this.mDivider.setBounds(i, n, j, i1);
			this.mDivider.draw(paramCanvas);
		}
	}

	public void getItemOffsets(Rect paramRect, int paramInt, RecyclerView paramRecyclerView) {
		if (this.mOrientation == 1) {
			paramRect.set(0, 0, 0, this.mDivider.getIntrinsicHeight());
			return;
		}
		paramRect.set(0, 0, this.mDivider.getIntrinsicWidth(), 0);
	}

	public void onDraw(Canvas paramCanvas, RecyclerView paramRecyclerView) {
		if (this.mOrientation == 1) {
			drawVertical(paramCanvas, paramRecyclerView);
		}else{
			drawHorizontal(paramCanvas, paramRecyclerView);
		}
	}

	public void setOrientation(int paramInt) {
		if ((paramInt != 0) && (paramInt != 1))
			throw new IllegalArgumentException("invalid orientation");
		this.mOrientation = paramInt;
	}
}