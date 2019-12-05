package com.server.utils.assertUtil;

import com.server.api.common.ReporterLogger;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class AssertMethod {

    private static ReporterLogger LOGGET = new ReporterLogger();

    public static boolean flag=true;
    public static List<Error> errors=new ArrayList<>();
    /**
     * 判定实际值包含预期值
     * @param actual
     * @param expected
     * @return
     * */
    public static void contains(String actual, String expected) {
        Assert.assertTrue(actual.contains(expected),
                String.format("期待'%s'包含'%s'，实际为不包含.", actual, expected));
    }
    /**
     * 判定实际值不包含预期值
     * @param actual
     * @param expected
     * @return
     * */
    public static void notContains(String actual, String expected) {
        Assert.assertFalse(actual.contains(expected),
                String.format("期待'%s'不包含'%s'，实际为包含.", actual, expected));
    }
    /**
     * 判定实际值等于预期值
     * @param actual
     * @param expected
     * @param message
     * @return
     * */
    public static void equalsAssert(Object actual,Object expected,String message){
        Assert.assertEquals(actual, expected, "验证预期结果失败");
    }
    /**
     * 判定实际值大于预期值
     * @param actual
     * @param expected
     * @return
     * */
    public static boolean greaterThanAssert(double actual,double expected){ if (actual>expected) return true;else return false; }
    /**
     * 判定实际值小于预期值
     * @param actual
     * @param expected
     * @return
     * */
    public static boolean lessThanAssert(double actual,double expected){ if (actual<expected) return true;else return false; }
    /**
     * 判定实际值等于预期值
     * @param actual
     * @param expected
     * @return
     * */
    public static boolean equalsAssertNumber(double actual,double expected){ if (actual == expected) return true;else return false; }

    /**
     * 判定实际值等于预期值，错误后继续执行
     * @param actual
     * @param expected
     * @param message
     * @return
     * */
    public static void verifyEquals(Object actual,Object expected,String message){
        try{
            Assert.assertEquals(actual,expected,message);
        }catch(Error e){
            errors.add(e);
            flag=false;
            LOGGET.INFO("Assert失败：{}，继续断言",e.getStackTrace());
        }
    }
}





