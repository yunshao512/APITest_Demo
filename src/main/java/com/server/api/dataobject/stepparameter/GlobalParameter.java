package com.server.api.dataobject.stepparameter;

import java.util.ArrayList;


import com.server.api.dataobject.TestObject;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "GlobalParameters")
public class GlobalParameter extends TestObject {
	@ElementList(name = "Parameters", required = false)
	public ArrayList<Parameter> parameters = new ArrayList<Parameter>();

	public String getValue(String key) {
		String value = "";
		for (Parameter keyPairs : parameters) {
			if (keyPairs.key.equals(key)) {
				value = keyPairs.value;
				break;
			}
		}
		return value.trim();
	}
}
