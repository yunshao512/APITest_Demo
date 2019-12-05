package com.server.api.manager;

import java.util.List;

import com.server.api.common.ReporterLogger;
import com.server.api.dataobject.TestObject;
import com.server.api.common.XMLParser;
import org.dom4j.Element;


public abstract class TestObjectManager implements IManager
{
	private static final ReporterLogger LOGGER = new ReporterLogger();
	public TestObjectManager() 
    {
		// TODO Auto-generated constructor stub
	}

	public abstract TestObject getItem(String ID) throws Exception;
	
	protected String getTestObjectXML(String elementID,String xmlFilePath,String elementXpath,String attributName) throws Exception
	{
		LOGGER.INFO("elementID:{},xmlFilePath:{},elementXpath:{},attributName:{}",elementID,xmlFilePath,elementXpath,attributName);
		Element XMLElement= XMLParser.getElementByID(xmlFilePath, elementXpath,attributName,elementID);
		if (XMLElement==null) throw new Exception("TestObject with id "+elementID+" can not be found");
		return XMLElement.asXML();
	}
	
	protected List<Element> getTestObjectXMLs(String xmlFilePath,String elementXpath) throws Exception
	{
		List<Element> XMLElements=XMLParser.getElementsByXPath(xmlFilePath, elementXpath);
		return XMLElements;
	}
	
	

}
