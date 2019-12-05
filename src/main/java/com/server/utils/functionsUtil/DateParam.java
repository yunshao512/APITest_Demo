package com.server.utils.functionsUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateParam {
    public static String getCurrentDate(){
      /*  Calendar now = Calendar.getInstance();
        System.out.println("年：" + now.get(Calendar.YEAR));
        System.out.println("月：" + (now.get(Calendar.MONTH) + 1));
        System.out.println("日：" + now.get(Calendar.DAY_OF_MONTH));
        System.out.println("时：" + now.get(Calendar.HOUR_OF_DAY));
        System.out.println("分：" + now.get(Calendar.MINUTE));
        System.out.println("秒：" + now.get(Calendar.SECOND));*/
        //使用Date获取当前日期
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //yyyy-MM-dd
        String dateStr=sdf.format(d);
        System.out.println("当前日期：" + dateStr);
        return dateStr;
    }
    public static String getCurrentTimehhmmss(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //yyyy-MM-dd HH:mm:ss
        String dateStr=sdf.format(d);
        System.out.println("当前时间时分秒：" + dateStr);
        return dateStr;
    }
    public static String getCurrentTime(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //yyyy-MM-dd HH:mm:ss
        String dateStr=sdf.format(d);
        System.out.println("当前时间：" + dateStr);
        return dateStr;
    }
    public static String getStartTime(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //yyyy-MM-dd HH:mm:ss
        String dateStr=sdf.format(d)+"+00%3A00%3A00";
        System.out.println("今天的开始时间：" + dateStr);
        return dateStr;
    }
    public static String getEndTime(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //yyyy-MM-dd HH:mm:ss
        String dateStr=sdf.format(d)+"+23%3A59%3A59";
        System.out.println("今天的结束时间：" + dateStr);
        return dateStr;
    }
    public static String  getBeforeDate(){
        Date date = new Date();
        //声明日期格式化样式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //将格式化的日期字符串转为Date
            date = dateFormat.parse(dateFormat.format(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //通过Calendar的实现类获得Calendar实例
        Calendar calendar = GregorianCalendar.getInstance();
        //设置格式化的日期
        calendar.setTime(date);
        //获取当前日
        int day = calendar.get(Calendar.DATE);
        //获取设置前一天的日期
        calendar.set(Calendar.DATE, day-1);
        //获取
        String dateStr = dateFormat.format(calendar.getTime());
        System.out.println("昨天日期：" + dateStr);
        return dateStr;
    }
    public static String  getBeforeDateStart(){
        Date date = new Date();
        //声明日期格式化样式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //将格式化的日期字符串转为Date
            date = dateFormat.parse(dateFormat.format(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //通过Calendar的实现类获得Calendar实例
        Calendar calendar = GregorianCalendar.getInstance();
        //设置格式化的日期
        calendar.setTime(date);
        //获取当前日
        int day = calendar.get(Calendar.DATE);
        //获取设置前一天的日期
        calendar.set(Calendar.DATE, day-1);
        //获取
        String dateStr = dateFormat.format(calendar.getTime())+"+00%3A00%3A00";
        System.out.println("昨天开始时间：" + dateStr);
        return dateStr;
    }
    public static String  getBeforeDateEnd(){
        Date date = new Date();
        //声明日期格式化样式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //将格式化的日期字符串转为Date
            date = dateFormat.parse(dateFormat.format(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //通过Calendar的实现类获得Calendar实例
        Calendar calendar = GregorianCalendar.getInstance();
        //设置格式化的日期
        calendar.setTime(date);
        //获取当前日
        int day = calendar.get(Calendar.DATE);
        //获取设置前一天的日期
        calendar.set(Calendar.DATE, day-1);
        //获取
        String dateStr = dateFormat.format(calendar.getTime())+"+23%3A59%3A59";
        System.out.println("昨天结束时间：" + dateStr);
        return dateStr;
    }
    public void getDate (){
        DateParam.getEndTime();
        DateParam.getStartTime();

        DateParam.getBeforeDate();
        DateParam.getBeforeDateEnd();
        DateParam.getBeforeDateStart();

        DateParam.getCurrentDate();
        DateParam.getCurrentTime();
        DateParam.getCurrentTimehhmmss();
    }
/*        public static void main(String[] args) {

    }*/
}

