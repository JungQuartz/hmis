package com.jung.hmis.common.exception;

import com.jung.hmis.common.base.OpenApiCode;
import lombok.Data;

/**
 * 返回结果自定义异常类
 * @author jimlly
 * @createtime: 2018/3/16下午2:44
 */
@Data
public class ResultException extends RuntimeException {

    /**应答码*/
    private String code;

    /**应答信息*/
    private String message;

    public ResultException(String message){

        super(message);
        this.code= OpenApiCode.FAILED_CODE;
        this.message=message;
    }

    public ResultException(String message, Throwable e) {
        super(message, e);
        this.message = message;
        this.code =  OpenApiCode.FAILED_CODE;
    }

    public ResultException(String message, String code ) {
        super(message);
        this.message = message;
        this.code =  code;
    }

    public ResultException(String message, String code, Throwable e) {
        super(message, e);
        this.message = message;
        this.code = code;
    }

}
