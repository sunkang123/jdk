package com.java.reflect.merbers.methods;

import java.lang.reflect.Method;

/**
 * 由于泛型会被擦除，编译器会用其上限类型替换泛型类型，<T> 的T上限就是 Object, <T extends Integer>那么T就是上限就是Integer
 * @param <T>
 */
public class MethodTrouble<T>  {
	//编译之后就是   public void com.java.reflect.merbers.methods.MethodTrouble.lookup(java.lang.Object)
	//   T extends Integer 那么就是上限就是Integer
	public void lookup(T t) {}
	public void find(Integer i) {}

	public static void main(String... args) {
		args = new  String[]{"lookup","java.lang.Integer"};
		//解决方案可以
//		args  = new String[]{"lookup","java.lang.Object"};
		try {
			String mName = args[0];
			Class cArg = Class.forName(args[1]);

			Class<?> c = (new MethodTrouble<Integer>()).getClass();
			Method m = c.getMethod(mName, cArg);
			System.out.format("Found:%n  %s%n", m.toGenericString());
		} catch (NoSuchMethodException x) {
			x.printStackTrace();
		} catch (ClassNotFoundException x) {
			x.printStackTrace();
		}
	}
}