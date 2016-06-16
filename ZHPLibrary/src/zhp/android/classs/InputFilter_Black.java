package zhp.android.classs;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * 不允许输入指定的字符
 */
public class InputFilter_Black implements InputFilter{
	private char[] blackList;
	
	public InputFilter_Black(char[] blackList){
		this.blackList = blackList;
	}
	
	@Override
	public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart,
			int dend) {
		
		if (isContainBlackChar(source)) {
			// 拦截
			return "";
		}
		return null;
	}
	
	/**
	 * 判断这个字符串中是否包含黑名单中的字符。
	 * @return 包含返回true，不包含返回false
	 */
	private boolean isContainBlackChar(CharSequence source){
		int len = blackList.length;
		String temp = source.toString();
		
		for(int i = 0; i < len; i++){
			if(temp.contains(blackList[i] + "")){
				return true;
			}
		}
		return false;
	}
}

	
