package com.java.reflect.arraysAndEnumeratedTypes.enumTypes;

import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.Arrays;
import static java.lang.System.out;


/**
 *  枚举检测类
 *Class.getEnumConstants()  获取枚举类型的实例 ，Class.getEnumConstants这个方法的本质是通过反射调用values方法获取枚举的实例
 *
 *具体枚举类.values()   也是获取枚举类型的实例
 *java.lang.reflect.Field.isEnumConstant()   判断字段是否是枚举类型
 */
public class EnumConstants {
    public static void main(String... args) {

    	args = new String[]{RetentionPolicy.class.getName()};

	try {
	    Class<?> c = Class.forName(args[0]);

	    //Class.isEnum()  表明该类是否是枚举类
		System.out.println("isEnum : "+ c.isEnum());
		//Class.getEnumConstants()  返回类中声明的枚举实例   等同于 RetentionPolicy.values();
	    out.format("Enum name:  %s%nEnum constants:  %s%n",
		       c.getName(), Arrays.asList(c.getEnumConstants()));

	    for(Field field : c.getDeclaredFields()){
	    	if(field.isEnumConstant()){
				System.out.println(field.toGenericString());
			}
		}

	} catch (ClassNotFoundException x) {
	    x.printStackTrace();
	}
    }
}
