package com.server.api.manager;


import java.util.List;

import com.server.api.common.ReporterLogger;
import com.server.api.dataobject.TestObject;
import com.server.api.dataobject.stepparameter.GlobalParameter;
import com.server.api.dataobject.stepparameter.StepParameter;
import com.server.api.common.XMLParser;
import com.server.api.common.XMLSerializer;
import org.dom4j.Element;
import com.server.api.common.GlobalConfig;


public abstract class StepParameterManager extends TestObjectManager
{
	private static final ReporterLogger LOGGER = new ReporterLogger();

	@Override
	public TestObject getItem(String ID) throws Exception
	{
		StepParameter stepParameter=getStepParameter(ID);
		GlobalParameter globalParameter=this.getGlobalParameter();
		stepParameter.globalParameter=globalParameter;
		return stepParameter;
	}

	protected abstract StepParameter getStepParameter(String parameterID) throws Exception;
	
	protected GlobalParameter getGlobalParameter()
	{
		GlobalParameter parameterResult=new GlobalParameter();
		String filePath=GlobalConfig.getStepsParameterFilePath();
		String stepParameterXPth="AllStepParameters/GlobalParameters";
		List<Element> globalParameterList= XMLParser.getElementsByXPath(filePath, stepParameterXPth);
		if(globalParameterList.size()>0)
		{
		   String parameterXML=globalParameterList.get(0).asXML();
		   parameterResult= (GlobalParameter) XMLSerializer.XMLToObject(parameterResult,parameterXML);
		}
		return parameterResult;
	}
	

}
