package zhp.iyalee2.beans;

public class OwnItemData {

	private String title, profile;
	private int pictureId;
	
	public OwnItemData(String title, String profile, int pictureId) {
		this.title = title;
		this.profile = profile;
		this.pictureId = pictureId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * @return the pictureId
	 */
	public int getPictureId() {
		return pictureId;
	}

	/**
	 * @param pictureId the pictureId to set
	 */
	public void setPictureId(int pictureId) {
		this.pictureId = pictureId;
	}
	
	
	
}
