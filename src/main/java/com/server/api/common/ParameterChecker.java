package com.server.api.common;

public class ParameterChecker 
{

	public static void StringParameterCheck(String parameterName,String parameterValue) throws Exception
	{
		if(parameterValue==null || parameterValue=="") {throw new Exception(parameterName+" should not be null or empty");}
	}

}
