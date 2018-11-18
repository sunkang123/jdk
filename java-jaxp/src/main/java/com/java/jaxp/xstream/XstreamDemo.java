package com.java.jaxp.xstream;

import com.java.jaxp.dom4j.Beans;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Project: jdk
 * @description:  xstream 实现了xml到javabeans的转换，javabean到json的转换
 * @author: sunkang
 * @create: 2018-10-19 10:38
 * @ModificationHistory who      when       What
 **/
public class XstreamDemo {

    public static void main(String[] args) {

        XStream xStream = new XStream(new DomDriver());
        xStream.processAnnotations(BeansEntity.class);

        Property property1 =new Property("sun","kang");
        Property property2 =new Property("kang","sun");
        Property property3 =new Property("sun2","kang2");
        List<Property> properties1 = new ArrayList<Property>();
        properties1.add(property1);
        properties1.add(property3);
        List<Property> properties2 = new ArrayList<Property>();
        properties2.add(property2);

        Bean bean1 = new Bean(properties1,"id1","class1");
        Bean bean2 = new Bean(properties2,"id2","class2");
        List<Bean> beans = new ArrayList<Bean>();
        beans.add(bean1);
        beans.add(bean2);
        System.out.println("-------------把java实体转化成xml对象--------------------");
        //把java实体转化成xml对象
        BeansEntity entity = new BeansEntity(beans);
        String xmlMsg = xStream.toXML(entity);
        System.out.println(xmlMsg);


        System.out.println("-------------用xml转化成java实体-------");
        //用xml转化成java实体
         BeansEntity  beansEntity = (BeansEntity) xStream.fromXML(xmlMsg);
         System.out.println(beansEntity);

        System.out.println("-------------实体转化成json--------------------");
        //转化成json ,需要增加  jettison的jar包
        XStream xstreamTojson = new XStream(new JettisonMappedXmlDriver());
        xstreamTojson.setMode(XStream.NO_REFERENCES);
        xstreamTojson.processAnnotations(BeansEntity.class);
        String json = xstreamTojson.toXML(entity);
        System.out.println(json);

        System.out.println("-------------json 转化成java--------------------");
        //json 转化成java
        BeansEntity beansEntity1 = (BeansEntity) xstreamTojson.fromXML(json);
        System.out.println(beansEntity1);
    }
}
