package com.server.utils.functionsUtil;



import java.util.Calendar;
import java.util.Date;

public enum  DayCustomerId {
    CUSTOMER_NAME_MON(1,"2025757","测试zy分发线索一"),
    CUSTOEER_NAME_TUES(2,"2025756","测试zy分发线索二"),
    CUSTOMER_NAME_WED(3,"2025755","测试zy分发线索三"),
    CUSTOMER_NAME_THUR(4,"2025754","测试zy分发线索四"),
    CUSTOMER_NAME_RIR(5,"2025753","测试zy分发线索五"),
    CUSTOMER_NAME_SAT(6,"2025752","测试zy分发线索六"),
    CUSTOMER_NAME_SUN(0,"2025910","测试zy分发线索九");

    private String id;
    private String name;
    private int code;
    DayCustomerId(int code,String id, String name) {
        this.code = code;
        this.id = id;
        this.name = name;
    }
    private int getCode(){return code;};
    private String getId(){return id;}
    private String getName(){return name;}
    public static String getId(int code){
        for (DayCustomerId day : DayCustomerId.values()){
            if (code == day.getCode()){
                System.out.println("day.getId()"+day.getId());
                return day.getId();
            }
        }
        return null;
    }
    public static String getName(int code){
        for (DayCustomerId day : DayCustomerId.values()){
            if (code == day.getCode()){
                System.out.println("day.getName()"+day.getName());
                return day.getName();
            }
        }
        return null;
    }


    public static void getSaveCustomerName(){
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        System.out.println(w);
//        switch (w){
//            case 0:
//                SaveParamUtils.saveDatas.put("highseaCustomerId",DayCustomerId.getId(w));
//                SaveParamUtils.saveDatas.put("highseaCustomerName",DayCustomerId.getName(w));
//                break;
//            case 1:
//                SaveParamUtils.saveDatas.put("highseaCustomerId",DayCustomerId.getId(w));
//                SaveParamUtils.saveDatas.put("highseaCustomerName",DayCustomerId.getName(w));
//                break;
//            case 2:
//                SaveParamUtils.saveDatas.put("highseaCustomerId",DayCustomerId.getId(w));
//                SaveParamUtils.saveDatas.put("highseaCustomerName",DayCustomerId.getName(w));
//                break;
//            case 3:
//                SaveParamUtils.saveDatas.put("highseaCustomerId",DayCustomerId.getId(w));
//                SaveParamUtils.saveDatas.put("highseaCustomerName",DayCustomerId.getName(w));
//                break;
//            case 4:
//                SaveParamUtils.saveDatas.put("highseaCustomerId",DayCustomerId.getId(w));
//                SaveParamUtils.saveDatas.put("highseaCustomerName",DayCustomerId.getName(w));
//                break;
//            case 5:
//                SaveParamUtils.saveDatas.put("highseaCustomerId",DayCustomerId.getId(w));
//                SaveParamUtils.saveDatas.put("highseaCustomerName",DayCustomerId.getName(w));
//                break;
//            case 6:
//                SaveParamUtils.saveDatas.put("highseaCustomerId",DayCustomerId.getId(w));
//                SaveParamUtils.saveDatas.put("highseaCustomerName",DayCustomerId.getName(w));
//                break;
//            default:
//                SaveParamUtils.saveDatas.put("highseaCustomerId",DayCustomerId.getId(w));
//                SaveParamUtils.saveDatas.put("highseaCustomerName",DayCustomerId.getName(w));
//        }
    }

}
