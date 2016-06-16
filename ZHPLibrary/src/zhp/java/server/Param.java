package zhp.java.server;

import java.util.ArrayList;

public class Param {
	
	/**
	 * 参数的名字
	 */
	public String varName;
	
	/**
	 *	参数的值
	 */
	public String varValue;
	
	/**
	 * 构造一个参数
	 * @param name 参数的名字
	 * @param value 参数的值
	 */
	public Param(String name, String value){
		this.varName = name;
		this.varValue = value;
	}

	@Override
	public String toString() {
		return varName + "=" + varValue + "&";
	}
	
	/**
	 *	将一个参数的数组转化为post时用到的字符串。
	 */
	public static String toString(ArrayList<Param> params){
		if(params == null){
			return "";
		}
		
		String param = "";
		
		int size = params.size();
		
		for(int i = 0; i < size - 1; i ++){
			param += params.get(i).toString();
		}
		param += params.get(size - 1).varName + "=" + params.get(size - 1).varValue;
		
		return param;
	}
	
}
