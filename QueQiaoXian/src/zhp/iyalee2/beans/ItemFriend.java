package zhp.iyalee2.beans;

public class ItemFriend {
	private String iconPath;
	private String userName;
	private String profile;

	public ItemFriend(String iconPath, String userName, String profile) {
		this.iconPath = iconPath;
		this.userName = userName;
		this.profile = profile;
	}

	/**
	 * @return the icon
	 */
	public String getIconPath() {
		return iconPath;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("昵称：" + getUserName() + "\n");
		sb.append("头像：" + getIconPath() + "\n");
		sb.append("简介：" + getProfile() + "\n");
		return sb.toString();
	}
	
	
}
