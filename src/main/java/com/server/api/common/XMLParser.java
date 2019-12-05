package com.server.api.common;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class XMLParser {
    private static final ReporterLogger LOGGER = new ReporterLogger();
    /**
     *
     * @param filePath  xml file path
     * @param xpath xpath
     * @param attributeName  attr name
     * @param attributValue attr value
     * @return Element
     */
    public static Element getElementByID(String filePath, String xpath, String attributeName, String attributValue)
    {
        LOGGER.INFO("filePath:{},xpath:{},attributeName:{},attributValue:{}",filePath,xpath,attributeName,attributValue);
        Element result=null;
        List<Element> elementList=getElementsByXPath(filePath, xpath);
        for(Element item : elementList)
        {
            if(item.attributeValue(attributeName)!=null)
            {
                if( item.attributeValue(attributeName).equals(attributValue))
                {
                    result=item;
                    break;
                }
            }
        }
        return  result;
    }

    /**
     *
     * @param filePath file path
     * @param xpath xpath
     * @return Element list
     */
    public static List<Element> getElementsByXPath(String filePath,String xpath)
    {
        LOGGER.INFO("filePath:{}",filePath);
        List<Element> result=new ArrayList<Element>();

        Document document=getDocument(filePath);
        LOGGER.INFO("xpath:{}",xpath);
        List<Node> nodesList= document.selectNodes(xpath);
        LOGGER.INFO("nodesList:{}",nodesList);
        Iterator<Node> it=nodesList.iterator();
        LOGGER.INFO("it:{}",it);

        while(it.hasNext())
        {
            Element elm=(Element)it.next();
            LOGGER.INFO("elm:{}",elm);
            result.add(elm);
        }
        LOGGER.INFO("result:{}",result);
        return result;

    }

    public static Document getDocument(String filePath)
    {
        Document  document=null;
        try
        {
            File f = new File(filePath);
            SAXReader reader = new SAXReader();
            document = reader.read(f);
        }
        catch(Exception e)
        {
            LOGGER.ExceptionToString(e);
        }
        LOGGER.INFO("document:{}",document);
        return document;
    }
}
