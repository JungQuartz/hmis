package com.jung.hmis.common.base;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Created by lianwei on 2017/11/7.
 */
@Data
public class BaseModel implements Serializable {

    /**第三方id*/
    @NotBlank
    private String openId;
}
