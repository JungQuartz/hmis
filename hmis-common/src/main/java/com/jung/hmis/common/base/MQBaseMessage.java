package com.jung.hmis.common.base;

import com.alibaba.fastjson.JSONObject;
import com.jung.hmis.common.constants.MessageConstants;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 *
 */
@Data
public class MQBaseMessage implements Serializable {

    /**消息ID     UUID*/
    private String messageId;

    /**调用方应用ID  中和金服:1003*/
    private String appId;

    /**调用方应用子ID 自定义*/
    private String appSubId;

    /**推送类型
     *  短信：Sms | 微信：WeChat ｜ 钉钉：DingTalk ｜ 极光：JPush
     *  注：大小写敏感
     *
     */
    private String type;

    /**接收消息主体类型
     * 短信时，1：手机号；2：客户编号；3：员工编号；其它消息类型时为：0
     */
    private String toAliasType;

    /**
     *  接收消息主体
     *  微信：OpenId | 钉钉：手机号 | 极光：客户端Alias编号 |
     *  短信：根据AliasType判断， 手机号/客户编号/员工编号
     */
    private List<String> toAlias;

    /**
     * 消息主体类型
     * 文本消息         :   Text            适用:WeChat, DingTalk, JPush, Sms
     * 跳转卡片消息      :   ActionCard      适用:DingTalk
     */
    private String bodyType;

    /**推送内容*/
    private String body;

    /**消息创建时间   yyyy-MM-dd HH:mm:ss*/
    private String createDate;

    /**消息过期时间  yyyy-MM-dd HH:mm:ss*/
    private String expireDate;

    /**自定义类型,用于区分QUEUE*/
    private String bizType;

    /**
     * 创建默认信息
     * @return
     */
    public void buildBaseInfo(){
        this.messageId = UUID.randomUUID().toString();
        this.appId = MessageConstants.MESSAGE_APP_ID;
        this.toAliasType = "1";//目前默认为手机号
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
