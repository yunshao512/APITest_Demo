package com.server.api.dataobject;

import java.util.ArrayList;

public class InvokedMethodInfo
{
	public String classFullName;
	public String methodName;
	public ArrayList<Object> parameters=new ArrayList<Object>();
	public String jarFilePath=null;
}
