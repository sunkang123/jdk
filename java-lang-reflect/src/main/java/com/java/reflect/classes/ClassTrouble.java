package com.java.reflect.classes;

public class ClassTrouble {
    public static void main(String... args) {
	try {
	    Class<?> c = Class.forName("com.java.reflect.arraysAndEnumeratedTypes.Cls");
	    c.newInstance();  // InstantiationException
	} catch (InstantiationException x) {
	    x.printStackTrace();
	} catch (IllegalAccessException x) {
	    x.printStackTrace();
	} catch (ClassNotFoundException x) {
	    x.printStackTrace();
	}
	}
}
