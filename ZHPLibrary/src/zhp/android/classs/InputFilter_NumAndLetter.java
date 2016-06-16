package zhp.android.classs;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * 只允许输入指定的字符
 */
public class InputFilter_NumAndLetter implements InputFilter{
	@Override
	public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart,
			int dend) {
		if (!source.toString().matches("[a-zA-Z0-9_-]*")) {
			return "";
		}
		return null;
	}
}

	
