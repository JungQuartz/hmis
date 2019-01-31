/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.jung.hmis.common.base;

import com.jung.hmis.common.exception.ResultException;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author hai.yuan
 * @version $Id: MiscroServiceTemplate.java, v 0.1 2018年3月13日 下午3:54:05 hai.yuan Exp $
 */
@Slf4j
public class MiscroServiceTemplate {

    /**
     * 操作接口模板
     * @param <T>
     * 
     * @param callback
     * @return
     */
    public static <T> CommonResult<T> operate(MiscroServiceOperateCallback<T> callback) throws Exception {

        CommonResult<T> result = new CommonResult<T>();

        try {
            callback.checkParams();
            T data = callback.doOperate();
            result.setData(data);
            result.setSuccess();

        } catch (Exception e) {


            throw  e;

        }

        return result;
    }

    /**
     * 查询接口模板
     * @param <T>
     * 
     * @param callback
     * @return
     */
    public static <T> CommonResult<T> query(MiscroServiceQueryCallback<T> callback) {

        CommonResult<T> result = new CommonResult<T>();

        try {
            callback.checkParams();
            T data = callback.doQuery();
            result.setData(data);
            result.setSuccess();

        } catch (ResultException e) {


           throw  e;

        }  catch (Throwable e) {

            result.setFail();

            log.error("系统异常", e);
        }

        return result;
    }
}
