package com.java.jaxp.dom;

import com.sun.org.apache.xerces.internal.jaxp.JAXPConstants;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.w3c.dom.*;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import sun.misc.DoubleConsts;

import javax.sound.midi.Soundbank;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * @Project: jdk
 * @description:  dom的验证xml命名空间
 * @author: sunkang
 * @create: 2018-10-18 12:57
 * @ModificationHistory who      when       What
 **/
public class ValidateXmlTest {

    // "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    static final String JAXP_SCHEMA_LANGUAGE = JAXPConstants.JAXP_SCHEMA_LANGUAGE;

    //"http://www.w3.org/2001/XMLSchema";
    static final String W3C_XML_SCHEMA =JAXPConstants.W3C_XML_SCHEMA;

    //     "http://java.sun.com/xml/jaxp/properties/schemaSource";
    static final String JAXP_SCHEMA_SOURCE =JAXPConstants.JAXP_SCHEMA_SOURCE;

    public static void main(String[] args) throws Exception{
        //得到DocumentBuilderFactory工厂实例
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        //解析文档
        //设置解析器在解析文档的时候校验文档
        builderFactory.setValidating(true);
        //让解析器支持命名空间
        builderFactory.setNamespaceAware(true);
        //通过指定factory的属性，确定使用Schema进行校验
        builderFactory. setAttribute(JAXP_SCHEMA_LANGUAGE,W3C_XML_SCHEMA);

        //设置对该文件进行元素验证，不合法的元素会报错
        //方式1
        String  schemaFileName ="D:\\Eclipse2018Data\\personProject\\jdk\\java-jaxp\\src\\main\\java\\com\\java\\jaxp\\dom\\spring-beans.xsd";
       //方式2
        File schemaFile = new File(schemaFileName);
        //方式3
        String[] schemaFileNames = new String[]{schemaFileName};
        //setAttribute的value可以是在看字符串，也可以是字符串数组，表示有多个文件约束，也是可以文件的形式
        builderFactory.setAttribute(JAXP_SCHEMA_SOURCE, schemaFileName);

        //忽略空白
        boolean ignoreWhitespace = false;
        boolean ignoreComments = false;
        boolean putCDATAIntoText = false;
        boolean createEntityRefs = false;
        builderFactory.setIgnoringComments(ignoreComments);
        builderFactory.setIgnoringElementContentWhitespace(ignoreWhitespace);
        builderFactory.setCoalescing(putCDATAIntoText);
        builderFactory.setExpandEntityReferences(!createEntityRefs);
        DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
        //解析dtd文件
//        documentBuilder.setEntityResolver(new SpringBeansEntityResolver());
        //添加ErrorHandler,将解析的异常手动抛出
        documentBuilder.setErrorHandler(new ErrorHandler() {
            @Override
            public void warning(SAXParseException exception) throws SAXException {
                System.out.println("warning");
                throw  exception;
            }

            private String getParseExceptionInfo(SAXParseException spe) {
                String systemId = spe.getSystemId();
                if (systemId == null) {
                    systemId = "null";
                }

                String info = "URI=" + systemId + " Line=" + spe.getLineNumber() +
                        ": " + spe.getMessage();
                return info;
            }

            @Override
            public void error(SAXParseException exception) throws SAXException {
                String message = "Error: " + getParseExceptionInfo(exception);
                System.out.println(message);
                throw new SAXException(message);
        }

            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                System.out.println("fatal");
                throw  exception;
            }
        });

        File xmlFile = new File("D:\\Eclipse2018Data\\personProject\\jdk\\java-jaxp\\src\\main\\java\\com\\java\\jaxp\\dom\\applicationContext.xml");
        //解析文档
        Document document = documentBuilder.parse(xmlFile);
        //得到跟节点的信息 ，这里值beans节点
        Element element =  document.getDocumentElement();
        System.out.println(element);
        //得到自己的标签名
        System.out.println(element.getTagName());

        //输出节点的类型信息,属性名称和属性值
        echo(element);
        //得到某个节点下指定的节点
        Node childNode = findSubNode("props",element);
        System.out.println(childNode);
    }


    public static Node findSubNode(String name, Node node) {
        if (node.getNodeType() != Node.ELEMENT_NODE) {
            System.err.println("Error: Search node not of element type");
            System.exit(22);
        }

        if (! node.hasChildNodes()) return null;

        NodeList list = node.getChildNodes();
        System.out.println("has childrens  :"+ list.getLength());
        for (int i=0; i < list.getLength(); i++) {
            Node subnode = list.item(i);
            printlnCommon(subnode);
            if (subnode.getNodeType() == Node.ELEMENT_NODE) {
                if (subnode.getNodeName().equals(name))
                    return subnode;
            }
        }
        return null;
    }


    private  static void echo(Node n) {
        int type = n.getNodeType();
        switch (type) {
            case Node.ATTRIBUTE_NODE:
                System.out.print("ATTR:");
                printlnCommon(n);
                break;

            case Node.CDATA_SECTION_NODE:
                System.out.print("CDATA:");
                printlnCommon(n);
                break;

            case Node.COMMENT_NODE:
                System.out.print("COMM:");
                printlnCommon(n);
                break;

            case Node.DOCUMENT_FRAGMENT_NODE:
                System.out.print("DOC_FRAG:");
                printlnCommon(n);
                break;

            case Node.DOCUMENT_NODE:
                System.out.print("DOC:");
                printlnCommon(n);
                break;

            case Node.DOCUMENT_TYPE_NODE:
                System.out.print("DOC_TYPE:");
                printlnCommon(n);
                NamedNodeMap nodeMap = ((DocumentType)n).getEntities();
                for (int i = 0; i < nodeMap.getLength(); i++) {
                    Entity entity = (Entity)nodeMap.item(i);
                    echo(entity);
                }
                break;

            case Node.ELEMENT_NODE:
                System.out.print("ELEM:");
                printlnCommon(n);

                NamedNodeMap atts = n.getAttributes();
                for (int i = 0; i < atts.getLength(); i++) {
                    Node att = atts.item(i);
                    echo(att);
                }
                break;

            case Node.ENTITY_NODE:
                System.out.print("ENT:");
                printlnCommon(n);
                break;

            case Node.ENTITY_REFERENCE_NODE:
                System.out.print("ENT_REF:");
                printlnCommon(n);
                break;

            case Node.NOTATION_NODE:
                System.out.print("NOTATION:");
                printlnCommon(n);
                break;

            case Node.PROCESSING_INSTRUCTION_NODE:
                System.out.print("PROC_INST:");
                printlnCommon(n);
                break;

            case Node.TEXT_NODE:
                System.out.print("TEXT:");
                printlnCommon(n);
                break;

            default:
                System.out.print("UNSUPPORTED NODE: " + type);
                printlnCommon(n);
                break;
        }
        for (Node child = n.getFirstChild(); child != null;
             child = child.getNextSibling()) {
            echo(child);
        }
    }


    private static void printlnCommon(Node n) {
        //输出节点的名称
        System.out.print(" nodeName=\"" + n.getNodeName() + "\"");
        //输出节点的命名空间
        String val = n.getNamespaceURI();
        if (val != null) {
            System.out.print(" uri=\"" + val + "\"");
        }

        val = n.getPrefix();
        if (val != null) {
            System.out.print(" pre=\"" + val + "\"");
        }

        val = n.getLocalName();
        if (val != null) {
            System.out.print(" local=\"" + val + "\"");
        }

        val = n.getNodeValue();
        if (val != null) {
            System.out.print(" nodeValue=");
            if (val.trim().equals("")) {
                // Whitespace
                System.out.print("[WS]");
            }
            else {
                System.out.print("\"" + n.getNodeValue() + "\"");
            }
        }
        System.out.println();
    }





}
