package zhp.android.strategies;

/**
 * 上下文，用于策略模式。
 * @author 郑海鹏
 * @since 2015年6月8日
 */
public class HandlerContext {
	IStrategy strategy;
	
	/**
	 * 构造方法。
	 */
	public HandlerContext(IStrategy strategy){
		this.strategy = strategy;
	}
	
	/**
	 * 执行策略。
	 */
	public void run(){
		if(this.strategy == null){
			zhp.android.debug.Debug.Log(this.getClass().getName(), "this.strategy == null");
			return;
		}
		this.strategy.run();
	}
	
}
