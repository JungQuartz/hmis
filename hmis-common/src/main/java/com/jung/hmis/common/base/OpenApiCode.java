package com.jung.hmis.common.base;

/**
 * Created by lianwei on 2017/11/12.
 */
public abstract class OpenApiCode {

    /**成功code*/
    public static final String SUCCESS_CODE = "000000";
    /**成功msg*/
    public static final String SUCCESS_MSG = "SUCCESS";

    /**失败code*/
    public static final String FAILED_CODE = "999999";
    /**失败msg*/
    public static final String FAILED_MSG = "系统繁忙";

    public static final String RESOURCE_ERROR_CODE="401";
    /**请求的资源不存在**/
    public static final String RESOURCE_ERROR_MSG="请求的资源不存在";

    /**验证码不正确code*/
    public static final String CODE_NOT_MATCH_CODE = "999991";

    /**验证码不正确msg*/
    public static final String CODE_NOT_MATCH_MSG = "验证码不正确";

    /**验证码不正确code*/
    public static final String CODE_LOSE_EFFICACY_CODE = "999992";

    /**验证码不正确msg*/
    public static final String CODE_LOSE_EFFICACY_MSG = "验证码已失效，请重新获取";
    /**必填字段不完整CODE*/
    public static final String REQUIRED_FIELD_NOT_COMPLETE_CODE="999993";

    /**必填字段不完整MSG*/
    public static final String REQUIRED_FIELD_NOT_COMPLETE_MSG="必填字段不完整";

    /**重复请求CODE*/
    public static final String REQUEST_REPEAT_CODE="999994";

    /**重复请求MSG*/
    public static final String REQUEST_REPEAT_MSG="重复请求";


}
