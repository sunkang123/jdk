package com.java.reflect.arraysAndEnumeratedTypes.arrays;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import static java.lang.System.out;


/**
 * 鉴别数组类型
 *    Class.isArray  可以判断类型是否为数组
 *    class.getComponentType 可以得到一个数组元素类型
 *   对于字段类型为：byte[],type为class [B  ,componmentType组件类型为byte
 *   对于字段类型为：String[][],type为 class [[Ljava.lang.String;   ,componmentType组件类型为一维数组 [Ljava.lang.String;
 */
public class ArrayFind {
    public static void main(String... args) {
        args  =new String []{"java.nio.ByteBuffer"};
        boolean found = false;
        try {
            Class<?> cls = Class.forName(args[0]);
            Field[] flds = cls.getDeclaredFields();
            for (Field f : flds) {
                //得到字段类型
                Class<?> c = f.getType();
                if (c.isArray()) {//判断类型是否是数组
                    found = true;
                    //c.getComponentType() 得到数组单元的类型
                    out.format("%s%n"
                                    + "           Field: %s%n"
                                    + "            Type: %s%n"
                                    + "  Component Type: %s%n",
                            f, f.getName(), c, c.getComponentType());
                }
            }
            if (!found) {
                out.format("No array fields%n");
            }
        } catch (ClassNotFoundException x) {
            x.printStackTrace();
        }
    }
}