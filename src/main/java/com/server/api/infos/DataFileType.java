package com.server.api.infos;

import com.server.api.common.ReporterLogger;
import com.server.api.common.GlobalConfig;
import com.server.utils.DataFilesUtil;
import com.server.utils.FileUtil;
import com.server.utils.TemplateUtil;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.List;

public enum DataFileType {
	XML,
	EXCEL,
	WebUIXML;

	private static ReporterLogger LOGGER = new ReporterLogger();
	public static class TestClassGenerator {

		/**

		 * @param executorName testcase executor name
		 * @param testClassName class name
		 * @param testMethodNames method name
		 * @param executorParams executor parameters
		 * @return String
		 * @throws Exception  ex
		 */
		public static String generateTestClassContent(String testClasspackageName,String executorName, String testClassName,
													  List<String> testMethodNames, List<String> executorParams) throws Exception{

			String testString = null;
			StringBuilder stringBuilder = new StringBuilder();
			LOGGER.INFO("TemplateUtil.getTempPath() :{}",TemplateUtil.getTempPath() );
			//读取模板路径
			BufferedInputStream input = TemplateUtil.init(TemplateUtil.getTempPath() + "TestClassTmp");
			LOGGER.INFO("input:{}",input);
			String srcStrings = FileUtil.fileToString(input);
			String[] splitedStrings = TemplateUtil.splitTestClassTmp(srcStrings, TemplateInfos.__TEST__);

			for (int i = 0; i < splitedStrings.length; i++) {
				LOGGER.INFO("splitedStrings[i]:{},testClassName:{}",splitedStrings[i],testClassName);
				splitedStrings[i] = TemplateUtil.replaceTemplateFile(splitedStrings[i], TemplateInfos.$TestClasspackageName, testClasspackageName);
				splitedStrings[i] = TemplateUtil.replaceTemplateFile(splitedStrings[i], TemplateInfos.$TemplateClassName, testClassName);
				splitedStrings[i] = TemplateUtil.replaceTemplateFile(splitedStrings[i], TemplateInfos.$InterfaceStepExecutorName, executorName);
				if( i == 1 ){
					testString = splitedStrings[i];
					for(int j = 0; j < testMethodNames.size(); j++){
						splitedStrings[i] = TemplateUtil.replaceTemplateFile(testString, TemplateInfos.$TemplateTestMethod, testMethodNames.get(j));
						splitedStrings[i] = TemplateUtil.replaceTemplateFile(splitedStrings[i], TemplateInfos.$ExecutorParam, executorParams.get(j));
						stringBuilder.append(splitedStrings[i]);
					}
					splitedStrings[i] = "";
				}
				stringBuilder.append(splitedStrings[i]);
			}
			return stringBuilder.toString();
		}

		/**
		 * @param fileType  excle , xml
		 * @param filePath   file path
		 * @throws Exception ex
		 * @return String list
		 */
		public static List<String> generateTestClassFile(DataFileType fileType, String filePath) throws Exception{
			List<String> packageNameList=new ArrayList<String>();
			LOGGER.INFO("fileType:{},filePath:{}",fileType,filePath);
			if(fileType == XML)
			{
				packageNameList=createTestCaseClassFileForXml(filePath, fileType, ExecutorType.InterfaceStepsExecutor);
			}

			return packageNameList;

		}

		/**
		 *
		 * @param filePath xml file path
		 * @param fileType xml file type :WebUIXML,XML
		 * @param execturType   executurType
		 * @throws Exception ex
		 * @return String list
		 */
		private static List<String> createTestCaseClassFileForXml(String filePath,DataFileType fileType,ExecutorType execturType) throws Exception
		{

			List<String> dataFileNames = DataFilesUtil.getWholeNames(fileType);
			List<String> packageNameList=new ArrayList<String>();
			String classFilePath=filePath.replace("\\","/");
			for(String eachFileName:dataFileNames)
			{

				if(eachFileName.endsWith("TestCase.xml"))
				{
					eachFileName = eachFileName.replace("\\","/");
					eachFileName = eachFileName.replace(DataFilesUtil.getDataFilesPath(XML)+GlobalConfig.getSlash(),"");
					String className=DataFilesUtil.getTestClassNames(XML, eachFileName).get(0);
					List<String> testMethodNames = DataFilesUtil.getTestMethodNames(fileType, eachFileName, "");
					List<String> executorParams = DataFilesUtil.getExecutorParams(fileType, eachFileName, "");
					String testClasspackage_name=DataFilesUtil.getTestStepPackage(fileType, eachFileName).substring(0,DataFilesUtil.getTestStepPackage(fileType, eachFileName).length()-1)+"_unittest";

					String contents = TestClassGenerator.generateTestClassContent(testClasspackage_name,execturType.toString(),
						className, testMethodNames, executorParams);
					LOGGER.INFO("contents:{}",contents);
					String testStepPackageName=DataFilesUtil.getTestStepPackage(fileType, eachFileName);

				if(testStepPackageName.length()!=0)
				{
					String[] pageFolders=testStepPackageName.split("\\.");

					String tempPath="";
					for(Integer i=0;i<pageFolders.length;i++)
					{
						tempPath=tempPath+GlobalConfig.getSlash()+pageFolders[i];
					}
					classFilePath= GlobalConfig.getTestClassDir()+tempPath+"_unittest";
					String new_package_name=testStepPackageName.substring(0,testStepPackageName.length()-1)+"_unittest";
//					contents=contents.replaceAll(ProjectInfos.PACKAGE_NAME,new_package_name);
					if (packageNameList.contains(new_package_name+".")){
						LOGGER.INFO("packageNameList:{}","元素已存在");
					}
					else
					{
						packageNameList.add(new_package_name+".");
					}


				}
				FileUtil.createFileDir(classFilePath);
				LOGGER.INFO("contents:{}",contents);
				FileUtil.createFile(classFilePath+GlobalConfig.getSlash(), className + ".java", contents, true);
				}
			}
			return packageNameList;
		}

	}
}
