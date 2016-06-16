package zhp.android.data;

import java.util.HashMap;

/**
 * 无法实例化的一个类，保存当前用户的数据
 * 
 * @author 郑海鹏
 * @since 2015/3/29 11:18
 */
public class CurrentUser {
	protected static String userName = "";
	protected static String password = "";
	protected static HashMap<String, Object> data = new HashMap<String, Object>();
	public static boolean isLogin = false;

	private CurrentUser() {
	}
	
	/**
	 * 添加数据到当前用户
	 * @param key
	 * @param value
	 */
	public static void putData(String key, Object value){
		data.put(key, value);
	}
	
	/**
	 * 获取当前用户键为key的值
	 * @param key	要获得的值的键
	 * @return	key对应的值
	 */
	public static Object getData(String key){
		Object obj = data.get(key);
		return obj;
	}

	/**
	 * @return 当前用户的用户名
	 */
	public static String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public static void setUserName(String userName) {		
		CurrentUser.userName = userName;
	}

	/**
	 * @return 当前用户的密码
	 */
	public static String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public static void setPassword(String password) {
		CurrentUser.isLogin = true;
		CurrentUser.password = password;
	}

	/**
	 * 退出登录
	 */
	public static void exitAccount(){
		isLogin = false;
		userName = "";
		password = "";
		data = new HashMap<String, Object>();
	}
}
