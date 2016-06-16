package zhp.java.exceptions;

/**
 * 返回值无效
 * 
 * @author 郑海鹏
 * @since	2015年5月27日23:15:26
 */
public class Exception_ReturnValueInvalid extends Exception {
	private static final long serialVersionUID = 7517079675027508290L;

	public Exception_ReturnValueInvalid(String exceptionInfo){
		super(exceptionInfo);
	}
}
