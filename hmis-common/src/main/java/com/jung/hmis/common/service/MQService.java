package com.jung.hmis.common.service;

import com.jung.hmis.common.base.MQBaseMessage;
import com.jung.hmis.common.enums.MqType;

/**
 * MQ服务
 */
public interface MQService {

    void send(MQBaseMessage bizMessage);

    <T> void sendMessage(MqType mqType, T message);

    /*void confirm(CorrelationData correlationData, boolean ack, String cause);*/

}
