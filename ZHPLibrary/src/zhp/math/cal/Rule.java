package zhp.math.cal;


import java.math.BigDecimal;
import java.math.BigInteger;

/** 这个类用来计算加减乘除，避免 double 运算得到结果的精度不够 */
public class Rule {
	
	public static final int DEF_DIV_SCALE = 20;  
	public static final int Round_SCALE = 15;
	public static final double PI = 3.1415926535897932384626433832795;
	public static final double  e = 2.7182818284590452353602874713527;	//小数点后31位

	public static void main(String[] args){
//		double d = sin(0.005, "DEG");
//		System.out.println(d);
		double result = mul(2.8, 2.8);
		System.out.println("2.8 * 2.8 = " + result);
	} 
	
    public static double add(double d1,double d2){  
        BigDecimal b1=new BigDecimal(Double.toString(d1));  
        BigDecimal b2=new BigDecimal(Double.toString(d2));  
        return b1.add(b2).doubleValue();
    }  
      
    public static double sub(double d1,double d2){  
        BigDecimal b1=new BigDecimal(Double.toString(d1));  
        BigDecimal b2=new BigDecimal(Double.toString(d2));  
        return b1.subtract(b2).doubleValue();            
    }  
      
    public static double mul(double d1,double d2){  
        BigDecimal b1=new BigDecimal(Double.toString(d1));  
        BigDecimal b2=new BigDecimal(Double.toString(d2));  
        //BigDecimal b = new BigDecimal(Double.toString(Math.PI));
        return b1.multiply(b2).doubleValue();            
    }  
      
    public static double div(double d1,double d2){    
        return div(d1,d2,DEF_DIV_SCALE);            
    }  
      
    public static double div(double d1,double d2,int scale){  
        if(scale<0){  
            throw new IllegalArgumentException("The scale must be a positive integer or zero");  
        }
        BigDecimal b1=new BigDecimal(Double.toString(d1));  
        BigDecimal b2=new BigDecimal(Double.toString(d2));  
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }  
    
    public static double pow(double d1, int n){
    	try {
    		BigDecimal b1 = new BigDecimal(Double.toString(d1));
        	return b1.pow(n).doubleValue();	
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return 0;
    }
    //四舍五入
  	public static double round(double v){
    	return round(v, Round_SCALE);
    }
    
    //带精度的四舍五入
    public static double round(double v,int scale){ 
        if(scale<0){ 
            throw new IllegalArgumentException("The scale must be a positive integer or zero"); 
        } 
        BigDecimal b = new BigDecimal(Double.toString(v)); 
        BigDecimal one = new BigDecimal("1"); 
        return b.divide(one,scale,BigDecimal.ROUND_HALF_UP).doubleValue(); 
    } 
    
    /**
     *	@param	s 可以为"sin", "cos", "tan"
     *	@param	angle的单位是角度
     */
    public static double ANGLE(String s, double angle){
    	double d = angle*PI/180;  
    	if( s == "sin"){
    		d = Math.sin(d);
    	}else if( s == "cos"){
    		d = Math.cos(d);
    	}else{
    		d = Math.tan(d);
    	}
    	d = round(d);
    	return d;
    }
    
    /** 外部调用 */
    public static double sin(double num, AngleType type){
    	double d;
    	if( type == AngleType.DEG){
    		d = ANGLE("sin", num);
    	}else{
    		d = Math.sin(num);
    	}
    	return d;
    }
    
    /** 外部调用 */
    public static double cos(double num, AngleType type){
    	double d;
    	if( type == AngleType.DEG){
    		d = ANGLE("cos", num);
    	}else{
    		d = Math.cos(num);
    	}
    	return d;
    }
    
    /** 外部调用 */
    public static double tan(double num, AngleType type){
    	double d;
    	if( type == AngleType.DEG){
    		d = ANGLE("tan", num);
    	}else{
    		d = Math.tan(num);
    	}
    	return d;
    }
    
    /**
     * 两个十六进制数相加
     */
    public String add_16(String a, String b){
		BigInteger aa = new BigInteger(a, 16);
		BigInteger result = aa.add(new BigInteger(b, 16));
		return result.toString(16).toUpperCase();
	}
    
}












