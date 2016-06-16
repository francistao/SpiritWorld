package zhp.java.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Utils_Common {

	/**
	 *	获得系统时间，以格式“年-月-日  时：分”返回 
	 */
	public static String getTime(){
		GregorianCalendar calendar = new GregorianCalendar();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		String time = year + "-" + month + "-" + day + "  " + ((hour > 9) ? hour : ("0" + hour)) + ":" + ((min > 9) ? min : ("0" + min));
		
		return time;
	}
}
