package com.aynu.dao;

import com.aynu.bean.CountChart;
import com.aynu.dto.Tjb;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author susuper
 * @Date 2020/11/7 21:44
 * @description:
 */
@Mapper
@Repository
public interface TjbDao {
    List<Tjb> selectAll(@Param("start") String start, @Param("end") String end, @Param("department") String department, @Param("number") String number);

    List<CountChart> getCountChart(@Param("department") String department, @Param("yearstart") Integer yearstart, @Param("yearend") Integer yearend);
}
