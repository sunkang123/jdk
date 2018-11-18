package com.java.reflect.arraysAndEnumeratedTypes.arrays;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @Project: jdk
 * @description:  数组的创建，以给数组设置值
 * @author: sunkang
 * @create: 2018-10-06 12:31
 * @ModificationHistory who      when       What
 **/
public class ArrayStringCreator {
    public static void main(String[] args) {
        Class  clazz = String.class;
        //创建一个String 数组，长度为5
        Object arr =  Array.newInstance(clazz,5);
        //给数组指定的小标设置值
        Array.set(arr,0,"1");
        Array.set(arr,1,"2");
        Array.set(arr,2,"3");
        Array.set(arr,3,"4");

        //类型强转为String数组
        String[] oo  = (String[]) arr;
        System.out.println(Arrays.toString(oo));
    }
}


