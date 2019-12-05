package com.server.utils.functionsUtil;



import com.server.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DayFunction implements Function {
    @Override
    public String execute(String[] args) {
        StringBuffer sys = new StringBuffer();
        int len = args.length;
        String method = "";
        String hhmmss = "";
        int day = 0;
        int month = 0;
        int year = 0;
        String timeStr = null;
        if (len > 0) {// 第一个参数 调用的时间生成方法
            method = args[0];
        }
        if (len > 1) {// 第二个参数 生成格式
            hhmmss = args[1];
        }
        if (len > 2) {// 第二个参数 生成格式
            day = Integer.valueOf(args[2]);
        }
        if (len > 3) {// 第二个参数 生成格式
            month = Integer.valueOf(args[3]);
        }
        if (len > 4) {// 第二个参数 生成格式
            year = Integer.valueOf(args[4]);
        }
        switch (DateType.getValueCode(method)){
            case 0:
                timeStr = DateUtil.getCurrentDateTime(hhmmss);
                break;

            case 1:
                timeStr = DateUtil.getDayBegin(hhmmss);
                break;
            case 2:
                timeStr = DateUtil.getDayEnd(hhmmss);
                break;
            case 3:
                timeStr = DateUtil.beforeDateStart(hhmmss,day);
                break;
            case 4:
                timeStr = DateUtil.beforeDateEnd(hhmmss,day);
                break;
            case 5:
                timeStr = DateUtil.epochSecond()+"";
                break;
            case 6:
                timeStr = DateUtil.epochMilli()+"";
                break;
            case 7:
                timeStr = DateUtil.getDatesPlus(hhmmss,day,month,year);
                break;
            case 8:
                timeStr = DateUtil.getDatesStartPlus(hhmmss,day,month,year);
                break;
            case 9:
                timeStr = DateUtil.getDatesEndPlus(hhmmss,day,month,year);
                break;
            case 10:
                timeStr = DateUtil.getDatesMinus(hhmmss,day,month,year);
                break;
            case 11:
                timeStr = DateUtil.getDatesStartMinus(hhmmss,day,month,year);
                break;
            case 12:
                timeStr = DateUtil.getDatesEndMinus(hhmmss,day,month,year);
                break;
                default:
        }
        return timeStr;
    }

    @Override
    public String getReferenceKey() {
        return "day";
    }

}
