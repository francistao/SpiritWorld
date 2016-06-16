package zhp.java.netio.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 文件下载类
 * @author 郑海鹏
 * @since 2015年8月23日
 */
public class FileDownload {
	
	/**
	 * @param fileUrl	要下载的资源路径
	 * @param dstFileName	保存的文件名（含路径）
	 */
	public void download(String fileUrl, String dstFileName){
		try {
			// 链接资源
			URL url = new URL(fileUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.connect();			
			
			// 在本地新建文件，用来保存获得的数据
			File file = new File(dstFileName);
			File folder = new File(file.getParent());
			folder.mkdirs();
			file.createNewFile();
			
			// 获得输入流，输出流
			InputStream is = conn.getInputStream();
			FileOutputStream fos = new FileOutputStream(file);
			
			// 读取数据并保存数据
			byte[] buffer = new byte[1024];
			do{
				int count = is.read(buffer);
				if(count <= 0){
					break;
				}
				fos.write(buffer, 0, count);
			}while(true);
			
			fos.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
