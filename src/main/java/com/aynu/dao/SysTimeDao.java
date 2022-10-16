package com.aynu.dao;

import com.aynu.dto.SysTime;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @Author susuper
 * @Date 2020/10/20 21:54
 * @description:
 */
@Mapper
@Repository
public interface SysTimeDao {
    @Select("select * from systime")
    SysTime selectSysTime();
    @Update("update systime set starttime=#{starttime},endtime=#{endtime}")
    boolean updateSysTime(SysTime sysTime);
}
