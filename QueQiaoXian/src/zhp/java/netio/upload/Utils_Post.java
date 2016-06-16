package zhp.java.netio.upload;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * 模拟post传输
 * 
 * @author 郑海鹏
 * @since 2015年9月15日
 */
public class Utils_Post {
	// ========================================
	// 常量
	// ========================================
	final String boundary = "**********";
	final String enter = "\r\n";
	final String twoHyphens = "--";

	// ========================================
	// 公有方法
	// ========================================
	/**
	 * 基方法。 post 文件和数据到服务器
	 * 
	 * @param serverUrl
	 *            服务器的链接
	 * @param params
	 *            参数列表
	 * @param filePath
	 *            文件路径
	 * @param fileSimpleName
	 *            上传到服务器时的名字
	 * @return 服务器返回的信息
	 * @throws Exception
	 */
	public String post(URL serverUrl, ArrayList<Param> params, ArrayList<FileParam> files)
			throws Exception {
		try {
			HttpURLConnection connection = (HttpURLConnection) serverUrl.openConnection();
			connection.setChunkedStreamingMode(2 << 16); // 128*1024，可以防止因内存不足崩溃
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setRequestProperty("Charset", "utf-8");
			connection.setRequestProperty("Content-Type", "multipart/form-data; boundary="
					+ boundary);
			connection.connect();

			// 上传数据
			DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
			// 1. 普通数据
			if (params != null) {
				dos.writeUTF(Param.toString(params));
			}

			// 2. 文件数据
			if (files != null) {
				for (int i = 0; i < files.size(); i++) {
					if (i == files.size() - 1) {
						postFile(files.get(i), dos, true);
					} else {
						postFile(files.get(i), dos, false);
					}

				}
			}

			dos.flush();
			dos.close();

			// 获得返回参数
			InputStreamReader isr = new InputStreamReader(connection.getInputStream(), "utf-8");
			BufferedReader br = new BufferedReader(isr);

			StringBuilder reply = new StringBuilder();
			String temp = null;
			while ((temp = br.readLine()) != null) {
				reply.append(temp);
			}
			br.close();
			isr.close();
			return reply.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * post 文件和数据到服务器
	 * 
	 * @param serverUrl
	 *            服务器的链接
	 * @param params
	 *            参数列表
	 * @param filePath
	 *            文件路径
	 * @param fileSimpleName
	 *            上传到服务器时的名字
	 * @return 服务器返回的信息
	 */
	public String post(String serverUrl, ArrayList<Param> params, ArrayList<FileParam> files)
			throws Exception {
		URL url = new URL(serverUrl);
		return post(url, params, files);
	}

	/**
	 * 单纯地上传参数
	 */
	public String postParam(String serverUrl, ArrayList<Param> params) throws Exception {
		return post(serverUrl, params, null);
	}

	/**
	 * 单纯地上传文件
	 */
	public String postFile(String serverUrl, ArrayList<FileParam> files) throws Exception {
		return post(serverUrl, null, files);
	}

	/**
	 * 上传数据 + 上传一个文件
	 */
	public String post(URL serverUrl, ArrayList<Param> params, String filePath,
			String fileSimpleName) throws Exception {
		FileParam param = new FileParam(filePath, fileSimpleName, null);
		ArrayList<FileParam> files = new ArrayList<FileParam>();
		files.add(param);
		return post(serverUrl, params, files);
	}

	/**
	 * 上传数据 + 上传一个文件
	 */
	public String post(String serverUrl, ArrayList<Param> params, String filePath,
			String fileSimpleName) throws Exception {
		URL url = new URL(serverUrl);
		return post(url, params, filePath, fileSimpleName);
	}

	// ========================================
	// 私有方法
	// ========================================
	/**
	 * 上传文件
	 */
	private void postFile(FileParam fileParam, DataOutputStream dos, boolean isEnd)
			throws IOException {
		String filePath = fileParam.getFilePath();
		if (filePath != null && !filePath.equals("")) {
			// 判断文件是否存在
			File file = new File(filePath);
			if (!file.exists() || file.isDirectory()) {
				android.util.Log.i("郑海鹏", "Utils_Post#postFile(): " + "传入的文件路径下没有文件，或者路径是一个文件夹。");
				android.util.Log.i("郑海鹏", "Utils_Post#postFile(): " + "路径为：" + filePath);
				throw new IllegalArgumentException(
						"zhp.java.netio.upload.PoastParam#post(): 传入的文件路径下没有文件，或者路径是一个文件夹。");
			} else {
				dos.writeBytes("Content-Disposition: form-data; name=\""
						+ fileParam.getFormDataName() + "\"; filename=\""
						+ fileParam.getSimpleName() + "\"" + enter);
				dos.writeBytes("Content-Type: application/octet-stream\r\n\r\n");
				// 从本地读取文件流
				FileInputStream fis = new FileInputStream(file);
				byte[] buffer = new byte[8192];// 8K缓存
				int count = 0;
				while ((count = fis.read(buffer)) != -1) {
					dos.write(buffer, 0, count);
				}
				if (isEnd) {
					dos.writeBytes(enter + twoHyphens + boundary + twoHyphens + enter);
				} else {
					dos.writeBytes(enter + twoHyphens + boundary + enter);
				}

				fis.close();
			}
		}

	}

}
