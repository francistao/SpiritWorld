package zhp.java.utils;

import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class Utils_Exception {
	
	/**
	 * 获得一个异常的内容，以字符串的形式返回。
	 */
	public String getExceptionContains(Throwable e) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        e.printStackTrace(printWriter);
        return result.toString();
      }
	
	/**
	 * 将一个异常信息保存到文件中。
	 * @param e
	 * @param filePath
	 */
	public void saveExceptionInFile(Throwable e, String filePath){
		try {
			File file = new File(filePath);
			PrintStream stream = new PrintStream(file);
			e.printStackTrace(stream);
		} catch (Exception e2) {
			System.out.println("保存异常时出现异常!");
		}
		
		
	}
	
}
