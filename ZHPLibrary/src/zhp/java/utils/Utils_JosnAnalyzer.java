package zhp.java.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import zhp.java.exceptions.Exception_BraNotComplete;

public class Utils_JosnAnalyzer {
	
	public static void main(String[] args){
		Utils_JosnAnalyzer u = new Utils_JosnAnalyzer();
//		String json = "{\"firstName\":\"Brett\",\"lastName\":\"McLaughlin\",\"email\":\"aaaa\"}";
		
		Scanner scanner = new Scanner(System.in);
		String json = scanner.nextLine();
		scanner.close();
		
		try {
			Map<String, String> result = u.analysisSimple(json);
			Set<Map.Entry<String, String>> set = result.entrySet();
			Iterator<Map.Entry<String, String>> it = set.iterator();
			while (it.hasNext()) {
				Entry<String, String> entry = it.next();
				String key = entry.getKey();
				String value = entry.getValue();
				System.out.println(key + " -> " + value);
			}
			
		} catch (Exception_BraNotComplete e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 处理简单的json语句。value是一个String对象
	 * @throws Exception_BraNotComplete 
	 * @输入示例	{"name":"Michael","age":20,"school":"nuc"}	注意年龄的写法
	 * @异常 当输入的语句大括号不匹配时返回Exception_BraNotComplete异常。
	 */
	public Map<String, String> analysisSimple(String json) throws Exception_BraNotComplete{
		// 判断json语句的第一个字符是否为'{'
		boolean isFirstLegal = json.charAt(0) == '{';
		// 判断json语句的最后一个字符是否为'}'
		boolean isLastLegal = json.charAt(json.length() - 1) == '}';
		// 判断大括号是否完整
		boolean isBigBraComplete = isFirstLegal && isLastLegal;
		if(!isBigBraComplete){
			// 当大括号不匹配时返回Exception_BraNotComplete异常
			throw new Exception_BraNotComplete();
		}else{
			json = json.substring(1, json.length() - 1);
		}
		
		// 用逗号分隔开所有的key-value对，保存的是 "key":"value"
		String[] splitByComma = json.split(",");
		
		// 用 ':' 将每一个key-value对分隔开
		Map<String, String> result = new HashMap<String, String>();
		for(int i = 0; i < splitByComma.length; i++){
			String[] keyValue = analysisKeyValueCouple(splitByComma[i]);
			System.out.println("解析到key：" + keyValue[0]);
			System.out.println("解析到value：" + keyValue[1]);
			result.put(keyValue[0], keyValue[1]);
		}
		
		return result;
	}
	
	// ===============================================
	// 私有方法
	// ===============================================
	/**
	 * @输入示例	"key":"value"
	 * @return	去掉引号后的一个数组 {key, value}
	 */
	private String[] analysisKeyValueCouple(String keyWithValue) {
		String[] splited = keyWithValue.split(":");
		// 获得去掉 引号 的key和value
		String key = stripQuotes(splited[0]);
		String value = stripQuotes(splited[1]);
		return new String[]{key, value};
	}
	
	/**
	 * 去掉一个字符串开始和结束的 '"'号。
	 * 如果只有开始有，或者只有结束有，或者开始和结束都没有，则返回原字符串。
	 */
	private String stripQuotes(String s) {
		boolean isFirst = s.charAt(0) == '"';
		boolean isLast = s.charAt(s.length() - 1) == '"';
		if (isFirst && isLast) {
			int startIndex = 1;
			int endIndex = s.length() - 1;
			String result = s.substring(startIndex, endIndex);
			return result;
		} else {
			return s;
		}
	}
	
}
/*
class CoupleString {
	String key;
	String value;

	public CoupleString(String key, String value) {
		this.key = key;
		this.value = value;
	}
}
*/