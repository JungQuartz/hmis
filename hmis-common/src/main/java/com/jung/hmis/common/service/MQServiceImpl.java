package com.jung.hmis.common.service;

import com.jung.hmis.common.base.MQBaseMessage;
import com.jung.hmis.common.config.RabbitConfig;
import com.jung.hmis.common.constants.MessageConstants;
import com.jung.hmis.common.enums.MqType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lianwei on 2017/11/30.
 */
@Service
@Slf4j
public class MQServiceImpl implements MQService/*, RabbitTemplate.ConfirmCallback*/ {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void send(MQBaseMessage bizMessage) {
        try {
            if(MessageConstants.MESSAGE_BIZ_TYPE_VERIFY.equals(bizMessage.getBizType())){
                rabbitTemplate.setQueue(RabbitConfig.BASE+RabbitConfig.QUEUE+RabbitConfig.TYPE_SMS_VERIFYCODE);
                rabbitTemplate.setExchange(RabbitConfig.BASE+RabbitConfig.EXCHANGE+RabbitConfig.TYPE_SMS_VERIFYCODE);
                rabbitTemplate.setRoutingKey(RabbitConfig.BASE+RabbitConfig.ROUTINGKEY+RabbitConfig.TYPE_SMS_VERIFYCODE);
            }else if(MessageConstants.MESSAGE_BIZ_TYPE_DINGTALK.equals(bizMessage.getBizType())){
                rabbitTemplate.setQueue(RabbitConfig.BASE+RabbitConfig.QUEUE+RabbitConfig.TYPE_DINGTALK);
                rabbitTemplate.setExchange(RabbitConfig.BASE+RabbitConfig.EXCHANGE+RabbitConfig.TYPE_DINGTALK);
                rabbitTemplate.setRoutingKey(RabbitConfig.BASE+RabbitConfig.ROUTINGKEY+RabbitConfig.TYPE_DINGTALK);
            }else if(MessageConstants.MESSAGE_BIZ_TYPE_WECHAT.equals(bizMessage.getBizType())){
                rabbitTemplate.setQueue(RabbitConfig.BASE+RabbitConfig.QUEUE+RabbitConfig.TYPE_WECHAT);
                rabbitTemplate.setExchange(RabbitConfig.BASE+RabbitConfig.EXCHANGE+RabbitConfig.TYPE_WECHAT);
                rabbitTemplate.setRoutingKey(RabbitConfig.BASE+RabbitConfig.ROUTINGKEY+RabbitConfig.TYPE_WECHAT);
            }
            bizMessage.setBizType(null);
            rabbitTemplate.setMessageConverter(new SimpleMessageConverter());
            if(log.isDebugEnabled()){
                log.debug("MQ消息:{}", bizMessage.toString());
            }
            rabbitTemplate.convertAndSend(bizMessage.toString());
        }catch (Exception e){
            log.error("send exception", e);
        }
    }

    private String getExchangeByType(MqType type) {
        switch (type) {
            case UDC_MQ_SMS:
                return "UDC";
            default:
                return "";
        }
    }

    /**
     * 根据类型获取queue，may be useful
     *
     * @param type
     * @return
     */
    private String getQueueByType(MqType type) {
        switch (type) {
            case UDC_MQ_SMS:
                return "cs.udc.receivemessage";
            default:
                return "";
        }
    }

    private String getRoutingKeyByType(MqType type) {
        switch (type) {
            case UDC_MQ_SMS:
                return "cs.udc.receivemessage";
            default:
                return "";
        }
    }

    public <T> void sendMessage(T message, String exchange, String routingKey) {
        /*this.rabbitTemplate.setConfirmCallback(this);*/
        this.rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        this.rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

    @Override
    public <T> void sendMessage(MqType mqType, T message) {
        this.sendMessage(message, this.getExchangeByType(mqType), this.getRoutingKeyByType(mqType));
    }

    /*public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (!ack) {
            log.info("消息路由失败：{}", cause);
        }
    }*/

}
