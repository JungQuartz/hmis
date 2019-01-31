package com.jung.hmis.common.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MQ配置
 * Created by lianwei on 2017/12/16.
 */
@Configuration
public class RabbitConfig {

    /**基础配置**/
    public static final String BASE = "udc.mq.";
    public static final String EXCHANGE = "exchange.";
    public static final String QUEUE = "queue.";
    public static final String ROUTINGKEY = "routingkey.";

    /**验证码短信*/
    public static final String TYPE_SMS_VERIFYCODE = "sms.verificationcode";

    /**钉钉消息*/
    public static final String TYPE_DINGTALK = "dingtalk.message";

    /**微信消息*/
    public static final String TYPE_WECHAT = "webchat.message";


    /**
     * 验证码短信配置
     * @return
     */
    @Bean
    Queue smsVerifyCodeQueue(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE).append(QUEUE).append(TYPE_SMS_VERIFYCODE);
        return new Queue(stringBuilder.toString());
    }

    @Bean
    DirectExchange smsVerifyCodeExchange(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE).append(EXCHANGE).append(TYPE_SMS_VERIFYCODE);
        return new DirectExchange(stringBuilder.toString());
    }

    @Bean
    Binding smsVerifyCodeBinding(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE).append(ROUTINGKEY).append(TYPE_SMS_VERIFYCODE);
        return BindingBuilder
                .bind(smsVerifyCodeQueue())
                .to(smsVerifyCodeExchange())
                .with(stringBuilder.toString());
    }



    /**
     * 钉钉消息配置
     * @return
     */
    @Bean
    Queue dingtalkQueue(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE).append(QUEUE).append(TYPE_DINGTALK);
        return new Queue(stringBuilder.toString());
    }

    @Bean
    DirectExchange dingtalkExchange(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE).append(EXCHANGE).append(TYPE_DINGTALK);
        return new DirectExchange(stringBuilder.toString());
    }

    @Bean
    Binding dingtalkBinding(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE).append(ROUTINGKEY).append(TYPE_DINGTALK);
        return BindingBuilder
                .bind(dingtalkQueue())
                .to(dingtalkExchange())
                .with(stringBuilder.toString());
    }


    /**
     * 微信消息配置
     * @return
     */
    @Bean
    Queue wechatQueue(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE).append(QUEUE).append(TYPE_WECHAT);
        return new Queue(stringBuilder.toString());
    }

    @Bean
    DirectExchange wechatExchange(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE).append(EXCHANGE).append(TYPE_WECHAT);
        return new DirectExchange(stringBuilder.toString());
    }

    @Bean
    Binding wechatBinding(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE).append(ROUTINGKEY).append(TYPE_WECHAT);
        return BindingBuilder
                .bind(wechatQueue())
                .to(wechatExchange())
                .with(stringBuilder.toString());
    }
}
