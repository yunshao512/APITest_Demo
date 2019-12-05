package com.server.api.common;

import org.slf4j.LoggerFactory;
import org.slf4j.spi.LocationAwareLogger;
import org.testng.Reporter;
import sun.misc.JavaLangAccess;
import sun.misc.SharedSecrets;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

public class ReporterLogger {


    /**空数组*/
    private static final Object[] EMPTY_ARRAY = new Object[] {};
    /**全类名*/
    private static final String FQCN = ReporterLogger.class.getName();

    private static String logDataName = "";

    public static String splitTimeAndMsg = " === ";

    /**
     * 获取栈中类信息
     * @param stackDepth 栈深（下标） 2：调用者类信息
     * @return org.slf4j.spi.LocationAwareLogger
     * @author wxt
     * @date 2019/7/10 8:40
     */
    private static LocationAwareLogger getLocationAwareLogger(final int stackDepth) {
        /**通过堆栈信息获取调用当前方法的类名和方法名*/
        JavaLangAccess access = SharedSecrets.getJavaLangAccess();
        Throwable throwable = new Throwable();
        StackTraceElement frame = access.getStackTraceElement(throwable, stackDepth);

        String logName=frame.getClassName().toString()+" "+frame.getMethodName()+"("+frame.getLineNumber()+")";
        System.out.println(logName);
        reporterLog(logName);
        return (LocationAwareLogger) LoggerFactory.getLogger(frame.getClassName());
    }

    /**
     * 封装Debug级别日志
     * @param msg
     * @param arguments
     * 作者：wxt
     * 日期：2019年3月27日下午5:38:01
     */
    public static void DEBUG(String msg, Object... arguments) {
        logDataName = "";
        if (arguments != null && arguments.length > 0) {
            MessageFormat temp = new MessageFormat(msg);
            msg = temp.format(arguments);
        }
        getLocationAwareLogger(2).log(null, FQCN, LocationAwareLogger.DEBUG_INT, msg, EMPTY_ARRAY, null);
        String log = logDataName+" - "+msg;
        Reporter.log(log);
    }

    /**
     * 封装Info级别日志
     * @param msg
     * @param arguments
     * 作者：wxt
     * 日期：2019年3月27日下午5:37:42
     */
    public static void INFO(String msg, Object... arguments) {
        logDataName = "";
        if (arguments != null && arguments.length > 0) {
          /*  MessageFormat temp = new MessageFormat(msg);
            msg = temp.format(arguments);*/
            msg = logdeta(msg,arguments);
        }
        getLocationAwareLogger(2).log(null, FQCN, LocationAwareLogger.INFO_INT, msg, EMPTY_ARRAY, null);
        String log = logDataName+" - "+msg;
        Reporter.log(log);

    }

    /**
     * 封装Warn级别日志
     * @param msg
     * @param arguments
     * 作者：wxt
     * 日期：2019年3月27日下午5:37:30
     */
    public static void WARN(String msg, Object... arguments) {
        logDataName = "";
        if (arguments != null && arguments.length > 0) {
            MessageFormat temp = new MessageFormat(msg);
            msg = temp.format(arguments);
        }
        getLocationAwareLogger(2).log(null, FQCN, LocationAwareLogger.WARN_INT, msg, EMPTY_ARRAY, null);
        String log = logDataName+" - "+msg;
        Reporter.log(log);
    }

    /**
     * 封装Error级别日志
     * @param msg
     * @param arguments
     * 作者：wxt
     * 日期：2019年3月27日下午5:37:14
     */
    public static void ERROR(String msg, Object... arguments) {
        logDataName = "";
        if (arguments != null && arguments.length > 0) {
            MessageFormat temp = new MessageFormat(msg);
            msg = temp.format(arguments);
        }
        getLocationAwareLogger(2).log(null, FQCN, LocationAwareLogger.ERROR_INT, msg, EMPTY_ARRAY, null);
        String log = logDataName+" - "+msg;
        Reporter.log(log);
    }
    private static String logdeta(String message , Object... arguments){
        if ( message.contains("{}"))
            message = message.replace("{}","{%s}");
        else if (message.contains(":"))
            message = message.replace(":",":%s");
        else if (message.contains("："))
            message = message.replace("：","：%s");
        else
            message = message;
        return new Formatter().format(message, arguments).toString();
    }
    public static void reporterLog(String name){
        Long timeStamp = System.currentTimeMillis();
        String dateString = timestampToDate(timeStamp);
        logDataName = dateString+splitTimeAndMsg+name;
    }
    //时间戳转date字符串
    public static String timestampToDate(Long timestamp){
        if(timestamp.toString().length() <13){
            timestamp = Long.valueOf(timestamp.toString().substring(0,10)+"000");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(timestamp);
        String dateStr = sdf.format(date);
        return dateStr;
    }
    /**
     * 异常堆栈转字符串
     * @param e
     * @return
     * 作者：wxt
     * 日期：2019年3月27日下午5:37:08
     */
    public static String ExceptionToString(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            if (e == null) {
                return "无具体异常信息";
            }
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            ReporterLogger.INFO("异常信息:   "+sw.toString());
            return "异常信息:   "+sw.toString();
        } catch (Exception ex) {
            ReporterLogger.ERROR("异常堆栈转字符串异常", ex);
            return "";
        } finally {
            sw.flush();
            pw.flush();
            pw.close();
        }

    }
}