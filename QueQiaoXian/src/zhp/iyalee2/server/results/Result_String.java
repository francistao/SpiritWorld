package zhp.iyalee2.server.results;

import zhp.java.server.IResult;

public class Result_String implements IResult{
	String contain;	
	
	public Result_String(String contain){
		this.contain = contain;
	}
	
	/**
	 * @throws IllegalArgumentException	当内容为null时抛出异常
	 */
	public String getContain(){
		return this.contain;
	}
	
}
