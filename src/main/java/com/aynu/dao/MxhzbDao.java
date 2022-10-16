package com.aynu.dao;

import com.aynu.dto.Mxhzb;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @Author susuper
 * @Date 2020/10/29 16:47
 * @description:
 */
@Mapper
@Repository
public interface MxhzbDao {
    List<Mxhzb> selectAll(@Param("start") String start,@Param("end") String end,@Param("department") String department,@Param("number") String number);

}
