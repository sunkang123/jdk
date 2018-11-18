package com.java.jaxp.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Project: jdk
 * @description: dom4j解析
 * @author: sunkang
 * @create: 2018-10-18 23:28
 * @ModificationHistory who      when       What
 **/
public class Dom4jParseXml {

    public static void main(String[] args) throws DocumentException {
        SAXReader saxReader = new SAXReader();

        String fileUrl = "D:\\Eclipse2018Data\\personProject\\jdk\\java-jaxp\\src\\main\\java\\com\\java\\jaxp\\dom4j\\Beans.xml";

        Document document =saxReader.read(fileUrl);

       Element root =  document.getRootElement();

       StringBuffer sbf = new StringBuffer();
       Beans beans = new Beans();
        beans.bean = new ArrayList<Bean>();
        //利用迭代器查找beans元素下的bean的元素
       for(Iterator i = root.elementIterator("bean");i.hasNext();){
           Bean bean = new Bean();
           Element  beanElement= (Element) i.next();
           String id  = beanElement.attributeValue("id");
           String clazz  = beanElement.attributeValue("class");
           bean.id= id;
           bean.clazz=clazz;
           bean.properties = new ArrayList<Property>();
           for(Iterator j = beanElement.elementIterator("property");j.hasNext();){
               Property property = new Property();
               Element  propertyElement= (Element) j.next();
               //获取property元素的属性name的值
               property.name=propertyElement.attributeValue("name");
               property.value = propertyElement.getText();
               //添加property实体
               bean.properties.add(property);
           }
           //添加bean实体
           beans.bean.add(bean);
       }

        System.out.println(beans);
    }
}
