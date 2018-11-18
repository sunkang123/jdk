package com.java.jaxp.sax;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * sax 解析示例
 */
public class SaxXML {
    public static void main(String[] args) {
        File file = new File("D:\\Eclipse2018Data\\personProject\\jdk\\java-jaxp\\src\\main\\java\\com\\java\\jaxp\\sax\\Person.xml");
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser parser = spf.newSAXParser();
            SaxHandler handler = new SaxHandler("People");
            //给SAXParser设置xml的数据源和DefaultHandler的具体实现
            parser.parse(new FileInputStream(file), handler);
            List<People> peopleList = handler.getPeoples();
            //打印内容
            for(People people : peopleList){
                System.out.println(people.getId()+"\t"+people.getName()+"\t"+people.getEnglishName()+"\t"+people.getAge());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
//SaxHandler 默认实现了DefaultHandler 接口，这个DefaultHandler接口实现了四个接口
class SaxHandler extends DefaultHandler {
    private List<People> peoples = null;
    private People people;
    private String currentTag = null;
    private String currentValue = null;
    private String nodeName = null;

    public List<People> getPeoples() {
        return peoples;
    }

    public SaxHandler(String nodeName) {
        this.nodeName = nodeName;
    }


    //  当读到一个开始标签的时候，会触发这个方法
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        //创建一个pesples的数组对象
        peoples = new ArrayList<People>();
    }

    //当文档解析的时候，会触发这个方法
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
    // 当遇到元素标签的时候，调用这个方法
    @Override
    public void startElement(String uri, String localName, String name,
            Attributes attributes) throws SAXException {
        super.startElement(uri, localName, name, attributes);
        //判断是否有People这个元素，有则创建People对象
        if (name.equals(nodeName)) {
            people = new People();
        }
        //People标签的属性是否为null
        if (attributes != null && people != null) {
            for (int i = 0; i < attributes.getLength(); i++) {
                if(attributes.getQName(i).equals("id")){
                    //获取People标签的属性为id的值
                    people.setId(attributes.getValue(i));
                }
                else if(attributes.getQName(i).equals("en")){
                    people.setEnglishName(attributes.getValue(i));
                }
            }
        }
        //给currentTag赋值
        currentTag = name;
    }
    //  这个方法用来处理在XML文件中读到的内容
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        super.characters(ch, start, length);
        if (currentTag != null && people != null) {
            //得到标签体的内容
            currentValue = new String(ch, start, length);
            if (currentValue != null && !currentValue.trim().equals("") && !currentValue.trim().equals("\n")) {
                if(currentTag.equals("Name")){
                    people.setName(currentValue);
                }
                else if(currentTag.equals("Age")){
                    people.setAge(currentValue);
                }
            }
        }
        currentTag = null;
        currentValue = null;
    }

    //在遇到结束标签的时候，调用这个方法
    @Override
    public void endElement(String uri, String localName, String name)
            throws SAXException {
        super.endElement(uri, localName, name);
        if (name.equals(nodeName)) {
            peoples.add(people);
        }
    }

}