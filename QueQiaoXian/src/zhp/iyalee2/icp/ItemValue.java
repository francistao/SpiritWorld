//package zhp.iyalee2.icp;
//
//public class ItemValue {
//	// ===============================
//	// 成员变量
//	// ===============================	
//	/**
//	 * 目标图片的路径
//	 */
//	private String targetPath;
//	
//	/**
//	 * 介绍
//	 */
//	private String profile;
//	
//	/**
//	 * 增强内容的路径
//	 */
//	private String containPath;
//	
//	/**
//	 * 增强现实内容是哪种类型的
//	 */
//	private ContainType type;
//
//	// ===============================
//	// 构造方法
//	// ===============================	
//	/**
//	 * 构造方法
//	 * @param targetPath	目标图片的路径
//	 * @param profile	介绍
//	 * @param containPath	增强内容的路径
//	 * @param type	增强现实内容是哪种类型的，ItemValue.ContainType.Picture等
//	 */
//	public ItemValue(String targetPath, String profile, String containPath, ContainType type) {
//		this.targetPath = targetPath;
//		this.profile = profile;
//		this.containPath = containPath;
//		this.type = type;
//	}
//	
//	// ===============================
//	// getter / setter
//	// ===============================	
//	/**
//	 * @return the targetPath
//	 */
//	public String getTargetPath() {
//		return targetPath;
//	}
//
//	/**
//	 * @param targetPath the targetPath to set
//	 */
//	public void setTargetPath(String targetPath) {
//		this.targetPath = targetPath;
//	}
//
//	/**
//	 * @return the profile
//	 */
//	public String getProfile() {
//		return profile;
//	}
//
//	/**
//	 * @param profile the profile to set
//	 */
//	public void setProfile(String profile) {
//		this.profile = profile;
//	}
//
//	/**
//	 * @return the containPath
//	 */
//	public String getContainPath() {
//		return containPath;
//	}
//
//	/**
//	 * @param containPath the containPath to set
//	 */
//	public void setContainPath(String containPath) {
//		this.containPath = containPath;
//	}
//
//	/**
//	 * @return the type
//	 */
//	public ContainType getType() {
//		return type;
//	}
//
//	/**
//	 * @param type the type to set
//	 */
//	public void setType(ContainType type) {
//		this.type = type;
//	}
//
//	// ===============================
//	// 内部类
//	// ===============================	
//	/**
//	 * 增强现实的类型
//	 * @author 郑海鹏
//	 * @since 2015年7月31日
//	 */
//	public static enum ContainType{
//		Picture, Video, Model;
//	}
//}
