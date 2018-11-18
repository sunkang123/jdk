package com.java.reflect.classes;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @Project: jdk
 * @description:
 * @author: sunkang
 * @create: 2018-09-26 19:43
 * @ModificationHistory who      when       What
 **/
public class RetrievClassTest {

    //幂名内部类   跟  static Object o =new Object();  含义不同
    static Object o = new Object() {
        public void m() {}
    };

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        //1.Object.getClass()  适用于引用类型
        Class c ="foo".getClass();
        System.out.println("引用类型"+c);
        //枚举类型
        System.out.println("枚举类型:"+ E.A.getClass());
        //字节数据类型
        byte[] bytes =new byte[1024];
        System.out.println("字节数组类型"+bytes.getClass());

        System.out.println("字符串数组："+new String[]{}.getClass());
        //2. The .class Syntax
        Class b =boolean.class;
        System.out.println("原型布尔类型："+b);

        System.out.println("打印流："+java.io.PrintStream.class);

        System.out.println("int数组:"+int[][].class);
        System.out.println("String数组："+new String[][]{}.getClass());
        //3.Class.forName()
        Class sc = Class.forName("com.java.reflect.classes.RetrievClassTest");
        System.out.println("RetrievClassTest:"+sc.getName());
        //double数组
        Class cDoubleArray = Class.forName("[D");
        System.out.println("dubbo数组:"+cDoubleArray.getName());
        //String数组
        Class cStringArray = Class.forName("[[Ljava.lang.String;");
        System.out.println("String数组"+cStringArray.getName());

        //4.TYPE Field for Primitive Type Wrappers  获取包装类型的原生类型
        System.out.println("Double Type:"+Double.TYPE);

        //跟void.class 一模一样
        System.out.println("Void Type:"+ Void.TYPE);


        //5.Methods that Return Classes  返回类的方法

        //Class.getSuperclass()  返回父类
        Class superClass = javax.swing.JButton.class.getSuperclass();
        System.out.println("JButton的父类："+superClass.getName());

        //Class.getClasses()  返回该类中（包括继承的成员）公开的内部类，内部接口类，内部枚举类
        Class<?>[] publicClass = Character.class.getClasses();
        for(Class merber : publicClass){
            if(merber.isEnum()){
                System.out.println("内部枚举类："+merber);
            }else if(merber.isInterface()){
                System.out.println("内部接口类："+merber);
            }else{
                System.out.println("普通内部类："+merber);
            }
        }

        //结果为：java.lang.Character$Subset 静态内部类       java.lang.Character$UnicodeBlock  静态内部类     java.lang.Character$UnicodeScript 静态枚举
        //Class.getDeclaredClasses()  得到该类中中所有声明的类，包括内部类，内部接口，内部枚举，私有的内部类也会存在（不包括继承的内部类）
        Class<?>[] allClass = Character.class.getDeclaredClasses();
        for(Class merber : allClass){
            System.out.println(merber.getName());
        }

        /**
         * Class.getDeclaringClass()  获取声明类的意思
         * java.lang.reflect.Field.getDeclaringClass()
         * java.lang.reflect.Method.getDeclaringClass()
         * java.lang.reflect.Constructor.getDeclaringClass()
         * 都是返回一个声明的类
         */
        Field f = System.class.getField("out");
        Class declaringClass = f.getDeclaringClass();
        System.out.println("out字段声明的类："+declaringClass.getName()); //结果为java.lang.System，out字段在system类声明的




        //Class.getEnclosingClass()  内部类获取外部类的信息（也就是包围在外部类的信息）
        System.out.println("普通成员变量的类"+o.getClass());//结果为 com.java.reflect.RetrievClassTest$1
        System.out.println("普通成员变量的类"+o.getClass().getEnclosingClass()); //
        Class stateClass = Thread.State.class;
        System.out.println("Thread.State的声明的类为："+stateClass);
        System.out.println("Thread.State该类的包围类:"+stateClass.getEnclosingClass());
        System.out.println("对比内部类调用getClass方法："+stateClass.getClass());

    }

}

enum  E{
    A,B,C
}
