package com.jung.hmis.common.constants;

/**
 * Created by lianwei on 2017/12/5.
 */
public class MessageConstants {

    /**中和金服-调用方应用ID-UDC分配*/
    public static final String MESSAGE_APP_ID = "1003";

    /**中和金服-MQ业务类型-验证码短信*/
    public static final String MESSAGE_BIZ_TYPE_VERIFY = "verify";

    /**中和金服-MQ业务类型-钉钉*/
    public static final String MESSAGE_BIZ_TYPE_DINGTALK = "dingTalk";

    /**中和金服-MQ业务类型-微信*/
    public static final String MESSAGE_BIZ_TYPE_WECHAT = "wechat";

    /**中和金服-MQ推送方式-微信*/
    public static final String MESSAGE_TYPE_WECHAT = "WeChat";

    /**中和金服-MQ推送方式-钉钉*/
    public static final String MESSAGE_TYPE_DINGTALK = "DingTalk";

    /**中和金服-MQ推送方式-JPUSH*/
    public static final String MESSAGE_TYPE_JPUSH = "JPush";

    /**中和金服-MQ推送方式-短信*/
    public static final String MESSAGE_TYPE_SMS = "Sms";

    /**中和金服-MQ主体类型-文本   适用:WeChat, DingTalk, JPush, Sms*/
    public static final String MESSAGE_BODY_TYPE_TEXT = "Text";

    /**中和金服-MQ主体类型-跳转卡片消息   适用:DingTalk*/
    public static final String MESSAGE_BODY_TYPE_ACTIONCARD = "ActionCard";

    /**中和金服-MQ主体类型-图文消息   适用:WeChat*/
    public static final String MESSAGE_BODY_TYPE_PICTEXT = "PicText";

    /**中和金服-MQ主体类型-模板消息   适用:WeChat*/
    public static final String MESSAGE_BODY_TYPE_TEMPLATE = "Template";


}
