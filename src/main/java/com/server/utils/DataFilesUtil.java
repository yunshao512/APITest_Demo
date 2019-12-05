package com.server.utils;

import com.server.api.common.ReporterLogger;
import com.server.api.common.GlobalConfig;

import com.server.api.common.XmlExtents;
import com.server.api.infos.DataFileType;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataFilesUtil {

	private static ReporterLogger LOGGER = new ReporterLogger();
	/**
	 * @param fileType xml excel
	 * @return String
	 * @throws IOException ex
	 */
	public static String getDataFilesPath(DataFileType fileType) throws IOException{
		String dataFilePath = null;

		dataFilePath = GlobalConfig.getXmlCaseDir();
		LOGGER.INFO("dataFilePath:{}",dataFilePath);
		return dataFilePath.replace("\\","/");
	}
	
	/**
	 * @param fileType  xml excel
	 * @return String list
	 * @throws IOException ex
	 */
	public static List<String> getWholeNames(DataFileType fileType) throws IOException{
		String dataFilePath = null;
		ArrayList<String> allFileNames = new ArrayList<String>();
		
		if(fileType == DataFileType.EXCEL)
		{
			dataFilePath = getDataFilesPath(fileType);
			FileUtil.getFilesName(dataFilePath, allFileNames);
		}else
		{
			System.out.println("***************fileType************");
			dataFilePath = getDataFilesPath(fileType);
			LOGGER.INFO("dataFilePath:{}",dataFilePath);
			FileUtil.getFilesName(dataFilePath, allFileNames);
		}
		System.out.println("***************allFileNames************");
		return allFileNames;
	}
	
	/**
	 * @param fileType excel xml 
	 * @return String list
	 * @throws IOException ex
	 */
	public static List<String> getPreNames(DataFileType fileType) throws IOException{
		String dataFilePath = null;
		ArrayList<String> wholeFileNames =new ArrayList<String>();
		ArrayList<String> preFileNames = new ArrayList<String>();
		
		if(fileType == DataFileType.EXCEL){
			dataFilePath = getDataFilesPath(fileType);
			FileUtil.getFilesName(dataFilePath, wholeFileNames);
			for(int j=0; j<wholeFileNames.size(); j++){
				String eachPreName = wholeFileNames.get(j).substring(0, wholeFileNames.get(j).lastIndexOf("."));
				preFileNames.add(eachPreName);
			}
			return preFileNames;
		}else if(fileType == DataFileType.XML){
			dataFilePath = getDataFilesPath(fileType);
			FileUtil.getFilesName(dataFilePath, wholeFileNames);
			for(int j=0; j<wholeFileNames.size(); j++){
				String eachPreName = wholeFileNames.get(j).substring(0, wholeFileNames.get(j).lastIndexOf("."));
				preFileNames.add(eachPreName);
			}
			return preFileNames;
		}else{
			return preFileNames;
		}
	}
	
	/**
	 * Excel: TestClassName = ExcelFile sheetName
	 * @param fileType excel|xml
	 * @param eachFileName filename
	 * @return String list
	 * @throws IOException ex
	 * @throws DocumentException ex
	 */
	public static List<String> getTestClassNames(DataFileType fileType, String eachFileName) throws IOException, DocumentException {
		List<String> classNameList = new ArrayList<String>();
		String dataFilePath = null;
		
		if(eachFileName.endsWith("TestCase.xml"))
		{
			String className=eachFileName.substring(0, eachFileName.indexOf("."));
			System.out.println(className);
			classNameList.add(className.replace(GlobalConfig.getSlash(),"_"));
		}

		return classNameList;
	}
	

	
	/**
	 * @param fileType excel|xml
	 * @param eachFileName filename
	 * @param fileSheetName excel sheetname
	 * @return TestMethod name list
	 * @throws Exception  ex
	 */
	public static List<String> getTestMethodNames(DataFileType fileType, String eachFileName, String fileSheetName) throws Exception{
		List<String> methodNameList = new ArrayList<String>();
		String dataFilePath = null;

		if(eachFileName.endsWith("TestCase.xml"))
		{
			dataFilePath =DataFilesUtil.getDataFilesPath(fileType)+GlobalConfig.getSlash()+eachFileName;
			LOGGER.INFO("dataFilePath:{}",dataFilePath);
			methodNameList = XmlExtents.getAttributeValueByElementName(dataFilePath, "TestCase", "Name");
		}
		LOGGER.INFO("methodNameList:{}",methodNameList);
		return methodNameList;
	}
	
	public static List<String> getTestCaseID(DataFileType fileType, String eachFileName, String fileSheetName) throws IOException, DocumentException{
		List<String> caseIDList = new ArrayList<String>();
		String dataFilePath = null;
	    if(eachFileName.endsWith("TestCase.xml"))
		{
			   dataFilePath =DataFilesUtil.getDataFilesPath(fileType)+GlobalConfig.getSlash()+ eachFileName;
			   caseIDList = XmlExtents.getAttributeValueByElementName(dataFilePath, "TestCase", "ID");
		}
		return caseIDList;
	}
	
	public static String getTestStepPackage(DataFileType fileType, String eachFileName) throws IOException, DocumentException{
		String packageName="";
		String dataFilePath = null;
		try
		{
			 if(eachFileName.endsWith("TestCase.xml"))
				{
					   dataFilePath =DataFilesUtil.getDataFilesPath(fileType)+GlobalConfig.getSlash()+ eachFileName;
					   packageName = XmlExtents.getElementByName(dataFilePath,"StepAssembly").getTextTrim();
				}
			
		}
		catch(Exception ex)
		{
			
		}
	 
		return packageName;
	}
	
	/**
	 * @param fileType  excel|xml
	 * @param fileName file name
	 * @param sheetName excel sheet name
	 * @return String list
	 * @throws IOException ex
	 * @throws DocumentException ex
	 */
	public static List<String> getExecutorParams(DataFileType fileType, String fileName, String sheetName) throws Exception, DocumentException{
		List<String> executorParamList = new ArrayList<String>();
				
		if(fileType == DataFileType.EXCEL)
		{
			List<String> ids = getTestMethodNames(fileType, fileName, sheetName);
			for(String id:ids)
			{
				executorParamList.add("\"" + fileName.substring(0, fileName.lastIndexOf(".")).replace("\\","\\\\") + "." + sheetName + "." + id + "\"");
			}
		}
		else
		{
			List<String> testCaseNames = getTestClassNames(fileType, fileName);
			for(String eachTestCaseName:testCaseNames)
			{
				List<String> testCaseIds = getTestCaseID(fileType, fileName, "");
				for(String id:testCaseIds)
				{

					
//					fileName=fileName.replace(DataFilesUtil.getDataFilesPath(fileType)+GlobalConfig.getSlash(),"");
					executorParamList.add("\"" + fileName.substring(0, fileName.lastIndexOf(".")).replace("\\","\\\\") + ".xml\"" + ","+"\""+id+"\"");
				}
			}
		}
		return executorParamList;
	}
}
