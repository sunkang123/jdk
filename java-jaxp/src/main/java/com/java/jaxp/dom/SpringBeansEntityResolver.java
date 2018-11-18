package com.java.jaxp.dom;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

/**
 * @Project: jdk
 * @description:  验证xml元素的实体解析器
 * @author: sunkang
 * @create: 2018-10-18 14:01
 * @ModificationHistory who      when       What
 **/
public class SpringBeansEntityResolver implements EntityResolver {

    private static final String SPRING_BEANS_SYSTEM = "spring-beans-3.2.xsd";

    @Override
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
        System.out.println("publicId : "+ publicId);
        System.out.println("systemId : "+systemId );
        //如果这个xsd的资源没有设置绝对路劲之前会是： systemId ：http://www.springframework.org/schema/beans/spring-beans-3.2.xsd

        if (systemId != null) {
            String lowerCaseSystemId = systemId.toLowerCase(Locale.ENGLISH);
            if (lowerCaseSystemId.contains(SPRING_BEANS_SYSTEM)) {
                return this.getInputSource("org/springframework/beans/factory/xml/spring-beans.xsd", publicId, systemId);
            }
        }
        return null;
    }


    private InputSource getInputSource(String path, String publicId, String systemId) {
        InputSource source = null;
        if (path != null) {
            try {
                InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
                source = new InputSource(in);
                source.setPublicId(publicId);
                source.setSystemId(systemId);
            } catch (Exception var6) {
                ;
            }
        }

        return source;
    }
}
