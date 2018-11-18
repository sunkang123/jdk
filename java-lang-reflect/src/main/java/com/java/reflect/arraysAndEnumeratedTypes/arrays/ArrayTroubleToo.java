package com.java.reflect.arraysAndEnumeratedTypes.arrays;

import java.lang.reflect.Array;
import static java.lang.System.out;

/**
 *
 * 值的精度缩小会出错的
 *
 *  遵循的返回如下，这里 int数组，设置的元素必须是byte和short和int
 * byte , short, int, long, float, or double
 */
public class ArrayTroubleToo {
    public static void main(String... args) {
        Object o = new int[2];
        Array.setByte(o,0,"2".getBytes()[0]);  //
        Array.setShort(o, 1, (short)2);  // widening, succeeds
//        Array.setLong(o, 1, 2L);         // narrowing, fails   失去精度的操作将会出错
    }
}