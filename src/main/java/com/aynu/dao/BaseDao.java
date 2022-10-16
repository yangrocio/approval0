package com.aynu.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/8/29 16:35
 */
public interface BaseDao<T,K> {
    boolean save(T t);
    boolean update(T t);

    boolean delete(K k);
    T getById(K k);
    List<T> findByPage(@Param("start") Integer start, @Param("rows") Integer rows);
    List<T> findAll();
    Integer findTotals();

}
