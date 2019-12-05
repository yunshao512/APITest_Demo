package com.server.api.common;

import com.server.utils.StringUtil;

import java.io.File;
import java.util.Properties;

public class GlobalConfig {

    private static ReporterLogger LOGGER = new ReporterLogger();

    private static String TestCaseFilePath="";
    private static String StepsParameterFilePath="";
    private static String TestClassDir="";

//    private static String uIElementsFilePath="";
    private static String preStepResult="PRESTEPRESULT";
    //配置工程路径
    private static String rootDir="";
    //配置用例文件路径
    private static String XmlCaseDir="";
    //生成测试类输出路径
    private static String TestCaseAotuDir="";

    public static boolean isOSLinux() {
        Properties prop = System.getProperties();
        String os = prop.getProperty("os.name");
        if (os != null && os.toLowerCase().indexOf("linux") > -1) {
            System.out.println(true);
            return true;
        } else {
            System.out.println(false);
            return false;
        }
    }

    /**
     * @return the testCaseFilePath
     */
    public static String getTestCaseFilePath() {
        return TestCaseFilePath;
    }


    /**
     * @param testCaseFilePath the testCaseFilePath to set
     */
    public static void setTestCaseFilePath(String testCaseFilePath)
    {
        TestCaseFilePath =testCaseFilePath;
    }


    /**
     * @return the stepsParameterFilePath
     */
    public static String getStepsParameterFilePath() {
        return StepsParameterFilePath;
    }

    public static String getSlash()
    {
/*        String slash="\\";
        Properties props=System.getProperties();
        String oSName= props.getProperty("os.name");
        if(!oSName.startsWith("Windows"))
        {
            slash="/";
        }*/
        String slash="/";
        return slash;
    }

    /**
     * @return 获取工程根路径
     */
    public static String getRootDir()
    {

        try
        {
            rootDir= ConfigReader.GetValue("DemoConfig.properties","rootDir");
            if(rootDir.equals(""))
            {
                File directory = new File("");
                //项目工程跟目录
                rootDir=directory.getCanonicalPath();
            }
            else
            {
                //配置文件配置路径
                rootDir=rootDir+getSlash();
            }
        }
        catch(Exception e)
        {
            LOGGER.ExceptionToString(e);
        }
        rootDir = StringUtil.getReplacePath(rootDir);
        return rootDir;
    }
    /**
     * @return 获取XML用例路径
     */
    public static String getXmlCaseDir()
    {

        try
        {
            XmlCaseDir=ConfigReader.GetValue("DemoConfig.properties","XmlCaseDir");
            if(XmlCaseDir.equals(""))
            {
                File directory = new File("");
                //项目工程跟目录
                XmlCaseDir=directory.getCanonicalPath()+getSlash()+"/DataFiles/Xmls";
            }
            else
            {
                //配置文件配置路径
                XmlCaseDir=getRootDir()+XmlCaseDir;
            }
        }
        catch(Exception e)
        {
            LOGGER.ExceptionToString(e);
        }
        XmlCaseDir = StringUtil.getReplacePath(XmlCaseDir);
        return XmlCaseDir;
    }
    /**
     * @return 获取测试类输出文件路径
     */
    public static String getTestCaseAotuDir()
    {

        try
        {
            TestCaseAotuDir=ConfigReader.GetValue("DemoConfig.properties","TestCaseAotuDir");
            if(TestCaseAotuDir.equals(""))
            {
                throw new Exception("Config.properties文件TestCaseAotuDir参数不允许为空");
            }
            //项目工程跟目录
            TestCaseAotuDir=GlobalConfig.getRootDir()+TestCaseAotuDir;
        }
        catch(Exception e)
        {
            LOGGER.ExceptionToString(e);
        }
        TestCaseAotuDir = StringUtil.getReplacePath(TestCaseAotuDir);
        return TestCaseAotuDir;
    }
    /**
     * @return 获取测试类输出文件路径
     */
    public static String getTestClassDir()
    {

        try
        {
            TestClassDir=ConfigReader.GetValue("DemoConfig.properties","TestClassDir");
            if(TestClassDir.equals(""))
            {
                throw new Exception("Config.properties文件TestClassDir参数不允许为空");
            }
            File directory = new File("");
            //项目工程跟目录
            TestClassDir=directory.getCanonicalPath()+TestClassDir;
        }
        catch(Exception e)
        {
            LOGGER.ExceptionToString(e);
        }
        TestClassDir = StringUtil.getReplacePath(TestClassDir);
        return TestClassDir;
    }
    /**
     * @return 获取测试模板文件路径
     */
    public static String getTemplateFiles()
    {
        String TemplateFiles="";
        try
        {
            TemplateFiles=ConfigReader.GetValue("DemoConfig.properties","TemplateFiles");
            if(TemplateFiles.equals(""))
            {
                throw new Exception("Config.properties文件TemplateFiles参数不允许为空");
            }
        }
        catch(Exception e)
        {
            LOGGER.ExceptionToString(e);
        }
        TemplateFiles = StringUtil.getReplacePath(TemplateFiles);
        return TemplateFiles;
    }
    /**
     * @return 获取资源文件路径
     */
    public static String getResourcesDirPath()
    {
        String resourcesDirPath="";
        try
        {
            resourcesDirPath=ConfigReader.GetValue("DemoConfig.properties","ResourcesDirPath");
            if(resourcesDirPath.equals(""))
            {
                throw new Exception("Config.properties文件ResourcesDirPath参数不允许为空");
            }
        }
        catch(Exception e)
        {
            LOGGER.ExceptionToString(e);
        }
        resourcesDirPath = StringUtil.getReplacePath(resourcesDirPath);
        return resourcesDirPath;
    }
    /**
     * @return 获取测试报告路径
     */
    public static String getEmailReportPath()
    {
        String emailReportPath="";
        try
        {
            emailReportPath=ConfigReader.GetValue("DemoConfig.properties","EmailReportPath");
            if(emailReportPath.equals(""))
            {
                throw new Exception("Config.properties文件EmailReportPath参数不允许为空");
            }

        }
        catch(Exception e)
        {
            LOGGER.ExceptionToString(e);
        }
        emailReportPath = StringUtil.getReplacePath(getRootDir()+emailReportPath);
        return emailReportPath;
    }
    /**
     * @param stepsParameterFilePath the stepsParameterFilePath to set
     */
    public static void setStepsParameterFilePath(String stepsParameterFilePath)
    {
        StepsParameterFilePath =stepsParameterFilePath;
    }


    /**
     * @return the urlParametersSignal
     */
    public static String getUrlParametersSignal()
    {
        //return ConfigReader.GetValue("DemoConfig.properties", "urlParametersSignal");
        return null;
    }


    /**
     * @return the preStepResult
     */
    public static String getPreStepResult() {
        return preStepResult;
    }


    public static String apiHost()
    {
//        return ConfigReader.GetValue("DemoConfig.properties", "apiHost");
        return null;
    }





    /**
     * @return the domainName
     */
    public static String getDomainName() {
//        return ConfigReader.GetValue("DemoConfig.properties","domainName");
        return null;
    }


    public static String getStepMethodJarPath()
    {
        if(ConfigReader.GetValue("DemoConfig.properties","stepMethodJarFiles")=="")
        {
            return null;
        }
        else
        {
//            return getRootDir()+"Libs"+getSlash()+ConfigReader.GetValue("DemoConfig.properties","stepMethodJarFiles");
            return null;
        }
    }

    public static String getDescColumnSignal()
    {
//        return ConfigReader.GetValue("DemoConfig.properties", "descColumnSignal");
        return null;
    }


    /**
     * @return the uIElementsFilePath
     */
//    public static String getuIElementsFilePath() {
//        return uIElementsFilePath;
//    }


//    /**
//     * @param uIElementsFilePath the uIElementsFilePath to set
//     */
//    public static void setuIElementsFilePath(String uIElementsFilePath) {
//        GlobalConfig.uIElementsFilePath =getRootDir()+ uIElementsFilePath;
//    }

    public static String getWebControllDefaultPackage()
    {
//        return ConfigReader.GetValue("DemoConfig.properties","webcontrolldefaultpackage");
        return null;
    }

    public static String getBrowserType()
    {
//        return ConfigReader.GetValue("DemoConfig.properties","browserType");
        return null;
    }

    public static String screenPicuterPath()
    {
//        return ConfigReader.GetValue("DemoConfig.properties","screenpicturedir")+getSlash();
        return null;
    }

    public static String getAutoProjectName()
    {
//        String rootDir=ConfigReader.GetValue("DemoConfig.properties","rootDir");
//        String autoProjectName=ConfigReader.GetValue("DemoConfig.properties","autoprojectfolder");
        if(rootDir.equals(""))
        {
//            autoProjectName="";
        }
        else
        {
//            autoProjectName=autoProjectName+getSlash();
        }
//        return autoProjectName;
        return null;
    }

    public static String getGlobalParameterFileName()
    {
//        String globalParameterFileName=ConfigReader.GetValue("DemoConfig.properties","globalParameterFileName");
//        return globalParameterFileName;
        return null;
    }

}
