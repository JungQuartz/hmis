package com.jung.hmis.common.enums;

/**
 * Created by jimlly on 2017/11/7.
 */
public enum ResultCode {
    /** 成功 */
    SUCCESS("0", "ok"),

    /** 没有登录 */
    NOT_LOGIN("201", "没有登录"),



    /** 发生异常 */
    EXCEPTION("401", "发生异常"),
    INVALID_ACCOUNT("2002", "account is stop"),
    SYSTEM_ERR("2003", "System Error"),
    PERMISSION_DENIED("2004", "Permission Denied"),
    INVALID_CLIENTID("2005", "Invalid clientid"),
    INVALID_PASSWORD("2007", "User name or password is incorrect"),
    INVALID_CAPTCHA("2008", "Invalid captcha or captcha overdue"),
    INVALID_TOKEN("2009", "Invalid token"),

    /** 参数错误 */
    PARAMS_ERROR("2010", "参数错误 "),

    /** 不支持或已经废弃 */
    NOT_SUPPORTED("2011", "不支持或已经废弃"),

    /** AuthCode错误 */
    INVALID_AUTHCODE("2012", "无效的AuthCode"),

    /** 太频繁的调用 */
    TOO_FREQUENT("2013", "太频繁的调用"),
    OPERTOR_FAIL("2014", "操作失败"),
    PARAMS_EMPTY("2015", "参数为空 "),

    /** 未知的错误 */
    UNKNOWN_ERROR("499", "未知错误"),
    RESOURCE_ERROR("404","请求的资源不存在");

    private ResultCode(String code, String msg){
        this.code = code;
        this.msg = msg;
    }



    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
