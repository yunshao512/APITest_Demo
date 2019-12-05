package com.server.api.dataobject;


import com.server.api.common.ReporterLogger;
import com.server.api.dataobject.testcase.StepsCase;
import com.server.api.dataobject.testcase.TestStep;
import com.server.api.common.GlobalConfig;

public abstract class StepsExecutor extends CaseExecutor
{
	private static ReporterLogger LOGGER = new ReporterLogger();
	protected String caseFilePath="";	
    protected StepsCase targetCase=null;

	public StepsExecutor(String caseID,String caseFilePath)
	{
		this.caseFilePath=GlobalConfig.getXmlCaseDir()+GlobalConfig.getSlash()+caseFilePath;
		this.setCaseID(caseID);
	}
	
	protected abstract void InvokeStepModule(TestStep stepModule) throws Exception;
	

	protected  void setGlobalConfig()
	{
		GlobalConfig.setTestCaseFilePath(this.caseFilePath); //set global config for testcase file path
	}
	
	protected StepsCase getTestCase() throws Exception
	{
		throw new Exception("The function doesn't implient.");
	}
	

    protected InvokedMethodInfo getStepMethodInfo(TestStep step)
    {
    	InvokedMethodInfo resultInfo=new InvokedMethodInfo();
    	resultInfo.classFullName=step.StepAssembly+step.StepGroup;
    	resultInfo.methodName=step.StepName;
    	resultInfo.parameters.add(step.StepParameterID);
    	resultInfo.jarFilePath=GlobalConfig.getStepMethodJarPath();
    	return resultInfo;
    }
    
   
    
    protected void setStepModulePath(String moduleFileName)
    {
    	String moduleFilePath=GlobalConfig.getXmlCaseDir()+GlobalConfig.getSlash()+moduleFileName;
		GlobalConfig.setTestCaseFilePath(moduleFilePath); //set global config for testcase file path
    }

}
