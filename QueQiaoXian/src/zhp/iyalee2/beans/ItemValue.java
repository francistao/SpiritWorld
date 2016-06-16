package zhp.iyalee2.beans;

import java.io.Serializable;

/**
 * 一个增强现实内容
 * @author 郑海鹏
 * @since 2015年8月23日
 */
public class ItemValue implements Serializable{
	private static final long serialVersionUID = 1L;

	// ===============================
	// 成员变量
	// ===============================	
	/**
	 * 目标图片的路径
	 */
	private String targetPath;
	
	/**
	 * 介绍
	 */
	private String profile;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 增强内容的路径
	 */
	private String containPath;
	
	/**
	 * 增强现实内容是哪种类型的
	 */
	private ContainType type;
	
	/**
	 * 创建时间，用于上传到服务器时作为主键之一
	 */
	private String createTime;

	// ===============================
	// 构造方法
	// ===============================	
	/**
	 * 构造方法，不能用于从数据库中获得item
	 * @param targetPath	目标图片的路径
	 * @param profile	介绍
	 * @param containPath	增强内容的路径
	 * @param type	增强现实内容是哪种类型的，ItemValue.ContainType.Picture等
	 */
	public ItemValue(String targetPath, String title, String profile, String containPath, ContainType type) {
		this.targetPath = targetPath;
		this.profile = profile;
		this.containPath = containPath;
		this.type = type;
		this.title = title;
		this.createTime = System.currentTimeMillis() + "";
	}
	
	/**
	 * 用于从数据库中获得Item
	 */
	public ItemValue(String targetPath, String title, String profile, String containPath, ContainType type, String createTime) {
		this.targetPath = targetPath;
		this.profile = profile;
		this.containPath = containPath;
		this.type = type;
		this.title = title;
		this.createTime = createTime;
	}
	
	// ===============================
	// getter / setter
	// ===============================	
	/**
	 * @return the targetPath
	 */
	public String getTargetPath() {
		return targetPath;
	}

	/**
	 * @param targetPath the targetPath to set
	 */
	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}

	/**
	 * @return the profile
	 */
	public String getProfile() {
		return profile;
	}

	/**
	 * @param profile the profile to set
	 */
	public void setProfile(String profile) {
		this.profile = profile;
	}

	/**
	 * @return the containPath
	 */
	public String getContainPath() {
		return containPath;
	}

	/**
	 * @param containPath the containPath to set
	 */
	public void setContainPath(String containPath) {
		this.containPath = containPath;
	}

	/**
	 * @return the type
	 */
	public ContainType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(ContainType type) {
		this.type = type;
	}

	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	/**
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	// ===============================
	// 内部类
	// ===============================	
	/**
	 * 增强现实的类型
	 * @author 郑海鹏
	 * @since 2015年7月31日
	 */
	public static enum ContainType{
		Picture, Video, Model;
		
		@Override
		public String toString(){
			switch(this){
			case Picture:
				return "Picture";
			case Video:
				return "Video";
			case Model:
				return "Model";				
			}
			return null;
		}
		
		/**
		 * 字符串转ContainType
		 */
		public static ContainType toContainType(String type){
			switch(type){
			case "Picture":
				return Picture;
			case "Video":
				return Video;
			case "Model":
				return Model;
			}
			return null;
		}
		
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("\n");
		sb.append("标题：[" + getTitle() + "]\n");
		sb.append("时间：[" + getCreateTime() + "]\n");
		sb.append("\n");
		return sb.toString();
	}

	
}
