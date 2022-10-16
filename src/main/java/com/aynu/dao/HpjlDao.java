package com.aynu.dao;

import com.aynu.bean.CountChart;
import com.aynu.bean.Hpjl;
import com.aynu.dto.Audit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/9/11 11:59
 */
@Mapper
@Component
public interface HpjlDao {
    List<Hpjl> selectHpjlByDetail(@Param("department") String department, @Param("username") String username,
                                  @Param("name") String name, @Param("start") String start, @Param("end") String end
            , @Param("judgestatus") String judgestatus, @Param("roletype") String roletype);

    List<Hpjl> selectHpjlByDetail2(@Param("department") String department, @Param("username") String username,
                                   @Param("name") String name, @Param("start") String start, @Param("end") String end, @Param("judgestatus") String judgestatus, @Param("roletype") String roletype,
                                   @Param("CGName") String CGName, @Param("JLName") String JLName, @Param("DYHJR") String DYHJR, @Param("QTHJR") String QTHJR, @Param("JIXDBM") String JIXDBM, @Param("JLRank") String JLRank, @Param("HJTime") String HJTime);
    boolean insertHpjl(Hpjl hpjl);

    //删除功能  需要 tid  应该是 删除 一个集合类型的
    boolean deleteHpjl(@Param("list") List<String> list, @Param("number") String number, @Param("department") String department);
    int deleteById(@Param("list") List<String> list);
    Hpjl showHpjl(@Param("department") String department, @Param("number") String number, @Param("id") String id);

    boolean updateHpjl(Hpjl hpjl);

    boolean updateHpjlByHigh(Audit audit);

    //根据 list集合 获取 文件名称
    List<String> getFileNameByList(@Param("list") List<String> list);

    List<Hpjl> selectHpjlByDiff(@Param("department") String department, @Param("number") String number,
                                @Param("roletype") String roletype);

    List<Map<Object,Object>> getExcelJson(@Param("department") String department,
                                          @Param("number") String number,
                                          @Param("name") String name,
                                          @Param("start") String start,
                                          @Param("end") String end,
                                          @Param("judgestatus") String judgestatus,
                                          @Param("params") List<String> params);
    int insertExcel(List<Hpjl> list);

    @Update("UPDATE hpjl SET  fjsc=#{fjscName} WHERE hid=#{id}")
    boolean updateFjscName(@Param("fjscName") String fjscName, @Param("id") String id);

    List<CountChart> getCountChart(@Param("department") String department, @Param("yearstart") Integer yearstart, @Param("yearend") Integer yearend);
}
