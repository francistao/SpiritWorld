package zhp.java.utils;

import java.util.ArrayList;

public class Utils_Analyzer {
	
	/**
	 * 把xml中所有leftLabel...rightLabel之间的内容从前往后提取出来
	 * 例如：输入 ("1#234$5#6789$#12345$6789", "#", "$")
	 * 输出 {"234", "6789", "12345"}
	 * @param xml 输入
	 * @param leftLabel 左标记
	 * @param rightLabel 右标记
	 * @return 所有满足的内容
	 */
	public static ArrayList<String> analyze(String xml, String leftLabel, String rightLabel) {
		ArrayList<String> result = new ArrayList<String>();
		int len = xml.length();
		int leftIndex = 0;

		while (leftIndex < len) {
			leftIndex = xml.indexOf(leftLabel, leftIndex) + leftLabel.length();
			int rightIndex = xml.indexOf(rightLabel, leftIndex);

			if (leftIndex < leftLabel.length() || rightIndex < 0) {
				break;
			} else {
				result.add(xml.substring(leftIndex, rightIndex));
			}
			leftIndex = rightIndex + rightLabel.length();
		}

		return result;
	}
	
	public static String analyzeFirstOne(String xml, String leftLabel, String rightLabel) {
		String result = null;
		int len = xml.length();
		int leftIndex = 0;

		while (leftIndex < len) {
			leftIndex = xml.indexOf(leftLabel, leftIndex) + leftLabel.length();
			int rightIndex = xml.indexOf(rightLabel, leftIndex);

			if (leftIndex < leftLabel.length() || rightIndex < 0) {
				break;
			} else {
				result = xml.substring(leftIndex, rightIndex);
				return result;
			}
		}

		return null;
	}
	
}
