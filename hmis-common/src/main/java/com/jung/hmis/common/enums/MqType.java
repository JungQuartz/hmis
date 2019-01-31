package com.jung.hmis.common.enums;

import lombok.Getter;

/**
 * @Description TODO
 * @Author YuJie
 * @Date 2018/10/9 10:26
 * @Version 1.0
 **/
@Getter
public enum MqType {

    UDC_MQ_SMS("UDC_MQ_SMS");

    private String code;

    private MqType(String code){
        this.code = code;
    }

}
