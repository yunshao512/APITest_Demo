package com.server.utils.functionsUtil;

public enum DateType {
    /**
     *指定格式的当前时间(String format)
     * */
    DAY_CURRENT_DATE(0,"DateTime"),
    /**
     *获取当天开始时间可自定义格式(String format)
     * */
    DAY_BEGIN(1,"DayBegin"),
    /**
     *获取当天结束时间可自定义格式(String format)
     * */
    DAY_END(2,"DayEnd"),
    /**
     *获取昨天的开始时间可自定义格式 ( FormatEnum formatEnum  ，int daysToAddOrSubtract)
     * */
    DAY_BEFORE_DATE_START(3,"BeforeDateStart"),
    /**
     *获取昨天的结束时间可自定义格式 ( FormatEnum formatEnum  ，int daysToAddOrSubtract)
     * */
    DAY_BEFORE_DATE_END(4,"BeforeDateEnd"),
    /**
     *获取当前时间秒级时间戳
     * */
    DAY_EPOCH_SECOND(5,"EpochSecond"),
    /**
     *获取当前时间毫秒级时间戳
     * */
    DAY_EPOCH_MILLI(6,"EpochMilli"),
    /**
     *获取加某个时间的时间，可自定义格式   (String formatEnum ,int day,int month,int year)
     * */
    DATESPLUS(7,"DatesPlus"),
    /**
     *获取加某个时间的开始时间可自定义格式   (String formatEnum ,int day,int month,int year)
     * */
    DATES_START_PLUS(8,"DatesStartPlus"),
    /**
     *获取加某个时间的结束时间可自定义格式    (String formatEnum ,int day,int month,int year)
     * */
    DATES_END_PLUS(9,"DatesEndPlus"),
    /**
     *获取减某个时间的时间，时间可自定义格式   (String formatEnum ,int day,int month,int year)
     * */
    DATESMINUS(10,"DatesMinus"),
    /**
     *获取减某个时间的开始时间可自定义格式   (String formatEnum ,int day,int month,int year)
     * */
    DATES_START_MINUS(11,"DatesStartPlus"),
    /**
     *获取减某个时间的结束时间可自定义格式    (String formatEnum ,int day,int month,int year)
     * */
    DATES_END_MINUS(12,"DatesEndPlus");//getDates

    private int code;
    private String value;

    DateType(int code, String value) {
        this.code = code;
        this.value = value;
    }
    public void setCode(int code){this.code = code;}
    public int getCode(){return code;}
    public String getValue(){return value;}
    /**
     * 根据code获取去name
     * @param code
     * @return
     */
    public static int getValueCode(String code){
        for (DateType type : DateType.values()){
            if (code.equals(type.getValue())){
                System.out.println("type.getValue()"+type.getValue());
                return type.getCode();
            }
        }
        System.out.println("type.getValue()"+9999);
        return 9999;
    }



}
