package com.java.reflect.classes;

import java.lang.reflect.Method;

public class ClassWarning {
     public void m() {
	try {
	    Class c = ClassWarning.class;
	    Method m = c.getMethod("m");  // warning

        // production code should handle this exception more gracefully
	} catch (NoSuchMethodException x) {
    	    x.printStackTrace();
    	}
    }

	public static void main(String[] args) {
		ClassWarning  classWarning = new ClassWarning();
		classWarning.m();
	}
}
