package com.server.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.server.api.common.ReporterLogger;
import com.server.api.common.GlobalConfig;
import com.server.api.common.XmlExtents;
import org.apache.xmlbeans.XmlName;
import org.dom4j.Document;



public class ProjectUtil {

	private static ReporterLogger LOGGER = new ReporterLogger();
	public static String getProjectBasePath(){
		File parentFile = null;
		String basePath = null;
		try {
			parentFile = FileUtil.pathToFile("..");
			basePath = FileUtil.getFileDir(parentFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return basePath;
	}
	
	
	private ArrayList<String> appendDirs(){
		ArrayList<String> wholeDirStrings = new ArrayList<String>();
		return wholeDirStrings;
	}
	
	/*
	* 创建testngXML
	* @param packageNameList
	* */
	public static void createTestngXml(List<String> packageNameList)throws IOException
	{
		//要生成的测试类的名称
		List<String> classStringNames = new ArrayList<String>();
		for(String packageName:packageNameList)
		{
			String[] pageFolders=packageName.split("\\.");
			String tempPath="";
			for(Integer i=0;i<pageFolders.length;i++)
			{
				tempPath=tempPath+GlobalConfig.getSlash()+pageFolders[i];
			}
			LOGGER.INFO("GlobalConfig.getRootDir()+GlobalConfig.getTestClassDir()+tempPath:{}",GlobalConfig.getRootDir()+GlobalConfig.getTestClassDir()+tempPath);
			List<String> classNames= FileUtil.getFilesNameNoSuffix(GlobalConfig.getTestClassDir()+tempPath);

			for(String eachName:classNames)
			{
				classStringNames.add(packageName + eachName);
				LOGGER.INFO("packageName:{},eachName:{}",packageName,eachName);
			}
			//packageNameList 大于1等于多个项目，多个项目生成多个项目的testng 执行文件
			/*if (packageNameList.size()>1)
				outputTestngXML(classStringNames,pageFolders[2] + "_testng.xml");*/
		}
		outputTestngXML(classStringNames,"testng.xml");
	}
	public static void outputTestngXML(List<String> classStringNames,String XmlName) throws IOException{
		//读取testng运行模板
		Document document = XmlExtents.readXml(classStringNames);
		LOGGER.INFO(document.toString());
		//更新testng XML
		XmlExtents.XmlOutput(document, GlobalConfig.getRootDir() + GlobalConfig.getSlash(), XmlName);
	}
}
