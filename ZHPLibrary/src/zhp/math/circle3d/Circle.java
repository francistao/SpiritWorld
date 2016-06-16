package zhp.math.circle3d;

import zhp.math.cal.AngleType;
import zhp.math.cal.Rule;

/**
 * 请勿创建一个法线垂直坐标轴的圆。
 */
public class Circle {

	/**
	 * 圆心坐标
	 */
	Vector3 center;

	/**
	 * 法线向量
	 */
	Vector3 normal;

	/**
	 * 圆的半径
	 */
	double r;

	/**
	 * U向量是一个与法向量normal垂直的、在圆平面内的向量。 它的值为：(normal.y, -normal.x, 0)。
	 */
	private Vector3 U;

	/**
	 * V向量是一个与normal向量和U向量都垂直的向量，也在圆平面内。
	 */
	private Vector3 V;

	/**
	 * @param center
	 *            圆心坐标
	 * @param normal
	 *            法线向量
	 */
	public Circle(Vector3 center, Vector3 normal, double r) {
		this.center = center;
		this.normal = normal;
		this.r = r;

//		System.out.println("center :" + center.toString());
//		System.out.println("normal :" + normal.toString());

		U = new Vector3(normal.y, -normal.x, 0.0);
		V = new Vector3(normal.x * normal.z, normal.y * normal.z, -(normal.x * normal.x + normal.y
				* normal.y));

//		System.out.println("U :" + U.toString());
//		System.out.println("V :" + V.toString());
	}

	/**
	 * 把t带入参数方程求得x, y, z。
	 * 
	 * @param type
	 *            t的类型，角度或者弧度
	 */
	public Vector3 getPostion(double t, AngleType type) {

//		System.out.println("U.unit :" + U.getUnit().toString());
//		System.out.println("V.unit :" + V.getUnit().toString());

		double cosT = Rule.cos(t, type);
		double sinT = Rule.sin(t, type);

		double x = center.x + r * (U.getUnit().x * cosT + V.getUnit().x * sinT);
		double y = center.y + r * (U.getUnit().y * cosT + V.getUnit().y * sinT);
		double z = center.z + r * (U.getUnit().z * cosT + V.getUnit().z * sinT);

		return new Vector3(x, y, z);
	}

	/**
	 *	把t带入参数方程求得x, y, z。
	 *	默认t的单位是角度
	 */
	public Vector3 getPostion(double t) {

//		System.out.println("U.unit :" + U.getUnit().toString());
//		System.out.println("V.unit :" + V.getUnit().toString());

		double cosT = Rule.cos(t, AngleType.DEG);
		double sinT = Rule.sin(t, AngleType.DEG);

		double x = center.x + r * (U.getUnit().x * cosT + V.getUnit().x * sinT);
		double y = center.y + r * (U.getUnit().y * cosT + V.getUnit().y * sinT);
		double z = center.z + r * (U.getUnit().z * cosT + V.getUnit().z * sinT);

		return new Vector3(x, y, z);
	}

}
