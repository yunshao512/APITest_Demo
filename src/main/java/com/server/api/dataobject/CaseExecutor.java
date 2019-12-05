package com.server.api.dataobject;

import java.lang.reflect.Method;


import com.server.api.common.ReporterLogger;
import com.server.api.common.GlobalConfig;
import com.server.api.common.ParameterChecker;
import com.server.api.common.StepValuePool;
import com.server.api.common.ClassReflector;
import com.server.api.manager.IManager;
import com.server.api.manager.TestObjectManagerFactory;

public abstract class CaseExecutor 
{

	private String caseID="";
	private static ReporterLogger LOGGER = new ReporterLogger();
	public final void execute() throws Exception
	{
		LOGGER.INFO("*************************************************************");
		LOGGER.INFO(":{},Start executing case :{}",this.getClass(),this.getCaseID());
		try 
		{
			setUp();
			executeCase();
		}
		finally
		{
			tearDown();
		}
	}
	
	protected abstract void setUp() throws Exception;
	
	protected abstract void executeCase() throws Exception;
	
	protected abstract void tearDown() throws Exception;
	
	
	protected TestObject getTestObject(String objectID, EnumObjectManager testObjectType) throws Exception
	{
		LOGGER.INFO(":{},objectID+testObjectType.toString():{},",this.getClass(),objectID+testObjectType.toString());
		ParameterChecker.StringParameterCheck(testObjectType.name()+"objectID",objectID);
		IManager testObjectManager= TestObjectManagerFactory.getTestObjectManager(testObjectType);
		return testObjectManager.getItem(objectID);
	}
	
	protected void preCleanup()
	{
		LOGGER.INFO(":{},preCleanup: run precleanup method",this.getClass());
		StepValuePool.createInstance().getValueDic().remove(GlobalConfig.getPreStepResult());
	}
	
	protected  Object invokeMethod(InvokedMethodInfo methodInfo) throws Exception
	{
		Object targetClassInstanceObject= ClassReflector.createInstance(methodInfo.jarFilePath,methodInfo.classFullName); //create target instance
		Method targetMethod= ClassReflector.getMethod(targetClassInstanceObject,methodInfo.methodName,methodInfo.parameters.toArray());
		LOGGER.INFO(":{},targetMethod.getName():{}",this.getClass(),targetMethod.getName());
		LOGGER.INFO("targetClassInstanceObject():{},methodInfo.parameters.toArray()():{}",targetClassInstanceObject,methodInfo.parameters.toArray());
		return targetMethod.invoke(targetClassInstanceObject,methodInfo.parameters.toArray());
	}

	protected void executeSql(String sqlContextID) throws Exception
	{
       //InterfaceStepParameter parameters=(InterfaceStepParameter)getTestObject(sqlContextID, EnumObjectManager.IStepParameterManager);
       //DBOperationService.executeNoneQuery(parameters.ConnectiongString,parameters.CommandText,DBProvider.Mysql);
		throw new Exception("not impliement!");
	}

	protected void invokeDBStep(String dbStepID) throws Exception
	{
		   throw new Exception("not impliement!");
	}

	protected InvokedMethodInfo parserPreStepMethodInfo(String methodInfo, Object[] extParameters) throws Exception
	{
		ParameterChecker.StringParameterCheck("invokeMethod",methodInfo);
    	InvokedMethodInfo resultInfo=new InvokedMethodInfo();
    	if(methodInfo.contains(":"))//has parameter for preStepMethod
		{
			String preStepMethodInfo=methodInfo.split(":")[0];
			resultInfo.parameters.add(methodInfo.split(":")[1]);
		    resultInfo.methodName=preStepMethodInfo.substring(preStepMethodInfo.lastIndexOf(".")+1);
		    resultInfo.classFullName=preStepMethodInfo.replace("."+resultInfo.methodName,"");
		}
		else
		{
			resultInfo.parameters.add("");
			resultInfo.methodName=methodInfo.substring(methodInfo.lastIndexOf(".")+1);
			resultInfo.classFullName=methodInfo.replace("."+resultInfo.methodName,"");
		}
    	resultInfo.jarFilePath=GlobalConfig.getStepMethodJarPath();
    	if(extParameters!=null)
    	{
    		for(Object obj : extParameters)
        	{
        		resultInfo.parameters.add(obj);
        	}
    	}
    	return resultInfo;
    }
    
	protected String getCaseID()
	{
		return caseID;
	}
	
	protected void setCaseID(String caseID)
	{
	   this.caseID=caseID;	
	}
}