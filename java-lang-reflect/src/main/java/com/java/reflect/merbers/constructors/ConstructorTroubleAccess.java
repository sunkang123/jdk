package com.java.reflect.merbers.constructors;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;



/**
 * 访问私有的构造函数报错
 */
public class ConstructorTroubleAccess {
    public static void main(String... args) {
	try {
	    Constructor c = Deny.class.getDeclaredConstructor();
//  	    c.setAccessible(true);   // solution
	    c.newInstance();
        // production code should handle these exceptions more gracefully
	} catch (InvocationTargetException x) {
	    x.printStackTrace();
	} catch (NoSuchMethodException x) {
	    x.printStackTrace();
	} catch (InstantiationException x) {
	    x.printStackTrace();
	} catch (IllegalAccessException x) {
	    x.printStackTrace();
	}
    }
}
class Deny {
	private Deny() {
		System.out.format("Deny constructor%n");
	}
}