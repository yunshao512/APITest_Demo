package com.server.DBMapper;

import java.util.HashMap;
import java.util.Map;

public class Header {
    private String service;

    private String version;

    private String provider;

    private String method;

    public Header(String service, String version, String provider, String method){
        this.service = service;
        this.version = version;
        this.provider = provider;
        this.method = method;
    }

    public static Map<String ,Object> setheaderMap(Header header) throws Exception{
        Map<String ,Object> headerMap = new HashMap<String, Object>();
        headerMap.put("service",header.service);
        headerMap.put("version",header.version);
        headerMap.put("provider",header.provider);
        headerMap.put("method",header.method);
        headerMap.put("Cache-Control","no-cache");
        return headerMap;
    }

    public String getRequestPath(String APPID_ENV){
        StringBuilder sb = new StringBuilder();
        sb.append(APPID_ENV);
        sb.append("/");
        sb.append(this.service);
        sb.append("/");
        sb.append(this.version);
        sb.append("/");
        sb.append(this.method);
        return sb.toString();
    }
}
