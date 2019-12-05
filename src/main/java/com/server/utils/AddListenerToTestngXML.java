package com.server.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 
* @Title: AddListenerToTestngXML.java 
* @Package  
* @Description: 修改xml文件，为文件新增监听标签
* @author: wjl 
* @date 2019年10月10日 下午5:29:35 
* @version V1.0
 */
public class AddListenerToTestngXML {
	//执行文件路径
	public static final String FILEPATH = "testng.xml";
	//监听的全类名
	public static final String LISTENERFULLNAME = "com.server.listener.ExtentTestNGIReporterListener";
	public static final Logger LOGGER = LoggerFactory.getLogger(AddListenerToTestngXML.class);

	public static void addListenerToTestngXml() {
		File file = new File(FILEPATH);
		StringWriter stringWriter = null;
		try {
			LOGGER.info("start update testng.xml");
			Document doc = new SAXReader().read(file);
			Element rootElement = doc.getRootElement();
			Element element_listeners = DocumentHelper.createElement("listeners");
			Element element_listener = DocumentHelper.createElement("listener");
			element_listener.addAttribute("class-name", LISTENERFULLNAME);
			element_listeners.add(element_listener);
			rootElement.add(element_listeners);
			OutputFormat outputFormat =new OutputFormat();
			outputFormat.setIndent(true); // 设置是否缩进
			outputFormat.setIndent("    "); // 以四个空格方式实现缩进
			outputFormat.setNewlines(true); // 设置是否换行
			XMLWriter xmlWriter = null;
			try {
				xmlWriter = new XMLWriter(new FileOutputStream(file), outputFormat);
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			try {
				xmlWriter.write(doc);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(xmlWriter != null) {
					try {
						xmlWriter.close();
						LOGGER.info("testng.xml update success!!");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	}

