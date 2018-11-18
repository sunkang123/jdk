package com.java.jaxp;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * @Project: jdk
 * @description:
 * @author: sunkang
 * @create: 2018-10-16 22:05
 * @ModificationHistory who      when       What
 **/
public class SAXLocalNameCount extends DefaultHandler {

    static final String JAXP_SCHEMA_LANGUAGE =
            "http://java.sun.com/xml/jaxp/properties/schemaLanguage";

    static final String W3C_XML_SCHEMA =
            "http://www.w3.org/2001/XMLSchema";

    static final String JAXP_SCHEMA_SOURCE =
            "http://java.sun.com/xml/jaxp/properties/schemaSource";

    static public void main(String[] args) throws Exception {
        File file = new File("D:\\Eclipse2018Data\\personProject\\jdk\\java-jaxp\\src\\main\\java\\com\\java\\jaxp\\test.xml");
        String   filename =   file.getAbsolutePath();

        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        SAXParser saxParser = spf.newSAXParser();
        XMLReader xmlReader = saxParser.getXMLReader();
        xmlReader.setContentHandler(new SAXLocalNameCount());
        xmlReader.parse(convertToFileURL(filename));
        xmlReader.setErrorHandler(new MyErrorHandler(System.err));


        boolean dtdValidate = false;
        boolean xsdValidate = false;
        spf.setValidating(dtdValidate || xsdValidate);

        if (xsdValidate) {
            try {
                saxParser.setProperty(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
            }
            catch (SAXNotRecognizedException x){
                System.err.println("Error: JAXP SAXParser property not recognized: "
                        + JAXP_SCHEMA_LANGUAGE);

                System.err.println( "Check to see if parser conforms to the JAXP spec.");
                System.exit(1);
            }
        }


    }

    private Hashtable tags;

    public void startDocument() throws SAXException {
        tags = new Hashtable();
    }

    public void startElement(String namespaceURI,
                             String localName,
                             String qName,
                             Attributes atts)
            throws SAXException {

        String key = localName;
        Object value = tags.get(key);

        if(key.equals("username")){
            System.out.println(atts.getValue("value"));
            System.out.println(atts.getLength());
            System.out.println(atts.getType("value"));
            System.out.println(atts.getValue(1));
        }

        if (value == null) {
            tags.put(key, new Integer(1));
        }
        else {
            int count = ((Integer)value).intValue();
            count++;
            tags.put(key, new Integer(count));
        }
    }


    public void endDocument() throws SAXException {
        Enumeration e = tags.keys();
        while (e.hasMoreElements()) {
            String tag = (String)e.nextElement();
            int count = ((Integer)tags.get(tag)).intValue();
            System.out.println("Local Name \"" + tag + "\" occurs "
                    + count + " times");
        }
    }



    private static String convertToFileURL(String filename) {
        String path = new File(filename).getAbsolutePath();
        if (File.separatorChar != '/') {
            path = path.replace(File.separatorChar, '/');
        }

        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        return "file:" + path;
    }



    public void characters(char[] ch, int start, int length){


        System.out.println(new String());
    }
}


