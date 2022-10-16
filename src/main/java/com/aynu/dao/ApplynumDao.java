package com.aynu.dao;

import com.aynu.bean.Applynum;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author susuper
 * @Date 2020/9/23 16:17
 * @description:
 */
@Mapper
@Repository
public interface ApplynumDao {
    @Select("select * from applynum")
    List<Applynum> queryAll();
    @Select("select * from applynum where name=#{name}")
    Applynum queryByName(String name);
    boolean updateApplynum(Applynum applynums);
}
