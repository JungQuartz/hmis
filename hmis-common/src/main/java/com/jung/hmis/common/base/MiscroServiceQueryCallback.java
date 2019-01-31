/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.jung.hmis.common.base;

/**
 * 
 * @author hai.yuan
 * @version $Id: MiscroServiceQueryCallback.java, v 0.1 2018年3月13日 下午3:56:01 hai.yuan Exp $
 */
public interface MiscroServiceQueryCallback<T> {

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
    public T doQuery() throws Exception;
}
