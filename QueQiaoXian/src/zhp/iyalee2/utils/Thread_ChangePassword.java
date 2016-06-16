package zhp.iyalee2.utils;

import zhp.iyalee2.ChangePassword;
import zhp.iyalee2.server.results.Result_ChangePassword;
import zhp.iyalee2.server.servers.Server_ChangePassword;
import android.os.Handler;

/**
 * 修改密码。
 * @author 郑海鹏
 * @since 2015年5月24日11:25:33
 */
public class Thread_ChangePassword extends AHandlerThread{
	String userName, oldPassword, newPassword;
	
	public Thread_ChangePassword(Handler handler, String userName, String oldPassword, String newPassword) {
		super(handler);
		this.userName = userName;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	@Override
	public void run() {
		Result_ChangePassword result = new Server_ChangePassword().getServer(userName, oldPassword, newPassword);
		if(result == Result_ChangePassword.CHANGE_SUCCESS){
			handler.sendEmptyMessage(ChangePassword.CHANGE_SUCCESS);
		}else if(result == Result_ChangePassword.CHANGE_FAIL){
			handler.sendEmptyMessage(ChangePassword.CHANGE_FAIL);
		}else if(result == Result_ChangePassword.CAN_NOT_CONNECT_TO_SERVER){
			handler.sendEmptyMessage(ChangePassword.CANNOT_CONN_SERVER);
		}else{
			handler.sendEmptyMessage(ChangePassword.UNKNOW_RESULT);
		}
		
	}
	
}
