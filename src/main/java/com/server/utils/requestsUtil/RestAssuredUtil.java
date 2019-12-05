package com.server.utils.requestsUtil;


import com.server.api.common.ReporterLogger;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RestAssuredUtil {

    private static ReporterLogger LOGGER = new ReporterLogger();

    public static Response response;

    /**
     * 此方法用于构建向服务器发送请求所需的规范。
     *
     * @param url
     * @param path : Path of desired resource at server
     * @param paramMap : param
     * @param headerMap : Headers that are needed to access service
     */
    public static RequestSpecification buildRequestSpecification(String url, String path,Map<String,String> headerMap, Map<String,String> paramMap) {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        PreemptiveBasicAuthScheme basicAuth = new PreemptiveBasicAuthScheme();
        if (url !=null || url != "" || !url.isEmpty())
            builder.setBaseUri(url);
        if (path !=null || path != "" || !path.isEmpty())
            builder.setBasePath(path);
        if (url.contains("https"))
            builder.setConfig(RestAssuredConfig.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()));

        builder.setContentType(ContentType.URLENC);
        try {
            if (paramMap !=null || paramMap.size()>0 || !paramMap.isEmpty())
                builder.addFormParams(paramMap);
        }catch(Exception e) {
            LOGGER.ExceptionToString(e);
        }
        try {
            if(headerMap !=null || headerMap.size()>0 || !headerMap.isEmpty())  { builder.addHeaders(headerMap);}
        }catch(Exception e) {
            LOGGER.ExceptionToString(e);
        }
        return builder.build();
    }
    /**
     * 此方法用于构建向服务器发送请求所需的规范。
     *
     * @param url
     * @param path : Path of desired resource at server
     * @param requestBody : Input Body
     * @param headerMap : Headers that are needed to access service
     */
    public static RequestSpecification buildRequestSpecification(String url, String path,Map<String,String> headerMap, String requestBody) {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        PreemptiveBasicAuthScheme basicAuth = new PreemptiveBasicAuthScheme();
        if (url !=null || url != "" || !url.isEmpty())
            builder.setBaseUri(url);
        if (path !=null || path != "" || !path.isEmpty())
            builder.setBasePath(path);
        if (url.contains("https"))
            builder.setConfig(RestAssuredConfig.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()));
        builder.setContentType(ContentType.JSON);
        try {
            if(requestBody !=null || requestBody !="" || !requestBody.isEmpty()) {builder.setBody(requestBody);}
        }catch(Exception e) {
            LOGGER.ExceptionToString(e);
        }
        try {
            if(headerMap !=null || headerMap.size()>0 || !headerMap.isEmpty())  { builder.addHeaders(headerMap);}
        }catch(Exception e) {
            LOGGER.ExceptionToString(e);
        }
        return builder.build();
    }

    /**
     * 此方法执行GET请求并帮助从服务器检索信息。
     *
     * @param baseURL
     * @param endpointUrl : Path of desired resource at server
     * @param headers : Headers that are needed to access service
     * @param params
     * @return Response
     */
    public static Response executeGetRequestWithParameters(String baseURL, String endpointUrl, Map<String, String> headers, Map<String, String> params,String body,String method,Map<String,Object> cookies) {
        Response valResponse=null;
        RequestSpecification requestSpec;
        try {
            if (body==null || body == "" || body.isEmpty())
                requestSpec = buildRequestSpecification(baseURL, endpointUrl, headers, params);
            else
                requestSpec = buildRequestSpecification(baseURL, endpointUrl, headers, body);
            switch (RestAssuredMethod.getValueCode(method)) {
                case 0:
                    if (cookies==null) {
                        valResponse = RestAssured.given()
                                .spec(requestSpec)
                                .get()
                                .then()
                                .log()
                                .all()
                                .statusCode(200)
                                .extract()
                                .response();//fication.get(url).then().log().all().statusCode(200).extract().response();
                    }else {
                        valResponse = RestAssured.given()
                                .spec(requestSpec)
                                .cookies(cookies)
                                .get()
                                .then()
                                .log()
                                .all()
                                .statusCode(200)
                                .extract()
                                .response();//fication.get(url).then().log().all().statusCode(200).extract().response();
                    }
                    break;
                case 1:
                    if (cookies==null) {
                        valResponse = RestAssured.given()
                                .spec(requestSpec)
                                .post()
                                .then()
                                .log()
                                .all()
                                .statusCode(200)
                                .extract()
                                .response();//fication.get(url).then().log().all().statusCode(200).extract().response();
                    }else {
                        valResponse = RestAssured.given()
                                .spec(requestSpec)
                                .cookies(cookies)
                                .post()
                                .then()
                                .log()
                                .all()
                                .statusCode(200)
                                .extract()
                                .response();//fication.get(url).then().log().all().statusCode(200).extract().response();
                    }

                    break;
                default:
                    LOGGER.INFO(String.format("暂不支持%s请求类型", method));
            }
        } catch (Exception e) {
            LOGGER.ExceptionToString(e);
        }
        return valResponse;
    }


    /**
     * This method is used to build specifications that are required for sending request to the server.
     *
     * @param baseURL
     * @param endpointUrl : Path of desired resource at server
     * @param requestBody : Input Body
     * @param headers : Headers that are needed to access service
     * @param params
     */

    public static RequestSpecification buildRequestSpecification(String baseURL, String endpointUrl, String requestBody,Map<String,String> headers, Map<String, String> params,String parameterType ) {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        try {
            builder.setBaseUri(baseURL);
            builder.setBasePath(endpointUrl);
            try {
                if(requestBody !=null) {builder.setBody(requestBody);}
            }catch(Exception e) {
                e.getCause().printStackTrace();
            }
            if (headers !=null) { builder.addHeaders(headers); }
            builder.setConfig(RestAssuredConfig.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()));
            builder.setUrlEncodingEnabled(false);
            if (params !=null && parameterType!=null) {
                if(parameterType.equalsIgnoreCase("Query")) {
                    System.out.println("*************Query*****************");
                    builder.addParams(params);
                }else if(parameterType.equalsIgnoreCase("Path")) {
                    System.out.println("*************Path*****************");
                    builder.addPathParams(params);
                }
            }

        } catch (Exception e) {
            LOGGER.ExceptionToString(e);
        }
        return builder.build();
    }

    /**
     * This method is used to build specifications that are required for sending request to the server.
     *
     * @param baseURL
     * @param endpointUrl : Path of desired resource at server
     * @param requestBody : Input Body
     * @param headers : Headers that are needed to access service
     * @param params
     * @param user : User name for basic Authentication
     * @param password : Password for basic Authentication
     * @return
     */

    public static RequestSpecification buildRequestSpecification_basicAuth(String baseURL, String endpointUrl, String requestBody,Map<String,String> headers, Map<String, String> params,String parameterType,String user,String password ) {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        PreemptiveBasicAuthScheme basicAuth = new PreemptiveBasicAuthScheme();
        try {
            builder.setBaseUri(baseURL);
            builder.setBasePath(endpointUrl);
            try {
                if(requestBody !=null) {builder.setBody(requestBody);}
            }catch(Exception e) {
                e.getCause().printStackTrace();
            }
            if (headers !=null) {
                builder.addHeaders(headers);
            }
            builder.setConfig(RestAssuredConfig.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()));
            builder.setUrlEncodingEnabled(false);
            if (user !=null||password != null) {
                basicAuth.setUserName(user);
                basicAuth.setPassword(password);
                builder.setAuth(basicAuth);
            }
            if (params !=null && parameterType!=null) {
                if(parameterType.equalsIgnoreCase("Query")) {
                    System.out.println("*************Query*****************");
                    builder.addParams(params);
                }else if(parameterType.equalsIgnoreCase("Path")) {
                    System.out.println("*************Path*****************");
                    builder.addPathParams(params);
                }
            }

        } catch (Exception e) {
            LOGGER.ExceptionToString(e);
        }
        return builder.build();
    }

    /**
     * This method executes GET request and helps in retrieving information from server.
     *
     * @param baseURL
     * @param endpointUrl : Path of desired resource at server
     * @param headers : Headers that are needed to access service
     * @param params
     * @return Response
     */
    public static Response executeGetRequestWithParameters(String baseURL, String endpointUrl, Map<String, String> headers, Map<String, String> params,String parameterType) {
        Response valResponse=null;
        try {
            RequestSpecification requestSpec = buildRequestSpecification(baseURL, endpointUrl,null, headers, params,parameterType);
            valResponse = RestAssured.given().spec(requestSpec).log().uri().get();
            //.then().statusCode(200);
        } catch (Exception e) {
            LOGGER.ExceptionToString(e);
        }
        return valResponse;
    }

    /**
     * This method executes GET request without input parameters and helps in retrieving information from server.
     *
     * @param baseURL
     * @param endpointUrl : Path of desired resource at server
     * @param headers : Headers that are needed to access service
     * @return Response
     */
    public static Response executeGetRequestWithoutParameters(String baseURL, String endpointUrl, Map<String,String> headers) {
        Response valResponse=null;
        try {
            RequestSpecification requestSpec = buildRequestSpecification(baseURL, endpointUrl, null,headers, null,null);
            valResponse = RestAssured.given().spec(requestSpec).log().uri().get();
            //.then().statusCode(200);
        } catch (Exception e) {
            LOGGER.ExceptionToString(e);
        }
        return valResponse;
    }

    /**
     * This method executes POST request and helps in sending information to server.
     *
     * @param baseURL
     * @param endpointUrl : Path of desired resource at server
     * @param requestBody : Input Body
     * @param headers : Headers that are needed to access service
     * @return Response
     */
    public static Response executePOSTRequest(String baseURL, String endpointUrl, String requestBody,Map<String,String> headers) {
        Response response = null;
        try {
            RequestSpecification requestSpec = buildRequestSpecification(baseURL, endpointUrl,requestBody, headers, null,null);
            response = RestAssured.given().spec(requestSpec).log().uri().post();

        } catch (Exception e) {
            LOGGER.ExceptionToString(e);
        }
        return response;

    }

    /**
     * This method executes POST request and helps in sending information to server.
     *
     * @param baseURL
     * @param endpointUrl : Path of desired resource at server
     * @param requestBody : Input Body
     * @param headers : Headers that are needed to access service
     * @param username : User name for basic Authentication
     * @param password : Password for basic Authentication
     * @return Response
     */

    public static Response executePOSTRequest_basicAuth(String baseURL, String endpointUrl, String requestBody,Map<String,String> headers, String username, String password) {
        Response response = null;
        try {
            RequestSpecification requestSpec = buildRequestSpecification_basicAuth(baseURL, endpointUrl,requestBody, headers, null,null,username,password);
            response = RestAssured.given().spec(requestSpec).log().uri().post();
        } catch (Exception e) {
            LOGGER.ExceptionToString(e);
        }
        return response;
    }

    /**
     * This method executes PUT request and helps in updating information to server.
     *
     * @param baseURL
     * @param endpointUrl : Path of desired resource at server
     * @param requestBody : Input Body
     * @param headers : Headers that are needed to access service
     * @return Response
     */
    public static Response executePUTRequest(String baseURL, String endpointUrl, String requestBody,Map<String,String> headers) {
        Response response = null;
        try {
            RequestSpecification requestSpec = buildRequestSpecification(baseURL, endpointUrl,requestBody, headers, null,null);
            response = RestAssured.given().spec(requestSpec).log().uri().put();

        } catch (Exception e) {
            LOGGER.ExceptionToString(e);
        }
        return response;
    }

    /**
     * This method executes DELETE request and helps in removing all current
     * representations of the target resource given by a URI.
     *
     * @param baseURL
     * @param endpointUrl : Path of desired resource at server
     * @param basicAuth
     * @return Response
     */
    public Response executeDELETERequest(String baseURL, String endpointUrl, Map<String,String> headers) throws Throwable {
        Response response = null;
        try {
            RequestSpecification requestSpec = buildRequestSpecification(baseURL, endpointUrl, null,headers, null,null);
            response = RestAssured.given().spec(requestSpec).delete();
        } catch (Exception e) {
            LOGGER.ExceptionToString(e);
        }
        return response;
    }

    /**
     * To find out the value from JSOn by using JSON path
     *
     * @param RequestJson
     * @param jsonpath
     * @return
     */
    public static String getStringValueFromJSONRequest(String RequestJson, String jsonpath) {
        try {
            JsonPath json = new JsonPath(RequestJson);
            return json.getJsonObject(jsonpath).toString();
        } catch (Exception e) {
            LOGGER.ExceptionToString(e);
            return jsonpath;
        }

    }

    public static String getStringValueFromXMLRequest(String Requestxml, String xmlpath) {
        try {
            XmlPath xml = new XmlPath(Requestxml);
            return xml.getString(xmlpath);
        } catch (Exception e) {
            LOGGER.ExceptionToString(e);
            return xmlpath;
        }
    }

//    public static String getJsonElementValueAsString(String json, String jsonpath) {
//        try {
//            DocumentContext jsonContext = com.jayway.jsonpath.JsonPath
//                    .parse(json);
//            return jsonContext.read(jsonpath).toString();
//        } catch (PathNotFoundException e) {
//            return null;
//        }
//    }

    /**
     * This method is used to validate status of response by returning status code.
     *
     * @param res
     *
     */

    public static int checkResponseStatusCode(Response res) {
        try {
            int status =res.getStatusCode();
            return status;
        }catch(Exception e) {
            LOGGER.ExceptionToString(e);
            return 0;
        }
    }
    public static JsonPath getAsJSON(Response response) {
        JsonPath jsonPath = new JsonPath(response.asString());
        return jsonPath;
    }

    public static XmlPath getAsXML(Response response) {
        String strResponse = response.asString();
        return new XmlPath(strResponse);
    }

}
