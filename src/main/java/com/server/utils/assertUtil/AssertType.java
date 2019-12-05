package com.server.utils.assertUtil;

public enum AssertType {

    /**
     *等于
     * */
    ASSERT_TYPE_EQUAL("equal",0),
    /**
     *包含
     * */
    ASSERT_TYPE_CONTAINS("contains",1),
    /**
     *不包含
     * */
    ASSERT_TYPE_NO_CONTAINS("onOntains",2),
    /**
     *大于
     * */
    ASSERT_TYPE_GREATER_THAN("greater",3),
    /**
     *小于
     * */
    ASSERT_TYPE_LESS_THAN("less",4),
    /**
     *数字等于
     * */
    ASSERT_TYPE_EQUAL_NUMBER("equalNumber",5);

    private String code;
    private int value;

    AssertType(String i,int value) {
        this.code = i;
        this.value = value;
    }
    public void setCode(String code){this.code = code;}
    public String getCode(){return code;}
    public int getValue(){return value;}
    /**
     * 根据code获取去name
     * @param code
     * @return
     */
    public static int getValueCode(String code){
        if (code == null||code.isEmpty()||code=="")
            return 0;
        for (AssertType type : AssertType.values()){
            if (code.equals(type.getCode())){
                return type.getValue();
            }
        }
        return 9999;
    }

}
