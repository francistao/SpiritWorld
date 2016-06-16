package zhp.math.circle3d;

import zhp.math.cal.Rule;

public class Vector3 {
	
	/**
	 *	三个维度的值 
	 */
	public double x, y, z;
	
	/**
	 *	该向量的单位向量 
	 */
	private Vector3 unitVector;

	public Vector3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		unitVector = null;
	}
	
	/**
	 *	获得该向量的单位向量 
	 */
	public Vector3 getUnit(){
		if(unitVector != null)
			return unitVector;
		
		// 平方和
		double temp =  Rule.pow(x, 2) + Rule.pow(y, 2) + Rule.pow(z, 2);
		
		// 开根号
		temp = Math.sqrt(temp);
		
		// 求倒数
		temp = 1.0 / temp;
		
		double unitX = Rule.mul(x, temp);
		double unitY = Rule.mul(y, temp);
		double unitZ = Rule.mul(z, temp);
		
		unitVector =new Vector3(unitX, unitY, unitZ); 
		return unitVector;
	}
	
	public String toString(){
		return "(" + x + ", " + y + ", " + z + ")";
	}
}
