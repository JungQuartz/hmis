package com.jung.hmis.common.constants;

/**
 * @author jimlly
 * @createtime: 2017/11/23上午11:02
 */
public class Constants {

    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String CONTENT_TYPE_CHARSET = "application/json;charset=utf-8";


    public static final String ENCODING_UTF8 = "UTF-8";
    /**
     * 微信临时二维码参数
     */
    public static final String QR_STR_SCENE="QR_STR_SCENE";

    /**
     * 微信永久二维码参数
     */
    public static final String QR_LIMIT_STR_SCENE="QR_LIMIT_STR_SCENE";

    /**
     * 是
     */
    public static final String YES="Y";
    /**
     * 否
     */
    public static final String NO="N";

    public static final String SIGN_METHOD_MD5="MD5";
    public static final String SIGN_METHOD_HMAC="HMAC";

    /**redis 缓存时间 cacheName**/
    /**
     * 5分钟
     */
    public static final String REDIS_CACHE_EXPIRES_300="REDIS_CACHE_EXPIRES_300";

    /**
     * 10分钟
     */
    public static final String REDIS_CACHE_EXPIRES_600="REDIS_CACHE_EXPIRES_600";
    /**
     * 半小时
     */
    public static final String REDIS_CACHE_EXPIRES_1800="REDIS_CACHE_EXPIRES_1800";

    /**
     * 1小时
     */
    public static final String REDIS_CACHE_EXPIRES_3600="REDIS_CACHE_EXPIRES_3600";
    /**
     * 2小时
     */
    public static final String REDIS_CACHE_EXPIRES_7200="REDIS_CACHE_EXPIRES_7200";
    /**
     * 1天
     */
    public static final String REDIS_CACHE_EXPIRES_86400="REDIS_CACHE_EXPIRES_86400";


}

