package com.java.reflect.merbers.field;


import sun.misc.URLClassPath;

import java.lang.reflect.Field;

public class FieldTrouble {
    public int val;
    public static void main(String... args) {
		FieldTrouble ft = new FieldTrouble();
		try {
			Class<?> c = ft.getClass();
			Field f = c.getDeclaredField("val");
			//f.setInt(ft, 42)会抛出IllegalArgumentException异常，参数类型不合法异常，val本身是个Integere类型，但是设置却是int类型
			//此时编译器并不能自动装箱，要转换的类型必须符合Class.isAssignableFrom()
		  	f.setInt(ft, 42);
			System.out.println(f.getDeclaringClass());
			System.out.println(Integer.class.isAssignableFrom(int.class) );
			System.out.println(int.class.isAssignableFrom(Integer.class) );

			//成功设置
			f.set(ft, new Integer(43));

			//只能转换为Class.isAssignableFrom()中的类型
			// 比如Nuber的子类，Integer,或者Number,反射调用的时候,符合 声明类型.isAssignableFrom(设置的类型)
			System.out.println(Number.class.isAssignableFrom(Integer.class));
			System.out.println(Number.class.isAssignableFrom(Float.class));
			c = Foo.class;
			Field f2 = c.getDeclaredField("va2");
			//加了 final字段表示不可改变.加了该修饰符，不可在反射进行设置值
			// 私有成员对外部类是不可见的，如果直接通过反射调用，会报错，需要设置访问权限，setAccessibel为true
			f2.setAccessible(true);
			f2.set(ft,new Integer(12));
			f2.set(ft,new Float(12));
			System.out.println(Foo.getNumber());
	} catch (NoSuchFieldException x) {
	    x.printStackTrace();
 	} catch (IllegalAccessException x) {
 	    x.printStackTrace();
	}
    }
}
class Foo{
	private    static   Number va2 = null;

	public static Number getNumber() {
		return  va2;
	}
}
