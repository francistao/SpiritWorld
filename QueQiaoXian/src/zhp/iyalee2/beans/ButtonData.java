package zhp.iyalee2.beans;

import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;


public class ButtonData {
	public final static int BACKGROUND_INVALID = -100;
	final static int TEXT_COLOR = Color.rgb(255, 255, 255);
	final static int TEXT_COLOR_PRESSED = Color.rgb(255, 255, 100);

	public int textColor;
	public int textColorPressed;
	public int backgroundResID;
	public String textContain;
	public View.OnClickListener listener;
	
	
	// =============================================
	//	构造方法
	// =============================================
	public ButtonData(String textContain) {
		this(TEXT_COLOR, TEXT_COLOR_PRESSED, BACKGROUND_INVALID, textContain);
	}
	
	public ButtonData(int backgroundResID, String textContain) {
		this(TEXT_COLOR, TEXT_COLOR_PRESSED, backgroundResID, textContain);
	}
	
	public ButtonData(int textColor, int textColorPressed, int backgroundResID, String textContain) {
		this(textColor, textColorPressed, backgroundResID, textContain, null);
	}
	
	public ButtonData(int textColor, int textColorPressed, int backgroundResID, String textContain, OnClickListener listener) {
		this.textColor = textColor;
		this.textColorPressed = textColorPressed;
		this.backgroundResID = backgroundResID;
		this.textContain = textContain;
		this.listener = listener;
	}

	
	// =============================================
	//	getter/setter
	// =============================================
	/**
	 * @return the textColor
	 */
	public int getTextColor() {
		return textColor;
	}

	/**
	 * @param textColor the textColor to set
	 */
	public void setTextColor(int textColor) {
		this.textColor = textColor;
	}

	/**
	 * @return the textColorPressed
	 */
	public int getTextColorPressed() {
		return textColorPressed;
	}

	/**
	 * @param textColorPressed the textColorPressed to set
	 */
	public void setTextColorPressed(int textColorPressed) {
		this.textColorPressed = textColorPressed;
	}

	/**
	 * @return the backgroundResID
	 */
	public int getBackgroundResID() {
		return backgroundResID;
	}

	/**
	 * @param backgroundResID the backgroundResID to set
	 */
	public void setBackgroundResID(int backgroundResID) {
		this.backgroundResID = backgroundResID;
	}

	/**
	 * @return the textContain
	 */
	public String getTextContain() {
		return textContain;
	}

	/**
	 * @param textContain the textContain to set
	 */
	public void setTextContain(String textContain) {
		this.textContain = textContain;
	}

	/**
	 * @return the listener
	 */
	public View.OnClickListener getListener() {
		return listener;
	}

	/**
	 * @param listener the listener to set
	 */
	public void setListener(View.OnClickListener listener) {
		this.listener = listener;
	}
}
