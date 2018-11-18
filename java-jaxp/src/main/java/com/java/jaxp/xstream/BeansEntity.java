package com.java.jaxp.xstream;



import com.thoughtworks.xstream.annotations.*;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;

import javax.xml.bind.annotation.XmlSchemaType;
import java.util.List;

/**
 * @Project: jdk
 * @description:  javabeans的实体
 * @author: sunkang
 * @create: 2018-10-19 10:42
 * @ModificationHistory who      when       What
 **/

@XStreamAlias("beans")
public class BeansEntity {

    @XStreamImplicit
    public List<Bean> beans;

    public BeansEntity(List<Bean> beans) {
        this.beans = beans;
    }

    @Override
    public String toString() {
        return "Beans{" +
                "bean=" + beans +
                '}';
    }
}


@XStreamAlias("bean")
class Bean{
    @XStreamImplicit
    public List<Property> properties;

    @XStreamAsAttribute
    public  String id;

    @XStreamAsAttribute
    @XStreamAlias("class")
    public  String clazz;

    public Bean(List<Property> properties, String id, String clazz) {
        this.properties = properties;
        this.id = id;
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "properties=" + properties +
                ", id='" + id + '\'' +
                ", clazz='" + clazz + '\'' +
                '}';
    }
}

@XStreamAlias("property")
//下面ToAttributedValueConverter的作用是把其他全部变成属性值，指定的为值
@XStreamConverter(value=ToAttributedValueConverter.class, strings={"value"})
class Property{
    @XStreamAsAttribute
    public String name ;

    public  String value;

    public Property(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Property{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
