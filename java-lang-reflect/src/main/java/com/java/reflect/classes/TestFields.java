package com.java.reflect.classes;

import java.lang.reflect.Field;

/**
 * @Project: jdk
 * @description:   fileds字段测试
 * @author: sunkang
 * @create: 2018-10-05 14:45
 * @ModificationHistory who      when       What
 **/
public class TestFields  extends  FiledConstants   {
    public  int age;

    public static  String addr;

    private   String email;

    public static void main(String[] args) {
        Class  clazz  =TestFields.class;
        //得到public的字段，包括继承的父类的，接口的，实现逻辑是先加载本地的，然后加载接口的（采用了递归），然后加载父类的（采用了递归）
        Field[]   fields= clazz.getFields();
        for( Field  field : fields){
            System.out.println(" publicField :"+field.toGenericString());
        }
        System.out.println("-----------------------------------");
        //得到本地声明的字段
        Field[]   declaredFields =  clazz.getFields();
        for(Field field : declaredFields){
            System.out.println("declaredField :"+field.toGenericString());
        }

        clazz.getConstructors();
        clazz.getDeclaredConstructors();
        clazz.getMethods();
        clazz.getDeclaredMethods();

        clazz.getDeclaredAnnotations();


    }
}
class FiledConstants implements  interfaceConstants{
    public  int age;

    public  String  name;

    private String  phoneNumber;
}
interface interfaceConstants {
    int age = 22;
}


