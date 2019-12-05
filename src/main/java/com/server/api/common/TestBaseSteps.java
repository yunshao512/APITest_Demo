package com.server.api.common;



import com.server.api.common.Cipher;
import com.server.api.common.ReporterLogger;
import com.server.api.dataobject.StepMethodDesc;
import com.server.api.dataobject.stepparameter.InterfaceStepParameter;
import com.server.api.dataobject.stepparameter.Parameter;
import com.server.api.common.StepValueHelper;

import com.server.utils.DateUtil;
import com.server.utils.SaveParamUtils;
import com.server.utils.StringUtil;
import com.server.utils.assertUtil.AssertUtil;
import com.server.utils.requestsUtil.RestAssuredUtil;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestBaseSteps {

    private static ReporterLogger LOGGER = new ReporterLogger();
    //请求cookie
    public Map<String,Object> cookies = new HashMap<>();
    //请求参数map
    public Map<String,String> paramMap = new HashMap<>();
    //请求头map
    public Map<String,String> headersMap = new HashMap<>();
    //请求body
    public String requestBody ;

    public Boolean contentType = false;
    //断言类型
    public String assertType ;
    //返回状态码
    public int requestStatus ;
    //校验参数map
    public Map<String,String> verifyMap = new HashMap<>();

    @StepMethodDesc(description = "提取请求参数", owner = "yun.zhang")
    public void extractParameter(InterfaceStepParameter parameter){
        LOGGER.INFO("开始执行：{}","******开始赋值");
        cookies = ResponseCookies(StepValueHelper.getStepOutputStringValue("cookie"));
        LOGGER.INFO("cookies:{}",cookies);
        paramMap = saveParam(parameter.parameters);
        LOGGER.INFO("paramMap值:{}",paramMap);
//
        requestBody = parameter.RequestBody;
        LOGGER.INFO("RequestBody:{}",requestBody);

        headersMap = saveParam(parameter.RequestHeader);
        LOGGER.INFO("headersMap值:{}",headersMap);

        assertType = parameter.AssertType;
        LOGGER.INFO("assertType值:{}",assertType);

        requestStatus = Integer.valueOf(parameter.RequestStatus);
        LOGGER.INFO("requestStatus值:{}",requestStatus);

        verifyMap = saveParam(parameter.VerifyResult);
        LOGGER.INFO("verifyMap值:{}",verifyMap);
    }
    @StepMethodDesc(description = "遍历参数保存为map", owner = "yun.zhang")
    public Map<String,String> saveParam(List<Parameter> parameter) {
        Map<String,String> paramMap = new HashMap<>();
        for (Parameter param : parameter){
            if (param.key.equals("password"))
                paramMap.put(param.key, Cipher.md5(param.value));
            else
                paramMap.put(param.key, SaveParamUtils.buildParam(param.value));//param.value);
        }
        return paramMap;
    }
    @StepMethodDesc(description = "遍历参数保存为map", owner = "yun.zhang")
    public Map<String, Header> saveHeader(List<Parameter> parameter) {
        Map<String, Header> paramMap = new HashMap<>();
        for (Parameter param : parameter){
            if (param.value.contains("application/json"))
                contentType = true;
            paramMap.put(param.key, new BasicHeader(param.key, param.value));
        }
/*        for (String key:paramMap.keySet()){
            System.out.println(paramMap.get(key).toString() +"         "+key);
        }*/
        return paramMap;
    }
    @StepMethodDesc(description = "添加单个header", owner = "yun.zhang")
    public Map<String,String> putHeader(String keyHeader,String valueHeader) {
        if (!keyHeader.isEmpty()&&!valueHeader.isEmpty()){
            headersMap.put(keyHeader, valueHeader);
            return headersMap;
        }
//            headersMap.put(keyHeader, new BasicHeader(keyHeader, valueHeader));
//        return headersMap;
            return null;
    }
    @StepMethodDesc(description = "请求获取response对象", owner = "yun.zhang")
    public Response HttpResponseData(String url,String path,Map<String,String> headersMap,Map<String,String> paramMap,String requestBody,String method){
        Response response = null;
        response = RestAssuredUtil.executeGetRequestWithParameters(url,path,headersMap,paramMap,requestBody,method,cookies);
        if (assertType.equals("contains")||assertType.equals("onContains")){
            for (String key : verifyMap.keySet()) {
                LOGGER.INFO("assertType:{},response.asString:{},verifyMap:{}", assertType, response.asString(), verifyMap.get(key));
                AssertUtil.getAssertResult(assertType, response.asString(), verifyMap.get(key));
            }
        }else{
            AssertResult(RestAssuredUtil.getAsJSON(response));
        }
        return response;
    }
    @StepMethodDesc(description = "token字符串格式化为map", owner = "yun.zhang")
    public Map<String,Object> ResponseCookies(String cookie){
        Map<String,Object> cookies = new HashMap<>();
        if (StringUtil.isEmpty(cookie)) {//StringUtil.isEmpty(cookie)){
            return cookies;
        }
        else
        {
            String[] headerArray=cookie.split(",");
            for (String head : headerArray){
                String[] tempArray=head.split("=");
                if (tempArray.length<2)
                    cookies.put(tempArray[0],"");
                else
                    cookies.put(tempArray[0],tempArray[1]);
            }
            return cookies;
        }
    }
    @StepMethodDesc(description = "解析json跟verifyMap的value值进行断言", owner = "yun.zhang")
    public void AssertResult(JsonPath json){//String sourchData, String verifyStr
        for(String key : verifyMap.keySet()) {
            Object sourchData = json.get(key);
            if (sourchData.toString() != null || sourchData.toString() != "") {
                String sourchStr = sourchData.toString();
                sourchStr = sourchStr.replaceAll("[\\[\\]\\s]", "");
                String[] sourchArr = sourchStr.toString().split(",");
                for (int i = 0; i < sourchArr.length; i++) {
                    LOGGER.INFO("assertType:{},sourchArr:{},verifyMap:{}", assertType, sourchArr[i], verifyMap.get(key));
                    AssertUtil.getAssertResult(assertType, sourchArr[i], verifyMap.get(key));
                }
            }
        }
    }

}
