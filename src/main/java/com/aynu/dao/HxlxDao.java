package com.aynu.dao;

import com.aynu.bean.CountChart;
import com.aynu.bean.Hxlx;
import com.aynu.dto.Audit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author susuper
 * @Date 2020/11/9 11:09
 * @description:
 */
@Mapper
@Component
public interface HxlxDao {
    List<Hxlx> selectHxlxByDetail(@Param("department") String department, @Param("username") String username,
                                  @Param("name") String name, @Param("start") String start, @Param("end") String end,
                                  @Param("judgestatus") String judgestatus, @Param("roletype") String roletype);

    List<Hxlx> selectHxlxByDetail2(@Param("department") String department, @Param("username") String username,
                                   @Param("name") String name, @Param("start") String start, @Param("end") String end,
                                   @Param("judgestatus") String judgestatus, @Param("roletype") String roletype,
                                   @Param("XMName") String XMName, @Param("Principal") String Principal, @Param("AUnit") String AUnit,
                                   @Param("ZJE") String ZJE, @Param("ContractNumber") String ContractNumber, @Param("QDTime") String QDTime);

    boolean insertHxlx(Hxlx hxlx);

    //删除功能  需要 tid  应该是 删除 一个集合类型的
    boolean deleteHxlx(@Param("list") List<String> list, @Param("number") String number, @Param("department") String department);
    int deleteById(@Param("list") List<String> list);
    Hxlx showHxlx(@Param("department") String department, @Param("number") String number, @Param("id") String id);

    boolean updateHxlx(Hxlx hxlx);

    boolean updateHxlxByHigh(Audit audit);

    //根据 list集合 获取 文件名称
    List<String> getFileNameByList(@Param("list") List<String> list);

    List<Hxlx> selectHxlxByDiff(@Param("department") String department, @Param("number") String number,
                                @Param("roletype") String roletype);

    List<Map<Object,Object>> getExcelJson(@Param("department") String department,
                                          @Param("number") String number,
                                          @Param("name") String name,
                                          @Param("start") String start,
                                          @Param("end") String end,
                                          @Param("judgestatus") String judgestatus,
                                          @Param("params") List<String> params);
    int insertExcel(List<Hxlx> list);

    @Update("UPDATE hxlx SET  fjsc=#{fjscName} WHERE hid=#{id}")
    boolean updateFjscName(@Param("fjscName") String fjscName, @Param("id") String id);

    List<CountChart> getCountChart(@Param("department") String department, @Param("yearstart") Integer yearstart, @Param("yearend") Integer yearend);

}
