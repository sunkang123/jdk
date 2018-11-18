package com.java.reflect.arraysAndEnumeratedTypes.arrays;

import java.lang.reflect.Array;

import static java.lang.System.err;


/**
 * 数组类型设置不正确，导致 转换错误
 *原本类型是Integer数组，在发射代码中，类型检查只有在运行期间在会发生，所以无法对原生类型进行包装
 *  art[0] = 1   ,这个是可以的，因为编译器对1进行了包装，使1变成了引用类型
 */
public class ArrayTroubleAgain {
	public static void main(String... args) {
		Integer[] ary = new Integer[2];
		try {
//			Array.setInt(ary, 0, 1);  // IllegalArgumentException  不可以

//			Array.setInt(ary,0,new Integer(1));//不可以


//			Integer.isAssignableFrom().  符合这里对象是可以的
			//这里设置的1 当做是Ojbect对象
			Array.set(ary,0,1);  //可以，set用于引用类型

			Array.set(ary,0,new Integer(1));//可以


		} catch (IllegalArgumentException x) {
			err.format("Unable to box%n");
		} catch (ArrayIndexOutOfBoundsException x) {
			x.printStackTrace();
		}
	}
}