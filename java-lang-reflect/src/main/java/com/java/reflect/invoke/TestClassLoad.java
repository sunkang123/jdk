package com.java.reflect.invoke;


import java.lang.reflect.Method;

public class TestClassLoad {
    public static void main(String[] args) throws Exception {
        Class<?> clz = Class.forName("com.java.reflect.invoke.A");
        Object o = clz.newInstance();
        Method m = clz.getMethod("foo", String.class);


        for (int i = 0; i < 16; i++) {
            if(i==15){
                m.invoke(o, Integer.toString(i));
            }else{
                m.invoke(o, Integer.toString(i));
            }
        }
    }
}
