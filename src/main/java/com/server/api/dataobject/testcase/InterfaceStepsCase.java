package com.server.api.dataobject.testcase;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;





public class InterfaceStepsCase extends StepsCase
{
	@ElementList(name="Steps",required=true,inline=true)
	public List<InterfaceTestStep> Steps=new ArrayList<InterfaceTestStep>();
	
	
	@Attribute(name="InterfaceID",required=false)
	public String InterfaceID="";
}
