package com.java.reflect.merbers.methods;

import java.lang.reflect.*;
import java.util.function.*;
import static java.lang.System.out;
 
public class MethodParameterExamples {


    /**
     *
     * 内部类的传递外部类是隐式的
     * public class MethodParameterExamples {
     *     public class InnerClass {
     *         final MethodParameterExamples parent;
     *         InnerClass(final MethodParameterExamples this$0) {
     *             parent = this$0;
     *         }
     *     }
     * }
     */
    public class InnerClass { }

    /**
     *
     * 枚举类型的内部创造是继承了一个枚举
     * final class Colors extends java.lang.Enum<Colors> {
     *     public final static Colors RED = new Colors("RED", 0);
     *     public final static Colors BLUE = new Colors("WHITE", 1);
     *
     *     private final static values = new Colors[]{ RED, BLUE };
     *
     *     private Colors(String name, int ordinal) {
     *         super(name, ordinal);
     *     }
     *
     *     public static Colors[] values(){
     *         return values;
     *     }
     *
     *     public static Colors valueOf(String name){
     *         return (Colors)java.lang.Enum.valueOf(Colors.class, name);
     *     }
     * }
     */
    enum Colors {
        RED, WHITE;
    }
     
    public static void main(String... args) {
        System.out.println("InnerClass:");
        MethodParameterSpy.printClassConstructors(InnerClass.class);
         
        System.out.println("enum Colors:");
        MethodParameterSpy.printClassConstructors(Colors.class);
        MethodParameterSpy.printClassMethods(Colors.class);
    }
}