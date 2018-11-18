package com.java.reflect.merbers.constructors;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;

/**
 * @Project: jdk
 * @description:
 * @author: sunkang
 * @create: 2018-10-06 12:13
 * @ModificationHistory who      when       What
 **/
public class ConstrutorTest {


    public static void main(String[] args) {
        Class clazz = List.class;

        for(Constructor  constructor : clazz.getDeclaredConstructors()){
            System.out.println(constructor.toGenericString());
        }
    }
}
