package zhp.java.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * 
 * @author ZHP
 *	@since 2015年3月21日9:06:47
 */
public class Post {
	/**
	 * 发送post数据
	 *
	 * @param url
	 *            ， 示例："http://1.zhpblue.sinaapp.com/login.php"
	 * @param param
	 *            ， 示例："userID=100001&password=123456&submit=login"
	 * @throws Exception 无法连接服务器
	 */
	public static String sendPost(String url, String param) throws Exception {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (ConnectException e) {
			e.printStackTrace();
			throw e;
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 *	发送Post数据
	 *	@param url 目标地址
	 *	@param params 参数列表
	 * @throws Exception 无法连接服务器
	 */
	public static String sendPost(String url, ArrayList<Param> params) throws Exception{
    	String param = Param.toString(params);
    	return sendPost(url, param);
    }
}
