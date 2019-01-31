package com.jung.hmis.common.base;

import java.util.List;

/**
 * Created by jimlly on 2017/7/24.
 */
public interface BaseMapper<T> {
    /**
     * 插入
     * @param T
     */
    public Integer insert(T t);

    /**
     * 根据ID号修改单个实体
     * @param T
     */
    public Integer updateById(T t);

    /**
     * 根据ID号删除单个实体
     * @param id
     */
    public void deleteById(String id);

    /**
     * 根据ID号查询单个实体
     * @param id
     */
    public T selectById(String id);

    /**
     * 根据实体对象查询
     * @param T
     * @return
     */
    public List<T> selectByEntity(T t);

    /**
     * 查所有
     * @return
     */
    public List<T> selectAll();
}
