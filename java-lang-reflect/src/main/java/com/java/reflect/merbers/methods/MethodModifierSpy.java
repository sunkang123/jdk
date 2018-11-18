package com.java.reflect.merbers.methods;



import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static java.lang.System.out;

public class MethodModifierSpy {

    private static int count;
    private static synchronized void inc() { count++; }
    private static synchronized int cnt() { return count; }

    public static void main(String... args) {

//    	args= new  String[]{Object.class.getName(),"wait"};
//		args= new  String[]{MethodModifierSpy.class.getName(),"main"};
		args= new  String[]{String.class.getName(),"compareTo"};
	try {
	    Class<?> c = Class.forName(args[0]);
	    Method[] allMethods = c.getDeclaredMethods();
	    for (Method m : allMethods) {
		if (!m.getName().equals(args[1])) {
		    continue;
		}
		out.format("%s%n", m.toGenericString());
		out.format("  Modifiers:  %s%n", Modifier.toString(m.getModifiers()));
		//m.isVarArgs() 返回方法是否是包含可变参数
		//m.isBridge()  如果为true ,说明是一种桥接的方法，避免泛型擦除后，泛型信息不再，避免发生编译错误,所以在增加了一个桥接的方法
			/**
			 * 原有的接口信息为： public interface Comparable<T> 中的方法public int compareTo(T o);，泛型为T，泛型擦除之后，
			 * 就会变成public int compareTo(java.lang.Ojbect o)
			 *
			 * 比如String 实现了Comparable<String>该接口，必须要实现泛型擦除后的public int compareTo(java.lang.Ojbect o)该接口，编译才不会报错
			 * public int java.lang.String.compareTo(java.lang.Object)
			 *   Modifiers:  public volatile
			 *   [ synthetic=true  var_args=false bridge=true  ]
			 */
		out.format("  [ synthetic=%-5b var_args=%-5b bridge=%-5b ]%n",
			   m.isSynthetic(), m.isVarArgs(), m.isBridge());

		inc();
	    }
	    out.format("%d matching overload%s found%n", cnt(),
		       (cnt() == 1 ? "" : "s"));

        // production code should handle this exception more gracefully
	} catch (ClassNotFoundException x) {
	    x.printStackTrace();
	}
    }
}