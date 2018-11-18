package com.java.reflect.merbers.methods;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * 方法访问权限不够导致调用错误   需要设置m.setAccessible(true);可以绕过访问的检查
 */
public class MethodTroubleAgain {
    public static void main(String... args) {
	AnotherClass ac = new AnotherClass();
	try {
	    Class<?> c = ac.getClass();
 	    Method m = c.getDeclaredMethod("m");
 	    //解决方案如下，设置 m.setAccessible(true)为true
        //m.setAccessible(true);      // solution
 	    Object o = m.invoke(ac);    // IllegalAccessException

	} catch (NoSuchMethodException x) {
	    x.printStackTrace();
	} catch (InvocationTargetException x) {
	    x.printStackTrace();
	} catch (IllegalAccessException x) {
	    x.printStackTrace();
	}
    }
}
class AnotherClass {
	private void m() {}
}