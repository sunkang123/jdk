package com.java.reflect.arraysAndEnumeratedTypes.arrays;

import java.lang.reflect.Array;
import static java.lang.System.out;

/**
 * 二维数组的创建与设值
 */
public class CreateMatrix {
    public static void main(String... args) {
        //创建一个二维数组，相当于   new int[2][2];
        Object matrix = Array.newInstance(int.class, 2 ,2);
        //获取二维数组的第一行
        Object row0 = Array.get(matrix, 0);
        Object row1 = Array.get(matrix, 1);
        //二维数组设置值，第一行设置值
        Array.setInt(row0, 0, 1);
        Array.setInt(row0, 1, 2);

        //第二行设置值
        Array.setInt(row1, 0, 3);
        Array.setInt(row1, 1, 4);

        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                out.format("matrix[%d][%d] = %d%n", i, j, ((int[][])matrix)[i][j]);
    }
}