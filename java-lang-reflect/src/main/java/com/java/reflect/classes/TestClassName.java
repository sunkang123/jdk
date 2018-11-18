package com.java.reflect.classes;

import java.lang.reflect.Field;

/**
 * @Project: jdk
 * @description:   Class.getName()和Class.getCanonicalName()以及Class.getSimpleName()的测试
 * @author: sunkang
 * @create: 2018-10-04 23:25
 * @ModificationHistory who      when       What
 **/
interface Person{
    void eat();
}
public class TestClassName {
    //这个是一个幂名内部类的实现的一个对象
   static Person person = new Person() {
        @Override
        public void eat() {
            System.out.println("eat something");
        }
    };
   class innerClass {

   }
    public static void main(String[] args) {
       //对于普通类
        Class test= TestClassName.class;
        System.out.println("普通类getName ："+test.getName());
        System.out.println("普通类getCanonicalName ："+test.getCanonicalName());
        System.out.println("普通类getSimpleName ："+test.getSimpleName());

        //对于数组
        Class arrayClass = new String[]{}.getClass();
        System.out.println("数组getName ："+arrayClass.getName()); //[Ljava.lang.String;
        System.out.println("数组getCanonicalName ："+arrayClass.getCanonicalName()); //java.lang.String[]
        System.out.println("数组getSimpleName ："+arrayClass.getSimpleName());//String[]

        //对于内部类
        Class innerClass= innerClass.class;
        System.out.println("内部类getName ："+innerClass.getName()); //这个是外部类+$+外部类
        System.out.println("内部类getCanonicalName ："+innerClass.getCanonicalName()); //外部类+.+外部类
        System.out.println("内部类getSimpleName ："+innerClass.getSimpleName());

        //对于匿名内部类
        Class clazz = person.getClass();
        System.out.println("匿名内部类getName："+clazz.getName());  //返回com.java.reflect.classes.TestClass$1
        System.out.println("匿名内部类getCanonicalName ："+clazz.getCanonicalName()); //返回null
        System.out.println("匿名内部类getSimpleName ："+clazz.getSimpleName());  //返回空




        Field[]   fileds =   test.getFields();
        for(Field field : fileds){
            System.out.println(field.toGenericString());
        }


        test.getDeclaredFields();


        test.getConstructors();
        test.getDeclaredMethods();



    }
}
