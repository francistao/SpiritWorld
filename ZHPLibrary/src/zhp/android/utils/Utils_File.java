package zhp.android.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import android.content.Context;

/**
 * 进行文件操作的工具。
 * @author 郑海鹏
 * @since 2015年6月23日
 */
public class Utils_File {
	
	private static class InstanceHolder{
		static final Utils_File instance = new Utils_File();
	}

	private Utils_File() {
	}

	public static Utils_File getInstance() {
		return InstanceHolder.instance;
	}

	/**
	 * 从srcFilePath对应的文件复制到dstFilePath中。如果目的文件不存在，会创建一个新的文件。如果目的文件存在，会被覆盖掉。
	 * 
	 * @param srcFilePath
	 *            源文件路径
	 * @param dstFilePath
	 *            目的文件路径
	 * @return 成功返回true，失败返回false
	 */
	public boolean copyFile(String srcFilePath, String dstFilePath) {
		boolean result = true;

		FileInputStream fis = null;
		FileOutputStream fos = null;
		FileChannel fcIn = null;
		FileChannel fcOut = null;

		try {
			fis = new FileInputStream(srcFilePath);

			// 目的文件
			File dstFile = new File(dstFilePath);
			// 创建目的文件所在的文件夹
			File folder = dstFile.getParentFile();
			folder.mkdirs();
			// 创建目的文件
			dstFile.createNewFile();
			fos = new FileOutputStream(dstFile);

			fcIn = fis.getChannel();
			fcOut = fos.getChannel();
			fcIn.transferTo(0, fcIn.size(), fcOut);
		} catch (FileNotFoundException e) {
			result = false;
			e.printStackTrace();
		} catch (IOException e) {
			result = false;
			e.printStackTrace();
		} finally {
			try {
				if(fis != null){
					fis.close();
				}
				if(fos != null){
					fos.close();
				}				
			} catch (IOException e) {
				zhp.android.debug.Debug.Log(this.getClass().getName(), "关闭流文件时出现异常。");
			} catch (NullPointerException e){
				result = false;
				e.printStackTrace();
				zhp.android.debug.Debug.Log(this.getClass().getName(), "出现空指针异常！");
			}
			
			try {
				if(fcIn != null){
					fcIn.close();
				}
				if(fcOut != null){
					fcOut.close();
				}				
			} catch (IOException e) {
				e.printStackTrace();
				zhp.android.debug.Debug.Log(this.getClass().getName(), "关闭通道时出现异常。");
			} catch (NullPointerException e){
				result = false;
				e.printStackTrace();
				zhp.android.debug.Debug.Log(this.getClass().getName(), "出现空指针异常！");
			}
		}
		return result;
	}

	/**
	 * 从srcFilePath对应的文件复制到dstFolderPath对应的文件夹中，新的文件名为dstFileName。
	 * {@link Utils_File#copyFile(String, String)}
	 * 
	 * @param srcFilePath
	 *            源文件路径
	 * @param dstFolderPath
	 *            新文件路径, 最后不需要加‘/’
	 * @param dstFileName
	 *            目的文件名
	 * @return 成功返回true，失败返回false
	 */
	public boolean copyFile(String srcFilePath, String dstFolderPath, String dstFileName) {
		String dstFilePath = dstFolderPath + "/" + dstFileName;
		return copyFile(srcFilePath, dstFilePath);
	}

	/**
	 * 复制文件到data/data/包名/files文件夹下。 {@link Utils_File#copyFile(String, String)}
	 * 
	 * @param srcFilePath
	 *            源文件路径
	 * @return 成功返回true，失败返回false
	 */
	public boolean copyToAssets(Context context, String srcFilePath) {
		String dstFolderPath = context.getFilesDir().getAbsolutePath();

		File oldFile = new File(srcFilePath);
		String fileName = oldFile.getName();

		return copyFile(srcFilePath, dstFolderPath + "/" + fileName);
	}

	/**
	 * 复制文件到data/data/包名/files文件夹下。
	 * {@link Utils_File#copyToAssets(Context, String)}
	 * 
	 * @param srcFilePath
	 *            源文件路径
	 * @param dstName
	 *            目的文件名
	 * @return 成功返回true，失败返回false
	 */
	public boolean copyToAssets(Context context, String srcFilePath, String dstName) {
		String dstFolderPath = context.getFilesDir().getAbsolutePath();
		return copyFile(srcFilePath, dstFolderPath + "/" + dstName);
	}
}
