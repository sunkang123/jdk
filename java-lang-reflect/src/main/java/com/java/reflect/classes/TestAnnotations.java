package com.java.reflect.classes;

import sun.invoke.anon.AnonymousClassLoader;

import java.lang.annotation.*;

/**
 * @Project: jdk
 * @description:  注解的测试
 * @author: sunkang
 * @create: 2018-10-05 17:00
 * @ModificationHistory who      when       What
 **/

@Test1(name = "test1")
public class TestAnnotations  extends ParentAnnotations  {
    public static void main(String[] args) {
        Class annoClass = TestAnnotations.class;
        //1. 得到指定类的所有注解
        Annotation[]   declaredAnnotations = annoClass.getDeclaredAnnotations();//得到这个类上的直接注解，不包含继承的注解
        for(Annotation annotation : declaredAnnotations ){
            System.out.println(annotation.annotationType()); //对应注解的类型信息
        }

        Annotation[]   annotations = annoClass.getAnnotations();//与getDeclaredAnnotations()方式相同
        for(Annotation annotation : annotations ){
            System.out.println(annotation.annotationType());
        }
        //2. 得到指定的注解
       if(annoClass.isAnnotationPresent(Test1.class)){ //判断指定类是否存在注解
           Test1 anotation = (Test1) annoClass.getAnnotation(Test1.class);  //得到指定的注解
           System.out.println(anotation.name());
       }
    }
}
@Test2
class ParentAnnotations{

}
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface  Test1{
    String name() default  "";
}

@Retention(RetentionPolicy.RUNTIME) //说明在运行期间生效
@Target({ElementType.TYPE,ElementType.METHOD})//target 表示指定在哪些目标，比如类，接口，枚举上面，或者是方法上面
@interface  Test2{

}


