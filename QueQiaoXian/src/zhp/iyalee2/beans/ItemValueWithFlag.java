package zhp.iyalee2.beans;

import java.util.ArrayList;

//===============================================
// 内部类-ItemValueWithFlag
// ===============================================
public class ItemValueWithFlag extends ItemValue {
	private static final long serialVersionUID = 1L;
	
	
	private boolean downlaoded;

	public ItemValueWithFlag(String targetPath, String title, String profile, String containPath,
			ContainType type) {
		super(targetPath, title, profile, containPath, type);
	}

	public ItemValueWithFlag(ItemValue item, boolean isDownloaded) {
		super(item.getTargetPath(), item.getTitle(), item.getProfile(), item.getContainPath(), item
				.getType(), item.getCreateTime());
		this.downlaoded = isDownloaded;
	}

	public void setIsDownload(boolean isDownloaded) {
		this.downlaoded = isDownloaded;
	}

	public boolean getIsDownloaded() {
		return this.downlaoded;
	}

	/**
	 * 将ItemValue 转化为 ItemValueWithFalg
	 * 
	 * @param isDownloaded
	 *            是否已下载
	 */
	public static ArrayList<ItemValueWithFlag> castDownloaded(ArrayList<ItemValue> items,
			boolean isDownloaded) {
		ArrayList<ItemValueWithFlag> result = new ArrayList<ItemValueWithFlag>();
		if (items == null)
			return result;

		for (int i = 0; i < items.size(); i++) {
			result.add(new ItemValueWithFlag(items.get(i), isDownloaded));
		}

		return result;
	}
}
