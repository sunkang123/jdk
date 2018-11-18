package com.java.reflect.merbers.constructors;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

import static java.lang.System.out;

/**
 *   构造函数访问修饰符  测试
 */
public class ConstructorAccess {
    public static void main(String... args) {
//    	args = new String[]{File.class.getName(),"private"};
        args = new String[]{SyntheticConstructor.class.getName(), "package-private"};
        try {
            Class<?> c = Class.forName(args[0]);
            Constructor[] allConstructors = c.getDeclaredConstructors();
            for (Constructor ctor : allConstructors) {
                int searchMod = modifierFromString(args[1]); //如果是package-private,这里是0
                int mods = accessModifiers(ctor.getModifiers());
                if (searchMod == mods) {
                    out.format("%s%n", ctor.toGenericString());
                    out.format("  [ synthetic=%-5b var_args=%-5b ]%n",
                            ctor.isSynthetic(), ctor.isVarArgs());
                }
            }
        } catch (ClassNotFoundException x) {
            x.printStackTrace();
        }
    }
    private static int accessModifiers(int m) {
        return m & (Modifier.PUBLIC | Modifier.PRIVATE | Modifier.PROTECTED);
    }

    private static int modifierFromString(String s) {
        if ("public".equals(s)) return Modifier.PUBLIC;
        else if ("protected".equals(s)) return Modifier.PROTECTED;
        else if ("private".equals(s)) return Modifier.PRIVATE;
        else if ("package-private".equals(s)) return 0;
        else return -1;
    }
}

class SyntheticConstructor {
    private SyntheticConstructor() {
    }
   // SyntheticConstructor(Inner inner){ }
     class Inner {
        Inner() {
            /**
             * 这里Inner()函数引用了外部的构造函数，于是编译器会生成一个构造函数，等同上面注释的SyntheticConstructor(Inner inner){ }
             * 而这个构造函数是package-private的构造函数
             */
            new SyntheticConstructor();
        }
    }
}