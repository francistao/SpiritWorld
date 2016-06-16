package zhp.java.server;

import java.util.ArrayList;

/**
 * 创建这个类时，是用于上传文件时同时上传参数。
 * 
 * @author 郑海鹏
 * @since 2015年5月8日21:18:21
 */
public class FormDataParam extends Param{

	public FormDataParam(String name, String value) {
		super(name, value);
	}
	
	/**
	 * 按表单格式返回该参数的内容
	 */
	public String toFormString(String boundary){
		final String end = "\r\n";
		final String twoHyphens = "--";

		StringBuilder sb = new StringBuilder();

		sb.append(twoHyphens + boundary + end);
		sb.append("Content-Disposition: form-data; name=\"" + this.varName + "\"" + end + end);
		sb.append(this.varValue + end);

		return sb.toString();
	}
	
	/**
	 * 将一个参数列表转化成要发送的字符串, 以表单形式
	 */
	public static String toFormString(ArrayList<FormDataParam> params, String boundary){
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < params.size(); i++){
			sb.append(params.get(i).toFormString(boundary));
		}
		
		return sb.toString();
	}
	
}
