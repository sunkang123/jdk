package com.java.reflect.merbers.methods;

import java.util.Collection;
import java.util.List;

public class ExampleMethods<T> {
   
    public boolean simpleMethod( final String stringParam, int intParam) {
        System.out.println("String: " + stringParam + ", integer: " + intParam); 
	    return true;
    }
    
    public int varArgsMethod(String... manyStrings) {
        return manyStrings.length;
    }
    
    public boolean methodWithList(List<String> listParam) {
        return listParam.isEmpty();
    }
    
    public <T> void genericMethod(T[] a, Collection<T> c) {
        System.out.println("Length of array: " + a.length);
        System.out.println("Size of collection: " + c.size()); 
    }


    public ExampleMethods()
    {

    }
}