package com.server.api.manager;

import com.server.api.common.ReporterLogger;
import com.server.api.common.GlobalConfig;
import com.server.api.dataobject.stepparameter.InterfaceStepParameter;
import com.server.api.common.XMLSerializer;

public class InterfaceStepsParameterManager extends StepParameterManager
{
	private static final ReporterLogger LOGGER = new ReporterLogger();
    @Override
	public InterfaceStepParameter getStepParameter(String parameterID) throws Exception
	{
		InterfaceStepParameter parameterResult=new InterfaceStepParameter();
		String filePath=GlobalConfig.getStepsParameterFilePath();
		String stepParameterXPth="AllStepParameters/StepParameter";
		String parameterXML=this.getTestObjectXML(parameterID,filePath, stepParameterXPth, "ID");
		LOGGER.INFO("filePath:{},stepParameterXPth:{},parameterXML:{}",filePath,stepParameterXPth,parameterXML);
		return (InterfaceStepParameter) XMLSerializer.XMLToObject(parameterResult,parameterXML);
	}
}
