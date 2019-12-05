package com.server.api.manager;

import java.util.List;

import com.server.api.dataobject.TestObject;
import com.server.api.dataobject.testcase.StepsCase;
import com.server.api.dataobject.testcase.TestStep;
import com.server.api.common.XMLParser;
import org.dom4j.Element;



public abstract class StepsCaseManager extends TestObjectManager
{

	@Override
	public TestObject getItem(String ID) throws Exception
	{
		return geTestCase(ID);
	}

	public abstract StepsCase geTestCase(String caseID) throws Exception;
	
	
	public abstract List<StepsCase> getAllTestCase(String filePath) throws Exception;
	
	protected void transforSpecialChar(TestStep step)
	{
		step.StepParametersFilePath=step.StepParametersFilePath.replaceAll("_","/");
	}
	
	protected void resetTestCaseModuleID(StepsCase testCase,String filePath)
	{
		String assemblyXpath="AllTestCases/ModuleID";
		if(testCase.ModuleID==null || testCase.ModuleID=="")
		{
		   List<Element> moduleElements= XMLParser.getElementsByXPath(filePath, assemblyXpath);
		   if(moduleElements.size()>0)
		   {
			   testCase.ModuleID=moduleElements.get(0).getTextTrim();
		   }
		   else
		   {
			   testCase.ModuleID="0";
		   }
		}
	}
}
