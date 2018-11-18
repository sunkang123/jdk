package com.java.jaxp.dom4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project: jdk
 * @description:  beans对象
 * @author: sunkang
 * @create: 2018-10-19 09:21
 * @ModificationHistory who      when       What
 **/
public class Beans {
    public List<Bean> bean;
    @Override
    public String toString() {
        return "Beans{" +
                "bean=" + bean +
                '}';
    }
}
class Bean{
    public List<Property> properties;

    public  String id;

    public  String clazz;

    @Override
    public String toString() {
        return "Bean{" +
                "properties=" + properties +
                ", id='" + id + '\'' +
                ", clazz='" + clazz + '\'' +
                '}';
    }
}

 class Property{
    public String name ;
    public  String value;
    @Override
    public String toString() {
        return "Property{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}



