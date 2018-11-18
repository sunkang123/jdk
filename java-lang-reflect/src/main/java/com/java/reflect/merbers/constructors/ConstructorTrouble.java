package com.java.reflect.merbers.constructors;

/**
 * 没有无参的构造函数 引发错误
 */
public class ConstructorTrouble {
    public static void main(String... args){
	try {
	    Class<?> c =ConstructorTroubleTest.class ;
	    Object o = c.newInstance();  // InstantiationException

        // production code should handle these exceptions more gracefully
	} catch (InstantiationException x) {
	    x.printStackTrace();
	} catch (IllegalAccessException x) {
	    x.printStackTrace();
	}
    }
}
class  ConstructorTroubleTest{
	//声明了带参数的构造方法，会阻止编译器生成无参的构造方法
	private ConstructorTroubleTest(int i) {}
}
