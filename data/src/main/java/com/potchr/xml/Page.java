package com.potchr.xml;


import com.potchr.xml.layout.Layout;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

@XmlType(name = "Page")
@XmlRootElement(name = "Page")
@XmlAccessorType(XmlAccessType.FIELD)
public class Page extends Layout {

    public static void main(String[] args) throws JAXBException, IOException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Page.class);

        /*final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        final Page unmarshal = (Page) unmarshaller.unmarshal(Page.class.getClassLoader().getResource("xml/test.xml"));
        System.out.println(unmarshal);*/
        jaxbContext.generateSchema(new SchemaOutputResolver() {
            @Override
            public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
                System.out.println("NameSpaceUrl:" + namespaceUri);
                System.out.println("SuggestedFileName:" + suggestedFileName);
                File schemaFile = new File("D:\\WorkSpace\\potchr\\data\\src\\main\\resources\\xml\\test.xsd");
                return new StreamResult(schemaFile);
            }
        });
    }
}
