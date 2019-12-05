package com.test;


import com.server.api.common.ReporterLogger;
import com.server.api.common.FrameGenerator;

/**
 * 
* @Title: GatCreator.java 
* @Package  
* @Description: 自动生成testng.xml文件，自动报告配置监听
* @author: wjl 
* @date 2019年11月6日 下午3:22:52 
* @version V1.2
 */
public class GatCreator {
	private static ReporterLogger LOGGER = new ReporterLogger();
	public static void main(String[] args)
	{
		FrameGenerator.createInterfaceTestProject();
//		AddListenerToTestngXML.addListenerToTestngXml();
	}
}
