package zhp.android.widgets;

import zhp.android.utils.Utils_PhoneInfo;
import zhp.library.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.widget.ImageButton;

/**
 * 悬浮的圆形按钮，经典的+按钮
 * @author 郑海鹏
 * @since 2015年4月13日
 */
public class FloatingActionButton extends ImageButton {

    private Context ctx;
    private int bgColor;
    private int bgColorPressed;

    public FloatingActionButton(Context context) {
        super(context);
        this.ctx = context;
        init(null);
    }

    public FloatingActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.ctx = context;
        init(attrs);
    }

    public FloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.ctx = context;
        init(attrs);
    }

    @SuppressLint("NewApi")
	public FloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.ctx = context;
        init(attrs);
    }

    private Drawable createButton(int color) {
        OvalShape oShape = new OvalShape();
        ShapeDrawable sd = new ShapeDrawable(oShape);
        setWillNotDraw(false);
        sd.getPaint().setColor(color);

        ShapeDrawable sd1 = new ShapeDrawable(oShape);

        sd1.setShaderFactory(new ShapeDrawable.ShaderFactory() {
            @Override
            public Shader resize(int width, int height) {
                LinearGradient lg = new LinearGradient(0,0,0, height,
                        new int[] {
                        		Color.argb(80, 100, 0, 0),
                                Color.argb(80, 255, 0, 0)
                        }, null, Shader.TileMode.REPEAT);

                return lg;
            }
        });

        LayerDrawable ld = new LayerDrawable(new Drawable[] { sd1, sd });
        ld.setLayerInset(0, 2, 2, 0, 0);
        ld.setLayerInset(1, 0, 0, 2, 2);

        return ld;
    }

    /**
     *	颜色就是在这里设置的 
     */
    @SuppressLint("NewApi")
	private void init(AttributeSet attrSet) {
		Resources.Theme theme = ctx.getTheme();
		
		TypedArray arr = theme.obtainStyledAttributes(attrSet, R.styleable.FAB, 0, 0);
		try {
			bgColor = arr.getColor(R.styleable.FAB_bg_color, Color.rgb(180, 0, 0));
			bgColorPressed = arr.getColor(R.styleable.FAB_bg_color_pressed, Color.rgb(180, 0, 0));

			StateListDrawable sld = new StateListDrawable();
			sld.addState(new int[] { android.R.attr.state_pressed }, createButton(bgColorPressed));
			sld.addState(new int[] {}, createButton(bgColor));
			if(Utils_PhoneInfo.getInstance().getAndroidVersionCode() >= 16){
				setBackground(sld);
			}
		} catch (Throwable t) {
		} finally {
			arr.recycle();
		}

	}
}
