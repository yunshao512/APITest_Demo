package com.test.test.cases;

import com.server.api.common.TestBaseSteps;
import com.server.api.dataobject.StepMethodDesc;
import com.server.api.dataobject.stepparameter.InterfaceStepParameter;
import com.server.api.common.ParameterHelper;
import com.server.api.common.ReporterLogger;
import io.restassured.response.Response;




public class TestSteps extends TestBaseSteps {

    private static final ReporterLogger LOGGER = new ReporterLogger();

    @StepMethodDesc(description = "TestApi", owner = "yun.zhang")
    public void TestApi(String parameterID) throws Exception {
        LOGGER.INFO("--- test start ---");
        InterfaceStepParameter parameter = (InterfaceStepParameter) ParameterHelper.getInterfaceStepParameter(parameterID);
        String url = "https://www.wanandroid.com" ;
        String path = "/article/list/0/json";
        LOGGER.INFO("url:{},path:{}",url,path);
        //初始化请求参数
        extractParameter(parameter);
        Response response = HttpResponseData(url,path,headersMap,paramMap,requestBody,"get");
    }

}

