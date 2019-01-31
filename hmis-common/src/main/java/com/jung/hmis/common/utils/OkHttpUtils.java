package com.jung.hmis.common.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;

/**
 * @author jimlly
 * http 请求工具类
 * CreateTime: 2017/11/20下午4:17
 */
@Slf4j
public class OkHttpUtils {

    private static OkHttpClient mOkHttpClient;//OKhttpClient对象
    private static Request mOkHttpRequest;//请求对象
    private static Request.Builder mRequestBuilder;//请求对象的构建者
    /**
     * get请求
     * @param url：url
     */
    public static String okHttpGet(String url) {
        return okHttpGet(url, null, null);
    }

    /**
     * get请求，可以传递参
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     */
    public static String okHttpGet(String url, Map<String, String> paramsMap) {
        return okHttpGet(url, paramsMap, null);
    }

    /**
     * get请求，可以传递参数
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     */
    public static String okHttpGet(String url, Map<String, String> paramsMap, Map<String, String> headerMap) {

        mOkHttpClient = new OkHttpClient();
        mRequestBuilder = new Request.Builder();

        String mUrl = url;
        String str="";
        if(paramsMap!=null&&paramsMap.size()>0){
            mUrl =url+"?";
            for (String key: paramsMap.keySet()){
                mUrl = mUrl + key+"="+paramsMap.get(key)+"&";
            }
            mUrl = mUrl.substring(0,mUrl.length()-1);
        }
        mRequestBuilder.url(mUrl);
        if(headerMap != null&&headerMap.size()>0){

                for (String key: headerMap.keySet()){
                    mRequestBuilder.addHeader(key,headerMap.get(key));
                }

        }
        //mRequestBuilder.addHeader("Authorization","Bearer "+"token");可以把token添加到这儿
        mOkHttpRequest = mRequestBuilder.build();

        Call call = mOkHttpClient.newCall(mOkHttpRequest);

        Response response = null;
        try {
            response = call.execute();

         str = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("okHttpGet 请求出错 ："+e.getMessage(),e);

        }
        return str;

    }

    /**
     * post请求
     * @param url：url
     */
    public static String okHttpPost(String url) {
      return   okHttpPost(url, null);
    }

    /**
     * post请求，可以传递参数
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     */
    public static String okHttpPost(String url, Map<String, String> paramsMap) {
       return okHttpPost(url, paramsMap, null);
    }

    /**
     * post请求，可以传递参数
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     */
    public static String okHttpPost(String url, Map<String, String> paramsMap, Map<String, String> headerMap) {
      String str="";

        mOkHttpClient = new OkHttpClient();
        mRequestBuilder = new Request.Builder();

        String mUrl = url;
        FormBody.Builder formBody = new FormBody.Builder();
        if(paramsMap!=null&&paramsMap.size()>0){

                for (String key : paramsMap.keySet()) {
                    formBody.add(key, paramsMap.get(key));
                }

        }
        RequestBody body = formBody.build();
        mRequestBuilder.url(mUrl);
        if(headerMap != null&&headerMap.size()>0){

            for (String key: headerMap.keySet()){
                mRequestBuilder.addHeader(key,headerMap.get(key));
            }

        }
        mRequestBuilder.post(body);
        //mRequestBuilder.addHeader("Authorization","Bearer "+"token");可以把token添加到这儿
        mOkHttpRequest = mRequestBuilder.build();

        Call call = mOkHttpClient.newCall(mOkHttpRequest);

        Response response = null;
        try {
            response = call.execute();

            str = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("okHttpPost 请求出错 ："+e.getMessage(),e);
        }
        return str;
    }
    /**
     * post请求，可以传递参数
     * @param url：url
     * @param json：body
     */
    public static String okHttpPostJson(String url, String json){
        return okHttpPostJson(url,json,null);
    }
    /**
     * post请求，可以传递参数
     * @param url：url
     * @param json：body
     * @param headerMap：map集合，封装请求头键值对
     */
    public static String okHttpPostJson(String url, String json, Map<String, String> headerMap) {
        String str="";
        if(!StringUtils.isEmpty(json)){
        mOkHttpClient = new OkHttpClient();
        mRequestBuilder = new Request.Builder();

        String mUrl = url;
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");



        mRequestBuilder.url(mUrl);
        if(headerMap != null&&headerMap.size()>0){

            for (String key: headerMap.keySet()){
                mRequestBuilder.addHeader(key,headerMap.get(key));
            }

        }
        RequestBody body =  RequestBody.create(JSON,json);
        mRequestBuilder.post(body);
        mOkHttpRequest = mRequestBuilder.build();

        Call call = mOkHttpClient.newCall(mOkHttpRequest);

        Response response = null;
        try {
            response = call.execute();

            str = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("okHttpPostJson 请求出错 ："+e.getMessage(),e);

        }}
        return str;
    }
    public static String okHttpPostText(String url, String content,String contentType){
        return okHttpPostText(url,content,contentType,null);
    }
    public static String okHttpPostText(String url, String content,String contentType, Map<String, String> headerMap) {
        String str="";
        if(!StringUtils.isEmpty(content)){
            mOkHttpClient = new OkHttpClient();
            mRequestBuilder = new Request.Builder();

            String mUrl = url;
            MediaType JSON = MediaType.parse(contentType);



            mRequestBuilder.url(mUrl);
            if(headerMap != null&&headerMap.size()>0){

                for (String key: headerMap.keySet()){
                    mRequestBuilder.addHeader(key,headerMap.get(key));
                }

            }
            RequestBody body =  RequestBody.create(JSON,content);
            mRequestBuilder.post(body);
            mOkHttpRequest = mRequestBuilder.build();

            Call call = mOkHttpClient.newCall(mOkHttpRequest);

            Response response = null;
            try {
                response = call.execute();

                str = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
                log.error("okHttpPostText 请求出错 ："+e.getMessage(),e);

            }}
        return str;
    }
}
