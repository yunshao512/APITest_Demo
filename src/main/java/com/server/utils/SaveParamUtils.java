package com.server.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.server.utils.functionsUtil.FunctionUtil;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SaveParam 工具类
 * @date 2019/6/27 下午1:55
 */

public class SaveParamUtils {
    public static Pattern replaceParamPattern = Pattern.compile("\\$\\{(.*?)\\}");

    /**
     * 截取自定义方法正则表达式：__xxx(ooo)
     */
    public static Pattern funPattern = Pattern.compile("__(\\w*?)\\((([\\w\\\\\\/:\\.\\$]*,?)*)\\)");// __(\\w*?)\\((((\\w*)|(\\w*,))*)\\)
                                                                   // __(\\w*?)\\(((\\w*,?\\w*)*)\\)

    public static Map<String, String> saveDatas = new HashMap<String, String>();
    /**
     * 对param参数中的变量进行赋值
     * @param param 请求参数
     * @return
     */
    public static String getCommonParam(String param) {
        if (StringUtil.isEmpty(param)) {
            return "";
        }
        Matcher m = replaceParamPattern.matcher(param);// 取公共参数正则

        while (m.find()) {
            String replaceKey = m.group(1);
            String value;
            // 从公共参数池中获取值
            value = getSaveData(replaceKey);
            // 如果公共参数池中未能找到对应的值，该用例失败。
            Assert.assertNotNull(value,
                    String.format("格式化参数失败，公共参数中找不到%s。", replaceKey));
            param = param.replace(m.group(), value);
        }
        return param;
    }
    /**
     * 取公共参数对应的key值
     * @param key map对应的key值
     * @return
     */
    public static String getSaveData(String key) {
        if ("".equals(key) || !saveDatas.containsKey(key)) {
            return null;
        } else {
            return saveDatas.get(key);
        }
    }
    /**
     * 跟进不同的正则提取参数，对变量赋值
     * @param param 请求参数
     * @return
     */
    public static String buildParam(String param) {
        // 处理${}
        param = getCommonParam(param);
        Matcher m = funPattern.matcher(param);
        while (m.find()) {
            String funcName = m.group(1);
            String args = m.group(2);
            String value;
            // bodyfile属于特殊情况，不进行匹配，在post请求的时候进行处理
            if (FunctionUtil.isFunction(funcName)
                    && !funcName.equals("bodyfile")) {
                // 属于函数助手，调用那个函数助手获取。
                value = FunctionUtil.getValue(funcName, args.split(","));
                System.out.println("value = "+value);
                // 解析对应的函数失败
                Assert.assertNotNull(value,
                        String.format("解析函数失败：%s。", funcName));
                param = StringUtil.replaceFirst(param, m.group(), value);
            }
        }
        return param;
    }
    /**
     * 获取json指定key值
     * @param sourceStr json字符串
     * @param key 对应的key值
     * @return
     */
    public static String setSaveData(String sourceStr, String key){
        Gson gson = new GsonBuilder().serializeNulls().create();
        Map map = gson.fromJson(sourceStr, Map.class);
        String data = (String) map.get(key);
        System.out.println(data);
        return data;
    }
    public static void setSaveDates(Map<String, String> map) {
        saveDatas.putAll(map);
    }
 /*   public static void main(String[] args) throws Exception {
        String str1 = "{ \"code\" : 0, \"message\" : \"success!\", \"data\" : { \"totalCount\" : 0, \"overdueStatList\" : [ { \"userId\" : 3, \"flag\" : null }, { \"userId\" : 4, \"flag\" : null }] } }";
        setSaveData(str1,"message");
    }*/
    public static void main(String[] args) {
//        String param = "{\"username\":\"__random(6,6,true)\"}";
//        String param1 = "{\"cookie\":\"__token(crm)\"}";
//        Pattern pattern = Pattern.compile("__(.*?)\\(.*\\)");// 取__开头的函数正则表达式
//        Pattern pattern1 = Pattern.compile("__(\\w*?)\\((\\w*,)*(\\w*)*\\)");// 取__开头的函数正则表达式
//
////        Matcher m = pattern.matcher(param);
////        Matcher m = pattern1.matcher(param);
//        Matcher m = funPattern.matcher(param);
//        System.out.println("****************");
//        while (m.find()) {
//            String funcName0 = m.group(0);
//            String funcName = m.group(1);
//            String funcName1 = m.group(2);
////            String funcName3 = m.group(3);
//            System.out.println(funcName0);
//            System.out.println(funcName);
//            System.out.println(funcName1);
//            System.out.println(m.group());
//
//        }
        String param1 = "{\"__token(crm)\"}";
        SaveParamUtils sa = new SaveParamUtils();
//        System.out.println(sa.buildParam(param));
        System.out.println(sa.buildParam(param1));


//        System.out.println(m.group(2));
    }
}
