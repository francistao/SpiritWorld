package zhp.java.listeners;

/**
 * 在回调方法中没有参数。一般用于不需要结果的回调中。
 * @author 郑海鹏
 *	@since 2015年5月24日17:38:10
 */
public interface OnFinishListener extends IListener {
	
	public void onFinish();
	
}
