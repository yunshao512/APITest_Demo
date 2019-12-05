package com.server.utils;


import java.io.UnsupportedEncodingException;

public class StringUtil {
	public static boolean isNotEmpty(String str) {
		return null != str && !"".equals(str);
	}

	public static boolean isEmpty(String str) {
		return null == str || "".equals(str);
	}
	
	/**
	 * 
	 * @param sourceStr 待替换字符串
	 * @param matchStr  匹配字符串
	 * @param replaceStr  目标替换字符串
	 * @return
	 */
	public static String replaceFirst(String sourceStr,String matchStr,String replaceStr){
		int index = sourceStr.indexOf(matchStr);
		int matLength = matchStr.length();
		int sourLength = sourceStr.length();
		String beginStr = sourceStr.substring(0,index);
		String endStr = sourceStr.substring(index+matLength,sourLength);
		sourceStr = beginStr+replaceStr+endStr;
		return sourceStr;
	}
	/**
	 *判定param字符串是否是json格式
	 * @param param 判定param参数是否是json格式
	 * @return
	 */
	public static boolean isJson(String param){
		if (param.startsWith("{\"")&&param.endsWith("\"}"))
			return true;
		else
			return false;
	}

	public static String getReplacePath(String str)
	{
		str = str.replace("\\","/");
		return str;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {

/*		System.out.println(Encode("/desktop/trace/queryCustomerAbstract?sortId=7&isDesc=1&timeTypeId=6&bizProbability=&ownerType=5&startIndex=0&pageSize=50&keyword=测试zy公司账号"));
		System.out.println(Encode("测试zy公司账号"));
		System.out.println(URLEncoder.encode("测试zy公司账号","UTF-8"));
		System.out.println(URLEncoder.encode("/desktop/trace/queryCustomerAbstract?sortId=7&isDesc=1&timeTypeId=6&bizProbability=&ownerType=5&startIndex=0&pageSize=50&keyword=测试zy公司账号","UTF-8"));*/
		String url = "sortId=7&keyword=测试zy公司账号&isDesc=1&timeTypeId=6&bizProbability=&ownerType=5&startIndex=0&pageSize=50";//
//		System.out.println(StringUtil.setParamEnUrl(url));
	}
}
