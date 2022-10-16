package com.aynu.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author susuper
 * @Date 2021/1/3 18:25
 * @description:
 */
@Mapper
@Repository
public interface CommonMethodDao {
    int remainCount(@Param("selecttype") String selecttype,@Param("number")  String number,@Param("department")  String department,@Param("judgestatus") String judgestatus);
}
