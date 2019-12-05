package com.server.api.manager;


import com.server.api.common.ReporterLogger;
import com.server.api.dataobject.EnumObjectManager;

public class TestObjectManagerFactory {

	private static final ReporterLogger LOGGER = new ReporterLogger();
	public static IManager getTestObjectManager(EnumObjectManager objectType) throws Exception
	{
		LOGGER.INFO("objectType:{}",objectType);
		if(objectType==null){throw new Exception("objectType should not be null or empty!");}
		IManager result=null;
		switch (objectType.ordinal()) 
		{
		   case 2:
			   LOGGER.INFO("2:{}",2);
	           result=new InterfaceStepsParameterManager();
	           break;
		   case 1:
			   LOGGER.INFO("1:{}",1);
			   result=new InterfaceStepsCaseManager();
			   break;
		}
		return result;
	}

}
