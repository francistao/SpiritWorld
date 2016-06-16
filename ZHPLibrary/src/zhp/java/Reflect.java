package zhp.java;

public class Reflect<T> {
	
	/**
	 *	获得对象所属的类
	 */
	@SuppressWarnings("unchecked")
	public Class<Object> getClassByObj(Object obj){
		String className = obj.getClass().getName();
		
		try {
			Class<Object> c = (Class<Object>) Class.forName(className);
			return c;
		} catch (ClassNotFoundException e) {
			System.out.println("反射->getClassByObj(): 根据对象获得类时出错！");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 *	将封装类转换为对应的基本数据类 
	 */
	@SuppressWarnings("rawtypes")
	public Class packageClassToBaseClass(Class packageClass){
		
		// Integer -> int
		if(packageClass.getName() == Integer.class.getName()){
			return int.class;
		}
		
		// Double -> double
		else if(packageClass.getName() == Double.class.getName()){
			return double.class;
		}
		
		// Float -> float
		else if(packageClass.getName() == Float.class.getName()){
			return float.class;
		}
		
		// Char -> char
		else if(packageClass.getName() == Character.class.getName()){
			return char.class;
		}
		
		// Boolean -> boolean
		else if(packageClass.getName() == Boolean.class.getName()){
			return boolean.class;
		}
		
		// Byte -> byte
		else if(packageClass.getName() == Byte.class.getName()){
			return byte.class;
		}
		
		// Long -> long
		else if(packageClass.getName() == Long.class.getName()){
			return long.class;
		}		
		
		// 都不是
		else{
			return packageClass;
		}
		
	}
	
	
	/**
	 * 由类名获得对象。 该方法会自动调用该类与参数列表符合的构造函数，创建一个新对象。 如果没有与给定参数列表匹配的构造函数，将会抛出异常
	 *
	 * @param className
	 *            类名
	 * @param paramValues
	 *            参数列表
	 */
	@SuppressWarnings("rawtypes")
	public T getObjByClass(String className, Object... paramValues) {
		// 参数个数
		int paramNum = paramValues.length;
		// 创建一个数组，保存各个参数的类。
		Class[] paramTypes = new Class[paramNum];
		// 获得各参数的类
		for (int i = 0; i < paramNum; i++) {
			// 这里就把封装类转换成了基本数据类
			paramTypes[i] = packageClassToBaseClass(getClassByObj(paramValues[i]));
		}

		return getObjByClass(className, paramTypes, paramValues);
	}
	
	
	/**
	 * 由类名的参数列表获得一个新的对象。
	 * 如果没有与给定参数列表匹配的构造函数，将会抛出异常。
	 * @param className	类名
	 * @param paramTypes	每个参数的类型
	 * @param paramValues	每个参数的值
	 * @return	一个新的对象
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public T getObjByClass(String className, Class[] paramTypes, Object[] paramValues){
		// 获得构造函数并创建新的对象！
		try {
			Class<T> c = (Class<T>) Class.forName(className);
			return c.getConstructor(paramTypes).newInstance(paramValues);
		} catch (NoSuchMethodException e){
			System.out.println("反射->getObjByClass(): 没有找到对应的方法！");
		} catch (Exception e) {
			System.out.println("反射->getObjByClass(): 异常！");
		}
		System.out.println("反射->getObjByClass(): 返回空对象！");
		return null;
	}
	
}