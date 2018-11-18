package com.java.reflect.merbers.methods;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collection;
import java.util.List;
import static java.lang.System.out;

/**
 * @Project: jdk
 * @description: 输出方法的参数信息
 * @author: sunkang
 * @create: 2018-10-05 21:32
 * @ModificationHistory who      when       What
 **/
public class MethodParameterTest {

    public boolean simpleMethod( final String stringParam, int intParam) {
        System.out.println("String: " + stringParam + ", integer: " + intParam);
        return true;
    }

    public int varArgsMethod(String... manyStrings) {
        return manyStrings.length;
    }

    public boolean methodWithList(List<String> listParam) {
        return listParam.isEmpty();
    }

    public <T> void genericMethod(T[] a, Collection<T> c) {
        System.out.println("Length of array: " + a.length);
        System.out.println("Size of collection: " + c.size());
    }

    private static final String  fmt = "%24s: %s%n";

    public static void printParameter(Parameter p) {
        //p.getType(); 返回参数类型
        out.format(fmt, "Parameter class", p.getType());
        // p.getName();  返回参数名称，都是编译后的参数名称，名称的格式为argN，N为参数的序号
        out.format(fmt, "Parameter name", p.getName());
        //p.getModifiers() 得到参数的修饰符，
        out.format(fmt, "Modifiers", p.getModifiers());
        //表明参数是隐式的
        out.format(fmt, "Is implicit?", p.isImplicit());
        out.format(fmt, "Is name present?", p.isNamePresent());
        //参数即没有显示声明也没有隐式声明，则说明是合成的
        out.format(fmt, "Is synthetic?", p.isSynthetic());
        //参数是否是可变参数  varArgsMethod该方法中参数是可变参数
        out.format(fmt, "Is varArgs?", p.isVarArgs());
    }

    public static void main(String[] args) {
        //得到MethodParameterTest的所有的方法
       Method[]  methods =  MethodParameterTest.class.getDeclaredMethods();
       for(Method method :methods){
           out.println("--------------------------");
           out.format("%s%n", method.toGenericString());
           out.format(fmt, "Return type", method.getReturnType());
           out.format(fmt, "Generic return type", method.getGenericReturnType());
           Parameter[]  parameters =   method.getParameters();
           for(Parameter parameter : parameters){
               printParameter(parameter);
           }
       }
    }
}
