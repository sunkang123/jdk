package com.java.reflect.merbers.methods;

import java.lang.reflect.Method;


/**
 * 反射的无参的调用的测试
 *
 * 对于反射调用无参的情况 ，符合调用的方式有三种：
 * 1.   m.invoke(obj)    传入一个对象，参数不传
 * 2.   m.invoke(obj,null)    参数传null,编译会有警告，但是可以正常调用
 * 3.   m.invoke(obj,new ObJect[0])   参数传一个空的数组，对于可变参数来说相当于没有参数
 */
public class MethodTroubleToo {

    public void ping() {
        System.out.format("PONG!%n");
    }

    /**
     * foo(Object... o) 在编译的时候会 参数类型转变为数组类型，也就是foo(Object[] o)
     * @param args
     */
    public void foo(Object ... args){

    }

    public static void main(String... args) {
        args = new String[]{ "3"};
        try {
            MethodTroubleToo mtt = new MethodTroubleToo();
            Method m = MethodTroubleToo.class.getMethod("ping");

            switch (Integer.parseInt(args[0])) {
                case 0:
                    m.invoke(mtt);                 // works
                    break;
                case 1:
                    m.invoke(mtt, null);           // works (expect compiler warning) 编译警告，但是可以成功运行
                break;
                case 2:
                    Object arg2 = null;           //arg2 的类型为Object,实际上是没有参数类型进行匹配的
                    m.invoke(mtt, arg2);           // IllegalArgumentException
                    break;
                case 3:                             //new Object[0]创建了一个空的数组， 对应可变参数来说，相当于没有传入参数
                    m.invoke(mtt, new Object[0]);  // works
                    break;
                case 4:
                    Object arg4 = new Object[0];  //new Object[0]存储在一个Object中，将Object对待，而原有的方法是没有参数的，将会不匹配
                    m.invoke(mtt, arg4);           // IllegalArgumentException
                    break;
                default:
                    System.out.format("Test not found%n");
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}