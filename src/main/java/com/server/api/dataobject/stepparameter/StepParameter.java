package com.server.api.dataobject.stepparameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.server.api.dataobject.TestObject;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "StepParameter")
public class StepParameter extends TestObject {

    @Attribute(name = "ID", required = true)
    public String ID;

    @Attribute(name = "Name", required = false)
    public String Name = "";

    @Element(name = "ConnectionString", required = false)
    public String ConnectiongString = "";

    @Element(name = "CommandText", required = false)
    public String CommandText = "";

    @Element(name = "RequestBody", required = false)
    public String RequestBody = "";

    @ElementList(name = "RequestHeader", required = false)
    public ArrayList<Parameter> RequestHeader = new ArrayList<Parameter>();

    @Element(name = "AssertType", required = false)
    public String AssertType = "";

    @Element(name = "RequestStatus", required = false)
    public String RequestStatus = "";

    @ElementList(name = "Parameters", required = false)
    public ArrayList<Parameter> parameters = new ArrayList<Parameter>();

    @ElementList(name = "VerifyResult", required = false)
    public ArrayList<Parameter> VerifyResult = new ArrayList<Parameter>();

    public GlobalParameter globalParameter = null;

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
	
	public Integer getIntegerValue(String key) {
		String value = "";
		for (Parameter keyPairs : parameters) {
			if (keyPairs.key.equals(key)) {
				value = keyPairs.value;
				break;
			}
		}
		return Integer.valueOf(value.trim());
	}
	
	public Boolean getBooleanValue(String key) {
		String value = "";
		for (Parameter keyPairs : parameters) {
			if (keyPairs.key.equals(key)) {
				value = keyPairs.value;
				break;
			}
		}
		return Boolean.valueOf(value.trim());
	}

	public String getGlobalValue(String key) {
		String value = "";
		if (globalParameter != null) {
			for (Parameter keyPairs : globalParameter.parameters) {
				if (keyPairs.key.equals(key)) {
					value = keyPairs.value;
					break;
				}
			}
		}
		return value.trim();
	}

	public HashMap<String, String> getURLParametersMap() {
		LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();
		for (Parameter keyPairs : parameters) {
			if (keyPairs.key.startsWith("$")) {
				String tempKey = keyPairs.key.substring(1);
				result.put(tempKey, keyPairs.value);
			}
		}
		return result;
	}

	public void updateURLParameters(String key, String value) {
		updateParameters("$" + key, value);
	}

	public void updateParameters(String key, String value) {
		removeParameter(key);
		Parameter parameter = new Parameter();
		parameter.key = key;
		parameter.value = value;
		this.parameters.add(parameter);
	}

	private void removeParameter(String key) {
		for (int i = 0; i < this.parameters.size(); i++) {
			if (this.parameters.get(i).key.equals(key)) {
				this.parameters.remove(i);
			}
		}
	}
}
