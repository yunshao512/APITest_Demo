package com.server.utils;

import com.server.api.common.ReporterLogger;
import com.server.api.common.GlobalConfig;
import com.server.api.infos.TemplateInfos;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TemplateUtil {

	private static ReporterLogger LOGGER = new ReporterLogger();
	/**
	 * 
	 * @param path file path
	 * @return  BufferedInputStream
	 * @throws FileNotFoundException
	 */
	public static BufferedInputStream init(String path)
			throws FileNotFoundException {
		FileInputStream finputStream = new FileInputStream(path);
		BufferedInputStream binputStream = new BufferedInputStream(finputStream);
		return binputStream;
	}
	
	public static String replaceTemplateFile(String srcString,
											 TemplateInfos tmpInfo, String replaceString) throws Exception
			{
		     srcString = srcString.replace(tmpInfo.toString(), replaceString);
		return srcString;
	}
	
	
	public static String[] splitTestClassTmp(String fileString, TemplateInfos tmpInfos) {
		String[] splitedString = null;
		if (fileString.contains(TemplateInfos.__TEST__.toString())) {
			splitedString = fileString.split(TemplateInfos.__TEST__.toString());
			return splitedString;
		}
		return splitedString;
	}
	
	
	public static String getTempPath() throws IOException{
		LOGGER.INFO("FileUtil.getProjectDir() + GlobalConfig.getTemplateFiles()+GlobalConfig.getSlash():{}",FileUtil.getProjectDir() + GlobalConfig.getTemplateFiles()+GlobalConfig.getSlash());
		return FileUtil.getProjectDir() + GlobalConfig.getTemplateFiles()+GlobalConfig.getSlash();
	}
	
	public static String getTempFileString(String tmpFilePath, String tmpFileName) throws IOException{
		BufferedInputStream input = init(tmpFilePath + tmpFileName);
		String tmpStrings = FileUtil.fileToString(input);
		return tmpStrings;
	}
}
