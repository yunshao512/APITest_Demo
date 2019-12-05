package com.server.api.common;


import com.server.api.dataobject.EnumObjectManager;
import com.server.api.dataobject.TestObject;
import com.server.api.dataobject.stepparameter.InterfaceStepParameter;
import com.server.api.manager.IManager;
import com.server.api.manager.TestObjectManagerFactory;

public class ParameterHelper {

	private static final ReporterLogger LOGGER = new ReporterLogger();

	public ParameterHelper() 
    {
		// TODO Auto-generated constructor stub
	}
	
	@Deprecated
	public static TestObject getParameter(String parameterID) throws Exception
	{
		LOGGER.INFO("parameterID:{}",parameterID);
		IManager manager= TestObjectManagerFactory.getTestObjectManager(EnumObjectManager.IStepParameterManager);
		LOGGER.INFO("manager:{}",manager);
		InterfaceStepParameter parameter=(InterfaceStepParameter)manager.getItem(parameterID);
		LOGGER.INFO("最终结果=parameter:{}",parameter);
		return parameter;
	}
	
	public static TestObject getInterfaceStepParameter(String parameterID) throws Exception
	{
		LOGGER.INFO("parameterID:{}",parameterID);
		IManager manager=TestObjectManagerFactory.getTestObjectManager(EnumObjectManager.IStepParameterManager);
		LOGGER.INFO("manager:{}",manager);
		InterfaceStepParameter parameter=(InterfaceStepParameter)manager.getItem(parameterID);
		LOGGER.INFO("最终结果=parameter:{}",parameter);
		return parameter;
	}
    
}
