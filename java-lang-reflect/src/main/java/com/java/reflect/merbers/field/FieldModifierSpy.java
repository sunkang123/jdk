package com.java.reflect.merbers.field;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static java.lang.System.out;

public class FieldModifierSpy {
   private  volatile int share;
   @Resource
    int instance;

    class Inner {}

    public static void main(String... args) {
    	args = new String[]{FieldModifierSpy.class.getName(),"volatile","private"};
//		args = new String[]{Spy.class.getName(),"public"};
//		args = new String[]{Spy.class.getName(),"private","static","final"};

	try {
	    Class<?> c = Class.forName(args[0]);
	    int searchMods = 0x0;
	    for (int i = 1; i < args.length; i++) {
		searchMods |= modifierFromString(args[i]);
	    }

		System.out.println("searchMods:"+ searchMods);
	    Field[] flds = c.getDeclaredFields();
	    out.format("Fields in Class '%s' containing modifiers:  %s%n",
		       c.getName(),
		       Modifier.toString(searchMods));
	    boolean found = false;
	    for (Field f : flds) {
			int foundMods = f.getModifiers();
		// Require all of the requested modifiers to be present
		if ((foundMods & searchMods) == searchMods) {
			//f.isSynthetic() 表示编译期间自动生成的   ，f.isEnumConstant表示是字段是枚举类型
		    out.format("%-8s [ synthetic=%-5b enum_constant=%-5b ]%n",
			       f.getName(), f.isSynthetic(),
			       f.isEnumConstant());
		    found = true;
		}
	    }
	    //编译自动合成的字段一般会在Classs.getDeclaredFields(),而Class.getField()会找不到，因为合成字段通常不是公共的

	    if (!found) {
		out.format("No matching fields%n");
	    }

        // production code should handle this exception more gracefully
	} catch (ClassNotFoundException x) {
	    x.printStackTrace();
	}
    }

    private static int modifierFromString(String s) {
	int m = 0x0;
	if ("public".equals(s))           m |= Modifier.PUBLIC;
	else if ("protected".equals(s))   m |= Modifier.PROTECTED;
	else if ("private".equals(s))     m |= Modifier.PRIVATE;
	else if ("static".equals(s))      m |= Modifier.STATIC;
	else if ("final".equals(s))       m |= Modifier.FINAL;
	else if ("transient".equals(s))   m |= Modifier.TRANSIENT;
	else if ("volatile".equals(s))    m |= Modifier.VOLATILE;
	return m;
    }
}
enum Spy { BLACK , WHITE }