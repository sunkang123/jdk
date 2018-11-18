package com.java.reflect.merbers.methods;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Project: jdk
 * @description:   方法的调用测试
 * @author: sunkang
 * @create: 2018-09-28 23:39
 * @ModificationHistory who      when       What
 **/
public class MethodInvokeTest {

    private  void test(String name,int age){
        System.out.println("name:"+name+"age:"+age);
    }

    private static void staticTest(String name,int age){
        System.out.println("name:"+name+",age:"+age);
    }

    private  void test(String ... args){
        System.out.println("args:"+args);
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MethodInvokeTest test  =new MethodInvokeTest();
        //1.普通方法的调用
      Method method =   MethodInvokeTest.class.getDeclaredMethod("test",String.class,int.class);
      method.invoke(test,"sunkang",22);

      //2.参数为可变参数的或者数组的情况的调用
        Method vargMethod=   MethodInvokeTest.class.getDeclaredMethod("test",String[].class);
        //这里必须强转为一个Object对象才可以
        vargMethod.invoke(test, (Object)new String[]{"sun","kang","Mr"});

        //3.静态方法的反射调用,此时调用的对象一般传入null就行，传入test对象也是可以的，但是没有意义
        Method staticMethod=   MethodInvokeTest.class.getDeclaredMethod("staticTest",String.class,int.class);
        staticMethod.invoke(null, "sunkang",22);
    }
}
