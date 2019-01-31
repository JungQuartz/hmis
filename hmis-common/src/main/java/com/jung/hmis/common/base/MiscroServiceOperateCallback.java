/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.jung.hmis.common.base;

/**
 * 
 * 
 * @author hai.yuan
 * @version $Id: MiscroServiceOperateCallback.java, v 0.1 2018年3月13日 下午4:48:00 hai.yuan Exp $
 */
public interface MiscroServiceOperateCallback<T> {

    /**
     * 参数校验
     * @throws Exception 
     */
    public void checkParams() throws Exception;

    /**
     * 查询
     * 
     * @return
     * @throws Exception 
     */
    public T doOperate() throws Exception;
}
