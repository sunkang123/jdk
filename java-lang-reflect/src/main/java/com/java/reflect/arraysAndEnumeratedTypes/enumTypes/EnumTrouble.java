package com.java.reflect.arraysAndEnumeratedTypes.enumTypes;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static java.lang.System.out;


enum Charge {
    POSITIVE, NEGATIVE, NEUTRAL;
     Charge() {
        out.format("under construction%n");
    }
}
/**
 * 不能通过反射创建枚举类型  ，会报错
 *
 */
public class EnumTrouble {

    public static void main(String... args) {
        try {
            Class<?> c = Charge.class;
            Constructor[] ctors = c.getDeclaredConstructors();
            for (Constructor ctor : ctors) {
                out.format("Constructor: %s%n", ctor.toGenericString());
                ctor.setAccessible(true);
                ctor.newInstance();
            }
            // production code should handle these exceptions more gracefully
        } catch (InstantiationException x) {
            x.printStackTrace();
        } catch (IllegalAccessException x) {
            x.printStackTrace();
        } catch (InvocationTargetException x) {
            x.printStackTrace();
        }
    }
}