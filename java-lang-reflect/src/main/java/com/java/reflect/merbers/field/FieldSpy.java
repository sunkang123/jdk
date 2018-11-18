package com.java.reflect.merbers.field;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.List;

    /**
     * @Project: jdk
     * @description:  类中字段扫描
     * @author: sunkang
     * @create: 2018-09-27 13:28
     * @ModificationHistory who      when       What
     **/
    public class FieldSpy<T> {
        public boolean[][] b = {{ false, false }, { true, true } };
        public String name  = "Alice";
        public List<Integer> list;
        public T val;

        public static void main(String... args) {
    //        args = new String[]{FieldSpy.class.getName(),"b"};
            args = new String[]{FieldSpy.class.getName(),"val"};
            try {
                Class<?> c = Class.forName(args[0]);
                Field f = c.getField(args[1]);
                System.out.format("Type: %s%n", f.getType()); //getType()返回字段的类型信息
                System.out.format("GenericType: %s%n", f.getGenericType());//返回字段的泛型类型信息
                // production code should handle these exceptions more gracefully
            } catch (ClassNotFoundException x) {
                x.printStackTrace();
            } catch (NoSuchFieldException x) {
                x.printStackTrace();
            }
        }
    }
