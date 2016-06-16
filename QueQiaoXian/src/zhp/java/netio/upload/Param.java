package zhp.java.netio.upload;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author 郑海鹏
 * @since 2015年8月22日
 */
public class Param {
	// ================================
	// 常量
	// ================================
	final String boundary = "**********";
	final String enter = "\r\n";
	final String twoHyphens = "--";
	
	// ================================
	// 成员变量
	// ================================
	/**
	 * 参数的键和值
	 */
	private String name, value;

	// ================================
	// 构造方法
	// ================================
	public Param(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	// ================================
	// 重写的方法
	// ================================
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(enter + twoHyphens + boundary + enter);			
		sb.append("Content-Disposition: form-data; name=\"" + this.name + "\"" + enter + enter);
		sb.append(this.value);
		sb.append(enter + twoHyphens + boundary + enter);
		return sb.toString();
	}
	
	// ================================
	// 静态方法
	// ================================
	/**
	 * 转化为bytes流，供dos.writeBytes()用
	 */
	public static String toString(Collection<Param> params){
		StringBuilder sb = new StringBuilder("");
		
		Iterator<Param> iterator = params.iterator();
		while(iterator.hasNext()){
			sb.append(iterator.next().toString());
		}
		
		return sb.toString();
	}
}
