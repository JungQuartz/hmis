package com.jung.hmis.common.base;

import lombok.Data;

import java.util.List;

/**
 * 分页通用类
 * @author jimlly
 * @createtime: 2018/5/15下午2:35
 */
@Data
public class PageBean<T> {

    /**分页结果*/
    private List<T> items;

    /**总页数*/
    private Long pages;

    /**总条数*/
    private Long total;
}
