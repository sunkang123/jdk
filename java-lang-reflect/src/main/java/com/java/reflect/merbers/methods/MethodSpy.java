package com.java.reflect.merbers.methods;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

import static java.lang.System.out;

/**
 * 扫描类中方法的信息 (获取方法的名称，返回值，参数，异常信息)
 */
public class MethodSpy {
    private static final String fmt = "%24s: %s%n";

    public static void main(String... args) {
        args = new String[]{Class.class.getName(), "getConstructor"};
        try {
            Class<?> c = Class.forName(args[0]);
            Method[] allMethods = c.getDeclaredMethods();
            for (Method m : allMethods) {
                if (!m.getName().equals(args[1])) {
                    continue;
                }
                out.format("%s%n", m.getName());
                //对比方法toGenericString 和toString 的信息，发现toGenericString有泛型信息输出
                out.format("%s%n", m.toGenericString());
                out.format("%s%n", m.toString());

                //得到方法的返回类型的信息
                out.format(fmt, "ReturnType", m.getReturnType());
                out.format(fmt, "GenericReturnType", m.getGenericReturnType());

                //得到参数类型的信息
                Class<?>[] pType = m.getParameterTypes();
                Type[] gpType = m.getGenericParameterTypes();
                for (int i = 0; i < pType.length; i++) {
                    out.format(fmt, "ParameterType", pType[i]);
                    out.format(fmt, "GenericParameterType", gpType[i]);
                }

                //得到异常的信息
                Class<?>[] xType = m.getExceptionTypes();
                Type[] gxType = m.getGenericExceptionTypes();
                for (int i = 0; i < xType.length; i++) {
                    out.format(fmt, "ExceptionType", xType[i]);
                    out.format(fmt, "GenericExceptionType", gxType[i]);
                }
            }
            // production code should handle these exceptions more gracefully
        } catch (ClassNotFoundException x) {
            x.printStackTrace();
        }
    }
}