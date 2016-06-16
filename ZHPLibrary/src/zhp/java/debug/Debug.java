package zhp.java.debug;

public class Debug {
	
	public static boolean isDebug = false;
	
	public static void println(Object obj){
		if(isDebug){
			System.out.println(obj);
		}
	}
	
}
