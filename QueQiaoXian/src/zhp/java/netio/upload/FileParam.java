package zhp.java.netio.upload;

public class FileParam {
	/**
	 * 文件在本地的路径
	 */
	private String filePath;
	
	/**
	 * 文件在服务器端收到的名字
	 */
	private String simpleName;
	
	/**
	 * 对应的表单中的key值。
	 */
	private String formDataName;
	
	/**
	 * 在服务器端，$_FILES['formDataName']['name']的值就是simpleName
	 * @param filePath	文件路径
	 * @param simpleName	上传到服务器的文件名
	 * @param formDataName	表单数据文件名，如果为null，默认为"file"
	 */
	public FileParam(String filePath, String simpleName, String formDataName) {
		this.filePath = filePath;
		this.simpleName = simpleName;
		if(formDataName == null){
			this.formDataName = "file";
		}else{
			this.formDataName = formDataName;
		}
	}
	
	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * @return the simpleName
	 */
	public String getSimpleName() {
		return simpleName;
	}
	/**
	 * @param simpleName the simpleName to set
	 */
	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}
	/**
	 * @return the formDataName
	 */
	public String getFormDataName() {
		return formDataName;
	}
	/**
	 * @param formDataName the formDataName to set
	 */
	public void setFormDataName(String formDataName) {
		this.formDataName = formDataName;
	}
	
	
}
