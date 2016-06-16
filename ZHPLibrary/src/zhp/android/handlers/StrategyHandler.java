package zhp.android.handlers;

import zhp.android.strategies.HandlerContext;
import zhp.android.strategies.IStrategy;
import android.os.Handler;
import android.os.Message;

/**
 * 用策略实现的Handler
 * @author 郑海鹏
 * @since 2015年6月8日
 */
public class StrategyHandler extends Handler {

	@Override
	public void handleMessage(Message msg) {
		if(msg.obj instanceof IStrategy){
			IStrategy strategy = null;
			try {
				strategy = (IStrategy) msg.obj;
			} catch (ClassCastException e) {
				zhp.android.debug.Debug.Log(this.getClass().getName(), "强制类型转换时出错。");
			}
			HandlerContext context = new HandlerContext(strategy);
			context.run();
		}
		super.handleMessage(msg);
	}
	
	/**
	 * 执行一个策略
	 * @param strategy
	 */
	public void execStrategy(IStrategy strategy){
		if(strategy == null){
			return;
		}
		Message msg = Message.obtain();
		msg.obj = strategy;
		zhp.android.debug.Debug.Log(this.getClass().getName(), "执行！");
		sendMessage(msg);
	}
	
	
}
