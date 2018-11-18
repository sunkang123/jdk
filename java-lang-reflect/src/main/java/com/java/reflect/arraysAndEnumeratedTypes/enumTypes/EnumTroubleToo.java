package com.java.reflect.arraysAndEnumeratedTypes.enumTypes;

import java.lang.reflect.Field;

enum E0 { A, B }
enum E1 { A, B }

class ETest {
    private E0 fld = E0.A;
}

/**
 * 设置不相容的枚举值会报错
 *
 * 设置的值必须满足下面的情况，X为声明的枚举类型，y为设置的枚举值
 *  *    X.class.isAssignableFrom(Y.class) == true
 * 必须为x的子类或者就是x
 *
 */

public class EnumTroubleToo {
    public static void main(String... args) {
	try {
	    ETest test = new ETest();
	    Field f = test.getClass().getDeclaredField("fld");
	    f.setAccessible(true);
 	    f.set(test, E1.A);  // IllegalArgumentException

        // production code should handle these exceptions more gracefully
	} catch (NoSuchFieldException x) {
	    x.printStackTrace();
	} catch (IllegalAccessException x) {
	    x.printStackTrace();
	}
    }
}