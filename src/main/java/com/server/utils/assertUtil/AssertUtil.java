package com.server.utils.assertUtil;

import com.alibaba.fastjson.JSONPath;
import com.server.api.common.ReporterLogger;
//import com.server.utils.SaveParamUtils;
//import com.edianzu.crm.utils.StringUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import com.utils.ReportUtil;
//import com.utils.ReporterLogger;
//import com.utils.SaveParamUtils;
//import com.utils.StringUtil;

public class AssertUtil {

    private static ReporterLogger LOGGER = new ReporterLogger();

    /**
     * 根据类型调用不同的断言
     * @param type      断言类型
     * @param actual    实际结果
     * @param expected  预期结果
     */
    public static void getAssertResult(String type,String actual,String expected){
        switch (AssertType.getValueCode(type)){
            case 0:    //"ASSERT_TYPE_EQUAL"
                AssertMethod.equalsAssert(actual,expected,"验证预期结果失败");
                break;
            case 1:    //"ASSERT_TYPE_CONTAINS":
                AssertMethod.contains(actual,expected);
                break;
            case 2:    //"ASSERT_TYPE_NO_CONTAINS":
                AssertMethod.notContains(actual,expected);
                break;
            case 3:    //"ASSERT_TYPE_GREATER_THAN"
                AssertMethod.greaterThanAssert(Double.parseDouble(actual),Double.parseDouble(expected));
                break;
            case 4:    //"ASSERT_TYPE_LESS_THAN":
                AssertMethod.lessThanAssert(Double.parseDouble(actual),Double.parseDouble(expected));
                break;
            case 5:    //"ASSERT_TYPE_EQUAL_NUMBER"
                AssertMethod.equalsAssertNumber(Double.parseDouble(actual),Double.parseDouble(expected));
                break;
            default:
                AssertMethod.verifyEquals(actual,expected,"验证预期结果失败");
        }
    }
}
