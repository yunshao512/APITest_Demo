package com.server.api.dataobject;

import com.server.api.common.ReporterLogger;
import com.server.api.dataobject.testcase.InterfaceStepsCase;
import com.server.api.dataobject.testcase.InterfaceTestStep;
import com.server.api.dataobject.testcase.TestStep;
import com.server.api.common.StepValuePool;
import com.server.api.common.GlobalConfig;
import org.apache.logging.log4j.simple.SimpleLogger;

import java.util.List;




public class InterfaceStepsExecutor extends StepsExecutor
{
	private static ReporterLogger LOGGER = new ReporterLogger();
	public InterfaceStepsExecutor(String caseFilePath,String caseID)
	{
		super(caseID, caseFilePath);
	}
	
	@Override
	public void setUp() throws Exception
    {
		 preCleanup();
		 setGlobalConfig();
		 exectuePreStep();
	}
	
	@Override
	public void tearDown() throws Exception 
	{
		try 
		{
			for(InterfaceTestStep step : ((InterfaceStepsCase)targetCase).Steps)
			{
				if(step.TearDown)
				{ GlobalConfig.getAutoProjectName();GlobalConfig.setStepsParameterFilePath(GlobalConfig.getXmlCaseDir()+GlobalConfig.getSlash()+step.StepParametersFilePath);//GlobalConfig.getRootDir()+"DataFiles"+GlobalConfig.getSlash()+"Xmls"+GlobalConfig.getSlash()+step.StepParametersFilePath);
				  InvokedMethodInfo resultInfo=getStepMethodInfo(step);
				  invokeMethod(resultInfo);
				}
			}
		} catch (Exception e) 
		{
			LOGGER.ExceptionToString(e);
		}
		finally
		{
			StepValuePool.cleanValuePool();
		}
		
	}
	
	protected void exectuePreStep() throws Exception
	{
		targetCase=getTestCase();
		for(InterfaceTestStep step : ((InterfaceStepsCase)targetCase).Steps)
		{
			if(step.SetUp)
			{
			    GlobalConfig.setStepsParameterFilePath(GlobalConfig.getAutoProjectName()+"DataFiles"+GlobalConfig.getSlash()+"Xmls"+GlobalConfig.getSlash()+step.StepParametersFilePath); //set glocal config for pre step parameters
		    	LOGGER.INFO("this.getClass():{}executeCase: set setup parameter path as",GlobalConfig.getStepsParameterFilePath());
			    InvokedMethodInfo resultInfo=getStepMethodInfo(step);
				LOGGER.INFO("this.getClass():{}executeCase: execute setup step:",resultInfo.classFullName+resultInfo.methodName+step.StepParameterID);
			    invokeMethod(resultInfo);
			}
		}
	}
	
	
	@Override
	public void executeCase() throws Exception 
	{
		this.setGlobalConfig();
		for(InterfaceTestStep step : ((InterfaceStepsCase)targetCase).Steps)
		{
			LOGGER.INFO(":{},Step case interface id is :{} ",this.getClass(), ((InterfaceStepsCase)targetCase).InterfaceID);
			LOGGER.INFO(":{},Step case interface module id is :{}",this.getClass(),targetCase.ModuleID);
			LOGGER.INFO(":{},Step case tags id are :{}",this.getClass(),targetCase.CaseTags);
			LOGGER.INFO(":{},Step case desc is :{}",this.getClass(),targetCase.Desc);
			if(!step.SetUp && !step.TearDown)
			{
				GlobalConfig.setStepsParameterFilePath(GlobalConfig.getXmlCaseDir()+GlobalConfig.getSlash()+step.StepParametersFilePath); //set glocal config for pre step parameters
				LOGGER.INFO(":{},executeCase: set step parameter path as :{}",this.getClass(),GlobalConfig.getStepsParameterFilePath());
				InvokedMethodInfo resultInfo=getStepMethodInfo(step);
				LOGGER.INFO(":{},executeCase: execute setup step:{}",this.getClass(),resultInfo.classFullName+resultInfo.methodName+step.StepParameterID);

				for(Integer i=0;i<step.For;i++)
				{
					LOGGER.INFO(":{},times execute step method :{}",this.getClass(),String.valueOf(i));

					if(step.StepModule)
					{
						InvokeStepModule(step);
					}
					else 
					{
						invokeMethod(resultInfo);	
					}
				}
			}
		}
	}
	
	@Override
	public void InvokeStepModule(TestStep stepModule) throws Exception
	{
		if(stepModule.StepModuleFilePath!="")
		{
		  this.setStepModulePath(stepModule.StepModuleFilePath);
		}
		this.setCaseID(stepModule.StepModuleID);
		InterfaceStepsCase stepModuleCase=this.getTestCase();
		stepModuleCase.Steps=changeModuleStepParameter(stepModuleCase.Steps,stepModule.ModuleStepParameters);
		for(InterfaceTestStep step : stepModuleCase.Steps)
		{
			if(!step.SetUp && !step.TearDown)
			{
				GlobalConfig.setStepsParameterFilePath(GlobalConfig.getXmlCaseDir()+GlobalConfig.getSlash()+step.StepParametersFilePath);//GlobalConfig.getRootDir()+"DataFiles"+GlobalConfig.getSlash()+"Xmls"+GlobalConfig.getSlash()+step.StepParametersFilePath);
				LOGGER.INFO(":{},executeCase: set setup parameter path as:{}",this.getClass(),GlobalConfig.getStepsParameterFilePath());
				InvokedMethodInfo resultInfo=getStepMethodInfo(step);
				LOGGER.INFO(":{},executeCase: execute setup step:{}",this.getClass(),resultInfo.classFullName+resultInfo.methodName+step.StepParameterID);
				for(Integer i=0;i<step.For;i++)
				{
					LOGGER.INFO(":{},times,execute step method :",this.getClass(),String.valueOf(i));
					invokeMethod(resultInfo);
				}
				
			}
		}
	}

	
	protected List<InterfaceTestStep> changeModuleStepParameter(List<InterfaceTestStep> steps,String newParameters)
	{
		if(newParameters=="")
		{
			return steps;
		}
		if(!newParameters.contains(","))
		{
			for(TestStep step:steps)
			{
				step.StepParameterID=newParameters;
			}
			return steps;
		}
		else 
		{
			String[] parameterArray=newParameters.split(",");
			for(Integer i=0;i<parameterArray.length;i++)
			{
				if(parameterArray[i].trim()!="")
				{
				  steps.get(i).StepParameterID=parameterArray[i].trim();	
				}
			}
			return steps;
		}
	   
	}
    
	@Override
    protected InterfaceStepsCase getTestCase() throws Exception
	{
		return (InterfaceStepsCase)getTestObject(this.getCaseID(), EnumObjectManager.IStepsCaseManager);
	}
}
