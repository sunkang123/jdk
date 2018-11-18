package com.java.reflect.merbers.methods;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * InvocationTargetException  包装了所有的非检测和检测的异常，当方法被调用的时候，可以用InvocationTargetException来抓取异常
 */
public class MethodTroubleReturns {
    private void drinkMe(int liters) {
        if (liters < 0)
            throw new IllegalArgumentException("I can't drink a negative amount of liquid");
    }

    public static void main(String... args) {
        try {
            MethodTroubleReturns mtr = new MethodTroubleReturns();
            Class<?> c = mtr.getClass();
            Method m = c.getDeclaredMethod("drinkMe", int.class);
            m.invoke(mtr, -1);
        } catch (InvocationTargetException x) {
            Throwable cause = x.getCause();
            System.err.format("drinkMe() failed: %s%n", cause.getMessage());
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}