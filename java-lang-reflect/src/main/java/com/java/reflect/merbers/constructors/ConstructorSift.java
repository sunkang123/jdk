package com.java.reflect.merbers.constructors;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import static java.lang.System.out;

public class ConstructorSift {
	public static void main(String... args) {
//		args =new String[]{Formatter.class.getName(),Locale.class.getName()};
		//[C 代表char[]数组
//		args =new String[]{String.class.getName(),"[C"};
		args  =new String[]{ProcessBuilder.class.getName(),"[Ljava.lang.String;"};
		try {
			Class<?> cArg = Class.forName(args[1]);
			Class<?> c = Class.forName(args[0]);
			Constructor[] allConstructors = c.getDeclaredConstructors();
			for (Constructor ctor : allConstructors) {

				Class<?>[] pType  = ctor.getParameterTypes();
				for (int i = 0; i < pType.length; i++) {
					if (pType[i].equals(cArg)) {
						//ctor.isVarArgs()  是区分可变参数与数组参数的区别，如果是可变参数则为true
						System.out.println("ctor:"+ctor.isVarArgs());
						out.format("%s%n", ctor.toGenericString());
						Type[] gpType = ctor.getGenericParameterTypes();
						for (int j = 0; j < gpType.length; j++) {
							char ch = (pType[j].equals(cArg) ? '*' : ' ');
							out.format("%7c%s[%d]: %s%n", ch,
									"GenericParameterType", j, gpType[j]);
						}
						break;
					}
				}
			}
			// production code should handle this exception more gracefully
		} catch (ClassNotFoundException x) {
			x.printStackTrace();
		}
	}
}