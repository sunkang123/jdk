package com.java.reflect.merbers.field;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * @Project: jdk
 * @description:  字段修饰符的测试案例
 * @author: sunkang
 * @create: 2018-10-05 18:40
 * @ModificationHistory who      when       What
 **/
public class FieldModifierTest {
    @Deprecated
    private  volatile int share;

    enum Color { BLACK , WHITE }

    public static void main(String[] args) {
        try {
            Class clazz =   FieldModifierTest.class;
            //得到指定的字段信息
            Field field  =  clazz.getDeclaredField("share");
            //得到所有的修饰符之和，不同的值代表不同修饰符，但是这些值都是进行相加的
            int modifiers =  field.getModifiers();
            //Modifier是一个工具类，toString 表示生成不同的修饰符
            System.out.println("share字段所指定的所有的修饰符:"+Modifier.toString(modifiers));
            //判断修饰符是否是Volatile
            System.out.println("isVolatile:"+Modifier.isVolatile(modifiers));
            //判断修饰符是否是私有的
            System.out.println("isPrivate:"+Modifier.isPrivate(modifiers));
            //得到字段的注解信息
            Deprecated  annotation = field.getAnnotation(Deprecated.class);
            System.out.println("字段的注解信息："+ annotation.annotationType());


            //得到所有声明的字段
            Field[] flds = Color.class.getDeclaredFields();
            for (Field f: flds ){
                System.out.format("%-8s [ synthetic=%-5b enum_constant=%-5b ]%n",
                        f.getName(), f.isSynthetic(),//f.isSynthetic()表示编译是否是自动生成的方法
                        f.isEnumConstant());// f.isEnumConstant()表示是否是枚举实例
            }
            //class.getEnumConstants可以得到一个类的枚举实例
            System.out.println(Arrays.asList(Color.class.getEnumConstants()));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}

