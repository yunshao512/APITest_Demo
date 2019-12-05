package com.server.utils.requestsUtil;

/**
 * http 请求方法类型
 * @author Yang WenJie
 * @date 2018/2/10 下午1:59
 */
public enum RestAssuredMethod {
    /**
     * 求获取Request-URI所标识的资源
     */
    GET("get",0),

    /**
     * 向指定资源提交数据进行处理请求（例如提交表单或者上传文件）。数据被包含在请求体中。
     * POST请求可能会导致新的资源的建立和/或已有资源的修改
     */
    POST("post",1),

    /**
     * 向服务器索要与GET请求相一致的响应，只不过响应体将不会被返回。
     * 这一方法可以在不必传输整个响应内容的情况下，就可以获取包含在响应消息头中的元信息
     * 只获取响应信息报头
     */
    HEAD("head",2),

    /**
     * 向指定资源位置上传其最新内容（全部更新，操作幂等）
     */
    PUT("put",3),

    /**
     * 请求服务器删除Request-URI所标识的资源
     */
    DELETE("delete",4),

    /**
     * 请求服务器回送收到的请求信息，主要用于测试或诊断
     */
    TRACE("trace",5),

    /**
     * 向指定资源位置上传其最新内容（部分更新，非幂等）
     */
    PATCH("patch",6),

    /**
     * 返回服务器针对特定资源所支持的HTTP请求方法。
     * 也可以利用向Web服务器发送'*'的请求来测试服务器的功能性
     */
    OPTIONS("options",7);

    private String name;
    private int code;

    private RestAssuredMethod(String name,int code){
        this.name = name;
        this.code = code;
    }
    private int getCode(){
        return code;
    }
    private String getName(){
        return name;
    }
    public static int getValueCode(String name){
        for (RestAssuredMethod type : RestAssuredMethod.values()){
            if (name.equals(type.getName())){
                return type.getCode();
            }
        }
        return 9999;
    }



}
