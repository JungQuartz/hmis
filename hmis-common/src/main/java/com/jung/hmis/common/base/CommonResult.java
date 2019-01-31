package com.jung.hmis.common.base;

import com.jung.hmis.common.utils.StringUtils;
import lombok.Data;

/**
 * @author lianwei
 * 统一返回类
 */
@Data
public class CommonResult<T> {

    /**应答码*/
    private String code;

    /**应答信息*/
    private String message;

    /**业务数据*/
    private T data;

    public void setSuccess(){
        this.setCode(OpenApiCode.SUCCESS_CODE);
        this.setMessage(OpenApiCode.SUCCESS_MSG);
    }

    public void setFail(){
        this.setCode(OpenApiCode.FAILED_CODE);
        this.setMessage(OpenApiCode.FAILED_MSG);
    }
    public void setFail(String code,String msg){
        if(StringUtils.isEmpty(code)){
            this.setCode(OpenApiCode.FAILED_CODE);
        }
        else {
            this.setCode(code);
        }
        this.setMessage(msg);
    }

    public Boolean isSuccess(){
        return OpenApiCode.SUCCESS_CODE.equals(this.code);
    }

}
