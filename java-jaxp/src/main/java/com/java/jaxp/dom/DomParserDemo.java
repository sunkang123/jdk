package com.java.jaxp.dom;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;

/**
 * @Project: jdk
 * @description:
 * @author: sunkang
 * @create: 2018-10-18 15:18
 * @ModificationHistory who      when       What
 **/
public class DomParserDemo {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        String fileName = "D:\\Eclipse2018Data\\personProject\\jdk\\java-jaxp\\src\\main\\java\\com\\java\\jaxp\\dom\\Beans.xml";
        File file = new File(fileName);

        Document document = documentBuilder.parse(file);
        StringBuffer sbf = new StringBuffer();
        //解析beans的元素
        populateBeans(document, sbf);
        System.out.println(sbf.toString());
    }

    public static void populateBeans(Document document, StringBuffer sbf) {
        Element root = document.getDocumentElement();
        String beans = root.getTagName();
        populateBean(document, sbf);
        sbf.append("}");
    }


    public static void populateBean(Document document, StringBuffer sbf) {
        NodeList nodeList = document.getElementsByTagName("bean");
        sbf.append("{bean:");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element beanElement = (Element) nodeList.item(i);
            String id = beanElement.getAttribute("id");
            String clazz = beanElement.getAttribute("class");
            sbf.append("{id :" + id + ",");
            sbf.append("clazz :" + id + ",");

            //解析property属性
            populateProperty(beanElement, sbf);
            if (i != nodeList.getLength() - 1) {
                sbf.append("},");
            } else {
                sbf.append("}");
            }
        }
        sbf.append("}");

    }

    public static void populateProperty(Element beanElement, StringBuffer sbf) {
        NodeList propertyList = beanElement.getElementsByTagName("property");
        for (int j = 0; j < propertyList.getLength(); j++) {
            Node node = propertyList.item(j);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element property = (Element) node;
                String name = property.getAttribute("name");
                //获取property的标签的值,下面的这个方法是可以的
                //String value = property.getFirstChild().getNodeValue();
                String value = property.getTextContent();
                sbf.append("property :{name:" + name+ ",");
                sbf.append("value:" + value + "}");
            }
        }
    }

}
