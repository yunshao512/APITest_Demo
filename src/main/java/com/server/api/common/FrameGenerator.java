package com.server.api.common;



import java.util.ArrayList;
import java.util.List;


import com.server.api.infos.DataFileType;
import com.server.utils.ProjectUtil;

public class FrameGenerator {

	private static ReporterLogger LOGGER = new ReporterLogger();
	public static void createInterfaceTestProject()
	{
		String testClassFilePath = GlobalConfig.getXmlCaseDir();
		LOGGER.INFO("testClassFilePath:{}",testClassFilePath);
		List<String> packageNameList=new ArrayList<String>();
		try
		{

            LOGGER.INFO("Start create Interface XML Testcase class files");
			List<String> XMLPackageNameList= DataFileType.TestClassGenerator.generateTestClassFile(DataFileType.XML, testClassFilePath);
 			packageNameList.addAll(XMLPackageNameList);

            LOGGER.INFO("Start create testng.xml files");

			ProjectUtil.createTestngXml(packageNameList);
		} catch (Exception e)
		{
			LOGGER.ExceptionToString(e);
		}
	}
}
