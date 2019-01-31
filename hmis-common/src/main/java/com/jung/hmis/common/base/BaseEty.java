package com.jung.hmis.common.base;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jimlly on 2017/11/7.
 */
@Data
public class BaseEty  implements Serializable {
    private static final long serialVersionUID = 8470697978259453214L;
    /**创建时间**/
    private String createTime;
    /**更新时间**/
    private String updateTime;

}
