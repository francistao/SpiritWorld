package zhp.android.autoUpdate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import zhp.android.data.FinalValue;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class Thread_DownloadApk extends Thread{
	Context context;
	URL url;
	String apkSimpleName;
	String folderPath;
	String appName;
	
	public Thread_DownloadApk(String appName, String url, Context context){
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace(); 
		}
		this.context = context;
		this.appName = appName;
	}
	
	@Override
	public void run(){
		try {
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			
			InputStream is = conn.getInputStream();
			
			folderPath = FinalValue.FOLDER_BASE_PATH + "apk/";
			zhp.android.debug.Debug.Log(this.getClass().getName(), folderPath);
			File folder = new File(folderPath);
			folder.mkdirs();
			
			apkSimpleName = new String(this.appName + "_" + System.currentTimeMillis() + ".apk");
			File apk = new File(folderPath + apkSimpleName);
			apk.createNewFile();
			
			FileOutputStream fos = new FileOutputStream(apk);
			byte buffer[] = new byte[1024];
			do {
				int numread = is.read(buffer);
				if(numread <= 0){
					installApk();
					break;
				}
				fos.write(buffer, 0, numread);
			} while (true);
			fos.close();
			is.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void installApk(){
		File apk = new File(folderPath + apkSimpleName);
		if(!apk.exists()){
			return;
		}
		
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.parse("file://" + apk.toString()),
				"application/vnd.android.package-archive");
		this.context.startActivity(intent);
	}
}
