package zhp.android.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import zhp.java.server.FormDataParam;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.Images.ImageColumns;

public class Utils_Http {

	/**
	 *	获得Uri的真实的绝对路径
	 */
	public String getRealFilePath(final Context context, final Uri uri) {
		if (null == uri){
			zhp.android.debug.Debug.Log(this.getClass().getName() + "#getRealFilePath()", "Uri == null");
			return null;
		}
		
		final String scheme = uri.getScheme();
		String data = null;
		if (scheme == null){
			data = uri.getPath();
		} else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
			data = uri.getPath();
		} else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
			Cursor cursor = context.getContentResolver().query(uri,
					new String[] { ImageColumns.DATA }, null, null, null);
			if (null != cursor) {
				if (cursor.moveToFirst()) {
					int index = cursor.getColumnIndex(ImageColumns.DATA);
					if (index > -1) {
						data = cursor.getString(index);
					}
				}
				cursor.close();
			}
		}else{
			zhp.android.debug.Debug.Log(this.getClass().getName(), "scheme == ohter !");
		}
		
		zhp.android.debug.Debug.Log(this.getClass().getName() + "#getRealFilePath()", data);
		
		// 特意为魅族这脑残手机弄的
		String keyString = "/document/primary:";
		if(data == null && uri.getPath().contains(keyString)){
			String uriPath = uri.getPath();
			int index = uriPath.indexOf(keyString) + keyString.length();
			String back = uriPath.substring(index);
			
			StringBuilder sb = new StringBuilder("/storage/emulated/0/");
			sb.append(back);
			return sb.toString();
		}
		zhp.android.debug.Debug.Log(this.getClass().getName() + "#getRealFilePath()", data);
		return data;
	}
	
	/**
	 * 上传文件到服务器。上传的文件名key是“file”,上传的文件名value是文件在手机上的名字（SimpleName）。
	 * @param	serverUrl	服务器地址
	 * @param	fileUri	文件的uri
	 * @return	返回服务器相应结果，出现异常返回null
	 */
	public String upLoadFile(Context context, String serverUrl, Uri fileUri) {
		String filePath = getRealFilePath(context, fileUri);
		String fileSimpleName = filePath.substring(filePath.lastIndexOf('/') + 1);		
		
		String end = "\r\n";
		String twoHyphens = "--";
		String boundary = "******";
		try {
			URL url = new URL(serverUrl);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			// 设置每次传输的流大小，可以有效防止手机因为内存不足崩溃
			// 此方法用于在预先不知道内容长度时启用没有进行内部缓冲的 HTTP 请求正文的流。
			httpURLConnection.setChunkedStreamingMode(128 * 1024);// 128K
			// 允许输入输出流
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setUseCaches(false);
			// 使用POST方法
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
			httpURLConnection.setRequestProperty("Charset", "UTF-8");
			httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

			DataOutputStream dos = new DataOutputStream(httpURLConnection.getOutputStream());
			
			/* ----------------------------- */
//			// 写入参数
//			dos.writeBytes(twoHyphens + boundary + end);
//			dos.writeBytes("Content-Disposition: form-data; name=\"text\"" + end + end);
//			dos.writeBytes("123456789" + end);
//			
//			dos.writeBytes(twoHyphens + boundary + end);
//			dos.writeBytes("Content-Disposition: form-data; name=\"submit\"" + end + end);
//			dos.writeBytes("UploadIcon" + end);
			/* ----------------------------- */
			
			// 上传文件
			dos.writeBytes(twoHyphens + boundary + end);
			dos.writeBytes("Content-Disposition: form-data; name=\"" + "file" + "\"; filename=\"" + fileSimpleName + "\"" + end);
			dos.writeBytes(twoHyphens + boundary + end);

			FileInputStream fis = new FileInputStream(filePath);

			byte[] buffer = new byte[8192]; // 8k
			int count = 0;
			// 读取文件
			while ((count = fis.read(buffer)) != -1) {
				dos.write(buffer, 0, count);
			}
			fis.close();
			// 写入文件
			dos.writeBytes(end + end);
			dos.writeBytes(twoHyphens + boundary + twoHyphens + end);
			
			dos.flush();
			dos.close();

			// 读取回复
			InputStream is = httpURLConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			
			StringBuilder result = new StringBuilder();
			String temp;
			while((temp = br.readLine()) != null){
				zhp.android.debug.Debug.Log(this.getClass().getName(), "读取到一行：" + temp);
				result.append(temp);
			}
			
			
			is.close();
			
			zhp.android.debug.Debug.Log(this.getClass().getName(), "result");
			
			return result.toString();

		} catch (Exception e) {
			zhp.android.debug.Debug.Log(this.getClass().getName(), "zhp.android.Utils:	上传文件出错！");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 上传文件到服务器，同时发送参数
	 * @param serverUrl		服务器地址
	 * @param params	要发送的参数
	 * @param fileUri	要上传的文件uri
	 * @return	服务的返回数据
	 */
	public String upLoadFileWithParams(Context context, String serverUrl, ArrayList<FormDataParam> params, Uri fileUri){
		// 根据文件的Uri，获得文件的真实路径
		final String filePath = getRealFilePath(context, fileUri);
		final String fileSimpleName = filePath.substring(filePath.lastIndexOf('/') + 1);
		
		// 界符
		final String boundary = "**************";
		final String end = "\r\n";
		final String twoHyphens = "--";
		
		try {
			URL url = new URL(serverUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setChunkedStreamingMode(128 * 1024);
			
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Charset", "UTF-8");
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
			
			DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
			
			// 写入参数
			dos.writeBytes(FormDataParam.toFormString(params, boundary));
			
			// 写入文件信息
			dos.writeBytes(twoHyphens + boundary + end);
			dos.writeBytes("Content-Disposition: form-data; name=\"" + "file" + "\"; filename=\"" + fileSimpleName + "\"" + end);
			dos.writeBytes(twoHyphens + boundary + end);

			FileInputStream fis = new FileInputStream(filePath);
			byte[] buffer = new byte[8192]; // 8k
			int count = 0;
			// 从手机中读取文件，并写入到传输中
			while ((count = fis.read(buffer)) != -1) {
				dos.write(buffer, 0, count);
			}
			dos.writeBytes(end);
			dos.writeBytes(twoHyphens + boundary + twoHyphens + end);

			fis.close();
			dos.flush();
			dos.close();
			
			// 读取回复
			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);

			StringBuilder result = new StringBuilder();
			String temp;
			while ((temp = br.readLine()) != null) {
				result.append(temp);
			}

			is.close();
			zhp.android.debug.Debug.Log(this.getClass().getName(), result);

			return result.toString();
		} catch (Exception e) {
			zhp.android.debug.Debug.Log(this.getClass().getName(), "上传文件出错！");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 纯Java的上传文件。
	 * @param serverUrl	服务器地址
	 * @param params	参数列表
	 * @param filePath	文件路径
	 */
	public String upLoadFileWithParams(String serverUrl, ArrayList<FormDataParam> params, String filePath){
		final String fileSimpleName = filePath.substring(filePath.lastIndexOf('/') + 1);
		
		// 界符
		final String boundary = "**************";
		final String end = "\r\n";
		final String twoHyphens = "--";
		
		try {
			URL url = new URL(serverUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setChunkedStreamingMode(128 * 1024);
			
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Charset", "UTF-8");
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
			
			DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
			
			// 写入参数
			dos.writeBytes(FormDataParam.toFormString(params, boundary));
			
			// 写入文件信息
			dos.writeBytes(twoHyphens + boundary + end);
			dos.writeBytes("Content-Disposition: form-data; name=\"" + "file" + "\"; filename=\"" + fileSimpleName + "\"" + end);
			dos.writeBytes(twoHyphens + boundary + end);

			FileInputStream fis = new FileInputStream(filePath);
			byte[] buffer = new byte[8192]; // 8k
			int count = 0;
			// 从手机中读取文件，并写入到传输中
			while ((count = fis.read(buffer)) != -1) {
				dos.write(buffer, 0, count);
			}
			dos.writeBytes(end);
			dos.writeBytes(twoHyphens + boundary + twoHyphens + end);

			fis.close();
			dos.flush();
			dos.close();
			
			// 读取回复
			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);

			StringBuilder result = new StringBuilder();
			String temp;
			while ((temp = br.readLine()) != null) {
				result.append(temp);
			}

			is.close();
			zhp.android.debug.Debug.Log(this.getClass().getName(), result);

			return result.toString();
		} catch (Exception e) {
			zhp.android.debug.Debug.Log(this.getClass().getName(), "上传文件出错！");
			e.printStackTrace();
			return null;
		}
	}
	
}
